package com.skyeyeh.leetcode;


/**
 * Created by TV6015 on 2016/4/19.
 */
public class Solution_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean result = true;

        // Check null safe.
        if (p == null && q == null) {
            return true;
        }

        // Check value.
        if ((p == null) || (q == null) || (p.val != q.val)) {
            return false;
        }

        // Check left tree.
        result = isSameTree(p.left, q.left);

        // If false break.
        if (!result)
            return result;

        // Check right tree.
        result = isSameTree(p.right, q.right);

        // If false break.
        if (!result)
            return result;

        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
