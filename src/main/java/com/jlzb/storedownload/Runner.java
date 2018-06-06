package com.jlzb.storedownload;

import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.thread.StoreThread;
import com.jlzb.storedownload.utils.ConnectNetWork;
import com.jlzb.storedownload.utils.GetIp;
import com.jlzb.storedownload.utils.NetState;
import com.jlzb.storedownload.utils.StringUtil;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 这是一个监听者类
 */
public class Runner {

    private Logger log = Logger.getLogger(Runner.class);

    private List<AppInfo> apps;

    /**
     * 1. 遍历所有软件
     * 2. 判断是否超过运行数量
     * 3. 记录运行信息
     * 4. 更换IP
     */
    public void start() {
        apps = RunnerContext.ruleManage.rule.getApps();

        if (apps == null || apps.size() == 0) {
            System.out.println("========>所有任务执行完成<========");
            log.info("========>所有任务执行完成<========");
        } else {
            //进行adsl拨号
            if(!NetState.isConnect()) {
                ConnectNetWork.connAdsl();

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            RunnerContext.uuid_count_map = new HashMap<>();

            //生成唯一标识，标记所创建的线程是同一批次的
            String uuid = StringUtil.getUUID();

            //默认0
            RunnerContext.uuid_count_map.put(uuid, 0);

            //设置启动线程数量
            RunnerContext.threadcount = apps.size();

            AppInfo app = null;
            for (int i = 0; i < RunnerContext.threadcount; i++) {
                app = apps.get(i);

                //启动软件解析下载线程
                new Thread(new StoreThread(uuid, app)).start();
            }
        }
    }



}
