package news.news.com.news.Mvp.Model.Response;

import news.news.com.news.Mvp.Model.NewsDetailModel;

/**
 * Created by junwen on 17/4/19.
 */

public class NewsDetailResponse {
    private NewsDetailModel newContent;

    public NewsDetailModel getNewContent() {
        return newContent;
    }

    public void setNewContent(NewsDetailModel newContent) {
        this.newContent = newContent;
    }
}
