package com.sundy.algorithm.leetcode.editor.cn;

//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 872 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-05-09 15:57:01
 */
public class MinimumPathSum{
    public static void main(String[] args){
        Solution solution = new MinimumPathSum().new Solution();
        int[][] grid = new int[][]{{1,2,3},{4,5,6}};
        solution.minPathSum(grid);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最小路径和
     */
    class Solution {
        /**
         * 二维DP
         * DP:dp[i][j] 表示从grid[0][0]走到grid[i][j]的最短路径
         * @param grid
         * @return
         */
        public int minPathSum1(int[][] grid) {
            int[][] dp = new int[grid.length + 1][grid[0].length + 1];
            dp[0][0] = 0;
            for (int i = 1; i < grid.length + 1; i++) {
                for (int j = 1; j < grid[i-1].length + 1; j++) {
                    if (i == 1 ) {
                        dp[i][j] = dp[i][j-1] + grid[i-1][j-1];
                    }else if (j == 1 ) {
                        dp[i][j] = dp[i-1][j] + grid[i-1][j-1];
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j] , dp[i][j-1]) + grid[i-1][j-1];
                    }
                }
            }
            return dp[grid.length][grid[0].length];
        }
        /**
         * 一维DP
         * @param grid
         * @return
         */
        public int minPathSum(int[][] grid) {
            int[] dp = new int[grid[0].length + 1];
            for (int i = 1; i < grid.length + 1; i++) {
                for (int j = 1; j < grid[i-1].length + 1; j++) {
                    if (i == 1) {
                        dp[j] = dp[j-1] + grid[i-1][j-1];
                    }else if (j == 1) {
                        dp[j] = dp[j] + grid[i-1][j-1];
                    }else {
                        dp[j] = Math.min(dp[j] ,dp[j-1]) + grid[i-1][j-1];
                    }
                }
            }
            return dp[grid[0].length];
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
