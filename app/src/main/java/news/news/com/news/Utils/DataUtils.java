package news.news.com.news.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import news.news.com.news.Mvp.Model.NewsDetailModel;
import news.news.com.news.Mvp.Model.NewsVideoModel;
import news.news.com.news.Mvp.Model.Response.NewsModel;

/**
 * Created by junwen on 17/4/12.
 */

public class DataUtils {

    private static String sp_str = "_";
    private static List<NewsModel> newsModels = new ArrayList<>();
    private static Map<String, NewsDetailModel> newsDetailModelMap = new HashMap<>();

    static {
    }

    public static List<NewsModel> getNewsList() {
        return newsModels;
    }

    public static NewsDetailModel getNewsDetail(String newsId) {
        if (newsDetailModelMap.containsKey(newsId)) {
            return newsDetailModelMap.get(newsId);
        }
        return null;
    }

//    private static NewsModel getNewsModel(String newsId, String title, String time, String commentNum, String type, String imgs) {
//        NewsModel newsModel = new NewsModel();
//        newsModel.setNewsId(newsId);
//        newsModel.setNewsTitle(title);
//        newsModel.setNewsTime(time);
//        newsModel.setNewsCommentNum(commentNum);
//        newsModel.setNewsType(type);
//        newsModel.setNewsImgs(imgs);
//        return newsModel;
//    }


    public static List<NewsVideoModel> getNewsVideoModel() {
        List<NewsVideoModel> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsVideoModel newsDetailModel = new NewsVideoModel();
            newsDetailModel.setVideoTitle("嫂子闭眼睛");
            newsDetailModel.setVideoUrl("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");
            data.add(newsDetailModel);
        }
        return data;
    }
}
