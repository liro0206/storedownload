package com.jlzb.storedownload.device;

import java.util.Random;

public class DeviceRandom {

    private static Random ran = new Random();

    private static String[] androidcodes = {"Android 8.1", "Android 8.0", "Android 7.1.1", "Android 7.1", "Android 7.0", "Android 6.0", "Android 5.1", "Android 5.0", "Android 4.4.4", "Android 4.4.3", "Android 4.4.2", "Android 4.4.1", "Android 4.4", "Android 4.3", "Android 4.2", "Android 4.1"};

    /**
     * 随机生成IMEI
     *
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

    /**
     * 随机生成android版本
     * @return
     */
    public static String randomAndroidCode() {
        return androidcodes[ran.nextInt(androidcodes.length)];
    }
}
