package com.sundy.algorithm.leetcode.editor.cn;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 1159 👎 0


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author: sundy
 * @Date: 2021-05-22 19:14:08
 */
public class NumberOfIslands{
    public static void main(String[] args){
        Solution solution = new NumberOfIslands().new Solution();
        solution.numIslands(new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}});
       solution.numIslands(new char[][]{{'1'},{'1'}});
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 岛屿数量
     */
    class Solution {
        /**
         * DFS
         * @param grid
         * @return
         */
        public int numIslands1(char[][] grid) {
            int[] dx = new int[] {-1, 0, 1, 0};
            int[] dy = new int[] { 0, -1,0, 1 };
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j, dx, dy);;
                        count++;
                    }
                }
            }
            return count;
        }

        public void dfs(char[][] grid, int i, int j, int[] dx, int[] dy) {
            if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[i].length - 1 || grid[i][j] != '1') {
                return ;
            }
            grid[i][j] = '0';
            for (int k = 0; k < 4; k++) {
                dfs(grid,i + dx[k], j + dy[k], dx, dy );
            }
        }

        /**
         * BFS
         * @param grid
         * @return
         */
        public int numIslands2(char[][] grid) {
            int count = 0;
            int[] dx = new int[] {-1, 0, 1, 0};
            int[] dy = new int[] { 0, -1,0, 1 };
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        grid[i][j] = '0';
                        queue.offer(new int[]{i,j});
                        count++;
                        while (!queue.isEmpty()) {
                            int[] poll = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int newX = poll[0] + dx[k];
                                int newY = poll[1] + dy[k];
                                if (newX < 0 || newX > grid.length - 1 || newY < 0 || newY > grid[i].length - 1 || grid[newX][newY] != '1') {
                                    continue;
                                }
                                queue.offer(new int[]{newX, newY});
                                grid[newX][newY] = '0';
                            }
                        }
                    }
                }
            }
            return count;
        }

        /**
         * 并查集
         * @param grid
         * @return
         */
        public int numIslands(char[][] grid) {
            UnionFind unionFind = new UnionFind(grid.length * grid[0].length);
            int[] dx = new int[]{0,1};
            int[] dy = new int[]{1,0};
            int countOcean = 0 ;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        //判断相邻的前面的岛屿
                        for (int k = 0; k < 2; k++) {
                            int newX = i + dx[k];
                            int newY = j + dy[k];
                            if (newX < 0 || newX > grid.length - 1 || newY < 0 || newY > grid[0].length - 1 || grid[newX][newY] == '0') {
                                continue;
                            }
                            unionFind.union(i * grid[i].length + j, newX * grid[i].length + newY);
                        }
                    }else {
                        countOcean++;
                    }
                }
            }
            return unionFind.count - countOcean;
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

            boolean isConnected(int node1, int node2) {
                return find(node1) == find(node2);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
