package com.skyeyeh.leetcode;

import java.util.*;

/**
 * Longest Substring Without Repeating Characters.
 */
public class Solution_3 {
    /**
     * My solution.
     *
     * @param s A string
     * @return Longest substring
     */
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        Queue<Character> temp = new LinkedList<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (temp.contains(c)) {
                while (!temp.remove().equals(c)) {
                }
            }

            // Add new substring.
            temp.add(c);
            result = Math.max(temp.size(), result);
        }

        return result;
    }

    /**
     * Brute Force [Time Limit Exceeded].
     * Time complexity : O(n^3).
     *
     * @param s A string
     * @return Longest substring
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    /**
     * If the characters in the substring are all unique return true.
     *
     * @param s     The characters
     * @param start A string start
     * @param end   A string end
     * @return The characters are all unique return true
     */
    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; ++i) {
            Character ch = s.charAt(start);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    /**
     * Sliding Window.
     * Time complexity : O(2n) = O(n).
     * Space complexity : O(min(m, n)).
     *
     * @param s A string
     * @return Longest substring
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * Sliding Window Optimized.
     * Time complexity : O(n).
     * Space complexity (HashMap) : O(min(m, n)).
     *
     * @param s A string
     * @return Longest substring
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; ++j) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new Solution_3().lengthOfLongestSubstring(s));
    }
}
