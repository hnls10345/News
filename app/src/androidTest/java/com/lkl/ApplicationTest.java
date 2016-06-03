package com.lkl;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.lkl.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public static final String URL_MAIN = "http://36kr.com/";
    public static final String URL_MAIN2 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "<title>测试</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "测试连接\n" +
            "<div class=\"mydiv\">\n" +
            "<a href=\"http://www.page1.html\">链接地址一</a><br>\n" +
            "<a href=\"http://www.example.com/page2.html\">链接地址二</a><br>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

    public ApplicationTest() {
        super(Application.class);
    }

    public void testJsonp2() {
        Document doc = Jsoup.parse(URL_MAIN2);

        //Elements divs = doc.select("div.my div");
        Elements divs = doc.getElementsByClass("mydiv");
        StringBuilder linkBuffer = new StringBuilder();
        if (divs != null) {
            for (Element div : divs) {
                Elements links = div.select("a[href]");
                if (null != links) {
                    for (Element link : links) {
                        linkBuffer.append(link.attr("abs:href"));//相对地址会自动转成绝对url地址
                        linkBuffer.append(" ");
                        linkBuffer.append(link.text());
                    }
                }
            }
        }

        Log.e("link", linkBuffer.toString());
    }

    public String getFromAssets(String fileName) {
        String result = "";
        try {

            Context mContext  =    getApplication().getApplicationContext();
            InputStreamReader inputReader = new InputStreamReader(mContext.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";

            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }

    public void testJsonp(){

                    /**
                     * 使用Jsoup解析html
                     */
                    //连接主页，获取html，开始进行解析
                    Document doc = Jsoup.parse(Utils.getFromAssets("test.html"));

                    //Document doc = Jsoup.connect(URL_MAIN).get();
                    //获得一个以movie_show_shot（热播电影）为id节点
                  //  Element nodes = doc.getElementById("movie_show_hot");
                    Elements nodes = doc.getElementsByClass("am-cf inner_li") ;

                    for (Element  element : nodes) {
                        Elements imgs =  element.select("div.img_box > a > img");

                        if (imgs!=null&&imgs.size()!=0){
                            String imageUrl =  imgs.get(0).attr("src");
                            String title =  imgs.get(0).attr("alt");
                            Log.e("url",imageUrl);
                            Log.e("title",title);
                        }

                    }

//                    //获得一个以<class="video"节点集合
//                    Elements links = nodes.getElementsByClass("video");
                    StringBuffer stringBuffer = new StringBuffer();
                    int i = 0;
//                    for (i = 0; i < links.size(); i++) {
//                        //遍历集合获得第一个节点元素
//                        Element et = links.get(i).select("a[href]").first();
//                        //获取元素的href属性
//                        stringBuffer.append(URL_MAIN + et.attr("href") + "\n");
//                    }


            }


}