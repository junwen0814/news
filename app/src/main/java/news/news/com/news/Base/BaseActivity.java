
package news.news.com.news.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import news.news.com.news.R;
import news.news.com.news.Utils.MyActivityManager;
import qiu.niorgai.StatusBarCompat;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
//        MyActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        initView();
        initData();
        initListener();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    /**
     * 设置标题栏颜色
     *
     * @param statusColor
     */
    public void setStatusBarColor(@ColorInt int statusColor) {
        StatusBarCompat.setStatusBarColor(this, statusColor);
    }

    /**
     * 跳转Activity
     *
     * @param tClass
     * @param <T>
     */
    public <T> void jumpToActivity(Class<T> tClass) {
        Intent intent = new Intent(getApplicationContext(), tClass);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     *
     * @param tClass
     * @param <T>
     */
    public <T> void jumpToActivityForResule(Class<T> tClass, int requestCode) {
        Intent intent = new Intent(getApplicationContext(), tClass);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转Activity
     *
     * @param intent
     * @param <T>
     */
    public <T> void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    /**
     * 跳转Activity
     *
     * @param intent
     * @param <T>
     */
    public <T> void jumpToActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    public void Toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述:清楚所有界面
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/10 上午11:30
     */
    public void removeAllActivity() {
        MyActivityManager.getInstance().removeAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
