package com.sundy.algorithm.leetcode.editor.cn;

//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。 
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表 
// 👍 539 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author: sundy
 * @Date: 2021-06-05 22:14:39
 */
public class FindAllAnagramsInAString{
    public static void main(String[] args){
        Solution solution = new FindAllAnagramsInAString().new Solution();
        solution.findAnagrams("cbaebabacd", "abc");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 找到字符串中所有字母异位词
     * 超时
     */
    class Solution {
        public List<Integer> findAnagrams1(String s, String p) {
            List<Integer> list = new ArrayList<>();
            int gap = p.length();
            for (int i = 0; i < s.length() - gap + 1; i++) {
                String substring = s.substring(i, i + gap);
                if (isAnagrams(substring, p)) {
                    list.add(i);
                }
            }
            return list;
        }

        /**
         * 判断是否是异位词
         * @param s
         * @param p
         * @return
         */
        private boolean isAnagrams(String s, String p) {
            if (s.length() != p.length()) {
                return false;
            }
            char[] chars1 = s.toCharArray();
            char[] chars2 = p.toCharArray();
            Arrays.sort(chars1);
            Arrays.sort(chars2);
            return Arrays.equals(chars1, chars2);
        }

        /**
         * 滑动窗口
         * @param s
         * @param p
         * @return
         */
        public List<Integer> findAnagrams2(String s, String p) {
            if (s.length() < p.length()) {
                return new ArrayList<>();
            }
            List<Integer> list = new ArrayList<>();
            int m = p.length();
            int[] pcnt = new int[26];
            int[] scnt = new int[26];
            for (int i = 0; i < m; i++) {
                int pIndex = p.charAt(i) - 'a';
                pcnt[pIndex] = pcnt[pIndex] + 1;
                int sIndex = s.charAt(i) - 'a';
                scnt[sIndex] = scnt[sIndex] + 1;
            }
            if (Arrays.equals(pcnt, scnt)) {
                list.add(0);
            }
            for (int j = m; j < s.length(); j++) {
                int needRemoveIndex = s.charAt(j - m) - 'a';
                scnt[needRemoveIndex] = scnt[needRemoveIndex] - 1;
                int needAddIndex = s.charAt(j) - 'a';
                scnt[needAddIndex] =  scnt[needAddIndex] + 1;
                if (Arrays.equals(pcnt, scnt)) {
                    list.add(j - m + 1);
                }
            }
            return list;
        }

        /**
         * 滑动窗口 + 左右指针
         * @param s
         * @param p
         * @return
         */
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> list = new ArrayList<>();
            int[] needs = new int[26];
            int[] windows = new int[26];
            for (int i = 0; i < p.length(); i++) {
                int index = p.charAt(i) - 'a';
                needs[index] = needs[index] + 1;
            }
            int left = 0, right = 0;
            while (right < s.length()) {
                int rIndex = s.charAt(right) - 'a';
                windows[rIndex] = windows[rIndex] + 1;
                right++;
                //只要windows[rIndex] > needs[rIndex]，左指针就一直可以往右边移动，因为中间的都不可能符合条件
                //cbaebabacd abc, 当右指针滑动到e的时候，左右指针都会指向index=4的位置，此时窗口中没有元素
                while (windows[rIndex] > needs[rIndex]) {
                    int lIndex = s.charAt(left) - 'a';
                    windows[lIndex] = windows[lIndex] - 1;
                    left++;
                }
                if (right - left == p.length()) {
                    list.add(left);
                }
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
