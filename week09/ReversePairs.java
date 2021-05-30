package com.sundy.algorithm.leetcode.editor.cn;

//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 284 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-05-29 18:39:38
 */
public class ReversePairs{
    public static void main(String[] args){
        Solution solution = new ReversePairs().new Solution();
        solution.reversePairs(new int[]{1,3,2,3,1});
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 翻转对
     */
    class Solution {
        int count = 0;

        public int reversePairs(int[] nums) {
            if (nums.length == 0) {
                return count;
            }
            mergeSort(nums, 0, nums.length - 1);
            return count;
        }

        public void mergeSort(int[] arr, int left, int right) {
            if (left >= right) {
                return ;
            }
            int mid = (left + right) >> 1;
            mergeSort(arr,  left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }

        public void merge(int[] arr, int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int i = left, k = left, m = 0;

            for (int j = mid + 1; j <= right; j++) {
                while (i <= mid && arr[i] <= arr[j]) {
                    temp[m++] = arr[i++];
                }
                //对于每一个k，查找第一个arr[k]/2.0 > arr[j]的下标
                while (k <= mid && arr[k]/2.0 <= arr[j]) {
                    k++;
                }
                count = count + mid - k + 1;
                temp[m++] = arr[j];
            }
            while (i <= mid) {
                temp[m++] = arr[i++];
            }

            for (int n = 0; n < temp.length; n++) {
                arr[left + n] = temp[n];
            }
        }

        /**
         * 翻转对
         * @param nums
         * @return
         */
        public int reversePairs2(int[] nums) {
            if (nums.length == 0) {
                return count;
            }
            return merge(nums, 0, nums.length - 1);
        }

        /**
         * 归并排序
         * @param arr
         * @param left
         * @param right
         * @return
         */
        public int merge(int[] arr, int left,  int right) {
            if (left >= right) {
                return 0;
            }
            int mid = (left + right) >> 1;
            int count = merge(arr, left, mid) + merge(arr, mid + 1, right);
            int[] temp = new int[right - left + 1];
            int i = left, k = left, m = 0;

            for (int j = mid + 1; j <= right; j++) {
                while (i <= mid && arr[i] <= arr[j]) {
                    temp[m++] = arr[i++];
                }
                //对于每一个k，查找第一个arr[k]/2.0 > arr[j]的下标
                while (k <= mid && arr[k]/2.0 <= arr[j]) {
                    k++;
                }
                this.count = this.count + mid - k + 1;
                temp[m++] = arr[j];
            }
            while (i <= mid) {
                temp[m++] = arr[i++];
            }

            for (int n = 0; n < temp.length; n++) {
                arr[left + n] = temp[n];
            }
            return count;
        }






    }
//leetcode submit region end(Prohibit modification and deletion)

}
