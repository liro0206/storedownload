package com.jlzb.storedownload.Bean;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RuleManage {

    public Rule rule = null;

    /**
     * 读取配置文件
     */
    public void readRule(){
        rule = new Rule();

        File rulefile = new File("runrule.xml");

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(rulefile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        Element configs = root.element("configs");
        List<Element> config = configs.elements("config");

        List<AppInfo> apps = new ArrayList<>();
        AppInfo app = null;
        for (int i = 0; i < config.size(); i++) {
            app = new AppInfo();

            app.setId(i);
            app.setStore(config.get(i).element("store").getText());
            app.setPackagename(config.get(i).element("packagename").getText());
            app.setKeyword(config.get(i).element("keyword").getText());
            app.setRuncount(Integer.parseInt(config.get(i).element("runcount").getText()));

            apps.add(app);
        }

        rule.setApps(apps);

        Element sleeptime = root.element("sleeptime");
        rule.setSleeptime(Long.parseLong(sleeptime.getText()));

        Element ischangeip = root.element("ischangeip");
        rule.setAdslpass(ischangeip.element("adslpass").getText());
        rule.setAdsluser(ischangeip.element("adsluser").getText());
        rule.setConnectname(ischangeip.element("connectname").getText());
        rule.setAdslswitch(Integer.parseInt(ischangeip.element("adslswitch").getText()));
        rule.setIntervaltime(Long.parseLong(ischangeip.element("intervaltime").getText()));
    }

}
