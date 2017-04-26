package news.news.com.news.Mvp.Model;

/**
 * Created by junwen on 17/4/14.
 */

public class NewsDetailModel {

    private String newsid; //新闻ID
    private String newstitle; //新闻标题
    private String newssource; //新闻来源
    private String newsreleasetime; //新闻时间
    private String newscontent; //新闻详情页
    private String iscollect; //是否收藏

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewssource() {
        return newssource;
    }

    public void setNewssource(String newssource) {
        this.newssource = newssource;
    }

    public String getNewsreleasetime() {
        return newsreleasetime;
    }

    public void setNewsreleasetime(String newsreleasetime) {
        this.newsreleasetime = newsreleasetime;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    public boolean isCollection() {
        return "1".equals(iscollect);
    }

    public void setCollection(boolean isCollection) {
        this.iscollect = isCollection ? "1" : "0";
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }
}
