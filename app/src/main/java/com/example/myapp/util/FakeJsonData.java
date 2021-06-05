package com.example.myapp.util;

import android.media.MediaPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 生产假的json数据
 */
public class FakeJsonData {
    public static  String videoList() throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg","success");
        jsonObject.put("code",0);

        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<10;i++){
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("vid",1);
            jsonObject1.put("vtitle","青龙战甲搭配机动兵");
            jsonObject1.put("author","狙击手麦克");
            jsonObject1.put("coverurl","https://gitee.com/xyliayang/note-image/raw/master/img/20210602100213.jpg");
            jsonObject1.put("headurl","https://gitee.com/xyliayang/note-image/raw/master/img/20210602100034.png");
            jsonObject1.put("commentNum",210);
            jsonObject1.put("likeNum",23);
            jsonObject1.put("collectNum",100);
            jsonArray.put(jsonObject1);
        }

        JSONObject jsonPage=new JSONObject();
        jsonPage.put("totalCount",4);
        jsonPage.put("pageSize",10);
        jsonPage.put("totalPage",1);
        jsonPage.put("currPage",1);
        jsonPage.put("list",jsonArray);

        jsonObject.put("page",jsonPage);
        return jsonObject.toString();
    }
}
