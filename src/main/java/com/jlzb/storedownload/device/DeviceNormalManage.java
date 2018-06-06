package com.jlzb.storedownload.device;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeviceNormalManage extends DeviceManage {

    private List<Device> list = new ArrayList<>();

    //用于记录当前运行到数量
    public static int index;

    public void load() {
        File file = new File("normalphone.txt");
        super.parse(file, list);

        System.out.println("=====>共加载普通机型" + list.size() + "<========");
    }

    @Override
    public Device random() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
