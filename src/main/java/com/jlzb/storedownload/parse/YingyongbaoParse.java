package com.jlzb.storedownload.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.http.NormalHttpRequest;
import com.jlzb.storedownload.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 抓取的是PC端
 */
public class YingyongbaoParse implements Parse{

    private Device device;

    private AppInfo appInfo;

    private String nextWord = "";

    public YingyongbaoParse(Device device, AppInfo appInfo) {
        this.device = device;
        this.appInfo = appInfo;
    }

    @Override
    public int parsesearch() {
        String word = "";
        try {
            word = URLEncoder.encode(appInfo.getKeyword(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://m5.qq.com/pcsoftmgr/searchapp.htm?keyword="+word+"&searchScene=1&pageSize=20&contextData=";

        int index = 0;
        int appid = 0;
        break1: while (index < 3) {
            String _url = url + nextWord;
            String result = dosearch(_url);
            if (!StringUtil.isEmpty(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject obj = jsonObject.getJSONObject("obj");

                nextWord = obj.getString("contextData");

                JSONArray dataList = obj.getJSONArray("dataList");

                for (int i = 0; i < dataList.size(); i++) {
                    JSONObject tempObject = dataList.getJSONObject(i);

                    JSONObject appInfoObject = tempObject.getJSONObject("appInfo");
                    if(appInfo.getPackagename().equals(appInfoObject.getString("pkgName"))) {
                        appid = appInfoObject.getInteger("appId");

                        break break1;
                    }
                }
            }

            index++;
        }

        return appid;
    }

    @Override
    public String getdownloadurl(int appid) {
        String url = "http://m5.qq.com/app/getappdetail.htm?pkgName="+appInfo.getPackagename()+"&sceneId=0";

        HttpRequest httpRequest = new NormalHttpRequest(url);
        String result = httpRequest.req();
        String apkUrl = "";
        if(!StringUtil.isEmpty(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject obj = jsonObject.getJSONObject("obj");

            JSONObject appInfoObject = obj.getJSONObject("appInfo");

            apkUrl = appInfoObject.getString("apkUrl");
        }

        return apkUrl;
    }

    @Override
    public void download(String downloadurl) {
        HttpRequest httpRequest = new NormalHttpRequest(downloadurl);
        httpRequest.download(appInfo);
    }

    private String dosearch(String url) {
        HttpRequest httpRequest = new NormalHttpRequest(url);
        String result = httpRequest.req();
        return result;
    }

}
