package news.news.com.news.Mvp.Model.Response;

import java.util.List;

/**
 * Created by junwen on 17/4/18.
 */

public class NewsListResponse {
    private List<NewsModel> list;

    public List<NewsModel> getList() {
        return list;
    }

    public void setList(List<NewsModel> list) {
        this.list = list;
    }
}
