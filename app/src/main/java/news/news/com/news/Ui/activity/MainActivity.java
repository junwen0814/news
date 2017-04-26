package news.news.com.news.Ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.socks.library.KLog;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Base.MyApp;
import news.news.com.news.Common.event.UserHeadEvent;
import news.news.com.news.Mvp.Model.Response.ColumnsModel;
import news.news.com.news.Mvp.Model.Response.ColumnsResponseModel;
import news.news.com.news.Mvp.Presenters.LoginPresenter;
import news.news.com.news.Mvp.Presenters.MainPresenter;
import news.news.com.news.Mvp.Views.LoginView;
import news.news.com.news.Mvp.Views.MainView;
import news.news.com.news.R;
import news.news.com.news.Ui.adapter.MainViewPagerAdapter;
import news.news.com.news.Ui.fragment.TabFragment;
import news.news.com.news.Ui.fragment.Ui.fragment.VideoFragment;
import news.news.com.news.Utils.SharedUtils;

public class MainActivity extends BaseActivity implements MainView, LoginView, View.OnClickListener {

    @InjectPresenter
    MainPresenter presenter;

    @InjectPresenter
    LoginPresenter loginPresenter;

    @Bind(R.id.main_activity_toolbar)
    Toolbar mainActivityToolbar;

    @Bind(R.id.main_magic_indicator)
    MagicIndicator mainMagicIndicator;

    @Bind(R.id.main_viewpager)
    ViewPager mainViewpager;

    @Bind(R.id.main_activity_user)
    CircleImageView mainActivityUser;

    private CommonNavigator commonNavigator;

    private List<Fragment> viewpager_list;

    private final int REQUEST_CODE = 0;
    private List<ColumnsModel> lists;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        setSupportActionBar(mainActivityToolbar);
        EventBus.getDefault().register(this);
        viewpager_list = new ArrayList<>();
        commonNavigator = new CommonNavigator(this);
        mainMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mainMagicIndicator, mainViewpager);
    }

    @Override
    public void initData() {
        presenter.requestCategory(); //请求新闻列表
        if (MyApp.isLogin()) {
            String username = SharedUtils.getInstance().getUsername();
            String password = SharedUtils.getInstance().getPassword();
            loginPresenter.login(username, password, true);
        }
    }


    @Override
    public void initListener() {
        mainActivityUser.setOnClickListener(this);
    }

    /**
     * 描述:初始化所有的新闻列表
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午3:48
     *
     * @param lists
     */
    private void initFragment(List<ColumnsModel> lists) {
        Bundle bundle = null;
        TabFragment videoFragment = null;
        for (ColumnsModel item : lists) {
            if ("视频".equals(item.getCname())) {
                VideoFragment videoFragment1 = new VideoFragment();
                bundle = new Bundle();
                bundle.putString(VideoFragment.ARGUMENTS_CID, item.getCid());
                videoFragment1.setArguments(bundle);
                viewpager_list.add(videoFragment1);
            } else {
                KLog.e("栏目 ：" + item.getCname());
                videoFragment = new TabFragment();
                bundle = new Bundle();
                bundle.putString(TabFragment.ARGUMENTS_CID, item.getCid());
                videoFragment.setArguments(bundle);
                viewpager_list.add(videoFragment);
            }
        }
        mainViewpager.setOffscreenPageLimit(lists.size());
        mainViewpager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), viewpager_list));
    }

    /**
     * 描述:新闻类别返回
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午3:25
     */
    @Override
    public void onCategorySuccess(ColumnsResponseModel columnsResponseModel) {
        lists = columnsResponseModel.getList();
        updateAdapter();
        initFragment(lists);
    }

    @Override
    public void onCategoryFail(String error) {
        Toast(error);
    }

    /**
     * 描述:更新主页的适配器
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午3:35
     */
    private void updateAdapter() {
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return lists.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#b5b5b5"));
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#f16467"));
                colorTransitionPagerTitleView.setText(lists.get(i).getCname());
                colorTransitionPagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainViewpager.setCurrentItem(i);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#f16467"));
                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                return indicator;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_activity_user:
                if (!MyApp.isLogin()) {
                    jumpToActivity(LoginActivity.class);
                } else {
                    jumpToActivityForResule(UserActivity.class, REQUEST_CODE);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //说明退出登录了,更新头像

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserHeadChange(UserHeadEvent userHeadEvent) {
        if (!TextUtils.isEmpty(userHeadEvent.getPath())) {
            mainActivityUser.setImageBitmap(BitmapFactory.decodeFile(userHeadEvent.getPath()));
        }
    }


    @Override
    public void onLoginFail(String errorUserPass) {
        Toast("账号有变更，请重新登录");
        SharedUtils.getInstance().clearUser();
    }

    @Override
    public void onLoginSuccess(String uid) {
        //加载自己的头像
        MyApp.userId = uid;
    }

    @Override
    public void onRegisterSuccess(String cid) {

    }

    @Override
    public void onRegisterFail(String error) {

    }
}