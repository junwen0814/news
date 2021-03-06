package news.news.com.news.Mvp.Presenters;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Base.MyApp;
import news.news.com.news.Mvp.Model.Request.AddCommentRequest;
import news.news.com.news.Mvp.Model.Request.CommentListRequest;
import news.news.com.news.Mvp.Model.Request.NewsDetailRequest;
import news.news.com.news.Mvp.Model.Request.UpdateCollectRequest;
import news.news.com.news.Mvp.Model.Response.NewsCommontResponse;
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
        newsDetailRequest.setUid(MyApp.userId);
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

    /**
     * 描述:更新收藏状态
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/25 下午9:30
     */
    public void updateCollect(String userId, String newsid, boolean iscollect) {
        if (TextUtils.isEmpty(MyApp.userId)) {
            getViewState().onUpdateCollectFail();
            return;
        }
        UpdateCollectRequest newsDetailRequest = new UpdateCollectRequest();
        newsDetailRequest.setNewsid(newsid);
        newsDetailRequest.setUid(userId);
        newsDetailRequest.setIsCollect(iscollect ? "true" : "false");
        ApiUtils.post(newsDetailRequest, Object.class, "upNewsCollect", "com.sxun.cloud.news.def.INewsCollectService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                getViewState().onUpdateCollectSuccess();
            }

            @Override
            public void onFail(String error) {
                getViewState().onUpdateCollectFail();
            }
        });
    }

    /**
     * 描述:请求获取新闻的所有评论
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/26 上午10:18
     */
    public void requestNewsComment(String newsId) {
        CommentListRequest commentListRequest = new CommentListRequest();
        commentListRequest.setNewsid(newsId);
        commentListRequest.setPageNum("1");
        commentListRequest.setPageSize("10");
        ApiUtils.post(commentListRequest, NewsCommontResponse.class, "getNewsReviewsApp", "com.sxun.cloud.news.def.INewsReviewService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                NewsCommontResponse newsCommontResponse = (NewsCommontResponse) data;
                if (newsCommontResponse != null) {
                    getViewState().onCommentResponse(newsCommontResponse.getList());
                }
            }

            @Override
            public void onFail(String error) {
            }
        });
    }

    /**
     * 描述:发送评论信息
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/26 下午10:26
     */
    public void sendCommentMessage(String newsid, String comment) {
        if (TextUtils.isEmpty(MyApp.userId)) {
            getViewState().onAddCommentFail("请先进行登录");
            return;
        }
        AddCommentRequest commentRequest = new AddCommentRequest();
        commentRequest.setUid(MyApp.userId);
        commentRequest.setNewsid(newsid);
        commentRequest.setRcontent(comment);
        ApiUtils.post(commentRequest, Object.class, "addNewsReviewApp", "com.sxun.cloud.news.def.INewsReviewService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                getViewState().onAddCommentResponse();
            }

            @Override
            public void onFail(String error) {
                getViewState().onAddCommentFail(error);
            }
        });
    }
}
