package com.jlzb.storedownload.utils;

import com.jlzb.storedownload.RunnerContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectNetWork {

    //记录上一次运行的IP
    private static String ip = null;

    //更新IP失败次数
    private static int changeipfail = 0;

    /**
     * 执行CMD命令,并返回String字符串
     */
    private static String executeCmd(String strCmd) throws IOException {
        Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);
        StringBuilder sbCmd = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(p
                .getInputStream(),  "GBK"));
        String line;
        while ((line = br.readLine()) != null) {
            sbCmd.append(line + "\n");
        }

        br.close();
        return sbCmd.toString();
    }

    /**
     * 连接ADSL
     */
    private static boolean connAdsl(String adslTitle, String adslName, String adslPass) {
        try {
            String adslCmd = "rasdial " + adslTitle + " " + adslName + " "
                    + adslPass;
            String tempCmd = executeCmd(adslCmd);
            // 判断是否连接成功
            if (tempCmd.indexOf("已连接") > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 断开ADSL
     */
    private static boolean cutAdsl(String adslTitle) {
        try {
            String cutAdsl = "rasdial " + adslTitle + " /disconnect";
            String result = executeCmd(cutAdsl);

            if (result.indexOf("没有连接") != -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 进行adsl拨号
     */
    public static void connAdsl() {
        //判断是否更换IP
        if (RunnerContext.ruleManage.rule.getAdslswitch() == 1) {
            //进行拨号
            if (!ConnectNetWork.connAdsl(RunnerContext.ruleManage.rule.getConnectname(), RunnerContext.ruleManage.rule.getAdsluser(), RunnerContext.ruleManage.rule.getAdslpass())) {
                System.err.println("========>第一次尝试拨号连接失败<========");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(!ConnectNetWork.connAdsl(RunnerContext.ruleManage.rule.getConnectname(), RunnerContext.ruleManage.rule.getAdsluser(), RunnerContext.ruleManage.rule.getAdslpass())) {
                    System.err.println("========>第二次尝试拨号连接失败<========");
                } else {
                    System.out.println("========>第二次尝试拨号连接成功<========");
                }
            } else {
                System.out.println("========>第一次尝试拨号连接成功<========");
            }



            if(ip != null) {
                String temp_ip = GetIp.getIp();
                if(!temp_ip.equals(ip)) {
                    System.out.println("========>IP更换成功<========");
                } else {
                    System.err.println("========>IP更换失败<========");

                    //重新进行一次adsl拨号
                    if(changeipfail <3) {
                        changeipfail++;
                        connAdsl();
                    } else {
                        changeipfail = 0;
                    }
                }
            }
        }
    }

    /**
     * 断开adsl连接
     */
    public static void cutAdsl() {
        //判断是否更换IP
        if (RunnerContext.ruleManage.rule.getAdslswitch() == 1) {
            //断开连接之前记录上次执行IP
            ip = GetIp.getIp();
            if (!ConnectNetWork.cutAdsl(RunnerContext.ruleManage.rule.getConnectname())) {
                System.err.println("========>第一次尝试断开拨号连接失败<========");

                try {
                    System.err.println("========>" + (RunnerContext.ruleManage.rule.getIntervaltime() / 1000) + "秒后尝试第二次断开拨号连接<========");
                    Thread.sleep(RunnerContext.ruleManage.rule.getIntervaltime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!ConnectNetWork.cutAdsl(RunnerContext.ruleManage.rule.getConnectname())) {
                    System.err.println("========>第二次尝试断开拨号连接失败<========");
                } else {
                    System.out.println("========>第二次尝试断开拨号连接成功<========");
                }
            } else {
                System.out.println("========>第一次尝试断开拨号连接成功<========");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        connAdsl("宽带连接", "057122073553", "423185");
        /*Thread.sleep(1000);
        cutAdsl("宽带连接");
        Thread.sleep(1000);
        //再连，分配一个新的IP
        connAdsl("宽带连接", "057122073553", "423185");*/
    }
}
