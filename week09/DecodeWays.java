package com.sundy.algorithm.leetcode.editor.cn;

//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" ，将消息分组为 (1 1 10 6) 
// "KJF" ，将消息分组为 (11 10 6) 
// 
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。 
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 
//
// 示例 4： 
//
// 
//输入：s = "06"
//输出：0
//解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 838 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-05-09 20:36:32
 */
public class DecodeWays{
    public static void main(String[] args){
        Solution solution = new DecodeWays().new Solution();
        solution.numDecodings("12");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 解码方法
     */
    class Solution {
        /**
         * DP
         * @param s
         * @return
         */
        public int numDecodings1(String s) {
            int[] dp = new int[s.length() + 1];
            dp[0] = 1;
            for (int i = 1; i <= s.length(); i++) {
                if (s.charAt(i-1) != '0') {
                    dp[i] = dp[i] + dp[i-1];
                }
                if (i > 1 && s.charAt(i-2) != '0' && (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0') <=26) {
                    dp[i] = dp[i] + dp[i-2];
                }
            }
            return dp[s.length()];
        }

        /**
         * 用变量代替dp
         * @param s
         * @return
         */
        public int numDecodings(String s) {
            //a=dp[i-2],b=dp[i-1],c=dp[i]
            int a = 0,b = 1;
            for (int i = 1; i <= s.length(); i++) {
                int c = 0;
                if (s.charAt(i-1) != '0') {
                    c = c + b;
                }
                if (i > 1 && s.charAt(i-2) != '0' && (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0') <=26) {
                    c = c + a;
                }
                a = b;
                b = c;
            }
            return b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
