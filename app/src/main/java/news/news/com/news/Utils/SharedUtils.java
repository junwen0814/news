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

    private String uid;

    private String username;

    private String password;

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

    public String getUid() {
        return sharedPreferences.getString("uid", "");
    }

    public void setUid(String uid) {
        sharedPreferences.edit().putString("uid", uid).commit();
    }

    public String getUsername() {
        return sharedPreferences.getString("username", "");
    }

    public void setUsername(String username) {
        sharedPreferences.edit().putString("username", username).commit();
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

    public void setPassword(String password) {
        sharedPreferences.edit().putString("password", password).commit();
    }

    /**
     * 描述:更新用户信息
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 上午10:56
     */
    public void setUser(String cid, String username, String password) {
        setUid(cid);
        setUsername(username);
        setPassword(password);
    }

    /**
     * 描述:清楚用户信息
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 上午11:05
     */
    public void clearUser() {
        setUser("", "", "");
    }
}
