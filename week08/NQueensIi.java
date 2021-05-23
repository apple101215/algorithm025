package com.sundy.algorithm.leetcode.editor.cn;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
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
// 👍 260 👎 0

import java.util.ArrayList;

/**
 *
 * @author: sundy
 * @Date: 2021-05-23 18:12:14
 */
public class NQueensIi{
    public static void main(String[] args){
        Solution solution = new NQueensIi().new Solution();
        solution.totalNQueens(4);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * N皇后II
     */
    class Solution {
        int count = 0;
        /**
         * 位运算
         * @param n
         * @return
         */
        public int totalNQueens(int n) {
            if (n < 1) {
                return 0;
            }
            dfs(n, 0, 0, 0, 0);
            return count;
        }

        private void dfs(int n, int row, int cols, int pie, int na) {
            if (row == n) {
                count++;
                return;
            }
            int bits = (~(cols|pie|na)) & ((1 << n) -1);
            while (bits != 0) {
                //取最低位的1
                int p = bits & -bits;
                //清零最低位的1
                bits = bits & (bits -1);
                dfs(n, row + 1, cols|p, (pie | p) << 1, (na | p) >> 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
