package com.jlzb.storedownload.device;

import java.util.Random;

public class DeviceRandom {

    private static Random ran = new Random();

    /**
     * 随机生成IMEI
     * @return
     */
    public static String randomImei() {

        return new StringBuilder().append(randomMax(1000000)).append(randomMax(100))
                .append(randomMax(1000000)).append(randomMax(10)).toString();
    }

    private static String randomMax(int i) {
        int j = ran.nextInt(i);
        if (j < i)
            j += i;
        return (new StringBuilder()).append(j).toString().substring(1);
    }

}
