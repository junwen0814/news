package news.news.com.news.Ui.adapter.MainTab;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import news.news.com.news.Mvp.Model.Response.NewsModel;

/**
 * Created by junwen on 17/4/12.
 */

public class MainTabAdapter extends MultiItemTypeAdapter<NewsModel> {

    public MainTabAdapter(Context context, List<NewsModel> datas) {
        super(context, datas);
        addItemViewDelegate(new SingleItemDelegate()); //没有图片的
        addItemViewDelegate(new OneItemDelegate()); //一张图片的
        addItemViewDelegate(new MultiItemDelegate()); //三张图片的
    }
}
