package news.news.com.news.Ui.activity;

import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.daasuu.bl.BubbleLayout;
import com.junwen.jlibrary.JDensityUtils;

import butterknife.Bind;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Mvp.Presenters.LoginPresenter;
import news.news.com.news.Mvp.Views.LoginView;
import news.news.com.news.R;
import news.news.com.news.Utils.SharedUtils;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @InjectPresenter
    LoginPresenter presenter;

    @Bind(R.id.login_activity_username)
    EditText loginActivityUsername;

    @Bind(R.id.login_activity_password)
    EditText loginActivityPassword;

    @Bind(R.id.login_activity_login)
    Button loginActivityLogin;

    @Bind(R.id.login_activity_tv_login)
    TextView loginActivityTvLogin;

    @Bind(R.id.login_activity_tv_register)
    TextView loginActivityTvRegister;

    @Bind(R.id.login_activity_user_flag)
    BubbleLayout loginActivityUserFlag;

    @Bind(R.id.login_activity_toolbar)
    Toolbar loginActivityToolbar;

    private boolean isLogin = true; //登录

    private TextView[] views;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        views = new TextView[]{loginActivityTvLogin, loginActivityTvRegister};
        loginActivityToolbar.setTitle("");
        setSupportActionBar(loginActivityToolbar);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        loginActivityLogin.setOnClickListener(this);
        loginActivityTvLogin.setOnClickListener(this);
        loginActivityTvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_login:
                //登录或注册
                presenter.login(loginActivityUsername.getText().toString(), loginActivityPassword.getText().toString(), isLogin);
                break;
            case R.id.login_activity_tv_login:
                checkFlag(loginActivityTvLogin);
                isLogin = true;
                loginActivityUserFlag.setArrowPosition(JDensityUtils.dp2px(mContext, 70));
                loginActivityLogin.setText("登录");
                break;
            case R.id.login_activity_tv_register:
                checkFlag(loginActivityTvRegister);
                isLogin = false;
                loginActivityUserFlag.setArrowPosition(JDensityUtils.dp2px(mContext, 235));
                loginActivityLogin.setText("注册");
                break;
        }

    }

    public void checkFlag(TextView textView) {
        for (TextView view : views) {
            if (view.getId() == textView.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.bg_shape_login_et));
                view.setTextColor(getResources().getColor(R.color.login_flag_text_color_select));
            } else {
                view.setBackground(null);
                view.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    /**
     * 描述:登录失败
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/9 下午5:12
     */
    @Override
    public void onLoginFail(String errorUserPass) {
        Toast(errorUserPass);
    }

    @Override
    public void onLoginSuccess(String cid) {
        SharedUtils.getInstance().setUser(cid, loginActivityUsername.getText().toString(), loginActivityPassword.getText().toString());
        Toast("登录成功");
        finish();
    }

    @Override
    public void onRegisterSuccess(String cid) {
        Toast("注册成功");
        SharedUtils.getInstance().setUser(cid, loginActivityUsername.getText().toString(), loginActivityPassword.getText().toString());
        finish();
    }

    @Override
    public void onRegisterFail(String error) {
        Toast("注册失败");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}