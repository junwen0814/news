package news.news.com.news.Ui.fragment.Ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import news.news.com.news.Base.BaseFragment;
import news.news.com.news.Mvp.Model.NewsVideoModel;
import news.news.com.news.R;
import news.news.com.news.Ui.fragment.Mvp.Presenters.VideoPresenter;
import news.news.com.news.Ui.fragment.Mvp.Views.VideoView;
import news.news.com.news.Utils.DataUtils;

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
            protected void convert(ViewHolder holder, NewsVideoModel newsVideoModel, int position) {
                JCVideoPlayerStandard videoPlayerStandard = holder.getView(R.id.video_item_video);
                videoPlayerStandard.setUp(newsVideoModel.getVideoUrl()
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, newsVideoModel.getVideoTitle());
                videoPlayerStandard.thumbImageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
