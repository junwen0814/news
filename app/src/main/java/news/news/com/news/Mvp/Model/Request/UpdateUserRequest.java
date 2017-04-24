package news.news.com.news.Mvp.Model.Request;

import news.news.com.news.Mvp.Model.BaseModel;

/**
 * Created by junwen on 17/4/19.
 */

public class UpdateUserRequest extends BaseModel{
    private String sex;
    private String nickname;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;

}
