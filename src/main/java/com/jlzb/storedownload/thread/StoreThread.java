package com.jlzb.storedownload.thread;

import com.jlzb.storedownload.Bean.AppInfo;
import com.jlzb.storedownload.Runner;
import com.jlzb.storedownload.RunnerContext;
import com.jlzb.storedownload.device.Device;
import com.jlzb.storedownload.device.DeviceManage;
import com.jlzb.storedownload.observer.RunnerObserver;
import com.jlzb.storedownload.parse.OppoParse;
import com.jlzb.storedownload.parse.Parse;
import com.jlzb.storedownload.parse.ParseFactory;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Observable;

public class StoreThread extends Observable implements Runnable {

    //随机数 用于标记这是同一批次的线程
    private String randomStr;

    //需要运行的软件信息
    private AppInfo appInfo;

    private Logger log = Logger.getLogger(StoreThread.class);

    /**
     * 在此处注册观察者
     */
    public StoreThread(String randomStr, AppInfo appInfo) {
        this.randomStr = randomStr;
        this.appInfo = appInfo;

        //注册监听者
        addObserver(new RunnerObserver());
    }

    @Override
    public void run() {
        DeviceManage deviceManage = RunnerContext.getManage(appInfo.getStore());

        Integer runcount = RunnerContext.indexMap.get("runcount:" + appInfo.getId());
        if(runcount == null)
            runcount=0;

        if (runcount < appInfo.getRuncount()) {//NULL第一次运行 或者 小于指定运行总数
            Device device = deviceManage.random();
            Parse parse = ParseFactory.get(appInfo.getStore(), device, appInfo);
            try {
                int appid = parse.parsesearch();
                if (appid != 0) {
                    String getdownloadurl = parse.getdownloadurl(appid);

                    if (getdownloadurl != null) {
                        parse.download(getdownloadurl);
                        log.info(appInfo.toString()+"======>"+(runcount+1)+"run succ");
                        System.out.println(appInfo.toString()+"======>"+(runcount+1)+"run succ");
                    } else {
                        log.error(appInfo.toString()+"===error2===>"+(runcount+1)+"run fail");
                        System.err.println(appInfo.toString()+"===error2===>"+(runcount+1)+"run fail");
                    }

                } else {
                    System.err.println(appInfo.toString()+"===error1===>"+(runcount+1)+"run fail");
                    log.error(appInfo.toString()+"===error1===>"+(runcount+1)+"run fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //记录运行次数
            RunnerContext.indexMap.put("runcount:" + appInfo.getId(), runcount + 1);
        } else {
            //将不需要再据需执行的app删除
            RunnerContext.ruleManage.rule.getApps().remove(appInfo);
        }


        //线程运行结束通知runner
        setChanged();
        notifyObservers(randomStr);
    }
}