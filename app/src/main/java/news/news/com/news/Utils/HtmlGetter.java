package news.news.com.news.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.junwen.jlibrary.JScreenUtils;

import news.news.com.news.R;

import static com.junwen.jlibrary.JBitmapUtils.getBitmap;

/**
 * Created by junwen on 17/4/14.
 */

public class HtmlGetter implements Html.ImageGetter {

    private TextView textView;
    Context context;
    private int mPicWidth;

    public HtmlGetter(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
        mPicWidth = JScreenUtils.getScreenWidth(context) - 100;
    }

    @Override
    public Drawable getDrawable(String source) {
        Log.e("getDrawable", "Url : " + source);
        final URLDrawable urlDrawable = new URLDrawable();
        if (!source.contains("http://")) {
            urlDrawable.setBounds(0, 0, 0, 0);
            return null;
        } else {
            urlDrawable.bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            urlDrawable.setBounds(0, 0, mPicWidth, mPicWidth / 3);
        }
        Glide.with(context).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap loadedImage, GlideAnimation<? super Bitmap> glideAnimation) {
                BitmapDrawable drawable = new BitmapDrawable(loadedImage);
                float imgWidth = drawable.getIntrinsicWidth();
                float imgHeight = drawable.getIntrinsicHeight();
                float rate = imgHeight / imgWidth;
                int height = (int) (mPicWidth * rate);
                if (loadedImage.getWidth() > 1 || loadedImage.getHeight() > 1) {
                    Bitmap bitmap = getBitmap(loadedImage, mPicWidth, height);
//                    img.setImageBitmap(loadedImage);
                    urlDrawable.bitmap = bitmap;
                    urlDrawable.setBounds(0, 0, mPicWidth, height);
//                    img.setImageBitmap(loadedImage);
                    textView.invalidate();
                    textView.setText(textView.getText()); // 解决图文重叠
                }
            }
        });
        return urlDrawable;
    }

    public class URLDrawable extends BitmapDrawable {

        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }
}