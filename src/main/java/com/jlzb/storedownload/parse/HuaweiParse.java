package com.jlzb.storedownload.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.http.NormalHttpRequest;
import com.jlzb.storedownload.utils.StringUtil;

import java.net.URLEncoder;

public class HuaweiParse implements Parse{

    private Device device;

    private AppInfo appInfo;

    public HuaweiParse(Device device, AppInfo appInfo) {
        this.device = device;
        this.appInfo = appInfo;
    }

    @Override
    public int parsesearch() {
        String url = "http://a.vmall.com/uowap/index?method=internal.getTabDetail&maxResults=25&serviceType=13&uri=searchApp" + URLEncoder.encode("|") + URLEncoder.encode(appInfo.getKeyword());
        int index = 1;
        int appid = 0;
        break1: while (index < 4) {
            String _url = url + "&reqPageNum=" + index;
            String result = dosearch(_url);
            if (!StringUtil.isEmpty(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);

                JSONArray layoutData = jsonObject.getJSONArray("layoutData");

                for (int i = 0; i < layoutData.size(); i++) {
                    JSONObject tempObject = layoutData.getJSONObject(i);
                    JSONArray dataList = tempObject.getJSONArray("dataList");

                    for (int j = 0; j < dataList.size(); j++) {
                        JSONObject tempObject1 = dataList.getJSONObject(j);
                        if(appInfo.getPackagename().equals(tempObject1.getString("package"))) {
                            appid = Integer.parseInt(tempObject1.getString("appid").replace("C", ""));
                            break break1;
                        }
                    }
                }
            }
            index++;
        }


        return appid;
    }

    @Override
    public String getdownloadurl(int appid) {
        String url="http://a.vmall.com/uowap/index?method=internal.getTabDetail&serviceType=13&reqPageNum=1&uri=app"+URLEncoder.encode("|")+"C"+appid+"&maxResults=10";
        HttpRequest httpRequest = new NormalHttpRequest(url);
        String result = httpRequest.req();
        String downloadurl = "";
        if(!StringUtil.isEmpty(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray array1 = jsonObject.getJSONArray("layoutData");
            break1 : for (int i = 0; i < array1.size(); i++) {
                JSONObject tempObject = array1.getJSONObject(i);
                if(tempObject.getString("layoutName").equals("detailhiddencard")) {
                    JSONArray array2 = tempObject.getJSONArray("dataList");

                    for (int j = 0; j < array2.size(); j++) {
                        JSONObject tempObject2 = array2.getJSONObject(j);
                        if(appInfo.getPackagename().equals(tempObject2.getString("package"))) {
                            downloadurl = tempObject2.getString("downurl");
                            break break1;
                        }
                    }

                }
            }
        }

        return downloadurl;
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
