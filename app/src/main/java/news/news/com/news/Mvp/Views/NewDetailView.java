package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import news.news.com.news.Mvp.Model.CommontModel;
import news.news.com.news.Mvp.Model.NewsDetailModel;

public interface NewDetailView extends MvpView {

    void onNewsDetailResponse(NewsDetailModel newsDetailResponse);

    void onNewsDetailFailResponse(String error);

    void onUpdateCollectSuccess();

    void onUpdateCollectFail();

    void onCommentResponse(List<CommontModel> list);
}