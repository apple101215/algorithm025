package com.sundy.algorithm.leetcode.editor.cn;

//
// 
// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连
//。 
//
// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。 
//
// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 
//isConnected[i][j] = 0 表示二者不直接相连。 
//
// 返回矩阵中 省份 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 200 
// n == isConnected.length 
// n == isConnected[i].length 
// isConnected[i][j] 为 1 或 0 
// isConnected[i][i] == 1 
// isConnected[i][j] == isConnected[j][i] 
// 
// 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 552 👎 0

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author: sundy
 * @Date: 2021-05-21 09:11:13
 */
public class NumberOfProvinces{
    public static void main(String[] args){
        Solution solution = new NumberOfProvinces().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 省份数量
     */
    class Solution {

        /**
         * DFS
         * @param isConnected
         * @return
         */
        public int findCircleNum1(int[][] isConnected) {
            Set<Integer> visited = new HashSet<>();
            int result = 0;
            for (int i = 0; i < isConnected.length; i++) {
                if (!visited.contains(i)) {
                    dfs(isConnected, visited, i);
                    result++;
                }
            }
            return result;
        }

        public void dfs (int[][] isConnected, Set<Integer> visited, int i) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1 && !visited.contains(j) ) {
                    visited.add(j);
                    dfs(isConnected, visited, j);
                }
            }
        }
        /**
         * BFS
         * @param isConnected
         * @return
         */
        public int findCircleNum2(int[][] isConnected) {
            Set<Integer> visited = new HashSet<>();
            int result = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < isConnected.length; i++) {
                if (!visited.contains(i)) {
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        Integer j = queue.poll();
                        visited.add(j);
                        for (int k = 0; k < isConnected.length; k++) {
                            if (isConnected[j][k] == 1 && !visited.contains(k)) {
                                queue.offer(k);
                            }
                        }
                    }
                    result++;
                }
            }
            return result;
        }

        /**
         * 并查集
         * @param isConnected
         * @return
         */
        public int findCircleNum(int[][] isConnected) {
            UnionFind unionFind = new UnionFind(isConnected.length);
            for (int i = 0; i < isConnected.length; i++) {
                //只遍历对称矩阵的右上部分
                for (int j = i + 1; j < isConnected.length; j++) {
                    if (isConnected[i][j] == 1) {
                        unionFind.union(i, j);
                    }
                }
            }
            return unionFind.count;
        }

        class UnionFind {
            private int count = 0;
            private int[] parent;

            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int p) {
                while (p != parent[p]) {
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                return p;
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return;
                parent[rootP] = rootQ;
                count--;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
