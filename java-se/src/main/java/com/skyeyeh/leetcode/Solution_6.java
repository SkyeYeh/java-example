package com.skyeyeh.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ZigZag Conversion.
 */
public class Solution_6 {
    /**
     * My solution.
     *
     * @param s       A string
     * @param numRows Zigzag rows.
     * @return A zigzag pattern string
     */
    public String convert(String s, int numRows) {
        List<List<String>> list = new ArrayList<>();

        int index = 0;
        int director = 0;
        for (int i = 0; i < s.length(); i++) {
            // Init stringList.
            List<String> stringList;
            if (list.size() <= index) {
                stringList = new ArrayList<>();
                list.add(stringList);
            } else {
                stringList = list.get(index);
            }

            // Add string to stringList.
            stringList.add(String.valueOf(s.charAt(i)));
            list.set(index, stringList);

            // Get director.
            director = getDirector(numRows, index, director);
            index += director;
        }

        // Get zigzag pattern string.
        return getList(list);
    }

    /**
     * Get director.
     *
     * @param numRows  Zigzag rows.
     * @param index    List index.
     * @param director Origin director.
     * @return director.
     */
    private int getDirector(int numRows, int index, int director) {
        if (numRows == 1) {
            director = 0;
        } else if (index == 0) {
            director = 1;
        } else if (index == numRows - 1) {
            director = -1;
        }
        return director;
    }

    /**
     * Get zigzag pattern string.
     *
     * @param list Data
     * @return Zigzag pattern string.
     */
    private String getList(List<List<String>> list) {
        StringBuffer result = new StringBuffer();
        for (List<String> stringList : list) {
            stringList.forEach(result::append);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution_6 solution_6 = new Solution_6();
        System.out.println(solution_6.convert("PAYPALISHIRING", 3));
//        System.out.println(solution_6.convert("AB", 1));
    }
}
