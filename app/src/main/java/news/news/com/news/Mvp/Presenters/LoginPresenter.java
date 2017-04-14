package news.news.com.news.Mvp.Presenters;

import android.text.Editable;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Common.StrConstant;
import news.news.com.news.Mvp.Views.LoginView;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public void login(Editable username, Editable password, boolean checked) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            getViewState().onLoginFail(StrConstant.ERROR_NULL_USER_PASS);
            return;
        }
        String user = username.toString();
        String pass = password.toString();
        if (checked) {
            //注册
            userRegister(user, pass);
        } else {
            userLogin(user, pass);
        }
    }

    /**
     * 描述:登录
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午5:19
     */
    private void userLogin(String username, String password) {
        getViewState().onLoginSuccess("登录 ： user : " + username + "pass : " + password);
    }

    /**
     * 描述:注册
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午5:19
     */
    private void userRegister(String username, String password) {
        getViewState().onLoginSuccess("注册 ： user : " + username + "pass : " + password);
    }


}
