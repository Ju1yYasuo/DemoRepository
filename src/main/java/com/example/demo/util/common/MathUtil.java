package com.example.demo.util.common;

/**
 * 数值处理
 *
 * @author luox
 * @date 2021/8/2
 */
public class MathUtil {

    /**
     * 获取HashMap最佳长度
     *
     * @param size 预设大小
     * @return int
     * @author luox
     * @date 2021/08/02
     */
    public static int getHashMapBestLength(int size){
        if(size == 0 || size == 1){
            return 2;
        }
        double logValue = Math.log(size) / Math.log(2);

        if(isIntegerPowerOf2(size)){
            return 2 << ((int)logValue - 1);
        }
        int digits = new Double(Math.floor(logValue)).intValue();
        return 2 << digits;
    }

    /**
     * 判断一个数是否为2的整数次幂
     *
     * @param number 数
     * @return boolean
     * @author luox
     * @date 2021/08/02
     */
    public static boolean isIntegerPowerOf2(int number){
        return (number & (number - 1)) == 0;
    }

}
