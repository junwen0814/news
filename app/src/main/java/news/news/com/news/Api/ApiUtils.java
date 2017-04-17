package news.news.com.news.Api;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.socks.library.KLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import news.news.com.news.Mvp.Model.Api.Content;
import news.news.com.news.Mvp.Model.Api.Input;
import news.news.com.news.Mvp.Model.Api.Request;
import news.news.com.news.Mvp.Model.Api.Root;
import news.news.com.news.Mvp.Model.BaseModel;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by junwen on 17/4/17.
 */

public class ApiUtils {

    public static <T extends BaseModel, K extends Object> void post(T data, final Class<K> targer, String operationName, String serverName, final OnApiResult onApiResult) {
        //组装请求实体
        Input input = new Input();
        input.setNewsColumn(data);

        //组装请求实体
        final Content content = new Content();
        content.setInput(input);
        Request request = new Request();
        request.setService_name(serverName);
        request.setOperation_name(operationName);
        request.setToken_id("9edb181c-e377-4f33-893a-da356d97a76e");
        request.setUser_id("1");
        request.setVersion("0100");
        request.setTimestamp("20170417141304");
        request.setRequest_seq("20170417141837");
        request.setRequest_source("");
        request.setContent(JSON.toJSONString(content));
        Root root = new Root();
        root.setRequest(request);
        String json = JSON.toJSONString(root);
        KLog.json(json);
        //组装RequestBody
        RequestBody requestBody = Api.getRequestBody(json);
        //开始请求
        Api.getInstance().getRetrofit(HttpService.class)
                .postRequest(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        KLog.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        onApiResult.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        String content = getContent(JSON.toJSONString(o));
                        if ("-1".equals(content)) {
                            onError(new Throwable("请求错误"));
                        } else {
                            K k = null;
                            if (content.startsWith("{")) {
                                //对象
                                k = new Gson().fromJson(content, targer);
                            } else if (content.startsWith("[")) {
                                //集合
                                try {
                                    JSONArray jsonArray = new JSONArray(content);
                                    List<K> list = new ArrayList<K>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        list.add(new Gson().fromJson(jsonArray.get(i).toString(), targer));
                                    }
                                    k = (K) list;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                onApiResult.onFail(content);
                                KLog.e("错误:" + content);
                                return;
                            }
                            onApiResult.onSuccess(k);
                        }
                    }
                });
    }


    public static String getContent(String body) {
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(body);
            JSONObject response = jsonObject.getJSONObject("response");
            response.get("content");
            JSONObject content = new JSONObject(String.valueOf(response.get("content")));
            JSONObject output = new JSONObject(String.valueOf(content.get("output")));
            String response_code = output.getString("response_code");
            if ("1".equals(response_code)) {
                JSONObject content1 = output.getJSONObject("content");
                return content1.toString();
            } else {
                return "-1";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public interface OnApiResult {

        <T> void onSuccess(T data);

        void onFail(String error);
    }
}
