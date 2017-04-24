package news.news.com.news.Mvp.Model.Response;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import news.news.com.news.Common.StrConstant;

/**
 * Created by junwen on 17/4/12.
 */

public class NewsModel {

    private String newsid; //新闻ID
    private String newstitle; //新闻标题
    private String newsImgs; //图片
    private String newssource; //新闻类型 社会 等类型
    private String newscommentnum; //评论数
    private String newsreleasetime; //新闻时间

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsImgs() {
        return newsImgs;
    }

    public void setNewsImgs(String newsImgs) {
        this.newsImgs = newsImgs;
    }

    public String getNewssource() {
        return newssource;
    }

    public void setNewssource(String newssource) {
        this.newssource = newssource;
    }

    public String getNewscommentnum() {
        return newscommentnum;
    }

    public void setNewscommentnum(String newscommentnum) {
        this.newscommentnum = newscommentnum;
    }

    public String getNewsreleasetime() {
        return newsreleasetime;
    }

    public void setNewsreleasetime(String newsreleasetime) {
        this.newsreleasetime = newsreleasetime;
    }

    /**
     * 描述:返回这个新闻图片类型
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/12 下午9:00
     */
    public ImgType getNewsImgType() {
        if (TextUtils.isEmpty(newsImgs)) {
            return ImgType.NOT_IMG;
        } else if (newsImgs.contains(StrConstant.NEWS_SPIPT)) {
            return ImgType.MULTI_IMG;
        } else {
            return ImgType.ONE_IMG;
        }
    }

    /**
     * 描述:返回此类型的图片集合
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/12 下午9:42
     */
    public List<String> getImgs() {
        List<String> imgs = new ArrayList<>();
        ImgType newsImgType = getNewsImgType();
        if (newsImgType == ImgType.NOT_IMG) {
            return null;
        } else if (newsImgType == ImgType.ONE_IMG) {
            imgs.add(newsImgs);
        } else if (newsImgType == ImgType.MULTI_IMG) {
            String[] split = newsImgs.split(StrConstant.NEWS_SPIPT);
            for (String s : split) {
                imgs.add(s);
            }
        }
        return imgs;
    }

    /**
     * 描述:新闻类型
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 日期:17/4/12 下午9:06
     */
    public enum ImgType {
        ONE_IMG,
        MULTI_IMG,
        NOT_IMG
    }
}
