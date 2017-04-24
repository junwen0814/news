package news.news.com.news.Mvp.Model.Request;

import news.news.com.news.Mvp.Model.BaseModel;

/**
 * Created by junwen on 17/4/19.
 */

public class UserInfoRequest extends BaseModel{
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
