package news.news.com.news.Ui.fragment.Ui.fragment;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
import news.news.com.news.Base.BaseFragment;
import news.news.com.news.Mvp.Model.NewsVideoModel;
import news.news.com.news.R;
import news.news.com.news.Ui.fragment.Mvp.Presenters.VideoPresenter;
import news.news.com.news.Ui.fragment.Mvp.Views.VideoView;
import news.news.com.news.Utils.DataUtils;
import news.news.com.news.Utils.DownThumb;

public class VideoFragment extends BaseFragment implements VideoView {

    @InjectPresenter
    VideoPresenter presenter;

    @Bind(R.id.video_fragment_xrecycleView)
    XRecyclerView videoFragmentXrecycleView;

    private List<NewsVideoModel> data = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        videoFragmentXrecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initData() {
        data = DataUtils.getNewsVideoModel();
        videoFragmentXrecycleView.setAdapter(new CommonAdapter<NewsVideoModel>(getActivity(), R.layout.item_video, data) {
            @Override
            protected void convert(ViewHolder holder, final NewsVideoModel newsVideoModel, int position) {
                final JCVideoPlayerStandard videoPlayerStandard = holder.getView(R.id.video_item_video);
                videoPlayerStandard.setUp(newsVideoModel.getVideoUrl()
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, newsVideoModel.getVideoTitle());
                if (newsVideoModel.getThumb() == null) {
                    //如果没有缩略图,就去请求缩略图
                    new DownThumb(newsVideoModel.getVideoUrl(), new DownThumb.OnDownThumbResponse() {
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
        });
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
