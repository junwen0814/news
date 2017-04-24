package news.news.com.news.Mvp.Model;

import android.graphics.Bitmap;

/**
 * Created by junwen on 17/4/17.
 */

public class NewsVideoModel {
    private String videoId; //视频ID
    private String videoTitle; //视频标题
    private String videoSize; //视频大小
    private String videoUrl; //视频地址
    private Bitmap thumb; //缩略图

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
