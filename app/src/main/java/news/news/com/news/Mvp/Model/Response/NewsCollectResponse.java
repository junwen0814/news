package news.news.com.news.Mvp.Model.Response;

import java.util.List;

import news.news.com.news.Mvp.Model.CollectModel;

/**
 * Created by junwen on 17/4/26.
 */

public class NewsCollectResponse {
    private List<CollectModel> lists;

    public List<CollectModel> getLists() {
        return lists;
    }

    public void setLists(List<CollectModel> lists) {
        this.lists = lists;
    }
}
