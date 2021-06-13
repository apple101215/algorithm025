package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。 
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//(上箭头符号 ^ 表示选取的字母)
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
// 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//(上箭头符号 ^ 表示选取的字母)
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^ 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 530 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-06-07 20:38:06
 */
public class DistinctSubsequences{
    public static void main(String[] args){
        Solution solution = new DistinctSubsequences().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 不同的子序列
     * dp[i][j]表示T的前i个字符可以由s的前j个字符组成最多个数
     */
    class Solution {
        /**
         * 递归
         * @param s
         * @param t
         * @return
         */
        public int numDistinct1(String s, String t) {
            return numDistinctHepler(s, t, 0, 0);
        }
        public int numDistinctHepler(String s, String t, int sStart, int tStart) {
            if (tStart == t.length()) {
                return 1;
            }
            if (sStart == s.length()) {
                return 0;
            }
            int count  = 0;
            if (s.charAt(sStart) == t.charAt(tStart)) {
                count = numDistinctHepler(s, t, sStart + 1, tStart + 1)
                        + numDistinctHepler(s, t, sStart + 1, tStart);
            } else {
                count = numDistinctHepler(s, t, sStart + 1, tStart);
            }
            return count;
        }


        /**
         * 递归+记忆化搜索
         * @param s
         * @param t
         * @return
         */
        public int numDistinct2(String s, String t) {
            Integer[][] dp = new Integer[t.length()][s.length()];
            return numDistinctHepler(s, t, 0, 0, dp);
        }
        public int numDistinctHepler(String s, String t, int sStart, int tStart, Integer[][] dp) {
            if (tStart == t.length()) {
                return 1;
            }
            if (sStart == s.length()) {
                return 0;
            }
            if (dp[tStart][sStart] != null) {
                return dp[tStart][sStart];
            }
            int count  = 0;
            if (s.charAt(sStart) == t.charAt(tStart)) {
                count = numDistinctHepler(s, t, sStart + 1, tStart + 1)
                        + numDistinctHepler(s, t, sStart + 1, tStart);
            } else {
                count = numDistinctHepler(s, t, sStart + 1, tStart);
            }
            return dp[tStart][sStart] = count;
        }

        public int numDistinct(String s, String t) {
            int[][] dp = new int[t.length() + 1][s.length() + 1];
            dp[0][0] = 1;
            for (int j = 0; j < s.length(); j++) {
                dp[0][j] = 1;
            }
            for (int i = 1; i <= t.length(); i++) {
                //可以写j = 1， 优化成j = i, 说明只有j>i时，才有可能组成
                for (int j = i; j <= s.length() ; j++) {
                    if (s.charAt(j - 1) == t.charAt(i - 1)) {
                        //最后一个字符用上 = 前j-1个字符组成前i-1个字符的个数(使用当前字符) + 前i-1个字符组成前i个字符(不使用当前字符)
                        dp[i][j] = dp[i-1][j - 1] + dp[i][j - 1];
                    } else {
                        //最后一个字符不相等，就等于前j-1个字符组成前i个字符最多个数
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
            return dp[t.length()][s.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
