package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class BaiduHttpRequest extends HttpRequest {

    private Device device;
    private URLConnection connection;

    public BaiduHttpRequest(String url, Device device) {
        super.setUrl(url);
        this.device = device;
        try {
            this.connection = createConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String req() {
        BufferedReader bufferedReader = null;
        InputStream urlStream = null;
        String result = "";
        try {
            urlStream = new GZIPInputStream(connection.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(urlStream,"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if(urlStream != null)
                try {
                    urlStream.close();
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
        File file = null;
        try {
            file = new File(appInfo.toString() + ".temp.apk");
            if (!file.exists())
                file.createNewFile();

            urlStream = connection.getInputStream();
            fileOutputStream = new FileOutputStream(file);

            int length = 0;
            byte[] b = new byte[1024];

            while ((length = urlStream.read(b)) > 0) {
                fileOutputStream.write(b, 0, length);
            }
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

            if(file != null && file.exists())
                file.delete();
        }

    }

    private URLConnection createConnection() throws IOException {
        URL realUrl = new URL(super.getUrl());
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();

        //connection.setRequestProperty("sign", "XGBWAEOl5peoqBhbz04RIw==");
        connection.setRequestProperty("User-Agent", "Dalvik/1.6.0 (Linux; U; "+this.device.getAndroidcode()+"; "+this.device.getModel()+" Build/KTU84P)");
        //connection.setRequestProperty("Host", "appc.baidu.com");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Accept-Encoding", "gzip");

        // 建立实际的连接
        connection.connect();
        return connection;
    }


}
