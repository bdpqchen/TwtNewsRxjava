package com.example.cdc.twtnewsrxjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cdc.twtnewsrxjava.R;
import com.example.cdc.twtnewsrxjava.bean.NewsBean;

import butterknife.InjectView;

/**
 * Created by cdc on 16-11-13.
 */

public class NewsAdapter extends BaseAdapter<NewsBean> {


    private int TYPE_NORMAL = 1;
    private int TYPE_FOOTER = 0;

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if(viewType == TYPE_NORMAL){
            return new NormalViewHolder(inflater.inflate(R.layout.item_normal, parent, false));
        }else if(viewType == TYPE_FOOTER){
            return new FooterViewHolder(inflater.inflate(R.layout.item_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder instanceof NormalViewHolder){
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            final String subject = mDataSet.get(position).subject;
            normalViewHolder.itemTitle.setText(subject);
            normalViewHolder.newsRead.setText(mDataSet.get(position).visitcount + mContext.getString(R.string.visitcount));
            normalViewHolder.newsCommented.setText(mDataSet.get(position).comments + mContext.getString(R.string.comments));
            if (!mDataSet.get(position).pic.equals("")) {
                Glide.with(mContext).load(mDataSet.get(position).pic).into(normalViewHolder.pic);
            } else {
                //无图重新定义ImageView为0
                RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) normalViewHolder.pic.getLayoutParams();
                relativeParams.width = 0;
                normalViewHolder.pic.setLayoutParams(relativeParams);
            }
            //设置点击事件
/*
            normalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsContentActivity.class);
                    Bundle intentBundle = new Bundle();
                    intentBundle.putString("index", String.valueOf(mDataSet.get(pos).getIndex()));
                    intentBundle.putString("subject", subject);
                    intent.putExtras(intentBundle);
                    mContext.startActivity(intent);
                }
            });
*/

            //长按事件
            normalViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position){
        if(position + 1 <= mDataSet.size()){
            return TYPE_NORMAL;
        }else{
            return TYPE_FOOTER;
        }
    }

    class NormalViewHolder extends BaseViewHolder {

        @InjectView(R.id.pic)
        ImageView pic;
        @InjectView(R.id.item_title)
        TextView itemTitle;
        @InjectView(R.id.news_read)
        TextView newsRead;
        @InjectView(R.id.news_commented)
        TextView newsCommented;
        @InjectView(R.id.item_frame)
        RelativeLayout itemFrame;
        @InjectView(R.id.card_view)
        CardView cardView;

        public NormalViewHolder(View itemView) {
            super(itemView);
        }

    }

    class FooterViewHolder extends BaseViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


}
