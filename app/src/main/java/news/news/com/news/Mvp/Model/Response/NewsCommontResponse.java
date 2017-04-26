package news.news.com.news.Mvp.Model.Response;

import java.util.List;

import news.news.com.news.Mvp.Model.CommontModel;

/**
 * Created by junwen on 17/4/26.
 */

public class NewsCommontResponse {
    private List<CommontModel> list;

    public List<CommontModel> getList() {
        return list;
    }

    public void setList(List<CommontModel> list) {
        this.list = list;
    }
}
