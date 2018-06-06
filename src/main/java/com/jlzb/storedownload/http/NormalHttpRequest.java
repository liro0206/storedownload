package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

public class NormalHttpRequest extends HttpRequest {

    public NormalHttpRequest(String url) {
        super.setUrl(url);
    }

    @Override
    public String req() {
        HttpClient httpClient = null;
        String result = "";
        try {
            httpClient = new DefaultHttpClient();
            URI uri = new URI(super.getUrl());
            HttpGet httpget = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpget);

            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpClient != null)
                httpClient.getConnectionManager().shutdown();
        }

        return result;
    }

    @Override
    public void download(AppInfo appInfo) {
        HttpClient httpClient = null;
        try {
            File f = new File(appInfo.toString() + ".temp.apk");
            if (!f.exists())
                f.createNewFile();

            httpClient = new DefaultHttpClient();

            URI uri = new URI(super.getUrl());
            HttpGet httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet);

            response.getEntity().writeTo(new FileOutputStream(f));

            //删除临时下载文件
            f.delete();

            //每次下载完休息2秒
            //Thread.sleep(RuleManage.rule.getSleeptime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpClient != null)
                httpClient.getConnectionManager().shutdown();
        }
    }

}
