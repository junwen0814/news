package news.news.com.news.Mvp.Model.Response;

import java.util.List;

/**
 * Created by junwen on 17/4/17.
 */

public class ColumnsResponseModel {
    private String total_count;
    private List<ColumnsModel> lists;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<ColumnsModel> getLists() {
        return lists;
    }

    public void setLists(List<ColumnsModel> lists) {
        this.lists = lists;
    }
}
