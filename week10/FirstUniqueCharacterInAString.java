package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 397 👎 0

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author: sundy
 * @Date: 2021-06-02 16:09:51
 */
public class FirstUniqueCharacterInAString{
    public static void main(String[] args){
        Solution solution = new FirstUniqueCharacterInAString().new Solution();
        int count = solution.firstUniqChar("leetcode");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 字符串中的第一个唯一字符
     */
    class Solution {
        /**
         * hashMap
         */
        public int firstUniqChar1(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                Integer count = map.getOrDefault(chars[i], 0);
                map.put(chars[i], count + 1);
            }
            for (int i = 0; i < chars.length; i++) {
                if (map.get(chars[i]) == 1) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * hash表优化
         * @param s
         * @return
         */
        public int firstUniqChar(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (map.containsKey(chars[i])) {
                    map.put(chars[i], -1);
                } else {
                    map.put(chars[i], i);
                }
            }
            for (int j = 0; j < chars.length; j++) {
                if (map.get(chars[j]) != -1) {
                    return map.get(chars[j]);
                }
            }
            return -1;
        }

        /**
         * 队列
         * @param s
         * @return
         */
        public int firstUniqChar3(String s) {
            Map<Character, Integer> map = new HashMap<>();
            Queue<Pair> queue = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!map.containsKey(ch)) {
                    map.put(ch, i);
                    queue.offer(new Pair(ch, i));
                } else {
                    map.put(ch, -1);
                    while (!queue.isEmpty() && map.get(queue.peek().ch) == -1){
                        queue.poll();
                    }
                }
            }
            return queue.isEmpty() ? -1 : queue.poll().position;

        }

        class Pair{
            char ch;
            int position;

            public Pair(char ch, int position) {
                this.ch = ch;
                this.position = position;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
