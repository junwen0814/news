package news.news.com.news.Base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;

import news.news.com.news.Utils.SharedUtils;

/**
 * Created by junwen on 17/4/9.
 */

public class MyApp extends Application {

    private static Context context;

    private static boolean logined = false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SharedUtils.init(getApplicationContext());
    }


    public static Context getApplication() {
        return context;
    }

    /**
     * 描述:判断是否登陆
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午4:25
     */
    public static boolean isLogin() {
        String uid = SharedUtils.getInstance().getUid();
        return !TextUtils.isEmpty(uid);
    }

    public static ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
            Glide.with(context).load(path).into(imageView);
        }
    };
}
