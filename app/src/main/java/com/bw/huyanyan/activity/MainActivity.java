package com.bw.huyanyan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.huyanyan.R;
import com.bw.huyanyan.adapter.MyAdapter;
import com.bw.huyanyan.base.BaseActivity;
import com.bw.huyanyan.bean.Bean;
import com.bw.huyanyan.contorct.IHomeContorct;
import com.bw.huyanyan.persenter.IHomePersenter;
import com.bw.huyanyan.utile.NetUtile;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends BaseActivity implements IHomeContorct.IView {


    private XBanner xb;
    private ListView lv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    //找控件
        xb = findViewById(R.id.xb);
        lv = findViewById(R.id.lv);
    }

    @Override
    protected void getData() {
        IHomePersenter Persenter = new IHomePersenter(this);

        String url="http://blog.zhaoliang5156.cn/api/news/news_data.json";
          Persenter.getBanner(url);
        Persenter.getlist(url);
    }

    @Override
    public void onUccess(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onFrailure(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onBanner(final List<Bean.ResultsBean.BannerBean> list) {
        //判断是否有网
        boolean iswork = NetUtile.getInstance().iswork(MainActivity.this);
        if (iswork){
            xb.setBannerData(list);
            xb.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Bean.ResultsBean.BannerBean bannerBean = list.get(position);
                    String imageurl = bannerBean.getImageurl();
                    Glide.with(MainActivity.this).load(imageurl).into((ImageView) view);
                }
            });
        }else{
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        }


          }

    @Override
    public void onlistUccess(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onlistFrailure(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onlist(List<Bean.ResultsBean.NewsistBean> list) {
        //判断是否有网
        boolean iswork = NetUtile.getInstance().iswork(MainActivity.this);
        if (iswork){
            MyAdapter myAdapter = new MyAdapter(MainActivity.this, list);
            lv.setAdapter(myAdapter);
        }else{
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        }

    }
}
