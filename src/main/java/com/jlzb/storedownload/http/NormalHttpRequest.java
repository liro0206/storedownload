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
import java.io.IOException;
import java.io.InputStream;
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
        FileOutputStream fileOutputStream = null;
        File f = null;
        try {
            f = new File(appInfo.toString() + ".temp.apk");
            if (!f.exists())
                f.createNewFile();

            httpClient = new DefaultHttpClient();

            URI uri = new URI(super.getUrl());
            HttpGet httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet);

            fileOutputStream = new FileOutputStream(f);
            response.getEntity().writeTo(fileOutputStream);


           /* byte[] bytes = new byte[1024];
            int lenght = 0;

            while((lenght = inputStream.read()) > 0) {
                fileOutputStream.write(bytes, 0 , lenght);
            }*/



            //每次下载完休息2秒
            //Thread.sleep(RuleManage.rule.getSleeptime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null)
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if(httpClient != null)
                httpClient.getConnectionManager().shutdown();

            //删除临时文件
            if(f != null && f.exists())
                f.delete();

        }
    }

}
