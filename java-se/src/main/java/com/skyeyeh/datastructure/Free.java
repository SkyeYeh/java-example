package com.skyeyeh.datastructure;

/**
 * Created by TV6015 on 2016/4/13.
 */
public class Free {
    public static int getGcd(int a, int b) {
        while ((a %= b) != 0 && (b %= a) != 0) ;
        return a + b;
    }

    public static int getLcm(int a, int b) {
        int result = a * b / getGcd(a, b);
        return result;
    }

    public static void main(String[] args) {
        int a = 45;
        int b = 6;
        System.out.println(Free.getGcd(a, b));
        System.out.println(Free.getLcm(a, b));
    }
}
