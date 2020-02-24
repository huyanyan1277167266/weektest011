package com.bw.huyanyan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.huyanyan.R;

import com.bw.huyanyan.activity.MainActivity;
import com.bw.huyanyan.bean.Bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/2/24
 *@Time:14:23
 *@Description:
 * */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<Bean.ResultsBean.NewsistBean> list;

    public MyAdapter(Context context, List<Bean.ResultsBean.NewsistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){

            convertView=View.inflate(context, R.layout.item,null);
            holder=new ViewHolder();

            holder.iv=convertView.findViewById(R.id.iv);
            holder.title=convertView.findViewById(R.id.title);
            holder.content=convertView.findViewById(R.id.content);
            holder.author=convertView.findViewById(R.id.author);
            holder.time=convertView.findViewById(R.id.time);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Bean.ResultsBean.NewsistBean newsistBean = list.get(position);
        String image = newsistBean.getImage();
        Glide.with(context).load(image).into(holder.iv);

        holder.title.setText(newsistBean.getTitle());
        holder.content.setText(newsistBean.getContent());
        holder.author.setText(newsistBean.getAuthor());
        holder.time.setText(newsistBean.getTime());


        return convertView;
    }
    private class ViewHolder{
        private ImageView iv;
        private TextView title;
        private TextView content;
        private TextView author;
        private TextView time;
    }

}
