package com.jlzb.storedownload.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.http.BaiduHttpRequest;
import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BaiduParse implements Parse{

    private Device device;

    private AppInfo appInfo;

    public BaiduParse(Device device, AppInfo appInfo) {
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
        String url = "http://appc.baidu.com/as?uid=YO2fi_il2ul3u287gO-oilicB8gO8v8fgiHh8laLHiqUuSiTga2E8_aa28_4av8V3dlqC&tn=native&abi=armeabi-v7a&from=1009306c&ver=16794028&is_support_webp=true&cct=qivtkjihetggRHi86iS3kliheug_MHf3odfqA&network=WF&cen=cuid_cut_cua_uid&platform_version_id=19&province=qivtkjihetggRHi86iS3kliheug_MHf3odfqA&st=10a001&apn=&native_api=1&psize=3&usertype=2&cll=ga24N_uN2tjpRvaEgPslfgadv8LqA&operator=460018&country=CN&pkname=com.baidu.appsearch&gms=false&pu=cua%40_avLC_aE-i4qywoUfpw1zyPLXio5uL8bxLqqC%2Cosname%40baiduappsearch%2Cctv%401%2Ccfrom%401000561u%2Ccuid%40YO2fi_il2ul3u287gO-oilicB8gO8v8fgiHh8laLHi67uviJlav8i_aL28_Aa285ga2VtqqHB%2Ccut%40lpw2qfy3AIYQuB8lz9v6iya8XizJuL8DjNL1j5J4mzqcC%2Ccsrc%40app_box_txt&word="+keyword+"&language=zh&disp=4.4.049.P0.8297-T01&&crid=1510718613999&native_api=1&f=search&bannert=26%4027%4028%4029%4030%4032%4043&rqt=rty&ptl=hp";

        int index = 1;
        int appid = 0;
        break1: while (index < 3) {
            String _url = url + "&pn=" + index;
            String result = dosearch(_url);
            if (!StringUtil.isEmpty(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject resultObject = jsonObject.getJSONObject("result");

                JSONArray dataArray = resultObject.getJSONArray("data");

                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject tempObject1 = dataArray.getJSONObject(i);
                    JSONObject itemdataObject = tempObject1.getJSONObject("itemdata");

                    if(appInfo.getPackagename().equals(itemdataObject.getString("package"))) {
                        appid = Integer.parseInt(itemdataObject.getString("docid"));
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
        String url="http://m.baidu.com/appsrv?country=CN&ver=16785288&cll=gPslfgadv9gzivij_h228_ODvfwqC&pc_channel=&pu=cuid%40_8BYig89H8lGaSt3laB1a0aeBaYa82iOjuvAi0ai286uuviJ0OvRi_OSBf_luvi9ga2Vttw6B%2Ccut%400t3_N0tN2iyqPXiDzuD58gIVQMlykSO6A%2Cctv%401%2Ccua%40_a-qi4uq-igBNE6lI5me6NIy2I_UhvCrSdNqA%2Cosname%40baiduappsearch%2Ccfrom%401000561u&platform_version_id=26&abi=armeabi-v7a&usertype=0&language=zh&gms=true&cen=cuid_cut_cua_uid&operator=460077&network=WF&pkname=com.baidu.appsearch&is_support_webp=true&uid=_8BYig89H8lGaSt3laB1a0aeBaYa82iOjuvAi0ai28qRuHf3gu2VfjOQ2iggaviJ3dFqC&firstdoc=&psize=3&cct=qivtkjihetggRHi86iS3kliheug_MHf3odfqA&native_api=1&action=detail&from=1000561u&apn=&&native_api=1&docid="+appid+"&f=homepage%40oneclickinstallold%401%402%40source%2BNATURAL%40boardid%2B20149%40pos%2B2%40searchid%2B223986765735699215%40terminal_type%2Bclient%40spec%2B0";

        HttpRequest httpRequest = new BaiduHttpRequest(url, device);

        String req = httpRequest.req();

        JSONObject jsonObject = JSONObject.parseObject(req);
        JSONObject resultObject = jsonObject.getJSONObject("result");
        JSONObject dataObject = resultObject.getJSONObject("data");

        String downloadurl = dataObject.getString("download_inner");

        return downloadurl;
    }

    @Override
    public void download(String downloadurl) {
        HttpRequest httpRequest = new BaiduHttpRequest(downloadurl.replace("\\", ""), device);
        httpRequest.download(appInfo);
    }

    private String dosearch(String url) {
        HttpRequest httpRequest = new BaiduHttpRequest(url, device);
        String result = httpRequest.req();
        return result;
    }

}
