package news.news.com.news.Ui.fragment.Ui.fragment;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import news.news.com.news.Base.BaseFragment;
import news.news.com.news.Mvp.Model.Response.NewsListResponse;
import news.news.com.news.Mvp.Model.Response.NewsModel;
import news.news.com.news.Mvp.Presenters.TabPresenter;
import news.news.com.news.Mvp.Views.TabView;
import news.news.com.news.R;
import news.news.com.news.Ui.fragment.Mvp.Presenters.VideoPresenter;
import news.news.com.news.Ui.fragment.Mvp.Views.VideoView;
import news.news.com.news.Utils.DownThumb;

public class VideoFragment extends BaseFragment implements VideoView, TabView {

    @InjectPresenter
    VideoPresenter presenter;

    @InjectPresenter
    TabPresenter tabPresenter;

    @Bind(R.id.video_fragment_xrecycleView)
    XRecyclerView videoFragmentXrecycleView;

    public static final String ARGUMENTS_CID = "cid";

    @Bind(R.id.video_tv_nulldata)
    TextView videoTvNulldata;

    private List<NewsModel> data = new ArrayList<>();

    private String cid = "";
    private CommonAdapter commonAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        videoFragmentXrecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cid = getArguments().getString(ARGUMENTS_CID);
        //如果没有缩略图,就去请求缩略图
        commonAdapter = new CommonAdapter<NewsModel>(getActivity(), R.layout.item_video, data) {
            @Override
            protected void convert(ViewHolder holder, final NewsModel newsVideoModel, int position) {
                final JCVideoPlayerStandard videoPlayerStandard = holder.getView(R.id.video_item_video);
                videoPlayerStandard.setUp(newsVideoModel.getNewscontent()
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, newsVideoModel.getNewstitle());
                if (newsVideoModel.getThumb() == null) {
                    //如果没有缩略图,就去请求缩略图
                    new DownThumb(newsVideoModel.getNewscontent(), new DownThumb.OnDownThumbResponse() {
                        @Override
                        public void onDownThumbSuccess(Bitmap videoThumbnail) {
                            videoPlayerStandard.thumbImageView.setImageBitmap(videoThumbnail);
                            newsVideoModel.setThumb(videoThumbnail);
                        }
                    }).start();
                } else {
                    videoPlayerStandard.thumbImageView.setImageBitmap(newsVideoModel.getThumb());
                }
            }
        };
        SlideInBottomAnimatorAdapter animatorAdapter = new SlideInBottomAnimatorAdapter(commonAdapter, videoFragmentXrecycleView);
        videoFragmentXrecycleView.setAdapter(animatorAdapter);
    }

    @Override
    public void initData() {
        tabPresenter.newsListByColumns(cid);
    }

    @Override
    public void initListener() {
        videoFragmentXrecycleView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                    JCVideoPlayer videoPlayer = JCVideoPlayerManager.getCurrentJcvd();
                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1
                            && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        //当滑动的时，正在播放的视频移除屏幕，取消播放这个视频
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });
        videoFragmentXrecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                tabPresenter.newsListByColumns(cid);
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onNewsListSuccess(NewsListResponse newsList) {
        videoFragmentXrecycleView.refreshComplete();
        if (newsList != null) {
            List<NewsModel> list = newsList.getList();
            if (list.size() > 0) {
                videoTvNulldata.setVisibility(View.GONE);
                data.clear();
                data.addAll(list);
                videoFragmentXrecycleView.getAdapter().notifyDataSetChanged();
            } else {
                videoTvNulldata.setVisibility(View.VISIBLE);
            }
        } else {
            videoTvNulldata.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNewsListFail(String error) {
        videoFragmentXrecycleView.refreshComplete();
    }

}
