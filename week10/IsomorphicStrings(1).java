package com.sundy.algorithm.leetcode.editor.cn;

//给定两个字符串 s 和 t，判断它们是否是同构的。 
//
// 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。 
//
// 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。 
//
// 
//
// 示例 1: 
//
// 
//输入：s = "egg", t = "add"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "foo", t = "bar"
//输出：false 
//
// 示例 3： 
//
// 
//输入：s = "paper", t = "title"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 可以假设 s 和 t 长度相同。 
// 
// Related Topics 哈希表 
// 👍 358 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author: sundy
 * @Date: 2021-06-08 22:39:19
 */
public class IsomorphicStrings{
    public static void main(String[] args){
        Solution solution = new IsomorphicStrings().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 同构字符串
     */
    class Solution {

        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> map = new HashMap<>();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                if (map.get(t.charAt(i)) != null && map.get(t.charAt(i)) != s.charAt(i)) {
                    return false;
                }
                if (map.get(t.charAt(i)) == null && set.contains(s.charAt(i))) {
                    return false;
                }
                map.put(t.charAt(i), s.charAt(i));
                set.add(s.charAt(i));
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
