package news.news.com.news.Ui.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import news.news.com.news.Mvp.Model.UserInfoModel;
import news.news.com.news.Mvp.Presenters.UserPresenter;
import news.news.com.news.Mvp.Views.UserView;
import news.news.com.news.R;
import news.news.com.news.Utils.SharedUtils;

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

    @Bind(R.id.user_activity_username)
    TextView userActivityUsername;

    private static final int REQUEST_CODE = 0;

    @Bind(R.id.user_activity_sex)
    ImageView userActivitySex;
    private UserInfoModel userInfoModel;

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
        String uid = SharedUtils.getInstance().getUid();
        if (!TextUtils.isEmpty(uid)) {
            presenter.requestUserInfo(uid);
        } else {
            Toast("用户信息不存在");
        }
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
                //发送sex
                Intent intent = new Intent(getApplicationContext(), UserDetailActivity.class);
                intent.putExtra(UserDetailActivity.INTENT_KEY_NAME, userInfoModel.getNickname());
                intent.putExtra(UserDetailActivity.INTENT_KEY_SEX, userInfoModel.getSex());
                jumpToActivityForResult(intent, REQUEST_CODE);
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

    /**
     * 描述:用户信息请求成功
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 上午11:18
     */
    @Override
    public void onRequestUserInfoSuccess(UserInfoModel user) {
        userInfoModel = user;
        if (TextUtils.isEmpty(userInfoModel.getSex())) {
            userInfoModel.setSex("男");
        }
        if (!TextUtils.isEmpty(userInfoModel.getNickname())) {
            userActivityUsername.setText(userInfoModel.getNickname());
        } else {
            userActivityUsername.setText(userInfoModel.getAccount());
        }
        userActivitySex.setImageResource("男".equals(userInfoModel.getSex()) ? R.drawable.img_user_male : R.drawable.img_user_girl);
    }

    @Override
    public void onRequestUserInfoFail(String error) {
        Toast(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //更新当前用户信息
                presenter.requestUserInfo(SharedUtils.getInstance().getUid());
            }
        }
    }

}