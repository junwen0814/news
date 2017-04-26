package news.news.com.news.Ui.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.junwen.jlibrary.JKeyboardUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import news.news.com.news.Base.BaseActivity;
import news.news.com.news.Base.MyApp;
import news.news.com.news.Common.ContentSizeConstant;
import news.news.com.news.Mvp.Model.CommontModel;
import news.news.com.news.Mvp.Model.NewsDetailModel;
import news.news.com.news.Mvp.Presenters.NewDetailPresenter;
import news.news.com.news.Mvp.Views.NewDetailView;
import news.news.com.news.R;
import news.news.com.news.Utils.HtmlGetter;
import news.news.com.news.Utils.SharedUtils;

public class NewDetailActivity extends BaseActivity implements NewDetailView, Toolbar.OnMenuItemClickListener, View.OnClickListener {

    @InjectPresenter
    NewDetailPresenter presenter;

    public static final String INTENT_KEY_NEWSID = "intent_key_newsId";

    @Bind(R.id.newsDetail_activity_toolbar)
    Toolbar newsDetailActivityToolbar;

    @Bind(R.id.new_detail_activity_collect)
    ImageView newDetailActivityCollect;

    @Bind(R.id.new_detail_activity_title)
    TextView newDetailActivityTitle;

    @Bind(R.id.new_detail_activity_source)
    TextView newDetailActivitySource;

    @Bind(R.id.new_detail_activity_time)
    TextView newDetailActivityTime;

    @Bind(R.id.new_detail_activity_html)
    TextView newDetailActivityHtml;

    @Bind(R.id.new_detail_activity_comment)
    EditText newDetailActivityComment;

    @Bind(R.id.new_detail_activity_comment_send)
    TextView newDetailActivityCommentSend;

    @Bind(R.id.new_detail_activity_recycleView)
    RecyclerView newDetailActivityRecycleView;

    private String newsId;

    private NewsDetailModel newsDetail;

