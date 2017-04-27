package news.news.com.news.Api;


import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import news.news.com.news.Base.MyApp;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * 描述:Api操作类
 * 作者:卜俊文
 * 邮箱:344176791@qq.com
 * 创建时间: 2017/2/3 9:03
 */
public class Api {

    public static String IP_ADDRESS = "172.16.2.134";

    private static final String BASE_URL = "http://" + IP_ADDRESS + ":8088/news/"; //根地址

    public static final String PATH = "service?format=JSON";

    private static final int DEFAULT_TIMEOUT = 5; //超时时间

    private static Api api;

    private final Retrofit retrofit;

    public Api() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Toast.makeText(MyApp.context, "http://" + IP_ADDRESS + ":8088/news/", Toast.LENGTH_SHORT).show();
        KLog.e();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://" + IP_ADDRESS + ":8088/news/")
                .client(initHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))//异步IO请求
//                .addConverterFactory(GsonConverterFactory.create())//转换层
                .addConverterFactory(GsonConverterFactory.create(gson))//转换层
                .build();
    }

    public static Api getInstance() {
        if (api == null) {
            api = new Api();
        }
        return api;
    }

    /**
     * 描述:初始化OkHttpClient
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/11/17 11:09
     */
    private OkHttpClient initHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(getApplicationInterceptor()); //添加应用拦截器
        httpClient.retryOnConnectionFailure(false); //错误重联,失败重试
        httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS); //连接超时
        httpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS); //写入超时
        httpClient.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS); //读取超时
        httpClient.addNetworkInterceptor(getNetWorkInterceptor());//网络拦截器
        httpClient.addInterceptor(new ChuckInterceptor(MyApp.getApplication()));//增加网络访问
        return httpClient.build();
    }

    /**
     * 描述:内存不足的情况，可以使用以下代码释放内存
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/12/15 11:58
     */
    private void closeMemory(OkHttpClient okHttpClient) {
        okHttpClient.dispatcher().executorService().shutdown();   //清除并关闭线程池
        okHttpClient.connectionPool().evictAll();                 //清除并关闭连接池
        try {
            okHttpClient.cache().close();                             //清除cache
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 描述:返回应用拦截器
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/12/15 11:53
     */
    private Interceptor getApplicationInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 描述:返回网络拦截器
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/12/15 14:14
     */
    private Interceptor getNetWorkInterceptor() {
        Interceptor authorizationInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                long t1 = System.nanoTime();
                Log.d(TAG, String.format("Sending request %s on %s%n%s", request.url(),
                        chain.connection(), request.headers()));

                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                return response;
            }
        };
        return authorizationInterceptor;
    }

    /**
     * 描述:返回对象
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/23 10:29
     */
    public <T> T getRetrofit(Class<T> httpServiceClass) {
        return retrofit.create(httpServiceClass);
    }

    /**
     * 描述:返回RequestBody
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/12/15 15:19
     */
    public static RequestBody getRequestBody(String info) {
        return RequestBody.create(MediaType.parse("application/octet-stream"), info.getBytes());
    }

    //Api使用示例
    /*Api.getInstance().getRetrofit(HttpService.class)
                    .login(mainUserName.getText().toString(), mainPassWord.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {
                            KLog.e("onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            KLog.e("onError" + e.getMessage());
                        }

                        @Override
                        public void onNext(Object o) {
                            KLog.e(o.toString());
                        }
                    });*/
}