package com.bw.huyanyan.persenter;

import com.bw.huyanyan.bean.Bean;
import com.bw.huyanyan.contorct.IHomeContorct;
import com.bw.huyanyan.model.IHomeModel;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/2/24
 *@Time:14:02
 *@Description:
 * */
public class IHomePersenter implements IHomeContorct.IPresenter {
    IHomeContorct.IView mview;
    IHomeModel mModel;

    public IHomePersenter(IHomeContorct.IView mview) {
        this.mview = mview;
        mModel=new IHomeModel();
    }

    @Override
    public void getBanner(String msg) {
    mModel.getBanner(msg, new IHomeContorct.IModel.BannerCallBack() {
        @Override
        public void onUccess(String str) {
            mview.onUccess(str);
        }

        @Override
        public void onFrailure(String str) {
            mview.onFrailure(str);
        }

        @Override
        public void onBanner(List<Bean.ResultsBean.BannerBean> list) {
            mview.onBanner(list);
        }
    });
    }

    @Override
    public void getlist(String msg) {
        mModel.getList(msg, new IHomeContorct.IModel.ListICallBack() {
            @Override
            public void onlistUccess(String str) {
                mview.onlistUccess(str);
            }

            @Override
            public void onlistFrailure(String str) {
                mview.onlistFrailure(str);
            }

            @Override
            public void onlist(List<Bean.ResultsBean.NewsistBean> list) {
                mview.onlist(list);
            }
        });
    }
}
