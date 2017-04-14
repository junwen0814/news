package news.news.com.news.Ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.junwen.jlibrary.JShareUtils;

import butterknife.Bind;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Mvp.Presenters.LoginPresenter;
import news.news.com.news.Mvp.Views.LoginView;
import news.news.com.news.R;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @InjectPresenter
    LoginPresenter presenter;

    @Bind(R.id.login_activity_username)
    EditText loginActivityUsername;

    @Bind(R.id.login_activity_password)
    EditText loginActivityPassword;

    @Bind(R.id.login_activity_register)
    CheckBox loginActivityRegister;

    @Bind(R.id.login_activity_login)
    Button loginActivityLogin;

    @Bind(R.id.login_activity_back)
    ImageView loginActivityBack;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        loginActivityRegister.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loginActivityLogin.setText(isChecked ? "注册" : "登录");
            }
        });
        loginActivityLogin.setOnClickListener(this);
        loginActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        presenter.login(loginActivityUsername.getText(), loginActivityPassword.getText(), loginActivityRegister.isChecked());
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
    public void onLoginSuccess(String s) {
        JShareUtils.put(this, "username", loginActivityUsername.getText().toString());
        JShareUtils.put(this, "password", loginActivityUsername.getText().toString());
        Toast("登录成功");
        finish();

    }

}