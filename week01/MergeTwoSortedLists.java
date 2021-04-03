package com.sundy.algorithm.leetcode.editor.cn;

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1637 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-04-03 21:25:04
 */
public class MergeTwoSortedLists{
    public static void main(String[] args){
        Solution solution = new MergeTwoSortedLists().new Solution();
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        ListNode listNode = solution.mergeTwoLists2(l1, l2);

    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 迭代：自己实现，不够熟练，调试了好几遍
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode prev = result;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                prev.next = l2;
                break;
            }
            if (l2 == null) {
                prev.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;

        }
        return result.next;
    }

    /**
     * 迭代：看了题解之后写的
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode prev = result;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return result.next;
    }
    /**
     * 递归：看了题解之后写的
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }else if (l2 == null) {
            return l1;
        }else if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursion(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoListsRecursion(l1, l2.next);
            return l2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
