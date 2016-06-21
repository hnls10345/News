package com.lkl.news.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.lkl.R;
import com.lkl.base.BaseFragmentActivity;
import com.lkl.news.adapter.RecyclerViewAdapter;
import com.lkl.news.model.NewsModel;
import com.lkl.news.presenter.INewPresenterCompl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 话说
 * Created by LeiKelong on 2016/6/2
 */
public class NewsActivity extends BaseFragmentActivity implements INewsView {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.layout_home)
    FrameLayout layoutHome;

    INewPresenterCompl iNewPresenterCompl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        iNewPresenterCompl.getNews();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initViews() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNewPresenterCompl.getNews();
            }
        });

        iNewPresenterCompl = new INewPresenterCompl(this);
    }

    @Override
    public void onGetNewsResult(List<NewsModel> newsModelList) {
           if (newsModelList !=null&&newsModelList.size()!=0){
               RecyclerViewAdapter adapter = new RecyclerViewAdapter(newsModelList);
               recyclerview.setAdapter(adapter);
               recyclerview.setHasFixedSize(false);
           }else{
               Snackbar.make(layoutHome, "no result", Snackbar.LENGTH_LONG).show();
           }
    }

    @Override
    public void showProgress() {
         showProgressDialog();
    }

    @Override
    public void dismissProgress() {
         dismissProgressDialog();
    }
}
