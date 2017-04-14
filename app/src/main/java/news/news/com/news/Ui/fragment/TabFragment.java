package news.news.com.news.Ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import news.news.com.news.Base.BaseFragment;
import news.news.com.news.Mvp.Model.NewsModel;
import news.news.com.news.Mvp.Presenters.TabPresenter;
import news.news.com.news.Mvp.Views.TabView;
import news.news.com.news.R;
import news.news.com.news.Ui.activity.NewDetailActivity;
import news.news.com.news.Ui.adapter.MainTab.MainTabAdapter;
import news.news.com.news.Utils.DataUtils;

public class TabFragment extends BaseFragment implements TabView, XRecyclerView.LoadingListener, MultiItemTypeAdapter.OnItemClickListener {

    @InjectPresenter
    TabPresenter presenter;

    @Bind(R.id.main_tab_fragment_xrecyclerView)
    XRecyclerView mainTabFragmentXrecyclerView;

    private List<NewsModel> data = new ArrayList<>();
    private MainTabAdapter mainTabAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_main_tab;
    }

    @Override
    protected void initView(View view) {
        mainTabFragmentXrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initData() {
        mainTabAdapter = new MainTabAdapter(getActivity(), data);
        //设置Item的动画
        SlideInBottomAnimatorAdapter animatorAdapter = new SlideInBottomAnimatorAdapter(mainTabAdapter, mainTabFragmentXrecyclerView);
        mainTabFragmentXrecyclerView.setAdapter(animatorAdapter);
        mainTabFragmentXrecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mainTabFragmentXrecyclerView));
        //请求数据
        newsListReq();
    }

    /**
     * 描述:新闻列表获取
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/12 下午8:40
     */
    private void newsListReq() {
        List<NewsModel> newsList = DataUtils.getNewsList();
//        data.clear();
        data.addAll(newsList);
        mainTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        mainTabFragmentXrecyclerView.setLoadingListener(this);
        mainTabAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainTabFragmentXrecyclerView.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainTabFragmentXrecyclerView.loadMoreComplete();
            }
        }, 2000);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        //跳转新闻详情页
        NewsModel newsModel = mainTabAdapter.getDatas().get(position - 1);
        Intent intent = new Intent(getActivity(), NewDetailActivity.class);
        intent.putExtra(NewDetailActivity.INTENT_KEY_NEWSID, newsModel.getNewsId());
        jumpToActivity(getActivity(), intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
