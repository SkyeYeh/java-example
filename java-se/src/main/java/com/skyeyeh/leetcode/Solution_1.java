package com.skyeyeh.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum.
 */
public class Solution_1 {
    /**
     * My solution.
     *
     * @param nums   An array of integers.
     * @param target specific target.
     * @return Indices of the two numbers such that they add up to a specific target..
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Brute Force.
     * Time complexity : O(n^2).
     * Space complexity : O(1).
     *
     * @param nums   An array of integers.
     * @param target specific target.
     * @return Indices of the two numbers such that they add up to a specific target..
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Two-pass Hash Table.
     * Time complexity : O(n).
     * Space complexity : O(n).
     *
     * @param nums   An array of integers.
     * @param target specific target.
     * @return Indices of the two numbers such that they add up to a specific target..
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * One-pass Hash Table.
     * Time complexity : O(n).
     * Space complexity : O(n).
     *
     * @param nums   An array of integers.
     * @param target specific target.
     * @return Indices of the two numbers such that they add up to a specific target..
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
