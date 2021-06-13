package com.sundy.algorithm.leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3710 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-06-06 08:09:57
 */
public class LongestPalindromicSubstring{
    public static void main(String[] args){
        Solution solution = new LongestPalindromicSubstring().new Solution();
        solution.longestPalindrome("cbbd");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最长回文子串
     */
    class Solution {
        private int low, maxLen;

        public String longestPalindrome1(String s) {
            char[] chars = s.toCharArray();
            //i表示长度
            for (int i = chars.length - 1; i >= 0; i--) {
                for (int j = 0; j < chars.length - i; j++) {
                    if (isPalindrome(chars, j, j + i)) {
                        return s.substring(j, j + i + 1);
                    }
                }
            }
            return "";
        }

        public boolean isPalindrome(char[] chars, int start, int end) {
            int i = start, j = end;
            while (i < j) {
                if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }

        /**
         * 枚举回文串的中心，向两边扩散
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }
            for (int i = 0; i < s.length() - 1; i++) {
                //odd length
                extendPalindrome(s, i, i);
                //even length
                extendPalindrome(s, i, i + 1);
            }
            return s.substring(low, low + maxLen);
        }

        private void extendPalindrome(String s, int i, int j) {
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            if (maxLen < (j - i - 1) ) {
                low = i + 1;
                maxLen = j - i - 1;
            }
        }


        /**
         * 动态规划
         * @param s
         * @return
         */
        public String longestPalindrome3(String s) {
            int n = s.length();
            char[] chars = s.toCharArray();
            String result = "";
            boolean[][] dp = new boolean[n][n];
            //二维矩阵的右上半边
            for (int i = n-1; i >= 0 ; i--) {
                for (int j = i; j < n; j++) {
                    dp[i][j] = chars[i] == chars[j] && (j - i < 2 || dp[i + 1][j - 1]);
                    if (dp[i][j] && j - i + 1 > result.length()) {
                        result = s.substring(i, j + 1);
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
