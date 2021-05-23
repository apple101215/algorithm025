package com.sundy.algorithm.leetcode.editor.cn;

//编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。 
//
// 
//
// 提示： 
//
// 
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的
//还是无符号的，其内部的二进制表示形式都是相同的。 
// 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
// 
//
// 示例 2： 
//
// 
//输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
// 
//
// 示例 3： 
//
// 
//输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。 
//
// 
//
// 提示： 
//
// 
// 输入必须是长度为 32 的 二进制串 。 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 如果多次调用这个函数，你将如何优化你的算法？ 
// 
// Related Topics 位运算 
// 👍 349 👎 0

/**
 * @author: sundy
 * @Date: 2021-05-23 11:04:50
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new NumberOf1Bits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 位1的个数
     */
    public class Solution {
        // you need to treat n as an unsigned value

        /**
         * 循环检查
         * @param n
         * @return
         */
        public int hammingWeight1(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                //X & 1 << n ,获取X的第n位幂值
                if ((n & (1 << i)) != 0 ) {
                    count++;
                }
            }
            return count;
        }

        /**
         * 清零最低位的1
         * @param n
         * @return
         */
        public int hammingWeight2(int n) {
            int count = 0;
            while (n != 0) {
                //每次清零最低位的1
                n = n & (n-1);
                count++;
            }
            return count;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}