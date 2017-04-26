package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Mvp.Model.CollectModel;
import news.news.com.news.Mvp.Model.Request.CollectRequest;
import news.news.com.news.Mvp.Model.Response.NewsCollectResponse;
import news.news.com.news.Mvp.Views.MyCollectView;

@InjectViewState
public class MyCollectPresenter extends MvpPresenter<MyCollectView> {

    /**
     * 描述:请求收藏列表
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/26 下午11:01
     */
    public void requestCollectList(String userId) {
        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setUid(userId);
        collectRequest.setPageNum("1");
        collectRequest.setPageSize("10");
        ApiUtils.post(collectRequest, NewsCollectResponse.class, "getNewsCollects", "com.sxun.cloud.news.def.INewsCollectService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                NewsCollectResponse newsDetailResponse = (NewsCollectResponse) data;
                if (newsDetailResponse != null) {
                    List<CollectModel> list = newsDetailResponse.getLists();
                    getViewState().onCollectResponse(list);
                }
            }

            @Override
            public void onFail(String error) {
            }
        });
    }
}
