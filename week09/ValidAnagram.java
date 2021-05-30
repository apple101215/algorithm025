package com.sundy.algorithm.leetcode.editor.cn;

//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 366 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author: sundy
 * @Date: 2021-04-11 18:10:25
 */
public class ValidAnagram{
    public static void main(String[] args){
        Solution solution = new ValidAnagram().new Solution();
        System.out.println(solution.isAnagram("", ""));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 有效的字母异位词
     * 解法1：排序
     * 解法2：hash
     *
     */
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            Arrays.sort(sChars);
            Arrays.sort(tChars);
            return Arrays.equals(sChars, tChars);
        }

        public boolean isAnagram2(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (Character ch: s.toCharArray()) {
                hashMap.put(ch, hashMap.getOrDefault(ch, 0) +1);
            }
            for (Character ch: t.toCharArray()) {
                hashMap.put(ch, hashMap.getOrDefault(ch, 0) -1);
            }
            for (Map.Entry<Character, Integer> entry: hashMap.entrySet()) {
                if (entry.getValue() != 0) {
                    return false;
                }
            }
            return true;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
