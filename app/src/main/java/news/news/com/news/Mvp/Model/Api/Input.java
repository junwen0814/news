package news.news.com.news.Mvp.Model.Api;

/**
 * Created by junwen on 17/4/17.
 */

public class Input<T> {
    private T newsColumn;

    public T getNewsColumn() {
        return newsColumn;
    }

    public void setNewsColumn(T newsColumn) {
        this.newsColumn = newsColumn;
    }
}

