package com.jlzb.storedownload.utils;

import java.util.UUID;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if(str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
