package com.skyeyeh.leetcode;

/**
 * Reverse Integer.
 */
public class Solution_7 {
    /**
     * My solution.
     *
     * @param x An integer
     * @return Reverse digits
     */
    public int reverse(long x) {
        String temp = "";

        // Reverse digits.
        String s = String.valueOf(x);
        for (int i = s.length() - 1; i >= 0; i--) {
            temp += s.charAt(i);
        }

        // Replace negative.
        if (temp.lastIndexOf('-') > -1) {
            temp = "-" + temp.replace("-", "");
        }

        // To int.
        return Integer.parseInt(temp);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_7().reverse(123));
    }
}
