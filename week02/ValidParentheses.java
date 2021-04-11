package com.sundy.algorithm.leetcode.editor.cn;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2300 👎 0

import javafx.util.Pair;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author: sundy
 * @Date: 2021-04-03 15:18:21
 */
public class ValidParentheses{
    public static void main(String[] args){
        Solution solution = new ValidParentheses().new Solution();
        boolean valid = solution.isValid("()");
        System.out.println(valid);

    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     *
     */
    class Solution {
        /**
         * 匹配判断
         * @param s
         * @return
         */
        public boolean isValid1(String s) {
            Deque<Character> deque = new LinkedList<>();
            Map<Character,Character> map = new HashMap<Character,Character>(4){{
                put('{','}');
                put('[',']');
                put('(',')');
                put('?','?');
            }};
            for (char c : s.toCharArray()) {
               if (map.containsKey(c)) {
                   deque.addFirst(c);
               }else {
                   if (!deque.isEmpty()) {
                       Character pop = deque.pop();
                       if (!map.get(pop).equals(c)) {
                           return false;
                       }
                   }else {
                       return false;
                   }
               }
            }
            return deque.isEmpty();
        }

        /**
         * 替换
         * @param s
         * @return
         */
        public boolean isValid(String s) {
            boolean needLoop = true;
            while (needLoop) {
                int length = s.length();
                s = s.replace("()", "");
                s = s.replace("[]", "");
                s = s.replace("{}", "");
                s = s.replace("??", "");
                needLoop = (length != s.length());
            }
            return s.isEmpty();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
