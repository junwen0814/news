package news.news.com.news.Mvp.Model.Response;

import java.util.List;

import news.news.com.news.Mvp.Model.CollectModel;

/**
 * Created by junwen on 17/4/26.
 */

public class NewsCollectResponse {
    private List<CollectModel> list;

    public List<CollectModel> getList() {
        return list;
    }

    public void setList(List<CollectModel> list) {
        this.list = list;
    }
}
