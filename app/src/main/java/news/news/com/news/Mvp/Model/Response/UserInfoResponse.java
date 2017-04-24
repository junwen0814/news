package news.news.com.news.Mvp.Model.Response;

import news.news.com.news.Mvp.Model.UserInfoModel;

/**
 * Created by junwen on 17/4/19.
 */

public class UserInfoResponse {
    private UserInfoModel user;

    public UserInfoModel getUser() {
        return user;
    }

    public void setUser(UserInfoModel user) {
        this.user = user;
    }
}
