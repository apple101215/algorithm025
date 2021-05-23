package com.sundy.algorithm.leetcode.editor.cn;

//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X"
//,"X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [["X"]]
//输出：[["X"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 542 👎 0

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author: sundy
 * @Date: 2021-05-23 08:31:40
 */
public class SurroundedRegions{
    public static void main(String[] args){
        Solution solution = new SurroundedRegions().new Solution();
        solution.solve(new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}});
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 被围绕的区域
     */
    class Solution {
        /**
         * DFS
         * @param board
         */
        public void solve1(char[][] board) {
            int[] dx = new int[] {0, 0, -1, 1};
            int[] dy = new int[] {-1, 1, 0, 0};

            for (int i = 0; i < board.length; i++) {
                dfs(board, i, 0, dx, dy);
                dfs(board, i, board[0].length -1, dx, dy);
            }
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, 0, j, dx, dy);
                dfs(board, board.length - 1, j, dx, dy);
            }

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 'X') {
                        continue;
                    }
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        /**
         * 将与外层O相关联的格子设置成A
         * @param board
         * @param i
         * @param j
         * @param dx
         * @param dy
         */
        public void dfs(char[][] board, int i, int j, int[] dx, int[] dy) {
            if (i < 0 || i > board.length -1 || j < 0 || j > board[0].length-1 || board[i][j] != 'O') {
                return;
            }
            board[i][j] = 'A';
            for (int k = 0; k < 4; k++) {
                dfs(board, i + dx[k], j+ dy[k], dx, dy);
            }
        }

        /**
         * BFS
         * @param board
         */
        public void solve2(char[][] board) {
            int[] dx = new int[] {0, 0, -1, 1};
            int[] dy = new int[] {-1, 1, 0, 0};
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < board.length; i++) {
                if (board[i][0] == 'O') {
                    queue.offer(new int[] {i, 0});
                }
                if (board[i][board[i].length - 1] == 'O') {
                    queue.offer(new int[] {i, board[i].length - 1});
                }
            }
            for (int j = 0; j < board[0].length; j++) {
                if (board[0][j] == 'O') {
                    queue.offer(new int[]{0, j});
                }
                if (board[board.length - 1][j] == 'O') {
                    queue.offer(new int[]{board.length -1, j});
                }
            }
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                board[poll[0]][poll[1]] = 'A';
                for (int k = 0; k < 4; k++) {
                    int newX = poll[0] + dx[k];
                    int newY = poll[1] + dy[k];
                    if (newX < 0 || newX > board.length -1 || newY < 0 || newY > board[0].length-1 || board[newX][newY] != 'O') {
                        continue;
                    }
                    queue.offer(new int[]{newX, newY});
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 'X') {
                        continue;
                    }
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        /**
         * 并查集
         * @param board
         */
        public void solve(char[][] board) {
            int[] dx = new int[] {0, 0, -1, 1};
            int[] dy = new int[] {-1, 1, 0, 0};
            UnionFind unionFind = new UnionFind(board.length * board[0].length + 1);
            int virtualNode = board.length * board[0].length;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'O') {
                        if(i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1){
                            unionFind.union(virtualNode, i * board[0].length + j);
                        }else {
                            for (int k = 0; k < 4; k++) {
                                int newX = i + dx[k];
                                int newY = j + dy[k];
                                if (newX < 0 || newX > board.length - 1 || newY < 0 || newY > board[0].length - 1 || board[newX][newY] != 'O') {
                                    continue;
                                }
                                unionFind.union(i * board[0].length + j, newX * board[0].length + newY);
                            }

                        }
                    }

                }
            }
            for (int i = 0; i <board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 'O' && !unionFind.isConnected(virtualNode, i * board[0].length + j)) {
                        board[i][j] = 'X';
                    }
                }

            }
        }

        class UnionFind {
            private int count = 0;
            private int[] parent;

            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int p) {
                while (p != parent[p]) {
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                return p;
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return;
                parent[rootP] = rootQ;
                count--;
            }

            boolean isConnected(int node1, int node2) {
                return find(node1) == find(node2);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
