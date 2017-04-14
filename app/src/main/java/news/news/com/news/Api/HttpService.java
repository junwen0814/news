package news.news.com.news.Api;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 描述:Api服务
 * 作者:卜俊文
 * 邮箱:344176791@qq.com
 * 创建时间: 2017/2/3 9:03
 */
public interface HttpService {

    @FormUrlEncoded
    @POST("login")
    Observable<Object> login(@Field("userName") String userName, @Field("passWord") String passWord);

}
