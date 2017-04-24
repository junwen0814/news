package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Mvp.Model.Request.UpdateUserRequest;
import news.news.com.news.Mvp.Views.UserDetailView;

@InjectViewState
public class UserDetailPresenter extends MvpPresenter<UserDetailView> {

    /**
     * 描述:更新用户信息
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 上午11:35
     */
    public void updateUserInfo(String uid, String name, String sex) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setUid(uid);
        updateUserRequest.setNickname(name);
        updateUserRequest.setSex(sex);
        ApiUtils.post(updateUserRequest, Object.class, "upNewsUserApp", "com.sxun.cloud.news.def.INewsUserService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                //说明更新成功
                getViewState().onUpdateUserInfoSuccess();
            }

            @Override
            public void onFail(String error) {
                //更新失败
                getViewState().onUpdateUserInfoFail();
            }
        });
    }
}
