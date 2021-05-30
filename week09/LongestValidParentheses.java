package com.sundy.algorithm.leetcode.editor.cn;

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 字符串 动态规划 
// 👍 1320 👎 0

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 *
 * @author: sundy
 * @Date: 2021-05-30 17:53:36
 */
public class LongestValidParentheses{
    public static void main(String[] args){
        Solution solution = new LongestValidParentheses().new Solution();
        int result = solution.longestValidParentheses("(()");
        System.out.println("result is:" + result);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最长有效括号
     */
    class Solution {
        /**
         * 暴力方法
         * @param s
         * @return
         */
        public int longestValidParentheses1(String s) {
            for (int i = s.length()/2 * 2; i > 0; i = i - 2) {
                for (int j = 0; j < s.length(); j++) {
                    if (j + i > s.length()) {
                        break;
                    }
                    String temp = s.substring(j, j + i);
                    if (isValid2(temp)) {
                        return i;
                    }
                }
            }
            return 0;
        }

        /**
         * 判断括号是否有效
         * @param s
         * @return
         */
        public boolean isValid(String s) {
            boolean needLoop = true;
            while (needLoop) {
                int length = s.length();
                s = s.replace("()", "");
                needLoop = length != s.length();
            }
            return s.isEmpty();
        }

        /**
         * 用stack判断有效
         * @param s
         * @return
         */
        public boolean isValid2(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.add('(');
                } else {
                    if (stack.isEmpty()){
                        return false;
                    }
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }

        /**
         * dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2
         * 动态规划
         * @param s
         * @return
         */
        public int longestValidParentheses2(String s) {
            int[] dp = new int[s.length()];
            char[] chars = s.toCharArray();
            int max = 0;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == ')') {
                    if (chars[i-1] == '(' || ((i - dp[i-1] - 1) >= 0 && chars[i - dp[i-1] - 1] == '(')) {
                        dp[i] = 2 + dp[i-1] + ((i - dp[i-1] - 2) > 0 ? dp[i - dp[i-1] - 2] : 0);
                        max = Math.max(max, dp[i]);
                    }
                }
            }
            return max;
        }

        /**
         * 栈
         * 始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
         * 如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
         * 为了保持统一，我们在一开始的时候往栈中放入一个值为 -1−1 的元素。
         * @param s
         * @return
         */
        public int longestValidParentheses3(String s) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int max = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            return max;
        }

        /**
         * 正反向遍历
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, max = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    max = Math.max(max, right * 2);
                }
                if (right > left) {
                    left = 0;
                    right = 0;
                }
            }
            left = 0;
            right = 0;
            for (int j = chars.length - 1; j >= 0 ; j--) {
                if (chars[j] == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    max = Math.max(max, left * 2);
                }
                if (right < left) {
                    left = 0;
                    right = 0;
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
