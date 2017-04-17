package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import news.news.com.news.Mvp.Model.Response.ColumnsResponseModel;

public interface MainView extends MvpView {


    void onCategorySuccess(ColumnsResponseModel columnsResponseModel);

    void onCategoryFail(String error);
}