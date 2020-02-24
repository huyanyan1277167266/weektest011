package com.bw.huyanyan.contorct;

import com.bw.huyanyan.bean.Bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/2/24
 *@Time:13:53
 *@Description:
 * */
public interface IHomeContorct {
    //创建view层接口
    interface IView{
        void onUccess(String str);
        void onFrailure(String str);
    void onBanner(List<Bean.ResultsBean.BannerBean>list);

        void onlistUccess(String str);
        void onlistFrailure(String str);
       void onlist(List<Bean.ResultsBean.NewsistBean>list);
    }

    //创建p层接口
    interface IPresenter{
        void getBanner(String msg);

        void getlist(String msg);

    }
    //创建M层接口
    interface IModel{
        void getBanner(String url,BannerCallBack callBack);
        interface BannerCallBack{
            void onUccess(String str);
            void onFrailure(String str);
            void onBanner(List<Bean.ResultsBean.BannerBean>list);
        }

        void getList(String url,ListICallBack iCallBack);
        interface ListICallBack{
            void onlistUccess(String str);
            void onlistFrailure(String str);
            void onlist(List<Bean.ResultsBean.NewsistBean>list);
        }
    }
}
