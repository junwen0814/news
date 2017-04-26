package news.news.com.news.Mvp.Model.Request;

import news.news.com.news.Mvp.Model.BaseModel;

/**
 * Created by junwen on 17/4/25.
 */

public class UpdateCollectRequest extends BaseModel{
    private String uid;
    private String isCollect;
    private String newsid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }
}
