package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import news.news.com.news.Mvp.Model.UserInfoModel;

public interface UserView extends MvpView {

    void onRequestUserInfoSuccess(UserInfoModel user);

    void onRequestUserInfoFail(String error);
}