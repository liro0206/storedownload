package com.jlzb.storedownload.parse;

import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;

/**
 * 解析类
 */
public interface Parse {

    public int parsesearch();

    public String getdownloadurl(int appid);

    public void download(String downloadurl);

}
