package com.lkl.news.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lkl.R;
import com.lkl.news.model.NewsModel;

import java.util.List;

/**
 *
 * Created by LeiKelong on 2016/6/3.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    List<NewsModel> newsData ;


    public RecyclerViewAdapter(List<NewsModel> newsData) {
        this.newsData = newsData;
    }

    public void setNewsData(List<NewsModel> newsData){
       if (newsData!=null && newsData.size()>0){
         if (this.newsData!=null){
             this.newsData.clear();
             this.newsData.addAll(newsData);
             notifyDataSetChanged();
         }
       }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          String url = newsData.get(position).getImageUrl();
          holder.draweeView.setImageURI(Uri.parse(url));

    }

    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;

        public ViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.draweeview);
        }
    }
}
