package news.news.com.news.Mvp.Model.Request;

import news.news.com.news.Mvp.Model.BaseModel;

/**
 * Created by junwen on 17/4/19.
 */

public class NewsDetailRequest extends BaseModel {
    private String newsid;
    private String uid;
    public String getNewsid() {
        return newsid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;

    }

}