    private List<CommontModel> data = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_new_detail;
    }

    @Override
    public void initView() {
        newsDetailActivityToolbar.setTitle("");//设置标题，必须在setSupportActionBar方法之前
        newDetailActivityHtml.setTextSize(getContentSize());
        setSupportActionBar(newsDetailActivityToolbar);
        newsId = getIntent().getStringExtra(INTENT_KEY_NEWSID);
        newDetailActivityRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        presenter.requestNewsDetail(newsId);
        presenter.requestNewsComment(newsId);
        newDetailActivityRecycleView.setAdapter(new CommonAdapter<CommontModel>(this, R.layout.item_news_comment, data) {
            @Override
            protected void convert(ViewHolder holder, CommontModel commontModel, int position) {
                holder.setText(R.id.news_comment_username, commontModel.getNickname());
                holder.setText(R.id.news_comment_time, commontModel.getRtime());
                holder.setText(R.id.news_comment_content, commontModel.getRcontent());
//                CircleImageView head = holder.getView(R.id.news_comment_head);
//                Glide.with(head.getContext()).load(commontModel.get)
            }
        });
    }

    /**
     * 描述:更新新闻内容
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/26 上午11:08
     */
    private void updateNewsModel(NewsDetailModel newsDetail) {
        if (newsDetail != null) {
            newDetailActivityCollect.setImageResource(newsDetail.isCollection() ? R.drawable.img_detail_collect_press : R.drawable.img_detail_collect_normal);
            newDetailActivityTitle.setText(newsDetail.getNewstitle());
            newDetailActivitySource.setText(newsDetail.getNewssource());
            newDetailActivityTime.setText(newsDetail.getNewsreleasetime());
            HtmlGetter html = new HtmlGetter(this, newDetailActivityHtml);
            newDetailActivityHtml.setText(Html.fromHtml(newsDetail.getNewscontent(), html, null));
        }
    }

    @Override
    public void initListener() {
        newsDetailActivityToolbar.setOnMenuItemClickListener(this);
        newDetailActivityCollect.setOnClickListener(this);
        newDetailActivityCommentSend.setOnClickListener(this);
        newDetailActivityComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(newDetailActivityComment.getText())) {
                    newDetailActivityCommentSend.setHintTextColor(getResources().getColor(R.color.text_default_color));
                } else {
                    newDetailActivityCommentSend.setHintTextColor(getResources().getColor(R.color.text_select_color));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 描述:当新闻详情返回
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/19 下午3:16
     */
    @Override
    public void onNewsDetailResponse(NewsDetailModel newsDetailModel) {
        this.newsDetail = newsDetailModel;
        if (newsDetail == null) {
            Toast("新闻不存在，请重试");
            return;
        }
        updateNewsModel(newsDetail);
    }

    @Override
    public void onNewsDetailFailResponse(String error) {
        Toast(error);
    }

    /**
     * 描述:更新收藏成功
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/25 下午9:35
     */
    @Override
    public void onUpdateCollectSuccess() {
        Toast("收藏成功");
        newDetailActivityCollect.setImageResource(newsDetail.isCollection() ? R.drawable.img_detail_collect_press : R.drawable.img_detail_collect_normal);
    }

    /**
     * 描述:更新收藏失败
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/25 下午9:35
     */
    @Override
    public void onUpdateCollectFail() {
        Toast("收藏失败");
        newsDetail.setCollection(!newsDetail.isCollection());
    }

    /**
     * 描述:评论列表返回
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/26 上午10:28
     */
    @Override
    public void onCommentResponse(List<CommontModel> list) {
        if (list.size() > 0) {
            data.clear();
            data.addAll(list);
            newDetailActivityRecycleView.getAdapter().notifyDataSetChanged();
        }
    }


    // 创建关联菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_text_size:
                //修改字体大小
                changeTextSize();
                break;
        }
        return false;
    }

    /**
     * 描述:修改字体大小
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/14 下午5:12
     */
    public void changeTextSize() {
        String contentSize = SharedUtils.getInstance().getContentSize();
        final String[] textList = {"特大号字", "大号字", "中号字", "小号字"};
        int textIndex = 2;
        if (!TextUtils.isEmpty(contentSize)) {
            for (int i = 0; i < textList.length; i++) {
                if (contentSize.equals(textList[i])) {
                    textIndex = i;
                    break;
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(NewDetailActivity.this);
        builder.setTitle("正文字号");
        builder.setSingleChoiceItems(new String[]{"特大号字", "大号字", "中号字", "小号字"}, textIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (textList[which]) {
                    case "特大号字":
                        newDetailActivityHtml.setTextSize(ContentSizeConstant.SIZE_EXTRA_LARGE);
                        break;
                    case "大号字":
                        newDetailActivityHtml.setTextSize(ContentSizeConstant.SIZE_LARGE_SIZE);
                        break;
                    case "中号字":
                        newDetailActivityHtml.setTextSize(ContentSizeConstant.SIZE_MEDIUM);
                        break;
                    case "小号字":
                        newDetailActivityHtml.setTextSize(ContentSizeConstant.SIZE_TRUMPET);
                        break;
                }
                SharedUtils.getInstance().setContentSize(textList[which]);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 描述:获取当前字体大小
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/14 下午5:12
     */
    public int getContentSize() {
        String contentSize = SharedUtils.getInstance().getContentSize();
        int size = 0;
        if (TextUtils.isEmpty(contentSize)) {
            return ContentSizeConstant.SIZE_MEDIUM;
        } else {
            switch (contentSize) {
                case "特大号字":
                    size = ContentSizeConstant.SIZE_EXTRA_LARGE;
                    break;
                case "大号字":
                    size = ContentSizeConstant.SIZE_LARGE_SIZE;
                    break;
                case "中号字":
                    size = ContentSizeConstant.SIZE_MEDIUM;
                    break;
                case "小号字":
                    size = ContentSizeConstant.SIZE_TRUMPET;
                    break;
            }
        }
        return size;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_detail_activity_collect:
                //切换收藏
                newsDetail.setCollection(!newsDetail.isCollection());
                presenter.updateCollect(MyApp.userId, newsDetail.getNewsid(), newsDetail.isCollection());
                break;
            case R.id.new_detail_activity_comment_send:
                //发送评论
                if (TextUtils.isEmpty(newDetailActivityComment.getText())) {
                    Toast("评论不可为空");
                    return;
                }
                sendComment();
        }
    }

    /**
     * 描述:发送评论
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/14 下午2:47
     */
    private void sendComment() {
        String comment = newDetailActivityComment.getText().toString();
        Toast(comment);
        JKeyboardUtils.closeKeybord(newDetailActivityComment, NewDetailActivity.this);
        newDetailActivityComment.setText("");
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}