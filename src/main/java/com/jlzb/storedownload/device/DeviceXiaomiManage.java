package com.jlzb.storedownload.device;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeviceXiaomiManage extends DeviceManage {
    private List<Device> list = new ArrayList<>();

    public void load() {
        File file = new File("xiaomi.txt");
        super.parse(file, list);

        System.out.println("=====>共加载小米机型" + list.size() + "<========");
    }

    @Override
    public Device random() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
