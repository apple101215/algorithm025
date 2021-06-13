package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心算法 字符串 动态规划 回溯算法 
// 👍 700 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-06-12 12:27:52
 */
public class WildcardMatching{
    public static void main(String[] args){
        Solution solution = new WildcardMatching().new Solution();
        solution.isMatch("aa", "*");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 通配符匹配
     */
    class Solution {
        /**
         * 递归，超时
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch1(String s, String p) {
            if (p.length() == 0) {
                return s.length() == 0;
            }
            boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?');
            if (p.charAt(0) == '*') {
                return isMatch1(s, p.substring(1)) || (s.length() > 0 && isMatch1(s.substring(1), p));
            } else {
                return firstMatch && isMatch1(s.substring(1), p.substring(1));
            }

        }

        /**
         * 递归+记忆化搜索
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch2(String s, String p) {
            //注意数组下标要记得+ 1，因为递归终止条件：p.length() == j
            return isMatchHepler(s, p, 0, 0, new Boolean[s.length() + 1][p.length() + 1]);
        }

        public boolean isMatchHepler(String s, String p, int i, int j, Boolean[][] dp) {
            if (p.length() == j) {
                return s.length() == i;
            }
            if (dp[i][j] != null) {
                return dp[i][j];
            }
            boolean firstMatch = s.length() > i && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
            boolean result;
            if (p.charAt(j) == '*') {
                result = isMatchHepler(s, p, i, j + 1, dp ) || (s.length() > i && isMatchHepler(s, p, i + 1, j, dp));
            } else {
                result = firstMatch && isMatchHepler(s, p, i + 1, j+ 1, dp);
            }
            return dp [i][j] = result;
        }

        /**
         * 动态规划
         * dp[i][j] 表示s的前i个字符和p的前j个字符是否能够匹配
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch3(String s, String p) {
            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
            dp[0][0] = true;
            //i要从0开始，空串和"*"能匹配上
            for (int i = 0; i <= s.length(); i++) {
                for (int j = 1; j <= p.length(); j++) {
                    if (p.charAt(j - 1) == '*') {
                       dp[i][j] = dp[i][j - 1] || (i > 0 && dp[i - 1][j]);
                    } else {
                        dp[i][j] = matches(s, p, i, j) && (i > 0 && dp[i - 1][j - 1]);
                    }
                }
            }
            return dp[s.length()][p.length()];
        }

        private boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            return s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?';
        }

        /**
         * 贪心算法
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch(String s, String p) {
            int sRight = s.length() - 1; int pRight = p.length() - 1;
            //匹配最后一个星号后面的字符
            while (sRight >= 0 && pRight >= 0 && p.charAt(pRight) != '*') {
                if (charMatch(s.charAt(sRight), p.charAt(pRight))) {
                    sRight--;
                    pRight--;
                } else {
                    return false;
                }
            }
            if (pRight == -1) {
                return sRight == -1;
            }
            int sIndex = 0, pIndex = 0;
            //-1表示害没有遇到过星号,pRecord表示*号后面的第一个字符，sRecord表示遇到*号的第一个字符
            int sRecord = -1,pRecord = -1;
            while (sIndex <= sRight && pIndex <= pRight) {
                if (p.charAt(pIndex) == '*') {
                    pIndex++;
                    sRecord = sIndex;
                    pRecord = pIndex;
                } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                    sIndex++;
                    pIndex++;
                } else if (sRecord != -1 && sRecord + 1 <= sRight){
                    //将遇到星号的第一个字符往后加1，继续匹配，例如：*abcd*ef*, abcabcdmnefgh或者aabcdefmn
                    sRecord++;
                    sIndex = sRecord;
                    pIndex = pRecord;
                } else {
                    return false;
                }
            }

            for (int i = pIndex; i <= pRight ; i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        private boolean charMatch(char u, char v) {
            return u == v || v== '?';
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
