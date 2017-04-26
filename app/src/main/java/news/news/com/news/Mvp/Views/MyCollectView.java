package news.news.com.news.Mvp.Views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import news.news.com.news.Mvp.Model.CollectModel;

public interface MyCollectView extends MvpView {

    void onCollectResponse(List<CollectModel> list);
}