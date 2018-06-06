package com.jlzb.storedownload.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.http.NormalHttpRequest;
import com.jlzb.storedownload.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class XiaomiParse implements Parse {

    private Device device;

    private AppInfo appInfo;

    public XiaomiParse(Device device, AppInfo appInfo) {
        this.device = device;
        this.appInfo = appInfo;
    }

    @Override
    public int parsesearch() {
        String keyword = "";
        try {
            keyword = URLEncoder.encode(appInfo.getKeyword(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://app.market.xiaomi.com/apm/search?channel=market_100_1_android&clientId=0726c9c46e155f4fba91b3d9a977b1f3&co=CN&densityScaleFactor=3.0&imei=" + device.getImei() + "&keyword=" + keyword + "&la=zh&marketVersion=147&model=" + device.getModel().replace(" ", "+") + "&os=eng.compil.20180515.183659&ref=input&resolution=" + device.getWidth() + "*" + device.getHeight() + "&sdk=25&session=2jmj7l5rSw0yVb_v";

        int index = 0;
        int appid = 0;
        break1:
        while (index < 3) {
            String _url = url + "&page=0" + index;
            String result = dosearch(_url);
            if (!StringUtil.isEmpty(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONArray listApp = jsonObject.getJSONArray("listApp");
                for (int i = 0; i < listApp.size(); i++) {
                    JSONObject tempObject = listApp.getJSONObject(i);
                    if (appInfo.getPackagename().equals(tempObject.getString("packageName"))) {
                        appid = tempObject.getInteger("appId");
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
        String param = "?channel=market_100_1_android&clientId=0726c9c46e155f4fba91b3d9a977b1f3&co=CN&densityScaleFactor=3.0&imei=" + device.getImei() + "&la=zh&marketVersion=147&model=" + device.getImei() + "&os=eng.compil.20180515.183659&ref=search&refPosition=17&resolution=" + device.getWidth() + "*" + device.getHeight() + "&sdk=25&session=2jmj7l5rSw0yVb_v";

        String url = "https://app.market.xiaomi.com/apm/app/id/" + appid + param;
        HttpRequest httpRequest = new NormalHttpRequest(url);
        httpRequest.req();

        String url1 = "https://app.market.xiaomi.com/apm/download/" + appid + param;
        httpRequest = new NormalHttpRequest(url1);
        String result = httpRequest.req();

        if (!StringUtil.isEmpty(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);

            String downloadurl = jsonObject.getString("host") + jsonObject.getString("apk");

            return downloadurl;
        }

        return null;
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
