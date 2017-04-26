package news.news.com.news.Ui.adapter.MainTab;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.junwen.jlibrary.JDensityUtils;
import com.junwen.jlibrary.JScreenUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import news.news.com.news.Mvp.Model.Response.NewsModel;
import news.news.com.news.R;

/**
 * Created by junwen on 17/4/12.
 */

public class MultiItemDelegate implements ItemViewDelegate<NewsModel> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news_tab_multi;
    }

    @Override
    public boolean isForViewType(NewsModel item, int position) {
        return item.getNewsImgType() == NewsModel.ImgType.MULTI_IMG;
    }

    @Override
    public void convert(ViewHolder holder, NewsModel newsModel, int position) {
        Context context = holder.getConvertView().getContext();
        holder.setText(R.id.multi_item_tv_title, newsModel.getNewstitle());
        holder.setText(R.id.multi_item_tv_newsType, newsModel.getNewssource());
        holder.setText(R.id.multi_item_tv_commentNum, newsModel.getNewscommentnum());
        holder.setText(R.id.multi_item_tv_Time, newsModel.getNewsreleasetime());
        List<String> imgs = newsModel.getImgs();
        if (imgs != null && imgs.size() > 3) {
            imgs = imgs.subList(0, 3);
            ImageView img_one = holder.getView(R.id.multi_item_img_img01);
            ImageView img_two = holder.getView(R.id.multi_item_img_img02);
            ImageView img_three = holder.getView(R.id.multi_item_img_img03);
            int width1 = JScreenUtils.getScreenWidth(context);
            int width = (width1 - 30) / 3;
            int height = JDensityUtils.dp2px(context, 90);
            Glide.with(holder.getConvertView().getContext()).load(imgs.get(0)).override(width, height).into(img_one);
            Glide.with(holder.getConvertView().getContext()).load(imgs.get(1)).override(width, height).into(img_two);
            Glide.with(holder.getConvertView().getContext()).load(imgs.get(2)).override(width, height).into(img_three);
        }
    }
}
