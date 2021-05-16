package com.sundy.algorithm.leetcode.editor.cn;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1568 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-03-30 08:59:47
 */
public class ClimbingStairs{
    public static void main(String[] args){
        Solution solution = new ClimbingStairs().new Solution();
        int num = solution.climbStairs5(45);
        System.out.println(num);

    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 爬楼梯
     */
    class Solution {
        /**
         * 滚动数组，适用于n比较小的情况
         * @param n
         * @return
         */
        public int climbStairs1(int n) {
            if (n < 3) {
                return n;
            }
            int f1 = 1,f2 = 2, f3 = f1 + f2;
            for (int i = 3; i <= n; i++) {
                f3= f1+f2;
                f1 = f2;
                f2 = f3;
            }
            return f3;
        }

        /**
         * 每次可以爬1，2，3级楼梯
         * @param n
         * @return
         */
        public int climbStairs2(int n) {
            if (n < 3) {
                return n;
            }
            int f1=1, f2 = 2, f3 = 4, f4 = f1 + f2 + f3;
            for (int i = 3; i <= n; i++) {
                f4 = f1 + f2 + f3 + f4;
                f1 = f2;
                f2 = f3;
                f3 = f4;
            }
            return f4;
        }


        /**
         * 每次可以爬1，2，3级楼梯,相邻两次不能相同
         * 思路：记录每一次最后上的级数对应的走法
         * @param n
         * @return
         */
        public int climbStairs3(int n) {
            if (n < 3) {
                return n;
            }
            int[][] dp = new int[5][4];
            //dp[n][1]表示上n级台阶最后一步为1的走法; dp[n][2]表示上n级台阶最后一步为2的走法; dp[n][3]表示上n级台阶最后一步为3的走法;
            dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0;
            dp[2][1] = 0; dp[2][2] = 1; dp[2][3] = 0;
            dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;
            dp[4][1] = dp[3][1] + dp[3][2]; dp[4][2] = dp[2][1] + dp[2][3]; dp[4][3] = dp[2][1] + dp[2][2];

            for (int i = 4; i <= n; i++) {
                dp[4][1] = dp[3][1] + dp[3][2]; dp[4][2] = dp[2][1] + dp[2][3]; dp[4][3] = dp[2][1] + dp[2][2];
                dp[1][1] = dp[2][1]; dp[1][2] = dp[2][2]; dp[1][3] = dp[2][3];
                dp[2][1] = dp[3][1]; dp[2][2] = dp[3][2]; dp[2][3] = dp[3][3];
                dp[3][1] = dp[4][1]; dp[3][2] = dp[4][2]; dp[3][3] = dp[4][1];
            }
            return dp[4][1] + dp[4][2] + dp[4][3];
        }


        /**
         * 递归写法
         * 每次可以爬1，2级楼梯
         * @param n
         * @return
         */
        public int climbStairs4(int n) {
            if (n < 3) {
                return n;
            }
            return climbStairs4(n-1) + climbStairs4(n-2);
        }


        /**
         * 递归写法
         * 每次可以爬1，2级楼梯
         * @param n
         * @return
         */
        public int climbStairs5(int n) {
            return climbStairsRecurOptimize(n, new int[n+1]);
        }

        /**
         * 递归写法
         * 每次可以爬1，2级楼梯
         * @param n
         * @return
         */
        public int climbStairsRecurOptimize(int n, int[] dp) {
            if (n < 3) {
                return n;
            }
            if (dp[n] != 0) {
                return dp[n];
            }
            return dp[n] = climbStairsRecurOptimize(n-1, dp) + climbStairsRecurOptimize(n-2, dp);
        }



}
//leetcode submit region end(Prohibit modification and deletion)

}
