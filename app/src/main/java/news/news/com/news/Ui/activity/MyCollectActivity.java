package news.news.com.news.Ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Base.MyApp;
import news.news.com.news.Mvp.Model.CollectModel;
import news.news.com.news.Mvp.Presenters.MyCollectPresenter;
import news.news.com.news.Mvp.Views.MyCollectView;
import news.news.com.news.R;

public class MyCollectActivity extends BaseActivity implements MyCollectView, MultiItemTypeAdapter.OnItemClickListener, XRecyclerView.LoadingListener {

    @InjectPresenter
    MyCollectPresenter presenter;
    @Bind(R.id.my_collect_back)
    ImageView myCollectBack;

    @Bind(R.id.my_collect_recycleView)
    XRecyclerView myCollectRecycleView;

    private List<CollectModel> data = new ArrayList<>();
    private CommonAdapter commonAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void initView() {
        myCollectRecycleView.setLayoutManager(new LinearLayoutManager(this));
        commonAdapter = new CommonAdapter<CollectModel>(this, R.layout.item_news_collect, data) {
            @Override
            protected void convert(ViewHolder holder, CollectModel collectModel, int position) {
                holder.setText(R.id.news_collect_title, collectModel.getNewstitle());
                holder.setText(R.id.news_collect_time, collectModel.getNewsreleasetime());
            }
        };
        SlideInBottomAnimatorAdapter animatorAdapter = new SlideInBottomAnimatorAdapter(commonAdapter, myCollectRecycleView);
        myCollectRecycleView.setAdapter(animatorAdapter);
    }

    @Override
    public void initData() {
        presenter.requestCollectList(MyApp.userId);
    }

    @Override
    public void initListener() {
        myCollectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonAdapter.setOnItemClickListener(this);
        myCollectRecycleView.setLoadingListener(this);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        CollectModel collectModel = data.get(position - 1);
        Intent intent = new Intent(getApplicationContext(), NewDetailActivity.class);
        intent.putExtra(NewDetailActivity.INTENT_KEY_NEWSID, collectModel.getNewsid());
        jumpToActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    /**
     * 描述:返回收藏列表
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/26 下午11:06
     */
    @Override
    public void onCollectResponse(List<CollectModel> list) {
        myCollectRecycleView.refreshComplete();
        if (list.size() > 0) {
            data.clear();
            data.addAll(list);
            commonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        presenter.requestCollectList(MyApp.userId);
    }

    @Override
    public void onLoadMore() {

    }
}