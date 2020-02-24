package com.bw.huyanyan.model;

import com.bw.huyanyan.activity.MainActivity;
import com.bw.huyanyan.bean.Bean;
import com.bw.huyanyan.contorct.IHomeContorct;
import com.bw.huyanyan.utile.NetUtile;
import com.google.gson.Gson;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/2/24
 *@Time:14:02
 *@Description:
 * */
public class IHomeModel implements IHomeContorct.IModel {

    @Override
    public void getBanner(String url, final BannerCallBack callBack) {

       NetUtile.getInstance().getjson(url, new NetUtile.ICallBack() {
           @Override
           public void onUccess(String json) {
               callBack.onUccess(json);
               Gson gson = new Gson();
               Bean.ResultsBean resultsBean = gson.fromJson(json, Bean.ResultsBean.class);
               List<Bean.ResultsBean.BannerBean> banner = resultsBean.getBanner();
               callBack.onBanner(banner);
           }

           @Override
           public void onFrailure(String msg) {
            callBack.onFrailure(msg);
           }
       });
    }

    @Override
    public void getList(String url, final ListICallBack iCallBack) {
        NetUtile.getInstance().getjson(url, new NetUtile.ICallBack() {
            @Override
            public void onUccess(String json) {
                iCallBack.onlistUccess(json);
                Gson gson = new Gson();

                Bean.ResultsBean resultsBean = gson.fromJson(json, Bean.ResultsBean.class);
                List<Bean.ResultsBean.NewsistBean> newsist = resultsBean.getNewsist();
                iCallBack.onlist(newsist);


            }

            @Override
            public void onFrailure(String msg) {
            iCallBack.onlistFrailure(msg);
            }
        });
    }
}
