package news.news.com.news.Ui.activity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.Bind;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Mvp.Presenters.SplashPresenter;
import news.news.com.news.Mvp.Views.SplashView;
import news.news.com.news.R;

public class SplashActivity extends BaseActivity implements SplashView {

    @InjectPresenter
    SplashPresenter presenter;

    @Bind(R.id.splash_activity_img_main)
    ImageView splashActivityImgMain;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        //启动2秒动画，进入主页面
        Animation splash_am = AnimationUtils.loadAnimation(this, R.anim.am_splash_img);
        splashActivityImgMain.startAnimation(splash_am);
        splash_am.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpToActivity(news.news.com.news.Ui.activity.MainActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}