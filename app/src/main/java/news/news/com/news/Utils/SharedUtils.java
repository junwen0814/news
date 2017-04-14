package news.news.com.news.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by junwen on 17/4/14.
 */

public class SharedUtils {

    private static SharedUtils shareUtils;
    private SharedPreferences sharedPreferences;
    private static Context context;

    private String contentSize;

    private SharedUtils() {
        sharedPreferences = context.getSharedPreferences("simple", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        SharedUtils.context = context;
    }

    public static SharedUtils getInstance() {
        if (shareUtils == null) {
            synchronized (SharedUtils.class) {
                if (shareUtils == null) {
                    shareUtils = new SharedUtils();
                }
            }
        }
        return shareUtils;
    }


    public String getContentSize() {
        return sharedPreferences.getString("contentSize", "");
    }

    public void setContentSize(String contentSize) {
        sharedPreferences.edit().putString("contentSize", contentSize).commit();
    }
}
