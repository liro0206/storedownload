package com.jlzb.storedownload.device;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class DeviceManage {


    /**
     * 解析文件
     * @param phonefile
     * @param list
     */
    public void parse(File phonefile, List<Device> list) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(phonefile));
            String s = null;
            while ((s = br.readLine()) != null) {
                String line = s.trim();
                String[] split = line.split(",");

                Device user = new Device(split[0], split[1], split[2], split[3], split[4]);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 加载机型配置文件
     */
    public abstract void load();

    /**
     * 随机获取一个机型
     * @return
     */
    public abstract Device random();

}
