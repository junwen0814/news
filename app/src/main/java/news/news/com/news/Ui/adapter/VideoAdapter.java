package news.news.com.news.Ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import news.news.com.news.Mvp.Model.NewsVideoModel;
import news.news.com.news.R;

/**
 * Created by junwen on 17/4/18.
 */

public class VideoAdapter extends BaseAdapter {

    private Context content;
    private List<NewsVideoModel> data = new ArrayList<>();
    private LayoutInflater inflater;

    public VideoAdapter(Context content, List<NewsVideoModel> data) {
        this.content = content;
        this.data = data;
        inflater = (LayoutInflater) content.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_video, null);
            viewHodler = new ViewHodler();
            viewHodler.jcVideoPlayerStandard = (JCVideoPlayerStandard) convertView.findViewById(R.id.video_item_video);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        NewsVideoModel newsVideoModel = data.get(position);
        viewHodler.jcVideoPlayerStandard.setUp(newsVideoModel.getVideoUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, newsVideoModel.getVideoTitle());
        viewHodler.jcVideoPlayerStandard.thumbImageView.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }

    public class ViewHodler {
        private JCVideoPlayerStandard jcVideoPlayerStandard;
    }
}
