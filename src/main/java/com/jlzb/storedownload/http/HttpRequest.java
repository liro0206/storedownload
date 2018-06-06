package com.jlzb.storedownload.http;

import com.jlzb.storedownload.Bean.AppInfo;

import java.util.Map;

public abstract class HttpRequest {

    private String url;

    private Map<String, String> parameters;

    private Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public abstract String req();

    //public abstract String gzipreq();

    public abstract void download(AppInfo appInfo);

}
