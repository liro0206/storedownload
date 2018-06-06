package com.jlzb.storedownload.utils;

import com.jlzb.storedownload.http.HttpRequest;
import com.jlzb.storedownload.http.NormalHttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 获取adsl拨号后的IP
 */
public class GetIp {

    /**
     * 返回null没有获取到
     * @return
     */
    public static String get() {
        String url = "https://www.ipip.net/ip.html";
        HttpRequest httpRequest = new NormalHttpRequest(url);
        String req = httpRequest.req();
        if(!StringUtil.isEmpty(req)) {
            Document parse = Jsoup.parse(req);

            Elements select = parse.select("input[autocomplete=off]");
            return select.get(0).attr("value");
        } else
            return null;
    }


    /**
     * 获取IP
     * @return
     */
    public static String getIp() {
        String temp_ip = GetIp.get();
        if (temp_ip == null) {
            System.err.println("========>第一次尝试获取IP失败<========");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            temp_ip = GetIp.get();
            if (temp_ip == null) {
                System.err.println("========>第二次尝试获取IP失败<========");
            } else {
                System.out.println("========>第二次获取IP成功[" + temp_ip + "]<========");
            }
        } else {
            System.out.println("========>第一次获取IP成功[" + temp_ip + "]<========");
        }

        return temp_ip;
    }

}
