package news.news.com.news.Mvp.Model.Api;

/**
 * Created by junwen on 17/4/17.
 */

public class Request {
    private String service_name;

    private String operation_name;

    private String token_id;

    private String user_id;

    private String version;

    private String timestamp;

    private String request_seq;

    private String request_source;

    private String content;

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_name() {
        return this.service_name;
    }

    public void setOperation_name(String operation_name) {
        this.operation_name = operation_name;
    }

    public String getOperation_name() {
        return this.operation_name;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getToken_id() {
        return this.token_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setRequest_seq(String request_seq) {
        this.request_seq = request_seq;
    }

    public String getRequest_seq() {
        return this.request_seq;
    }

    public void setRequest_source(String request_source) {
        this.request_source = request_source;
    }

    public String getRequest_source() {
        return this.request_source;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

}
