package news.news.com.news.Utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

/**
 * Created by junwen on 17/4/19.
 */

public class DownThumb extends Thread {

    private String url;
    private OnDownThumbResponse onDownThumbResponse;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap videoThumb = (Bitmap) msg.obj;
            if (videoThumb != null) {
                onDownThumbResponse.onDownThumbSuccess(videoThumb);
            }
        }
    };

    public DownThumb(String url, OnDownThumbResponse onDownThumbResponse) {
        this.url = url;
        this.onDownThumbResponse = onDownThumbResponse;
    }

    @Override
    public void run() {
        super.run();
        Bitmap videoThumbnail = ThumbUtils.createVideoThumbnail(url, 300, 300);
        Message message = handler.obtainMessage();
        message.obj = videoThumbnail;
        message.sendToTarget();
    }

    public interface OnDownThumbResponse {
        void onDownThumbSuccess(Bitmap videoThumbnail);
    }
}
