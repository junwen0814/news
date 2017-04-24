package news.news.com.news.Ui.adapter.MainTab;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import news.news.com.news.Mvp.Model.Response.NewsModel;
import news.news.com.news.R;

/**
 * Created by junwen on 17/4/12.
 */

public class OneItemDelegate implements ItemViewDelegate<NewsModel> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news_tab_one;
    }

    @Override
    public boolean isForViewType(NewsModel item, int position) {
        return item.getNewsImgType() == NewsModel.ImgType.ONE_IMG;
    }

    @Override
    public void convert(ViewHolder holder, NewsModel newsModel, int position) {
        holder.setText(R.id.one_item_tv_title, newsModel.getNewstitle());
        holder.setText(R.id.one_item_tv_newsType, newsModel.getNewssource());
        holder.setText(R.id.one_item_tv_commentNum, newsModel.getNewscommentnum());
        holder.setText(R.id.one_item_tv_Time, newsModel.getNewsreleasetime());
        List<String> imgs = newsModel.getImgs();
        if (imgs != null && imgs.size() > 0) {
            ImageView imageView = holder.getView(R.id.one_item_img_img);
            Glide.with(holder.getConvertView().getContext()).load(imgs.get(0)).into(imageView);
        }
    }
}
