package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;
import java.io.*;
import java.net.*;
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
        BufferedReader reader = null;
        String result = "";
        try {
            InputStream urlStream = new GZIPInputStream(connection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(urlStream, "utf-8"));
            String line;

            while ((line = reader.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return result;
    }

    @Override
    public void download(AppInfo appInfo) {
        InputStream urlStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(appInfo.toString() + ".temp.apk");
            if (!file.exists())
                file.createNewFile();

            urlStream = connection.getInputStream();
            fileOutputStream = new FileOutputStream(file);

            int length = 0;
            byte[] b = new byte[1024];

            while ((length = urlStream.read(b)) > 0) {
                fileOutputStream.write(b, 0, length);
            }

            //删除临时下载文件
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlStream != null)
                try {
                    urlStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if(fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
