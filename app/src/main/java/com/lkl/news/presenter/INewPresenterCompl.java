package com.lkl.news.presenter;

import android.os.Handler;
import android.os.Looper;

import com.facebook.common.logging.FLog;
import com.lkl.Constans;
import com.lkl.news.model.INews;
import com.lkl.news.model.NewsModel;
import com.lkl.news.view.INewsView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 *
 * Created by LeiKelong on 2016/6/3.
 */
public class INewPresenterCompl implements INewPresenter{

    INewsView iNewsView;
    INews news;
    Handler handler;

    public static String AcceptStr = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

    public static String AcceptEncoding = "gzip, deflate, sdch";

    public static String AcceptLanguage = "zh-CN,zh;q=0.8;UTF-8";

    public static String connectionStr = "keep-alive";

    public static String Host = "corp.1688.com";

    public static String UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.76 Safari/537.36";

    public INewPresenterCompl(INewsView iNewsView) {
        this.iNewsView = iNewsView;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getNews() {
//        List<NewsModel> newsModelList = parseNews2();;
//                        iNewsView.onGetNewsResult(newsModelList);
        iNewsView.showProgress();
        OkHttpUtils
        .get()
                .url(Constans.WEB_URL)

                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        FLog.e("response", response);
                        final List<NewsModel> newsModelList = parseNews(response);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iNewsView.onGetNewsResult(newsModelList);
                                iNewsView.dismissProgress();
                            }
                        },5000);

                    }
                });
    }




    private List<NewsModel> parseNews(String result){
        List<NewsModel> newsModelList = new ArrayList<>();
        Document doc = Jsoup.parse(result);
        Elements nodes = doc.select("div.img_box");
        for (Element element : nodes) {
            Elements imgs =  element.select("a > img");
            if (imgs!=null&&imgs.size()!=0){

                String imageUrl =  imgs.get(0).attr("data-src");
                String title =  imgs.get(0).attr("alt");
                NewsModel newsModel = new NewsModel(imageUrl,title);
                newsModelList.add(newsModel);
            }

        }
        return newsModelList;
    }



}
