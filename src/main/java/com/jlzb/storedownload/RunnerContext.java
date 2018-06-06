package com.jlzb.storedownload;

import com.jlzb.storedownload.Bean.RuleManage;
import com.jlzb.storedownload.Bean.StoreName;
import com.jlzb.storedownload.device.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 整个项目上下文，拥有所有管理类
 */
public class RunnerContext {

    /*手机机型管理类*/
    public static DeviceManage oppoManage, xiaomiManage, vivoManage, huaweiManage, normalManage;

    /*运行规则管理类*/
    public static RuleManage ruleManage;

    /*用于记录运行数量*/
    public static Map<String, Integer> indexMap = new HashMap<>();


    //用于记录同一批次执行完成的线程数量
    public static Map<String, Integer> uuid_count_map;

    //用于记录同一批次运行的线程数量
    public static int threadcount = 0;

    //通过软件市场得到相应手机机型管理类
    public static DeviceManage getManage(String storename) {
        if("oppo".equals(storename)) {
            return oppoManage;
        } else if("vivo".equals(storename)) {
            return vivoManage;
        } else if("huawei".equals(storename)) {
            return huaweiManage;
        } else if("xiaomi".equals(storename)) {
            return xiaomiManage;
        } else if("baidu".equals(storename)) {
            return normalManage;
        } else if("360".equals(storename)) {
            return normalManage;
        } else if("yingyongbao".equals(storename)) {
            return normalManage;
        }

        return null;
    }

    /**
     * 初始化方法
     */
    public static void init() {
        oppoManage = new DeviceOppoManage();
        xiaomiManage = new DeviceXiaomiManage();
        vivoManage = new DeviceVivoManage();
        huaweiManage = new DeviceHuaweiManage();
        normalManage = new DeviceNormalManage();

        ruleManage = new RuleManage();

        loadConfig();
    }

    /**
     * 加载所有资源文件
     */
    private static void loadConfig() {
        oppoManage.load();
        xiaomiManage.load();
        vivoManage.load();
        huaweiManage.load();
        normalManage.load();

        ruleManage.readRule();
    }

}
