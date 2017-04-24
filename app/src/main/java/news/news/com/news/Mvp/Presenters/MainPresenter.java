package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Api.ApiUtils;
import news.news.com.news.Mvp.Model.Response.ColumnsResponseModel;
import news.news.com.news.Mvp.Views.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    /**
     * 描述:请求新闻分类
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午3:24
     */
    public void requestCategory() {
//        ColumnsRequestModel model = new ColumnsRequestModel();
//        model.setPageNum("1");
//        model.setPageSize("10");
        ApiUtils.post(null, ColumnsResponseModel.class, "getNewsColumnsApp", "com.sxun.cloud.news.def.INewsColumnService", new ApiUtils.OnApiResult() {
            @Override
            public <T> void onSuccess(T data) {
                ColumnsResponseModel columnsResponseModel = (ColumnsResponseModel) data;
                getViewState().onCategorySuccess(columnsResponseModel);
            }

            @Override
            public void onFail(String error) {
                getViewState().onCategoryFail(error);
            }
        });
    }
}
