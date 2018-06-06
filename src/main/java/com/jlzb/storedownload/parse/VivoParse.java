package com.jlzb.storedownload.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.http.VivoHttpRequest;

public class VivoParse implements Parse {

    private Device device;

    private AppInfo appInfo;

    public VivoParse(Device device, AppInfo appInfo) {
        this.device = device;
        this.appInfo = appInfo;
    }

    @Override
    public int parsesearch() {
        int appid = 0;
        String url = "http://search.appstore.vivo.com.cn/port/packages/?nt=WIFI&model=" + device.getModel() + "&cfrom=2&density=2.0&screensize=" + device.getWidth() + "_" + device.getHeight() + "&imei=" + device.getImei() + "&build_number=4.4.049.P0.8297-T01&app_version=1063&av=19&apps_per_page=20&cs=0&sshow=110&id=0&u=90014a483847316505076c91bc695200&pictype=webp&elapsedtime=1440738&an=4.4.4&target=local&key=" + appInfo.getKeyword() + "&s=2%7C1111905542";
        int index = 1;
        while (index < 4) {
            String _url = url + "&page_index=" + index;
            String result = dosearch(_url);
            if (result != null) {
                JSONObject jsonObject = JSONObject.parseObject(result);

                JSONArray focusArray = jsonObject.getJSONArray("focus");
                break1:
                for (int i = 0; i < focusArray.size(); i++) {
                    JSONObject tempObject1 = focusArray.getJSONObject(i);


                    JSONArray appsArray = tempObject1.getJSONArray("apps");
                    for (int j = 0; j < appsArray.size(); j++) {
                        JSONObject tempObject2 = appsArray.getJSONObject(i);
                        String packagename = tempObject2.getString("package_name");
                        if (packagename != null && packagename.equals(appInfo.getPackagename())) {
                            appid = tempObject2.getInteger("id");
                            break break1;
                        }
                    }
                }

                JSONArray valueArray = jsonObject.getJSONArray("value");
                break2:
                for (int k = 0; k < valueArray.size(); k++) {
                    JSONObject tempObject1 = valueArray.getJSONObject(k);
                    String packagename = tempObject1.getString("package_name");
                    if (packagename != null && packagename.equals(appInfo.getPackagename())) {
                        appid = tempObject1.getInteger("id");
                        break break2;
                    }
                }

            }

            if (appid == 0) {
                index++;
            } else {
                break;
            }
        }

        return appid;
    }

    @Override
    public String getdownloadurl(int appid) {
        String url = "http://info.appstore.vivo.com.cn/port/package/?screensize=" + device.getWidth() + "_" + device.getHeight() + "&app_version=1011&density=2.0&nt=WIFI&need_comment=0&elapsedtime=688297156&cp=280&an=8.0.0target=local&cs=0&module_id=8&pictype=webp&u=1234567890&av=26&page_index=1&imei=" + device.getImei() + "&model=" + device.getModel() + "&content_complete=1&id=" + appid;

        HttpRequest httpRequest = new VivoHttpRequest(url);
        String result = httpRequest.req();

        if (result != null) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject valueObject = jsonObject.getJSONObject("value");
            String download_url = valueObject.getString("download_url");
            return download_url;
        }

        return null;
    }

    @Override
    public void download(String downloadurl) {
        HttpRequest httpRequest = new VivoHttpRequest(downloadurl);
        httpRequest.download(appInfo);
    }

    private String dosearch(String url) {
        HttpRequest httpRequest = new VivoHttpRequest(url);
        String result = httpRequest.req();
        return result;
    }

}
