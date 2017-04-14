package news.news.com.news.Mvp.Model;

/**
 * Created by junwen on 17/4/14.
 */

public class NewsDetailModel {
    private String title; //新闻标题
    private String source; //新闻来源
    private String time; //新闻时间
    private String html; //新闻详情页
    private boolean isCollection; //是否收藏

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }
}
