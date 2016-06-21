package com.lkl.news.view;

import com.lkl.news.model.NewsModel;

import java.util.List;

/**
 *
 * Created by LeiKelong on 2016/6/3.
 */
public interface INewsView {
     void onGetNewsResult(List<NewsModel> newsModelList );
     void showProgress();
     void dismissProgress();
}
