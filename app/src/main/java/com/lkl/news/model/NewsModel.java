package com.lkl.news.model;
/**
 *
 * Created by LeiKelong on 2016/6/3.
 */
public class NewsModel implements INews{
    private String content;
    private String imgUrl;

    public NewsModel(String content, String imgUrl) {
        this.content = content;
        this.imgUrl = imgUrl;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getImageUrl() {
        return imgUrl;
    }
}
