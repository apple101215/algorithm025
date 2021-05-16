package com.sundy.algorithm.leetcode.editor.cn;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 874 👎 0

import java.util.*;

/**
 *
 * @author: sundy
 * @Date: 2021-05-11 22:16:12
 */
public class NQueens{
    public static void main(String[] args){
        Solution solution = new NQueens().new Solution();
        solution.solveNQueens(5);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * N皇后问题
     */
    class Solution {
        List<List<String>> result = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> pie = new HashSet<>();
        Set<Integer> na = new HashSet<>();

        public List<List<String>> solveNQueens(int n) {
            if (n < 1) {
                return new ArrayList<>();
            }
            int[] queues = new int[n];
            Arrays.fill(queues, -1);
            dfs(n, 0, queues);
            return result;
        }

        public void dfs(int n, int row, int[] queues) {
            //terminal
            if (row == n) {
                result.add(generateQueuePosotionStr(queues, n));
                return ;
            }
            //process
            for (int i = 0; i < n; i++) {
                if (cols.contains(i)) {
                    continue;
                }
                if (pie.contains(row + i)) {
                    continue;
                }
                if (na.contains(row -i)) {
                    continue;
                }
                queues[row] = i;
                cols.add(i);
                pie.add(row + i);
                na.add(row - i);
                //drill down
                dfs(n, row + 1, queues);
                //recover
                //注意是queues[row] = -1;
                queues[row] = -1;
                cols.remove(i);
                pie.remove(row + i);
                na.remove(row - i);
            }
        }

        private List<String> generateQueuePosotionStr(int[] queues, int n) {
            List<String> positionStr = new ArrayList<>();
            for (int i = 0; i < queues.length; i++) {
                StringBuilder rowStr = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == queues[i]) {
                        rowStr.append("Q");
                    }else {
                        rowStr.append(".");
                    }
                }
                positionStr.add(rowStr.toString());
            }
            return positionStr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
