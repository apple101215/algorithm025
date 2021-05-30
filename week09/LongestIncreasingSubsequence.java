package com.sundy.algorithm.leetcode.editor.cn;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 二分查找 动态规划 
// 👍 1635 👎 0

import java.util.Arrays;

/**
 *
 * @author: sundy
 * @Date: 2021-05-30 08:37:08
 */
public class LongestIncreasingSubsequence{
    public static void main(String[] args){
        Solution solution = new LongestIncreasingSubsequence().new Solution();
        solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});

    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最长递增子序列
     */
    class Solution {

        /**
         * dp[i] 表示从下表0到当前位置，包含当前位置的最长递增子序列
         * dp[i] = Math.max{dp[j] where dp[j] < dp[i]} + 1
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS1(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int result = 1;
            int[] dp = new int[nums.length];
            dp[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                result = Math.max(result, dp[i]);
            }
            return result;
        }

        /**
         * 贪心 + 二分查找
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
           if (nums.length == 0) {
               return 0;
           }
           int[] result = new int[nums.length];
           int index = 0;
           result[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > result[index]) {
                    result[++index] = nums[i];
                } else {
                    //二分查找，找到第一个比nums[i] 小的数，
                    // pos = -1，是为了防止第一个数要被更新，pos没有被下面的程序更新的情况，例如{10,9,2,5,3,7,101,18}
                    int left = 0, right = index , pos = -1;
                    while (left <= right) {
                        int mid = (left + right) >> 1;
                        if (result[mid] < nums[i]) {
                            pos = mid;
                            left = mid + 1;
                        }else {
                            right = mid - 1;
                        }
                    }
                    result[pos + 1] = nums[i];
                }
            }
            return index + 1;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
