package news.news.com.news.Mvp.Model.Request;

import news.news.com.news.Mvp.Model.BaseModel;

/**
 * Created by junwen on 17/4/18.
 */

public class NewsListRequest extends BaseModel {
    private String cid;
    private String pageSize;
    private String pageNum;

    public String getCid() {
        return cid;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
