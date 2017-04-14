package news.news.com.news.Mvp.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import news.news.com.news.Mvp.Views.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    /**
     *描述:请求新闻分类
     *作者:卜俊文
     *邮箱:344176791@qq.com
     *日期:17/4/9 下午3:24
     */
    public void requestCategory() {
        String[] titles = null;
        titles = new String[10];
        for (int i = 0; i < 10; i++) {
            titles[i] = "标题" + i;
        }
        getViewState().onCategoryResponse(titles);
    }
}
