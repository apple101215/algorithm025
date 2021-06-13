package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串 
// 👍 125 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-06-05 16:18:27
 */
public class ReverseStringIi{
    public static void main(String[] args){
        Solution solution = new ReverseStringIi().new Solution();
        solution.reverseStr1("abcdefg", 2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 反转字符串 II
     */
    class Solution {

        /**
         * 暴力
         * @param s
         * @param k
         * @return
         */
        public String reverseStr1(String s, int k) {
            char[] chars = s.toCharArray();
            for (int start = 0; start < chars.length; start = start + 2 * k) {
                for (int i = start, j = Math.min(start + k - 1, (chars.length - 1)); i < j; i++, j--) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
            return String.valueOf(chars);
        }


        public String reverseStr2(String s, int k) {
            char[] chars = s.toCharArray();
            for (int start = 0; start < s.length(); start = start + 2 * k) {
                int i = start, j = Math.min(start + k -1, s.length() - 1);
                while (i < j) {
                    char temp = chars[i];
                    chars[i++] = chars[j];
                    chars[j--] = temp;
                }
            }
            return String.valueOf(chars);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
