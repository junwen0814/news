package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import news.news.com.news.Mvp.Model.NewsDetailModel;
import news.news.com.news.Mvp.Model.Response.NewsDetailResponse;

public interface NewDetailView extends MvpView {

    void onNewsDetailResponse(NewsDetailModel newsDetailResponse);

    void onNewsDetailFailResponse(String error);
}