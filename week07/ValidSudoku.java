package com.sundy.algorithm.leetcode.editor.cn;

//请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 注意： 
//
// 
// 一个有效的数独（部分已被填充）不一定是可解的。 
// 只需要根据以上规则，验证已经填入的数字是否有效即可。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = 
//[["5","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = 
//[["8","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：false
//解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无
//效的。 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 
// Related Topics 哈希表 
// 👍 520 👎 0

import java.util.HashMap;

/**
 *
 * @author: sundy
 * @Date: 2021-05-13 20:30:01
 */
public class ValidSudoku{
    public static void main(String[] args){
        Solution solution = new ValidSudoku().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 有效数独
     */
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            HashMap<Integer, Integer>[] rows = new HashMap[board.length];
            HashMap<Integer, Integer>[] cols = new HashMap[board.length];
            HashMap<Integer, Integer>[] boxes = new HashMap[board.length];
            for (int i = 0; i < board.length; i++) {
                rows[i] = new HashMap<>(board.length);
                cols[i] = new HashMap<>(board.length);
                boxes[i] = new HashMap<>(board.length);
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != '.'){
                        if (rows[i].containsKey((int)board[i][j])) {
                            return false;
                        }
                        if (cols[j].containsKey((int)board[i][j])) {
                            return false;
                        }
                        int boxIndex = (i/3) * 3 + j/3;
                        if (boxes[boxIndex].containsKey((int)board[i][j])) {
                            return false;
                        }
                        rows[i].put((int) board[i][j], 1);
                        cols[j].put((int) board[i][j], 1);
                        boxes[boxIndex].put((int) board[i][j], 1);
                    }
                }
            }
            return true;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
