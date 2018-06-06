package com.jlzb.storedownload.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.Bean.RuleManage;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.http.OppoHttpRequest;
import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.utils.StringUtil;

import java.io.UnsupportedEncodingException;

public class OppoParse implements Parse{

    private Device device;

    private AppInfo appInfo;

    public OppoParse(Device device, AppInfo appInfo) {
        this.device = device;
        this.appInfo = appInfo;
    }

    /**
     * 解析通过关键词搜索，找到对应包名的软件appid
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public int parsesearch() {
        String url="https://istore.oppomobile.com/search/v1/search?size=30&start=0&keyword="+ appInfo.getKeyword();
        HttpRequest httpGetter = new OppoHttpRequest(url, device);
        String req = httpGetter.req();

        int appid = 0;

        if(!StringUtil.isEmpty(req)) {
            JSONObject jsonObject = JSON.parseObject(req);
            JSONArray cards = jsonObject.getJSONArray("cards");

            if(cards.size() == 1) {
                JSONObject jsonObject0 = cards.getJSONObject(0);
                JSONArray apps =  jsonObject0.getJSONArray("apps");

                if(apps != null && apps.size() > 0) {
                    JSONObject temp = null;
                    for (int i = 0; i < apps.size(); i++) {
                        temp = apps.getJSONObject(i);
                        if(appInfo.getPackagename().equals(temp.getString("pkgName"))) {
                            appid = temp.getInteger("appId");
                        }
                    }
                }
            } else if(cards.size() == 2) {
                JSONObject jsonObject0 = cards.getJSONObject(0);
                JSONObject app = jsonObject0.getJSONObject("app");
                if(app != null) {
                    if(appInfo.getPackagename().equals(app.getString("pkgName")))
                        appid = app.getInteger("appId");
                }


                if(appid == 0) {
                    jsonObject0 = cards.getJSONObject(1);
                    JSONArray apps =  jsonObject0.getJSONArray("apps");

                    if(apps != null && apps.size() > 0) {
                        JSONObject temp = null;
                        for (int i = 0; i < apps.size(); i++) {
                            temp = apps.getJSONObject(i);
                            if(appInfo.getPackagename().equals(temp.getString("pkgName"))) {
                                appid = temp.getInteger("appId");
                            }
                        }
                    }
                }
            }
        }

        return appid;
    }

    /**
     * 通过详细信息页面得到下载地址
     * @param appid
     * @return
     */
    @Override
    public String getdownloadurl(int appid) {
        String url = "https://istore.oppomobile.com/detail/v1/"+appid+"?source=1";

        HttpRequest httpGetter = new OppoHttpRequest(url, device);
        String req = httpGetter.req();

        String downloadurl = null;

        if(!StringUtil.isEmpty(req)) {
           JSONObject jsonObject = JSONObject.parseObject(req);
           downloadurl = jsonObject.getString("url");
        }

        return downloadurl;
    }

    /**
     * 下载APK
     * @param downloadurl
     */
    @Override
    public void download(String downloadurl) {
        downloadurl += "?ref=1007&type=1";

        HttpRequest httpGetter = new OppoHttpRequest(downloadurl, device);
        httpGetter.download(appInfo);
    }

}
