package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Mvp.Model.Request.NewsListRequest;
import news.news.com.news.Mvp.Model.Response.NewsListResponse;
import news.news.com.news.Mvp.Views.TabView;

@InjectViewState
public class TabPresenter extends MvpPresenter<TabView> {

    public void newsListByColumns(String cid) {
//        List<NewsModel> newsModels = DataUtils.getNewsList();
        NewsListRequest request = new NewsListRequest();
        request.setCid(cid);
        ApiUtils.post(request, NewsListResponse.class, "getNewsContentsApp", "com.sxun.cloud.news.def.INewsContentService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                NewsListResponse newsModelList = (NewsListResponse) data;
                getViewState().onNewsListSuccess(newsModelList);
            }

            @Override
            public void onFail(String error) {
                getViewState().onNewsListFail(error);
            }
        });
    }
}
