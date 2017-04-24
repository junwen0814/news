package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Mvp.Model.Request.UserInfoRequest;
import news.news.com.news.Mvp.Model.Response.UserInfoResponse;
import news.news.com.news.Mvp.Views.UserView;

@InjectViewState
public class UserPresenter extends MvpPresenter<UserView> {

    /**
     * 描述:请求用户信息
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 上午11:09
     */
    public void requestUserInfo(String uid) {
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setUid(uid);
        ApiUtils.post(userInfoRequest, UserInfoResponse.class, "getUserByUidApp", "com.sxun.cloud.news.def.INewsUserService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                UserInfoResponse userInfoResponse = (UserInfoResponse) data;
                if (userInfoResponse != null) {
                    getViewState().onRequestUserInfoSuccess(userInfoResponse.getUser());
                }
            }

            @Override
            public void onFail(String error) {
                getViewState().onRequestUserInfoFail(error);
            }
        });
    }
}
