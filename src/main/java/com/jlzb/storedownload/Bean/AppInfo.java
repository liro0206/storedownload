package com.jlzb.storedownload.Bean;

/**
 * 运行软件信息
 */
public class AppInfo {

    private int id;

    private String store;

    private int runcount;

    private String keyword;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getRuncount() {
        return runcount;
    }

    public void setRuncount(int runcount) {
        this.runcount = runcount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    private String packagename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[" + packagename + "]," + "[" + keyword + "]," + "[" + store + "]";
    }
}
