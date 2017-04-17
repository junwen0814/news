package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import news.news.com.news.Mvp.Model.NewsModel;

public interface TabView extends MvpView {

    void onNewsListSuccess(List<NewsModel> newsList);
}