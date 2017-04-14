package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void onCategoryResponse(String[] titles);
}