package com.sundy.algorithm.leetcode.editor.cn;

//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 哈希表 回溯算法 
// 👍 836 👎 0

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author: sundy
 * @Date: 2021-05-14 08:42:48
 */
public class SudokuSolver{
    public static void main(String[] args){
        Solution solution = new SudokuSolver().new Solution();
        solution.solveSudoku2(new char[][] {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}});
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 解数独
     */
    class Solution {
        public void solveSudoku1(char[][] board) {
            solveSudokuHepler(board);
        }

        public boolean solveSudokuHepler(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == '.') {
                        for (char k = '1'; k <= '9' ; k++) {
                            if (isValid(board, i, j, k)) {
                                board[i][j] = k;
                                if (solveSudokuHepler(board)) {
                                    return true;
                                }else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValid(char[][] board, int i, int j, char k) {
            int sourceBoxIndex = (i/3) * 3 + j/3;
            for (int l = 0; l < board.length; l++) {
                for (int m = 0; m < board[l].length; m++) {
                    if (l == i) {
                        if (board[l][m] == k) {
                            return false;
                        }
                    }
                    if (m == j) {
                        if (board[l][m] == k) {
                            return false;
                        }
                    }
                    int boxIndex = (l/3) * 3 + m/3;
                    if (boxIndex == sourceBoxIndex) {
                        if (board[l][m] == k) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        /**
         * 解法2
         * @param board
         */
        public void solveSudoku2(char[][] board) {
            List<Integer>[] cols = init(board);
            List<Integer>[] rows = init(board);
            List<Integer>[] boxes = init(board);
            List<Pair<Integer, Integer>> empty = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != '.') {
                        rows[i].remove(Integer.valueOf(board[i][j] - '0'));
                        cols[j].remove(Integer.valueOf(board[i][j] - '0'));
                        int boxIndex = (i/3) * 3 + j/3;
                        boxes[boxIndex].remove(Integer.valueOf(board[i][j] - '0'));
                    }else {
                        empty.add(new Pair<>(i,j));
                    }
                }
            }
            solveSudoku2Helper(empty, 0, cols, rows, boxes, board);
        }

        public boolean solveSudoku2Helper(List<Pair<Integer, Integer>> empty, int emptyIndex, List<Integer>[] cols, List<Integer>[] rows, List<Integer>[] boxes, char[][] board) {
            if (emptyIndex == empty.size()){
                return true;
            }
            Pair<Integer, Integer> pair = empty.get(emptyIndex);
            int i = pair.getKey();
                int j = pair.getValue();
                int boxIndex = (i/3) * 3 + j/3;
                List<Integer> retainNums = new ArrayList<>(rows[i]);
                retainNums.retainAll(cols[j]);
                retainNums.retainAll(boxes[boxIndex]);
                for (int k = 0; k < retainNums.size(); k++) {
                    rows[i].remove(retainNums.get(k));
                    cols[j].remove(retainNums.get(k));
                    boxes[boxIndex].remove(retainNums.get(k));
                    board[i][j] = (char)('0' + retainNums.get(k));
                    if (solveSudoku2Helper(empty, emptyIndex +1 , cols, rows, boxes, board)) {
                        return true;
                    }
                    rows[i].add(retainNums.get(k));
                    cols[j].add(retainNums.get(k));
                    boxes[boxIndex].add(retainNums.get(k));
                }
                return false;
            }

        private ArrayList<Integer>[] init(char[][] board) {
            ArrayList<Integer>[] list = new ArrayList[board.length];
            for (int i = 0; i < board.length; i++) {
                list[i] = new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                    add(3);
                    add(4);
                    add(5);
                    add(6);
                    add(7);
                    add(8);
                    add(9);
                }};
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
