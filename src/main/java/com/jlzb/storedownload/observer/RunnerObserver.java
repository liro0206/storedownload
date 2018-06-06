package com.jlzb.storedownload.observer;

import com.jlzb.storedownload.Runner;
import com.jlzb.storedownload.RunnerContext;
import com.jlzb.storedownload.utils.ConnectNetWork;

import java.util.Observable;
import java.util.Observer;

/**
 * 用于监听线程运行情况
 */
public class RunnerObserver implements Observer {

    /**
     * 记录同一批次的所有线程都运行完，开始下一次运行
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Integer count = RunnerContext.uuid_count_map.get((String) arg);
        if (count + 1 == RunnerContext.threadcount) {//所有线程都执行完成，进入下一次
            //运行完成断开网络
            ConnectNetWork.cutAdsl();

            Runner runner = new Runner();
            runner.start();
        } else {
            RunnerContext.uuid_count_map.put((String) arg, count + 1);
        }
    }



}
