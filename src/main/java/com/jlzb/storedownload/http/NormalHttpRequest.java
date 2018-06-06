package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

public class NormalHttpRequest extends HttpRequest {

    public NormalHttpRequest(String url) {
        super.setUrl(url);
    }

    @Override
    public String req() {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            URI uri = new URI(super.getUrl());
            HttpGet httpget = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpget);

            String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

            httpClient.getConnectionManager().shutdown();

            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void download(AppInfo appInfo) {

    }

}
