package com.jlzb.storedownload.Bean;

import java.util.List;

public class Rule {
    private String adsluser;

    private String adslpass;

    private int adslswitch;

    private String connectname;

    private long sleeptime;

    public long getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(long sleeptime) {
        this.sleeptime = sleeptime;
    }

    public long getIntervaltime() {
        return intervaltime;
    }

    public void setIntervaltime(long intervaltime) {
        this.intervaltime = intervaltime;
    }

    //自动断线时间间隔
    private long intervaltime;

    private List<AppInfo> apps;

    public List<AppInfo> getApps() {
        return apps;
    }

    public void setApps(List<AppInfo> apps) {
        this.apps = apps;
    }

    public int getAdslswitch() {
        return adslswitch;
    }

    public void setAdslswitch(int adslswitch) {
        this.adslswitch = adslswitch;
    }

    public String getConnectname() {
        return connectname;
    }

    public void setConnectname(String connectname) {
        this.connectname = connectname;
    }

    public String getAdsluser() {
        return adsluser;
    }

    public void setAdsluser(String adsluser) {
        this.adsluser = adsluser;
    }

    public String getAdslpass() {
        return adslpass;
    }

    public void setAdslpass(String adslpass) {
        this.adslpass = adslpass;
    }

}
