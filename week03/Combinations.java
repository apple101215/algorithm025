package com.sundy.algorithm.leetcode.editor.cn;

//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 561 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: sundy
 * @Date: 2021-04-18 16:54:00
 */
public class Combinations{
    public static void main(String[] args){
        Solution solution = new Combinations().new Solution();
        solution.combine1(4,2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 组合
     */
    class Solution {
        public List<List<Integer>> combine1(int n, int k) {
            int[] nums = new int[n];
            for (int i = 1; i <= n; i++) {
                nums[i-1] = i;
            }
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, nums, new ArrayList<>(), 0, k);
            return result;
        }

        public void dfs(List<List<Integer>> result, int[] nums, List<Integer> list, int index, int k) {
            //terminal
            if (list.size() == k) {
                result.add(new ArrayList<>(list));
                return ;
            }
            if (nums.length == index) {
                return ;
            }
            //process
            if (list.size() < k) {
                dfs(result, nums, list, index + 1, k);
                list.add(nums[index]);
                dfs(result, nums, list, index+1, k);
                list.remove(list.size() - 1);
            }
            //drill down
        }

        /**
         * 环形移动不行，会缺失答案
         * @param n
         * @param k
         * @return
         */
        public List<List<Integer>> combine2(int n, int k) {
            List<Integer> nums = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                nums.add(i);
            }
            int initFirstElement = nums.get(0);
            List<List<Integer>> result = new ArrayList<>();
            do {
                result.add(new ArrayList<>(nums.subList(0, k)));
                //环形移位
                circularMovement(nums);
            }while (nums.get(0) != initFirstElement);
            return result;
        }

        /**
         * 环形移动数组
         * @param nums
         */
        private void circularMovement(List<Integer> nums) {
            int temp = nums.get(0);
            for (int i = 0; i < nums.size(); i++) {
                if (i == nums.size()-1) {
                    nums.set(i, temp);
                } else {
                    nums.set(i, nums.get(i+1));
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
