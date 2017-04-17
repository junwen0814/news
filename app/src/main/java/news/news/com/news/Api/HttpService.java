package news.news.com.news.Api;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 描述:Api服务
 * 作者:卜俊文
 * 邮箱:344176791@qq.com
 * 创建时间: 2017/2/3 9:03
 */
public interface HttpService {


    @POST(Api.PATH)
    Observable<Object> postRequest(@Body RequestBody requestBody);

}
