package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

public interface UserDetailView extends MvpView {

    void onUpdateUserInfoSuccess();

    void onUpdateUserInfoFail();

}