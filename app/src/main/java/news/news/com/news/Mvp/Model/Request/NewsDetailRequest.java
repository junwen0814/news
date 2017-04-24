package news.news.com.news.Mvp.Model.Request;

import news.news.com.news.Mvp.Model.BaseModel;

/**
 * Created by junwen on 17/4/19.
 */

public class NewsDetailRequest extends BaseModel {
    private String newsid;

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }
}
