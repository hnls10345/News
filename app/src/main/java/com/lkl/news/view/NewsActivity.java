package com.lkl.news.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.lkl.R;
import com.lkl.base.BaseActivity;
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
public class NewsActivity extends BaseActivity implements INewsView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.layout_home)
    FrameLayout layoutHome;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    INewPresenterCompl iNewPresenterCompl;


    private void init() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        iNewPresenterCompl = new INewPresenterCompl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        init();
        iNewPresenterCompl.getNews();
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
}
