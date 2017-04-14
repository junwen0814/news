package news.news.com.news.Utils;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junwen on 17/4/10.
 */

public class MyActivityManager {
    private static MyActivityManager myActivityManager;
    private static List<AppCompatActivity> list = new ArrayList<>();

    private MyActivityManager() {

    }

    public static MyActivityManager getInstance() {
        if (myActivityManager == null) {
            synchronized (MyActivityManager.class) {
                if (myActivityManager == null) {
                    myActivityManager = new MyActivityManager();
                }
            }
        }
        return myActivityManager;
    }

    public void addActivity(AppCompatActivity appCompatActivity) {
        list.add(appCompatActivity);
    }

    public void removeActivity(Class appCompatActivity) {
    }

    /**
     * 描述:清楚所有的Activity
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/10 上午11:28
     */
    public void removeAll() {
        list.clear();
        for (AppCompatActivity appCompatActivity : list) {
            appCompatActivity.finish();
        }
    }
}
