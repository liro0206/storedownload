package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class VivoHttpRequest extends HttpRequest {

    private URLConnection connection;

    public VivoHttpRequest(String url) {
        super.setUrl(url);
        try {
            connection = createconnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String req() {
        try {
            String result = "";
            InputStream urlStream = new GZIPInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream, "utf-8"));
            String line;

            while ((line = reader.readLine()) != null) {
                result += line;
            }

            reader.close();

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    @Override
    public void download(AppInfo appInfo) {
        try {
            File file = new File(appInfo.toString() + ".temp.apk");
            if (!file.exists())
                file.createNewFile();

            InputStream urlStream = connection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int length = 0;
            byte[] b = new byte[1024];

            while ((length = urlStream.read(b)) > 0) {
                fileOutputStream.write(b, 0, length);
            }

            urlStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private URLConnection createconnection() throws IOException {
        URL realUrl = new URL(super.getUrl());
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();

        connection.setRequestProperty("accept-encoding", "gzip");
        connection.setRequestProperty("Host", "pl.appstore.vivo.com.cn");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6");

        // 建立实际的连接
        connection.connect();

        return connection;
    }
}
