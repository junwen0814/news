package news.news.com.news.Ui.adapter.MainTab;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import news.news.com.news.Mvp.Model.Response.NewsModel;
import news.news.com.news.R;

/**
 * Created by junwen on 17/4/12.
 */

public class SingleItemDelegate implements ItemViewDelegate<NewsModel> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news_tab_single;
    }

    @Override
    public boolean isForViewType(NewsModel item, int position) {
        return item.getNewsImgType() == NewsModel.ImgType.NOT_IMG;
    }

    @Override
    public void convert(ViewHolder holder, NewsModel newsModel, int position) {
        holder.setText(R.id.single_item_tv_title, newsModel.getNewstitle());
        holder.setText(R.id.single_item_tv_newsType, newsModel.getNewssource());
        holder.setText(R.id.single_item_tv_commentNum, newsModel.getNewscommentnum());
        holder.setText(R.id.single_item_tv_Time, newsModel.getNewsreleasetime());
    }
}
