package com.skyeyeh.leetcode;

/**
 * Add Two Numbers.
 */
public class Solution_2 {
    /**
     * My solution.
     *
     * @param l1 linked lists 1
     * @param l2 linked lists 2
     * @return An add the two numbers linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result;

        // Add two numbers.
        int val = l1.val + l2.val;
        result = new ListNode(val);

        // Link node.
        if (l1.next != null || l2.next != null) {
            ListNode next1 = l1.next != null ? l1.next : new ListNode(0);
            ListNode next2 = l2.next != null ? l2.next : new ListNode(0);
            result.next = addTwoNumbers(next1, next2);
        }

        // Carry.
        ListNode target = result;
        while (target.val >= 10) {
            target.val %= 10;
            if (target.next != null) {
                target.next.val++;
            } else {
                target.next = new ListNode(1);
            }

            target = target.next;
        }

        return result;
    }

    /**
     * Intuition.
     * Time complexity : O(\max(m, n)).
     * Space complexity : O(\max(m, n)).
     *
     * @param l1 linked lists 1
     * @param l2 linked lists 2
     * @return An add the two numbers linked list.
     */
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(2);
        ListNode l1_2 = new ListNode(4);
        ListNode l1_3 = new ListNode(3);

        l1_1.next = l1_2;
        l1_2.next = l1_3;

        ListNode l2_1 = new ListNode(5);
        ListNode l2_2 = new ListNode(6);
        ListNode l2_3 = new ListNode(4);

        l2_1.next = l2_2;
        l2_2.next = l2_3;

        new Solution_2().addTwoNumbers(l1_1, l2_1);
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
