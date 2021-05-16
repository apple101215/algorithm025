package com.sundy.algorithm.leetcode.editor.cn;

//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意： 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 
//
// 示例 1： 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2： 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3： 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// 👍 73 👎 0

import java.util.*;

/**
 *
 * @author: sundy
 * @Date: 2021-05-15 18:00:48
 */
public class MinimumGeneticMutation{
    public static void main(String[] args){
        Solution solution = new MinimumGeneticMutation().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最小基因变化
     */
    class Solution {
        List<Character> candidate = new ArrayList<Character>(){{add('A');add('C');add('G');add('T');}};
        public int minMutation1(String start, String end, String[] bank) {
            HashSet<String> bankSet = new HashSet<>(Arrays.asList(bank));
            if (bankSet.size() == 0 || !bankSet.contains(end)) {
                return -1;
            }
            Set<String> visited = new HashSet<>();
            visited.add(start);
            Queue<String> queue = new LinkedList<>();
            queue.offer(start);
            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String poll = queue.poll();
                    if (changeOneLetter(poll, end, bankSet, queue, visited)) {
                        return step + 1;
                    }
                }
                step++;
            }
            return -1;
        }

        private boolean changeOneLetter(String poll, String end, HashSet<String> bankSet, Queue<String> queue, Set<String> visited) {
            char[] chars = poll.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char old = chars[i];
                for (Character character : candidate) {
                    chars[i] = character;
                    String newWord = String.valueOf(chars);
                    if (bankSet.contains(newWord)) {
                        if (newWord.equals(end)) {
                            return true;
                        }
                        if (!visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                }
                chars[i] = old;
            }
            return false;
        }

        /**
         * 双向BFS
         * @param start
         * @param end
         * @param bank
         * @return
         */
        public int minMutation(String start, String end, String[] bank) {
            HashSet<String> bankSet = new HashSet<>(Arrays.asList(bank));
            if (bankSet.size() == 0 || !bankSet.contains(end)) {
                return -1;
            }
            HashSet<String> startSet = new HashSet<>();
            startSet.add(start);
            HashSet<String> endSet = new HashSet<>();
            endSet.add(end);
            HashSet<String> visited = new HashSet<>();
            int step = 0;
            while (!startSet.isEmpty()) {
                HashSet<String> temp = new HashSet<>();
                for (String word : startSet) {
                    char[] chars = word.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        char old = chars[i];
                        for (Character character : candidate) {
                            chars[i] = character;
                            String newWord = String.valueOf(chars);
                            if (bankSet.contains(newWord)) {
                                if (endSet.contains(newWord)) {
                                    return step + 1;
                                }
                                if (!visited.contains(newWord)) {
                                    temp.add(newWord);
                                    visited.add(newWord);
                                }
                            }
                        }
                        chars[i] = old;
                    }
                }
                step++;
                startSet = temp;
                if (startSet.size() > endSet.size()) {
                    HashSet<String> set = startSet;
                    startSet = endSet;
                    endSet = set;
                }
            }
            return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
