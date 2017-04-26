package news.news.com.news.Mvp.Presenters;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Common.StrConstant;
import news.news.com.news.Mvp.Model.Request.UserLoginRequest;
import news.news.com.news.Mvp.Model.Response.LoginModel;
import news.news.com.news.Mvp.Views.LoginView;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public void login(String username, String password, boolean isLogin) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            getViewState().onLoginFail(StrConstant.ERROR_NULL_USER_PASS);
            return;
        }
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setAccount(username.toString());
        userLoginRequest.setPassword(password.toString());
        if (isLogin) {
            ApiUtils.post(userLoginRequest, LoginModel.class, "userLoginApp", "com.sxun.cloud.news.def.INewsUserService", new ApiUtils.OnApiResult() {
                @Override
                public <T> void onSuccess(T data) {
                    //登录成功
                    LoginModel loginModel = (LoginModel) data;
                    getViewState().onLoginSuccess(loginModel.getUid());
                }

                @Override
                public void onFail(String error) {
                    //登录失败
                    getViewState().onLoginFail("账号或者密码错误，请重试");
                }
            });
        } else {
            ApiUtils.post(userLoginRequest, LoginModel.class, "addNewsUserApp", "com.sxun.cloud.news.def.INewsUserService", new ApiUtils.OnApiResult() {
                @Override
                public <T> void onSuccess(T data) {
                    //注册成功
                    LoginModel loginModel = (LoginModel) data;
                    getViewState().onRegisterSuccess(loginModel.getUid());
                }

                @Override
                public void onFail(String error) {
                    //注册失败
                    getViewState().onRegisterFail("注册失败");
                }
            });
        }

    }
}
