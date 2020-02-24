package com.bw.huyanyan.utile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 *@Auther:cln
 *@Date: 2020/2/24
 *@Time:13:39
 *@Description:
 * */
public class NetUtile {
    //饿汉单例模式
    private static NetUtile netUtile=new NetUtile();
    //私有化无参构造

    private NetUtile() {
    }

    public static NetUtile getInstance() {
        return netUtile;
    }
    //判断网络
    public boolean iswork(Context context){
       ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    //创建handler
    private Handler handler=new Handler();
    //创建接口
    public interface ICallBack{
        void onUccess(String json);
        void onFrailure(String msg);
    }
    //图片
    public void getpath(final String path, final ImageView iv){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("xxx",e.toString());
                        }
                    });
                }
            }
        }).start();
    }
    //数据
    public void getjson(final String json, final ICallBack iCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(json);
                   HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                   conn.setRequestMethod("GET");
                   conn.setConnectTimeout(5000);
                   conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        //定义长度
                        int len=0;
                        byte[] bytes = new byte[1024];
                        StringBuilder sb = new StringBuilder();
                        while ((len=inputStream.read(bytes))!=-1){
                            String s = new String(bytes, 0, len);
                            sb.append(s);
                        }
                        //转换成字符串
                        final String s = sb.toString();
                        //关闭流
                        inputStream.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                               if (iCallBack!=null){
                                   iCallBack.onUccess(s);
                               }
                            }
                        });
                    }else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                               if (iCallBack!=null){
                                   iCallBack.onFrailure("加载失败");
                               }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
