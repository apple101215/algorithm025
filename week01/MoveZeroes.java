package com.sundy.algorithm.leetcode.editor.cn;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 987 👎 0

import com.alibaba.fastjson.JSON;

/**
 * 移动零
 * 解法1(优秀)：index
 * 解法2(优秀)：在3的基础上，滚雪球算法，nums[i] != 0判断加上countZeroes>0
 * @author shawn
 * @date 2021-04-03 14:18:15
 */
public class MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        int[] nums = new int[]{0,1,0,3,12};
        //方法1
//        solution.moveZeroes(nums);
        //方法2
//        nums = solution.moveZeroes2(nums);
//        solution.moveZeroesAfterOneDay(nums);
        solution.moveZeroes1(nums);

        System.out.println(JSON.toJSONString(nums));
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes1(int[] nums) {
       int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                   nums[i] = 0;
                }
                j++;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int snowBallCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                snowBallCount ++;
            }
            //必须判断snowBallCount >0，否则nums[i] = 0会错误，入参为[1]的时候，会出错
            if (nums[i] != 0 && snowBallCount > 0){
                nums[i-snowBallCount] == nums[i];
                nums[i] = 0;
            }

        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}