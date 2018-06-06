package com.jlzb.storedownload.parse;

import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.device.Device;

public class ParseFactory {

    public static Parse get(String storename, Device device, AppInfo appInfo) {
        if("oppo".equals(storename)) {
            return new OppoParse(device, appInfo);
        } else if("vivo".equals(storename)) {
            return new VivoParse(device, appInfo);
        } else if("huawei".equals(storename)) {
            return new HuaweiParse(device, appInfo);
        } else if("xiaomi".equals(storename)) {
            return new XiaomiParse(device, appInfo);
        } /*else if("baidu".equals(storename)) {
            return normalManage;
        } else if("360".equals(storename)) {
            return normalManage;
        } else if("yingyongbao".equals(storename)) {
            return normalManage;
        }*/

        return null;
    }

}
