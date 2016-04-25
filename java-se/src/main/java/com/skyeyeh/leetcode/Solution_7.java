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
    public int reverse(int x) {
        long origin = x;
        long result = 0;
        int lowerInt;
        while ((origin) != 0) {
            lowerInt = (int) (origin % 10);
            origin /= 10;

            result = 10 * result + lowerInt;

            // Check overflow.
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int) result;
    }

    public static void main(String[] args) {
        Solution_7 solution_7 = new Solution_7();
        System.out.println(solution_7.reverse(123));
        System.out.println(solution_7.reverse(-123));
    }
}
