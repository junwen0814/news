package news.news.com.news.Mvp.Model;

/**
 * Created by junwen on 17/4/17.
 */

public class ColumnsRequestModel extends BaseModel {

    private String cid;

    private String cname;

    private String pageNum;

    private String pageSize;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
