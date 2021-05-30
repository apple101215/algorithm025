package com.sundy.algorithm.leetcode.editor.cn;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 排序 数组 
// 👍 948 👎 0

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 *
 * @author: sundy
 * @Date: 2021-05-29 11:34:45
 */
public class MergeIntervals{
    public static void main(String[] args){
        Solution solution = new MergeIntervals().new Solution();

        String[][] intervals = new String[3][3];
        Arrays.sort(intervals, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int left = o1.length, right = o2.length;
                int i = 0;
                while ( i <= left && i <= right) {
                    if (o1[i].compareTo(o2[i]) != 0) {
                        return o1[i].compareTo(o2[i]);
                    }else {
                        i++;
                    }
                }
                if (left >= i) {
                    return 1;
                }
                if (right >= i) {
                    return -1;
                }
                return 0;
            }
        });
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 合并区间
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            List<int[]> mergeList = new ArrayList<>();
            for (int i = 0; i < intervals.length; i++) {
                int left = intervals[i][0]; int right = intervals[i][1];
                if (mergeList.size() == 0 || mergeList.get(mergeList.size() - 1)[1] < left) {
                    mergeList.add(new int[]{left, right});
                }else {
                    mergeList.get(mergeList.size() - 1)[1] = Math.max(mergeList.get(mergeList.size() - 1)[1], right);
                }
            }
            return mergeList.toArray(new int[mergeList.size()][]);

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
