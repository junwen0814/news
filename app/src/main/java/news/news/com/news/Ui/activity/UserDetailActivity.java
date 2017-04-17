package news.news.com.news.Ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mmin18.widget.FlexLayout;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Base.MyApp;
import news.news.com.news.Common.event.UserHeadEvent;
import news.news.com.news.Mvp.Presenters.UserDetailPresenter;
import news.news.com.news.Mvp.Views.UserDetailView;
import news.news.com.news.R;

public class UserDetailActivity extends BaseActivity implements UserDetailView, View.OnClickListener {

    @InjectPresenter
    UserDetailPresenter presenter;

    @Bind(R.id.user_detail_back)
    ImageView userDetailBack;

    @Bind(R.id.user_detail_head)
    FlexLayout userDetailHead;

    @Bind(R.id.user_detail_name)
    FlexLayout userDetailName;

    private final int REQUEST_IMAGE = 0;

    @Bind(R.id.user_detail_head_img)
    CircleImageView userDetailHeadImg;

    @Bind(R.id.user_detail_name_value)
    TextView userDetailNameValue;

    @Override
    public int getLayout() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        userDetailBack.setOnClickListener(this);
        userDetailHead.setOnClickListener(this);
        userDetailName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_detail_back:
                finish();
                break;
            case R.id.user_detail_head:
                //相册选择
                openPhoto();
                break;
            case R.id.user_detail_name:
                //昵称修改
                updateName();
                break;
        }
    }

    private void updateName() {
        final EditText inputServer = new EditText(this);
        inputServer.setHint("不大于5个字");
        inputServer.setText(userDetailNameValue.getText().toString());
        inputServer.selectAll();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("昵称").setView(inputServer)
                .setNegativeButton("返回", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(inputServer.getText())) {
                    userDetailNameValue.setText(inputServer.getText().toString());
                } else {
                    Toast("昵称不可为空");
                }
            }
        });
        builder.show();
    }

    private void openPhoto() {
        ImgSelConfig config = new ImgSelConfig.Builder(UserDetailActivity.this, MyApp.loader)
                // 是否多选, 默认true
                .multiSelect(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(getResources().getColor(R.color.colorPrimary))
                // 返回图标ResId
                .backResId(R.drawable.img_login_img_back)
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(getResources().getColor(R.color.colorPrimary))
                // 裁剪大小。needCrop为true的时候配置
//                        .cropSize(1, 1, 200, 200)
//                        .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(false)
                // 最大选择图片数量，默认9
//                        .maxNum(9)
                .build();
        ImgSelActivity.startActivity(this, config, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList.size() > 0) {
                String path = pathList.get(0);
                if (!TextUtils.isEmpty(path)) {
                    userDetailHeadImg.setImageBitmap(BitmapFactory.decodeFile(path));
                    updateUserHead(path);
                }
            }
        }
    }

    /**
     * 描述:更新其他页面的头像
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/10 下午5:08
     */
    private void updateUserHead(String path) {
        UserHeadEvent userHeadEvent = new UserHeadEvent();
        userHeadEvent.setPath(path);
        EventBus.getDefault().post(userHeadEvent);
    }

}