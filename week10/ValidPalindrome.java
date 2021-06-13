package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串 
// 👍 385 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-06-06 07:21:46
 */
public class ValidPalindrome{
    public static void main(String[] args){
        Solution solution = new ValidPalindrome().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 验证回文串
     */
    class Solution {
        public boolean isPalindrome(String s) {
            int i = 0, j = s.length() - 1;
            char[] chars = s.toCharArray();
            while (i < j) {
                while (i < j && !isDigitOrLetter(chars[i])) {
                    i++;
                }
                while (i < j && !isDigitOrLetter(chars[j])) {
                    j--;
                }
                if (Character.toLowerCase(chars[i++]) != Character.toLowerCase(chars[j--])) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Character.isLetterOrDigit
         * @param ch
         * @return
         */
        public boolean isDigitOrLetter(char ch) {
            return (ch >= 'a' && ch <= 'z' )||(ch >= '0' && ch <= '9');
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
