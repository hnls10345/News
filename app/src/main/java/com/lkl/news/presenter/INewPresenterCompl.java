package com.lkl.news.presenter;

import android.os.Handler;
import android.os.Looper;

import com.lkl.Constans;
import com.lkl.news.model.INews;
import com.lkl.news.model.NewsModel;
import com.lkl.news.view.INewsView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by LeiKelong on 2016/6/3.
 */
public class INewPresenterCompl implements INewPresenter{

    INewsView iNewsView;
    INews news;
    Handler handler;

    public INewPresenterCompl(INewsView iNewsView) {
        this.iNewsView = iNewsView;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getNews() {
        List<NewsModel> newsModelList = parseNews2();;
                        iNewsView.onGetNewsResult(newsModelList);

//        OkHttpUtils
//                .get()
//                .url(Constans.WEB_URL)
//                .addHeader("http-equiv", "Content-Type")
//                .addHeader("content", "text/html")
//                .addHeader("charset","UTF-8")
//                .build()
//                .execute(new StringCallback()
//                {
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        FLog.e("response",response);
//                        List<NewsModel> newsModelList = parseNews(response);
//                        iNewsView.onGetNewsResult(newsModelList);
//                    }
//                });
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

    private List<NewsModel> parseNews2() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NewsModel> newsModelList = new ArrayList<>();
                Document doc = null;
                try {
                    doc = Jsoup.connect(Constans.WEB_URL).get();
                    Elements nodes = doc.select("div.img_box");
                    for (Element element : nodes) {
                        Elements imgs = element.select("a > img");
                        if (imgs != null && imgs.size() != 0) {

                            String imageUrl = imgs.get(0).attr("data-src");
                            String title = imgs.get(0).attr("alt");
                            NewsModel newsModel = new NewsModel(imageUrl, title);
                            newsModelList.add(newsModel);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).run();

        return null;
    }



}
