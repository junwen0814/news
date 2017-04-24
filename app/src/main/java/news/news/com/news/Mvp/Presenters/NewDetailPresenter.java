package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Mvp.Model.Request.NewsDetailRequest;
import news.news.com.news.Mvp.Model.Response.NewsDetailResponse;
import news.news.com.news.Mvp.Views.NewDetailView;

@InjectViewState
public class NewDetailPresenter extends MvpPresenter<NewDetailView> {

    /**
     * 描述:请求新闻详情
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 下午3:07
     */
    public void requestNewsDetail(String newsId) {
        NewsDetailRequest newsDetailRequest = new NewsDetailRequest();
        newsDetailRequest.setNewsid(newsId);
        ApiUtils.post(newsDetailRequest, NewsDetailResponse.class, "getNewsContentByNewsidApp", "com.sxun.cloud.news.def.INewsContentService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                NewsDetailResponse newsDetailResponse = (NewsDetailResponse) data;
                if (newsDetailResponse != null) {
                    getViewState().onNewsDetailResponse(newsDetailResponse.getNewContent());
                }
            }

            @Override
            public void onFail(String error) {
                getViewState().onNewsDetailFailResponse(error);
            }
        });
    }
}
