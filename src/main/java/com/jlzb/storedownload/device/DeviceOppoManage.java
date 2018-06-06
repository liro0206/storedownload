package com.jlzb.storedownload.device;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 加载OPPO手机机型
 */
public class DeviceOppoManage extends DeviceManage {

    private List<Device> list = new ArrayList<>();

    public void load() {
        File file = new File("oppo.txt");
        super.parse(file, list);

        System.out.println("=====>共加载OPPO机型" + list.size() + "<========");
    }

    @Override
    public Device random() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
