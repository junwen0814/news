package news.news.com.news.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends MvpAppCompatFragment {

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        initListener();
        return view;
    }


    public abstract int getLayout();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initListener();

    /**
     * 跳转Activity
     *
     * @param tClass
     * @param <T>
     */
    public <T> void jumpToActivity(Context context, Class<T> tClass) {
        Intent intent = new Intent(context, tClass);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     *
     * @param tClass
     * @param <T>
     */
    public <T> void jumpToActivityForResule(Context context, Class<T> tClass, int requestCode) {
        Intent intent = new Intent(context, tClass);
        startActivity(intent);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转Activity
     *
     * @param intent
     * @param <T>
     */
    public <T> void jumpToActivity(Context context, Intent intent) {
        startActivity(intent);
    }

    /**
     * 跳转Activity
     *
     * @param intent
     * @param <T>
     */
    public <T> void jumpToActivity(Context context, Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    public void Toast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
