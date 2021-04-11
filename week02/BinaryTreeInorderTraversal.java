package com.sundy.algorithm.leetcode.editor.cn;
//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 882 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversalRevise {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversalRevise().new Solution();
//        TreeNode root = new TreeNode(1, null, new TreeNode(2,new TreeNode(3), null));
        TreeNode root = new TreeNode(3,  new TreeNode(1), new TreeNode(2));
        System.out.println(solution.inorderTraversal1(root));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * 方法1(优秀)：递归的方式
 * 方法3(优秀)： 迭代解法，官方的解法，简洁明了，x不为空||stack不为空，x不为空加入stack，x=x.left
 * 方法3(优秀)：x为空，popTemp = treeNodeStack.pop()，popTemp加入stack，x=popTemp.right
 * 方法4(优秀)：Morris中序遍历，能将非递归的中序遍历的空间复杂度降为O(1)
 * 方法4：Morris遍历算法步骤(假设当前遍历的节点为x)：
 * 方法4：如果x无左孩子，x加入结果数组，x=x.right
 * 方法4：如果x有左孩子，查找x左子树上面最右的节点，记为predecessor,查找方法就是：节点向左走一步，然后一直向右走至无法走为止
 * 方法4：如果predecessor无右节点，predecessor的右孩子指向x，x=x.left
 * 方法4：如果predecessor有右节点，将predecessor加入结果数组，x=x.right
 */
class Solution {
    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.add(temp);
                temp = temp.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            temp = pop.right;
        }
        return list;
    }

    /**
     * 莫里斯
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode temp = root;
        while (temp != null ) {
            if (temp.left == null) {
                list.add(temp.val);
                temp = temp.right;
            } else {
               //查找prdecessor
               TreeNode predecessor = temp.left;
               while (predecessor.right != null && predecessor.right != temp) {
                   predecessor = predecessor.right;
               }
               if (predecessor.right == null) {
                   predecessor.right = temp;
                   temp = temp.left;
               } else {
                   list.add(temp.val);
                   temp = temp.right;
               }
            }
        }
        return list;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;

    }

    public void inOrder(TreeNode root, List<Integer> list) {
        if(root != null){
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }






}
//leetcode submit region end(Prohibit modification and deletion)

}
