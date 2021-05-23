package com.sundy.algorithm.leetcode.editor.cn;

//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学 
// 👍 317 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-05-23 16:17:44
 */
public class PowerOfTwo{
    public static void main(String[] args){
        Solution solution = new PowerOfTwo().new Solution();
        System.out.println(~1);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 2的幂
     */
    class Solution {
        public boolean isPowerOfTwo1(int n) {
            if (n == 0 || n < 0) {
                return false;
            }
            return (n & n-1) == 0;
        }

        public boolean isPowerOfTwo2(int n) {
            if (n == 0 || n < 0) {
                return false;
            }
            //X&-X等到最低位的1
            return (n & -n) == n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
