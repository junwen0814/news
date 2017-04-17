package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import news.news.com.news.Mvp.Model.NewsModel;
import news.news.com.news.Mvp.Views.TabView;
import news.news.com.news.Utils.DataUtils;

@InjectViewState
public class TabPresenter extends MvpPresenter<TabView> {

    public void newsListByColumns(String columnsName) {
        List<NewsModel> newsModels = DataUtils.getNewsList();
        getViewState().onNewsListSuccess(newsModels);
    }
}
