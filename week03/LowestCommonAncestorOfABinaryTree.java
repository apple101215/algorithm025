package com.sundy.algorithm.leetcode.editor.cn;

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 
// 👍 1082 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author: sundy
 * @Date: 2021-04-17 20:40:14
 */
public class LowestCommonAncestorOfABinaryTree{
    public static void main(String[] args){
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
    class Solution {
        TreeNode result = null;

        /**
         * 递归的方式
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
            boolean dfs = dfs(root, p, q);
            return result;
        }

        public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
          if (root == null) {
              return false;
          }
            boolean left = dfs(root.left, p, q);
            boolean right = dfs(root.right, p, q);
            boolean found = (left && right) || (root == p || root == q) && (left || right);
            if (found) {
                result = root;
            }
            return left || right || root.val == p.val || root.val == q.val;
        }

        /**
         * 存储所有的父亲节点
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            Map<Integer, TreeNode> map = new HashMap<>();
            Set<Integer> visited = new HashSet<>();
            dfs2(root, map);

            while (p != null) {
                visited.add(p.val);
                p = map.get(p.val);
            }
            while (q != null) {
                if (visited.contains(q.val)) {
                    return q;
                }
                q = map.get(q.val);
            }
            return null;
        }

        public void dfs2(TreeNode root, Map<Integer, TreeNode> map){
            if (root.left != null) {
                map.put(root.left.val, root);
                dfs2(root.left, map);
            }
            if (root.right != null) {
                map.put(root.right.val, root);
                dfs2(root.right, map);
            }
        }

    /**
     * amazing,写不出来
     * @param root
     * @param p
     * @param q
     * @return
     */
        public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor3(root.left, p, q);
            TreeNode right = lowestCommonAncestor3(root.right, p, q);
            return left == null ? right : right == null ? left : root;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
