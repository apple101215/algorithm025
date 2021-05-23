package com.sundy.algorithm.leetcode.editor.cn;

//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 390 👎 0

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author: sundy
 * @Date: 2021-05-22 15:56:43
 */
public class WordSearchIi{
    public static void main(String[] args){
        Solution solution = new WordSearchIi().new Solution();
        solution.findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"});
//        solution.findWords(new char[][]{{'a'}, {'a'}}, new String[]{"aa"});
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 单词搜索II
     */
    class Solution {
        /**
         * 字典树+DFS
         * @param board
         * @param words
         * @return
         */
        public List<String> findWords(char[][] board, String[] words) {
            int[] dx = new int[] {0, 0, 1, -1};
            int[] dy = new int[] {1, -1, 0, 0};
            Set<String> result = new HashSet<>();
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    dfs(trie, "", board, i, j, result, dx, dy);
                }
            }
            return new ArrayList<>(result);
        }

        public void dfs (Trie trie, String temp, char[][] board, int i, int j, Set<String> result, int[] dx, int[] dy) {
            if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1
                    || board[i][j] == '@' || trie.children[board[i][j] - 'a'] == null) {
                return;
            }
            char c = board[i][j];
            temp = temp + board[i][j];
            trie = trie.children[board[i][j] - 'a'];
            if (trie.isEnd) {
                result.add(temp);
            }
            board[i][j] = '@';
            for (int k = 0; k < 4; k++) {
                dfs(trie, temp , board, i + dx[k] , j + dy[k], result, dx, dy);
            }
            board[i][j] = c;
        }

        class Trie {
            private Trie[] children;
            private boolean isEnd;

            /** Initialize your data structure here. */
            public Trie() {
                children = new Trie[26];
                isEnd = false;
            }

            /** Inserts a word into the trie. */
            public void insert(String word) {
                Trie node = this;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    int index = ch - 'a';
                    if (node.children[index] == null) {
                        node.children[index] = new Trie();
                    }
                    node = node.children[index];
                }
                node.isEnd = true;
            }

            /** Returns if the word is in the trie. */
            public boolean search(String word) {
                Trie node = searchPrefix(word);
                //注意这里要判断是不是end
                return node != null && node.isEnd;
            }

            /** Returns if there is any word in the trie that starts with the given prefix. */
            public boolean startsWith(String prefix) {
                Trie node = searchPrefix(prefix);
                return node != null;
            }

            private Trie searchPrefix(String prefix) {
                Trie node = this;
                for (int i = 0; i < prefix.length(); i++) {
                    char ch = prefix.charAt(i);
                    int index = ch - 'a';
                    if (node.children[index] == null) {
                        return null;
                    }
                    node = node.children[index];
                }
                return node;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
