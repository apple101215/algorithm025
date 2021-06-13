package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 294 👎 0

import com.sun.tools.javac.util.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author: sundy
 * @Date: 2021-06-05 18:37:59
 */
public class ReverseWordsInAStringIii{
    public static void main(String[] args){
        Solution solution = new ReverseWordsInAStringIii().new Solution();
        solution.reverseWords("Let's take LeetCode contest");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 翻转字符串中的单词III
     */
    class Solution {
        /**
         * 利用库函数
         * @param s
         * @return
         */
        public String reverseWords(String s) {
           String result =  new StringBuilder(s).reverse().toString();
            String[] strings = result.split(" +");
            Collections.reverse(Arrays.asList(strings));
            //join函数默认加了trim
            return String.join(" ", strings);
        }


        public String reverseWords2(String s) {
            char[] chars = s.toCharArray();
            int nextWordBegin = 0;
            boolean nextWordFlag = false;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    reverse(chars, nextWordBegin, i -1);
                    nextWordFlag = false;
                    continue;
                }
                //step 1,需要放在step2的前面，因为最后一个单词可能只有一个字母
                if (!nextWordFlag) {
                    nextWordBegin = i;
                    nextWordFlag = true;
                }
                //step 2,
                if (i == chars.length -1) {
                    reverse(chars, nextWordBegin, i);
                }

            }
            return String.valueOf(chars);
        }

        /**
         * 翻转
         * @param chars
         * @param nextWordBegin
         * @param end
         */
        private void reverse(char[] chars, int nextWordBegin, int end) {
            int i = nextWordBegin, j = end;
            while (i < j) {
                char temp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = temp;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
