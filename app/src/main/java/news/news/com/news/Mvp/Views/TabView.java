package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import news.news.com.news.Mvp.Model.Response.NewsListResponse;
import news.news.com.news.Mvp.Model.Response.NewsModel;

public interface TabView extends MvpView {

    void onNewsListSuccess(NewsListResponse newsList);

    void onNewsListFail(String error);
}