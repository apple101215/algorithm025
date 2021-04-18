package com.sundy.algorithm.leetcode.editor.cn;

//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 994 👎 0

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author: sundy
 * @Date: 2021-04-17 22:21:05
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args){
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int[] preOrder = new int[] {3,9,20,15,7};
        int[] inOrder = new int[] {9,3,15,20,7};
        solution.buildTree(preOrder, inOrder);
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
 */
class Solution {


    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderRootMap = getInOrderRootMap(inorder);
        return buildTreeHelper(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1, inOrderRootMap);
    }

    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preFrom ,int preTo, int inFrom, int inTo, Map<Integer, Integer> inOrderRootMap) {
        if (preFrom > preTo) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[preFrom]);
        int inorderRoot = inOrderRootMap.get(preorder[preFrom]);
        int leftNums = inorderRoot - inFrom;
        treeNode.left = buildTreeHelper(preorder, inorder, preFrom+1, preFrom + leftNums, inFrom, inorderRoot - 1, inOrderRootMap);
        treeNode.right = buildTreeHelper(preorder, inorder, preFrom + leftNums + 1 , preTo, inorderRoot + 1, inTo, inOrderRootMap);
        return treeNode;
    }

    public Map<Integer, Integer> getInOrderRootMap(int[] inorder){
        Map<Integer, Integer> inOrderRootMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderRootMap.put(inorder[i], i);
        }
        return inOrderRootMap;
    }

    /**
     * 迭代,不好理解
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inOrderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preOrderVal = preorder[i];
            if (!stack.isEmpty() && stack.peek().val != inorder[inOrderIndex]) {
                TreeNode peek = stack.peek();
                peek.left = new TreeNode(preOrderVal);
                stack.push(peek.left);
            }else {
                TreeNode pop = stack.peek();
                while (!stack.isEmpty() && stack.peek().val == inorder[inOrderIndex]) {
                    pop = stack.pop();
                    inOrderIndex ++ ;
                }
                pop.right = new TreeNode(preOrderVal);
                stack.push(pop.right);
            }
        }
        return root;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
