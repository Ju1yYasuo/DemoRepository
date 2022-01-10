package com.example.demo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * java测试
 *
 * @author luox
 * @date 2021/7/16
 */
public class JavaTest {

    @Test
    public void test2(){
        int points = 10000;
        List<BigDecimal[]> bigDecimalList = new ArrayList<>(points);
        for(int i = 0;i < points;i++){
            BigDecimal[] bigDecimals = new BigDecimal[5];
            for(int j = 0;j< 5;j++){
                BigDecimal bigDecimal = new BigDecimal("0.001" + (int) (Math.random() * 9));
                bigDecimals[j] = bigDecimal;
            }
            bigDecimalList.add(bigDecimals);
        }

        BigDecimal standard = new BigDecimal("0.0002");
        for(int t = 0,size = bigDecimalList.size();t < size;t++) {
            BigDecimal[] bigDecimals = bigDecimalList.get(t);
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 5; j++) {
                    if(bigDecimals[i].subtract(bigDecimals[j]).abs().compareTo(standard) > 0){
                        print("t:" + t + ",i:" + i + ",j:" + j
                                + " v1:" + bigDecimals[i].floatValue() + ",v2:" + bigDecimals[j].floatValue());
                    }
                }
            }
        }

    }

    @Test
    public void test1(){
        print((int) (Math.random() * 9));
        print((int) (Math.random() * 10));
    }

    public static void main(String[] args) {
        String[] letter = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        String[] digital = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        Thread thread1 = new Thread(() -> {
            synchronized (JavaTest.class) {
                for (int i = 0,size = letter.length;i < size;i++) {
                    print(letter[i]);
                    JavaTest.class.notify();
                    if(i != size - 1){
                        try {
                            JavaTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            synchronized (JavaTest.class) {
                for (int i = 0,size = digital.length;i < size;i++) {
                    print(digital[i]);
                    JavaTest.class.notify();
                    if(i != size - 1){
                        try {
                            JavaTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "thread2");
        thread1.start();
        thread2.start();
    }

    private static void print(Object obj){
        System.out.println(obj);
    }

}
