package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串 
// 👍 80 👎 0

/**
 *
 * @author: sundy
 * @Date: 2021-06-05 19:23:44
 */
public class ReverseOnlyLetters{
    public static void main(String[] args){
        Solution solution = new ReverseOnlyLetters().new Solution();
        solution.reverseOnlyLetters("ab-cd");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 仅仅反转字母
     */
    class Solution {
        public String reverseOnlyLetters(String s) {
            char[] chars = s.toCharArray();
            int i = 0, j = chars.length -1;
            while (i < j) {
                while (i < j && !isLetter(chars[i])){i++;}
                while (i < j && !isLetter(chars[j])){j--;}
                char temp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = temp;
            }
            return String.valueOf(chars);
        }

        private boolean isLetter(char ch) {
            return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
