package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

public class OppoHttpRequest extends HttpRequest {

    private Device device;

    public OppoHttpRequest(String url, Device device) {
        super.setUrl(url);
        this.device = device;
    }

    @Override
    public String req() {
        HttpClient httpClient = null;
        String result = "";
        try {
            httpClient = new DefaultHttpClient();
            HttpGet httpGet = createHttpGet();

            HttpResponse response = httpClient.execute(httpGet);

            String s = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

            result= URLDecoder.decode(s, HTTP.UTF_8);
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
            HttpGet httpGet = createHttpGet();
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

    /**
     * 创建sign
     *
     * @return
     */
    private String getSign(URI uri, StringBuilder localObject12, String imei) throws UnsupportedEncodingException {

        Object localObject = Locale.getDefault();
        String str1 = ((Locale) localObject).getLanguage() + "-" + ((Locale) localObject).getCountry();
        String time = System.currentTimeMillis() + "";//时间戳 long
//            								    Huawei%2F%EF%BB%BFHUAWEI+MT2-L01%2F22%2F5.1.1%2F0%2F2%2F2101%2F5203

//			connection.setRequestProperty("locale", str1);
        //sign localObject1是签名 ，以下是复杂的生成过程
        StringBuilder localObject5 = new StringBuilder("23a8ba872e430653").append("70f68c62df3ba8a45f1c1a57c91df63e").append(URLEncoder.encode(localObject12.toString(), "UTF-8")).append(time).append(imei);
        String localObject4 = uri.getPath();
        String localObject2 = uri.getQuery();

        StringBuilder localObject1 = ((StringBuilder) localObject5).append((String) localObject4).append((String) localObject2);
        ((StringBuilder) localObject1).append(((StringBuilder) localObject1).length());
        ((StringBuilder) localObject1).append("STORENEWMIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANYFY/UJGSzhIhpx6YM5KJ9yRHc7YeURxzb9tDvJvMfENHlnP3DtVkOIjERbpsSd76fjtZnMWY60TpGLGyrNkvuV40L15JQhHAo9yURpPQoI0eg3SLFmTEI/MUiPRCwfwYf2deqKKlsmMSysYYHX9JiGzQuWiYZaawxprSuiqDGvAgMBAAECgYEAtQ0QV00gGABISljNMy5aeDBBTSBWG2OjxJhxLRbndZM81OsMFysgC7dq+bUS6ke1YrDWgsoFhRxxTtx/2gDYciGp/c/h0Td5pGw7T9W6zo2xWI5oh1WyTnn0Xj17O9CmOk4fFDpJ6bapL+fyDy7gkEUChJ9+p66WSAlsfUhJ2TECQQD5sFWMGE2IiEuz4fIPaDrNSTHeFQQr/ZpZ7VzB2tcG7GyZRx5YORbZmX1jR7l3H4F98MgqCGs88w6FKnCpxDK3AkEA225CphAcfyiH0ShlZxEXBgIYt3V8nQuc/g2KJtiV6eeFkxmOMHbVTPGkARvt5VoPYEjwPTg43oqTDJVtlWagyQJBAOvEeJLno9aHNExvznyD4/pR4hec6qqLNgMyIYMfHCl6d3UodVvC1HO1/nMPl+4GvuRnxuoBtxj/PTe7AlUbYPMCQQDOkf4sVv58tqslO+I6JNyHy3F5RCELtuMUR6rG5x46FLqqwGQbO8ORq+m5IZHTV/Uhr4h6GXNwDQRh1EpVW0gBAkAp/v3tPI1riz6UuG0I6uf5er26yl5evPyPrjrD299L4Qy/1EIunayC7JYcSGlR01+EDYYgwUkec+QgrRC/NstV");

        return MD5Util.md5Hex(((StringBuilder) localObject1).toString());
    }

    /**
     * 创建请求
     *
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    private HttpGet createHttpGet() throws URISyntaxException, IOException {
        URI uri = new URI(super.getUrl());
        HttpGet httpget = new HttpGet(uri);


        String OSName = "5.1.1";
        String MobileRomVersion = "0";
        String AppCode = "2";
        String Channel = "2101";
        String AppVersionCode = "5203";
        String imei = device.getImei();
        StringBuilder localObject11 = new StringBuilder
                (device.getBrand()).append("/")
                .append(device.getModel()).append("/")
                .append(device.getVersion()).append("/")
                .append(OSName).append("/")
                .append(MobileRomVersion).append("/")
                .append(AppCode).append("/")
                .append(Channel).append("/")
                .append(AppVersionCode);
        StringBuilder localObject12 = new StringBuilder
                (device.getBrand()).append("/")
                .append(device.getModel()).append("/")
                .append(device.getVersion()).append("/")
                .append(OSName).append("/")
                .append(MobileRomVersion).append("/")
                .append(AppCode).append("/")
                .append(device.getModel()).append("/")
                .append(AppVersionCode);


        httpget.setHeader("User-Agent", URLEncoder.encode(localObject11.toString(), "UTF-8"));//由手机型号系统版本等参数加密生成
        httpget.setHeader("t", System.currentTimeMillis() + "");//时间戳
        httpget.setHeader("id", imei);//imei
        httpget.setHeader("ocs", URLEncoder.encode(localObject12.toString(), "UTF-8"));//由User-Agent和Channel，软件版本号生成
        httpget.setHeader("ch", Channel);//Channel字段，从app里的获取的
        httpget.setHeader("oak", "23a8ba872e430653");//写死的
        httpget.setHeader("sign", getSign(uri, localObject12, imei));

        return httpget;
    }

}
