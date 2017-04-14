package news.news.com.news.Ui.activity;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mmin18.widget.FlexLayout;
import com.junwen.jlibrary.JShareUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Common.ShareConstant;
import news.news.com.news.Common.event.UserHeadEvent;
import news.news.com.news.Mvp.Presenters.UserPresenter;
import news.news.com.news.Mvp.Views.UserView;
import news.news.com.news.R;

public class UserActivity extends BaseActivity implements UserView, View.OnClickListener {

    @InjectPresenter
    UserPresenter presenter;
    @Bind(R.id.user_activity_head_info)
    FlexLayout userActivityHeadInfo;

    @Bind(R.id.user_activity_collection)
    FlexLayout userActivityCollection;

    @Bind(R.id.user_activity_comment)
    FlexLayout userActivityComment;

    @Bind(R.id.user_activity_setting)
    FlexLayout userActivitySetting;

    @Bind(R.id.user_activity_back)
    ImageView userActivityBack;

    @Bind(R.id.user_activity_exit)
    FlexLayout userActivityExit;

    @Bind(R.id.user_activity_head_img)
    CircleImageView userActivityHeadImg;

    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        userActivityHeadInfo.setOnClickListener(this);
        userActivityCollection.setOnClickListener(this);
        userActivityComment.setOnClickListener(this);
        userActivitySetting.setOnClickListener(this);
        userActivityBack.setOnClickListener(this);
        userActivityExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_activity_head_info:
                jumpToActivity(UserDetailActivity.class);
                break;
            case R.id.user_activity_collection:
                Toast("我的收藏");
                break;
            case R.id.user_activity_comment:
                Toast("我的评论");
                break;
            case R.id.user_activity_setting:
                Toast("我的设置");
                break;
            case R.id.user_activity_back:
                finish();
                Toast("关闭");
                break;
            case R.id.user_activity_exit:
                //退出登录,返回到登录页面
                JShareUtils.put(this, ShareConstant.KEY_USERNAME, "");
                JShareUtils.put(this, ShareConstant.KEY_PASSWORD, "");
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserHeadChange(UserHeadEvent userHeadEvent) {
        if (!TextUtils.isEmpty(userHeadEvent.getPath())) {
            userActivityHeadImg.setImageBitmap(BitmapFactory.decodeFile(userHeadEvent.getPath()));
        }
    }

}