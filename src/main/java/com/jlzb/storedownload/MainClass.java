package com.jlzb.storedownload;

import com.jlzb.storedownload.utils.ConnectNetWork;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("=======running======");
        //初始化应用上下文
        RunnerContext.init();

        Runner runner = new Runner();
        runner.start();
    }

}
