package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

public interface LoginView extends MvpView {

    void onLoginFail(String errorUserPass);

    void onLoginSuccess(String s);
}