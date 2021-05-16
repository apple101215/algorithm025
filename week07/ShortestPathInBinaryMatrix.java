package com.sundy.algorithm.leetcode.editor.cn;

//给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。 
//
// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求
//： 
//
// 
// 路径途经的所有单元格都的值都是 0 。 
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。 
// 
//
// 畅通路径的长度 是该路径途经的单元格总数。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[0,1],[1,0]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 广度优先搜索 
// 👍 100 👎 0

import javafx.util.Pair;

import java.util.*;

/**
 *
 * @author: sundy
 * @Date: 2021-05-15 20:00:58
 */
public class ShortestPathInBinaryMatrix{
    public static void main(String[] args){
        Solution solution = new ShortestPathInBinaryMatrix().new Solution();
        int result = solution.shortestPathBinaryMatrix(new int[][]{{0, 1, 1, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 1, 0, 1, 0}, {0, 0, 0, 1, 1, 0}, {1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 0}});
        System.out.println(result);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 二进制矩阵中的最短路径
     */
    class Solution {
        /**
         * 二维DP，每次当我们遍历到一个点时，去更新它周围的所有点。
         * 错误解法，通过不了：{{0,1,1,0,0,0},{0,1,0,1,1,0},{0,1,1,0,1,0},{0,0,0,1,1,0},{1,1,1,1,1,0},{1,1,1,1,1,0}}
         * @param grid
         * @return
         */
        public int shortestPathBinaryMatrix1(int[][] grid) {
            if (grid[0][0] == 1) {
                return -1;
            }
            int[][] dp = new int[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = 1;
            int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    for (int k = 0; k < 8; k++) {
                        int newX = i + dx[k];
                        int newY = j + dy[k];
                        if (newX >=0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                            //更新
                            if (grid[newX][newY] == 1) {
                                dp[newX][newY] = Integer.MAX_VALUE;
                                continue;
                            }
                            int temp = getShortStep(grid, dp, dx, dy, newX, newY);
                            dp[newX][newY] = temp;
                        }
                    }
                }
            }
            return dp[grid.length-1][grid[0].length - 1] == Integer.MAX_VALUE ? -1 : dp[grid.length-1][grid[0].length - 1];
        }

        /**
         * 二维DP,每次当我们遍历到一个点时，去更新它周围的所有点。
         * 超时
         * @param grid
         * @return
         */
        public int shortestPathBinaryMatrix2(int[][] grid) {
            if (grid[0][0] == 1) {
                return -1;
            }
            int[][] dp = new int[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = 1;
            Set<Pair<Integer, Integer>> visited = new HashSet<>();
            visited.add(new Pair<>(0,0));
            int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    for (int k = 0; k < 8; k++) {
                        int newX = i + dx[k];
                        int newY = j + dy[k];
                        if (newX >=0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                            //更新
                            if (grid[newX][newY] == 1) {
                                dp[newX][newY] = Integer.MAX_VALUE;
                                continue;
                            }
                            int temp = getShortStep(grid, dp, dx, dy, newX, newY);
                            dp[newX][newY] = temp;
                            visited.add(new Pair<>(newX, newY));
                        }
                    }
                    for (Pair<Integer, Integer> pair : visited) {
                        int x = pair.getKey();
                        int y = pair.getValue();
                        dp[x][y] = getShortStep(grid, dp, dx, dy, x, y);;
                    }
                }
            }
            return dp[grid.length-1][grid[0].length - 1] == Integer.MAX_VALUE ? -1 : dp[grid.length-1][grid[0].length - 1];
        }

        private int getShortStep(int[][] grid, int[][] dp, int[] dx, int[] dy, int newX, int newY) {
            int temp = dp[newX][newY];
            for (int l = 0; l < 8; l++) {
                int nnX = newX + dx[l];
                int nnY = newY + dy[l];
                if (nnX >= 0 && nnX < grid.length && nnY >= 0 && nnY < grid[0].length && dp[nnX][nnY] != Integer.MAX_VALUE) {
                    temp = Math.min(temp, dp[nnX][nnY] + 1);
                }
            }
            return temp;
        }

        /**
         * BFS,很巧妙
         * @param grid
         * @return
         */
        public int shortestPathBinaryMatrix(int[][] grid) {
            int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
            if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
                return -1;
            }
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0,0});
            grid[0][0] = 1;
            while (!queue.isEmpty() && grid[grid.length - 1][grid[0].length - 1] == 0) {
                int[] poll = queue.poll();
                int preLength = grid[poll[0]][poll[1]];
                for (int i = 0; i < 8; i++) {
                   int newX = poll[0] + dx[i];
                   int newY = poll[1] + dy[i];
                   if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 0) {
                       queue.offer(new int[]{newX, newY});
                       grid[newX][newY] = preLength + 1;
                   }
                }
            }
            return grid[grid.length - 1][grid[0].length - 1] == 0 ? -1 : grid[grid.length - 1][grid[0].length - 1];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
