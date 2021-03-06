package news.news.com.news.Ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

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
import news.news.com.news.Mvp.Model.Response.NewsListResponse;
import news.news.com.news.Mvp.Model.Response.NewsModel;
import news.news.com.news.Mvp.Presenters.TabPresenter;
import news.news.com.news.Mvp.Views.TabView;
import news.news.com.news.R;
import news.news.com.news.Ui.activity.NewDetailActivity;
import news.news.com.news.Ui.adapter.MainTab.MainTabAdapter;

public class TabFragment extends BaseFragment implements TabView, XRecyclerView.LoadingListener, MultiItemTypeAdapter.OnItemClickListener {

    @InjectPresenter
    TabPresenter presenter;

    @Bind(R.id.main_tab_fragment_xrecyclerView)
    XRecyclerView mainTabFragmentXrecyclerView;

    @Bind(R.id.main_tab_tv_nulldata)
    TextView mainTabTvNulldata;

    private List<NewsModel> data = new ArrayList<>();

    private MainTabAdapter mainTabAdapter;

    public static final String ARGUMENTS_CID = "cid";
    private String cid;

    @Override
    public int getLayout() {
        return R.layout.fragment_main_tab;
    }

    @Override
    protected void initView(View view) {
        cid = getArguments().getString(ARGUMENTS_CID);
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
        if (!TextUtils.isEmpty(cid)) {
            newsListReq(cid);
        }
    }

    /**
     * 描述:新闻列表获取
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/12 下午8:40
     */
    private void newsListReq(String cid) {
        presenter.newsListByColumns(cid);
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
        presenter.newsListByColumns(cid);
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
        intent.putExtra(NewDetailActivity.INTENT_KEY_NEWSID, newsModel.getNewsid());
        jumpToActivity(getActivity(), intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    /**
     * 描述:新闻列表返回
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/17 下午10:26
     *
     * @param newsList
     */
    @Override
    public void onNewsListSuccess(NewsListResponse newsList) {
        mainTabFragmentXrecyclerView.refreshComplete();
        if (newsList != null) {
            List<NewsModel> list = newsList.getList();
            if (list.size() > 0) {
                //新闻列表没有数据
                mainTabTvNulldata.setVisibility(View.GONE);
                data.clear();
                data.addAll(list);
                mainTabAdapter.notifyDataSetChanged();
            } else {
                mainTabTvNulldata.setVisibility(View.VISIBLE);
            }
        } else {
            mainTabTvNulldata.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNewsListFail(String error) {
        Toast(error);
    }

}
