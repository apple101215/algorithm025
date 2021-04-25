package com.sundy.algorithm.leetcode.editor.cn;

//机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ： 
//
// 
// -2 ：向左转 90 度 
// -1 ：向右转 90 度 
// 1 <= x <= 9 ：向前移动 x 个单位长度 
// 
//
// 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点 obstacles[i] = (xi, yi) 。 
//
// 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。 
//
// 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ） 
//
// 
// 
// 
// 
// 
// 
//
// 
// 注意： 
//
// 
// 北表示 +Y 方向。 
// 东表示 +X 方向。 
// 南表示 -Y 方向。 
// 西表示 -X 方向。 
// 
// 
// 
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：commands = [4,-1,3], obstacles = []
//输出：25
//解释：
//机器人开始位于 (0, 0)：
//1. 向北移动 4 个单位，到达 (0, 4)
//2. 右转
//3. 向东移动 3 个单位，到达 (3, 4)
//距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25 
//
// 示例 2： 
//
// 
//输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//输出：65
//解释：机器人开始位于 (0, 0)：
//1. 向北移动 4 个单位，到达 (0, 4)
//2. 右转
//3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
//4. 左转
//5. 向北走 4 个单位，到达 (1, 8)
//距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65 
//
// 
//
// 提示： 
//
// 
// 1 <= commands.length <= 104 
// commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9]. 
// 0 <= obstacles.length <= 104 
// -3 * 104 <= xi, yi <= 3 * 104 
// 答案保证小于 231 
// 
// Related Topics 贪心算法 
// 👍 131 👎 0

import java.util.*;

/**
 *
 * @author: sundy
 * @Date: 2021-04-23 14:55:58
 */
public class WalkingRobotSimulation{
    public static void main(String[] args){
        Solution solution = new WalkingRobotSimulation().new Solution();
//        System.out.println(solution.robotSim(new int[] {7,-2,-2,7,5}, new int[][]{{-3,2},{-2,1},{0,1},{-2,4},{-1,0},{-2,-3},{0,-3},{4,4},{-3,3},{2,2}}));
//        System.out.println(solution.robotSim(new int[] {-2,-1,-2,3,7}, new int[][]{ {1,-3}, {2,-3}, {4,0}, {-2,5}, {-5,2}, {0,0}, {4,-4}, {-2,-5}, {-1,-2}, {0,2}}));
        System.out.println(solution.robotSim(new int[] {2,-1,-1,-1,-2,1,7,-2,9,2,3,-1,4,9,7,7,2,4,2,-2,1,5,8,-2,-2,4,2,9,7,5,5,-2,2,2,1,-1,-1,1,6,6,-1,7,-1,7   ,1,8,2,-1,8,7,-1,2,-2,2,2,   4,9   ,-1,4,-1,-2,8   ,-1,3,6,-2,7}, new int[][]{{75,61},{-27,-13},{-85,77},{-40,-30},{-71,-34},{41,-39},{73,-54},{-19,16},{76,50},{-12,-9},{-25,-100},{45,-86},{-43,-88},{50,-23},{-46,-89},{-66,91},{-57,-46},{-82,51},{78,98},{65,-61},{83,-14},{24,-17},{28,77},{-63,-3},{77,-39},{18,-63},{10,-91},{-11,-15},{-75,-80},{68,92},{21,-70},{91,-53},{-68,-64},{9,-68},{1,40},{-73,20},{56,15},{-90,-43},{-100,99},{-19,7},{14,76},{-80,-2},{24,-34},{47,7},{25,73},{-99,-39},{-55,-9},{85,31},{14,0},{-68,94},{-25,25},{44,-77},{-94,59},{92,-47},{40,5},{-68,-58},{87,39},{-43,93},{-83,-77},{-95,81},{82,37},{66,21},{-5,73},{-75,32},{30,70},{22,-68},{-27,31},{-91,80},{82,-58},{-95,-24},{15,22},{-10,38},{85,96},{68,26},{81,-18},{23,-47},{-80,-78},{30,18},{-56,4},{1,33},{-21,2},{-69,85},{41,92},{-72,79},{-48,-34},{-34,63},{48,-78},{17,73},{16,28},{-13,-14},{16,24},{11,-27},{44,52},{-78,67},{93,65},{-32,-33},{6,-2},{67,-100},{95,77},{-6,28},{10,81},{-45,48},{80,-34},{-49,46},{-38,17},{7,-81},{-29,52},{46,-82},{5,-71},{79,-87},{39,-82},{-78,-82},{-85,19},{74,-55},{22,45},{-40,-24},{44,97},{41,-21},{-17,-92},{17,49},{-1,-33},{39,-36},{37,-38},{41,-29},{72,-88},{-100,57},{-95,74},{-27,-16},{57,-34},{74,-85},{62,92},{44,0},{83,57},{90,96},{-65,70},{-58,99},{-70,-86},{75,-74},{-63,11},{-64,20},{-35,-40},{-86,-71},{-77,-62},{4,-95},{97,76},{36,-62},{-1,90},{99,91},{55,89},{80,77},{40,54},{79,-11},{44,-36},{-35,21},{-13,-86},{-55,84},{27,94},{74,91},{-77,-45},{-90,44},{-80,-35},{-38,80},{34,-28},{45,-77},{1,28},{-88,-50},{-100,87},{19,93},{-26,-39},{-83,-100},{-6,43},{89,42},{-35,-95},{-67,-96},{14,22},{-25,8},{-31,-9},{-94,48},{82,-3},{39,95},{44,47},{-62,-71},{73,-30},{92,-11},{2,85},{-91,97},{99,-18},{-57,-17},{57,73},{-41,9},{44,9},{17,-96},{-95,-16},{40,-3},{-48,-41},{95,18},{-34,-94},{15,-90},{42,11},{-65,-57}}));
//        ,3,6,2,8,6,6,-2,-2,4,2,4,1,2,2,2,8,6,4,6,7,-1,1,-2,-1,-1,7
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 模拟机器人走路
     */
    class Solution {

        /**
         * 看了题解之后写的
         * https://leetcode-cn.com/problems/walking-robot-simulation/solution/jiao-xiang-xi-de-ti-jie-by-alpen/
         * @param commands
         * @param obstacles
         * @return
         */
        public int robotSim(int[] commands, int[][] obstacles) {
            int robotLocationX = 0, robotLocationY = 0;
            int maxDistance = 0;
            //direction表当前朝向，0123 表 北东南西
            int direction = 0;
            int[][] directionCoefficient = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
            Set<String> obstacleSet = new HashSet<String>(){{
                if (obstacles != null) {
                    for (int[] obstacle : obstacles) {
                        add(obstacle[0] + "-" + obstacle[1]);
                    }
                }
            }};
            for (int command : commands) {
                if (command == -1) {
                    direction = (direction + 1) % 4;
                    continue;
                }
                if (command == -2) {
                    direction = (direction + 3) % 4;
                    continue;
                }
                for (int j = 0; j < command; j++) {
                    int targetRobotLocationX = robotLocationX + directionCoefficient[direction][0];
                    int targetRobotLocationY = robotLocationY + directionCoefficient[direction][1];
                    if (obstacleSet.contains(targetRobotLocationX + "-" + targetRobotLocationY)) {
                        break;
                    }
                    robotLocationX = targetRobotLocationX;
                    robotLocationY = targetRobotLocationY;
                    maxDistance = Math.max(maxDistance, robotLocationX * robotLocationX + robotLocationY * robotLocationY);
                }
            }
            return maxDistance;

        }

        /**
         * 错误的方式
         * @param commands
         * @param obstacles
         * @return
         */
        public int robotSim2(int[] commands, int[][] obstacles) {
            int robotLocationX = 0, robotLocationY = 0;
            int maxDistance = 0;
            String direction = "N";
            Map<String, Map<Integer, String>> directionMap = new HashMap<>();
            directionMap.put("N", new HashMap<Integer, String>(2){{put(-2, "W");put(-1, "E");}});
            directionMap.put("E", new HashMap<Integer, String>(2){{put(-2, "N");put(-1, "S");}});
            directionMap.put("W", new HashMap<Integer, String>(2){{put(-2, "S");put(-1, "N");}});
            directionMap.put("S", new HashMap<Integer, String>(2){{put(-2, "E");put(-1, "W");}});

            Map<String, Integer> coefficientMap = new HashMap<String, Integer>(4) {{
                put("N", 1);
                put("E", 1);
                put("W", -1);
                put("S", -1);

            }};
            /**
             * key为y轴值，value为x轴的值
             */
            Map<Integer, List<Integer>> xObstacleMap = new HashMap<Integer, List<Integer>>(){{
                if (obstacles != null) {
                    for (int i = 0; i < obstacles.length; i++) {
                        List<Integer> list = getOrDefault(obstacles[i][1],new ArrayList<>());
                        list.add(obstacles[i][0]);
                        put(obstacles[i][1], list);
                    }
                }
            }};

            /**
             * key为x轴值，value为y轴的值
             */
            Map<Integer, List<Integer>> yObstacleMap = new HashMap<Integer, List<Integer>>(){{
                if (obstacles != null) {
                    for (int i = 0; i < obstacles.length; i++) {
                        List<Integer> list = getOrDefault(obstacles[i][0], new ArrayList<>());
                        list.add(obstacles[i][1]);
                        put(obstacles[i][0], list);
                    }
                }
            }};


            for (int i = 0; i < commands.length; i++) {
                if (commands[i] < 0 ) {
                    direction = directionMap.get(direction).get(commands[i]);
                    continue;
                }else {
                    if ("N".equals(direction) || "S".equals(direction)) {
                        int targetRobotLocationY = robotLocationY + coefficientMap.get(direction) * commands[i];
                        if (yObstacleMap.containsKey(robotLocationX)) {
                            List<Integer> yObstacles = yObstacleMap.getOrDefault(robotLocationX, new ArrayList<>());
                            Collections.sort(yObstacles);
                            if ("N".equals(direction)) {
                                for (int j = 0; j < yObstacles.size(); j++) {
                                    if (yObstacles.get(j) > robotLocationY && yObstacles.get(j) <= targetRobotLocationY) {
                                        if (targetRobotLocationY - robotLocationY > 1) {
                                            targetRobotLocationY = yObstacles.get(j) - 1;
                                        }
                                        break;
                                    }
                                }
                            }
                            if ("S".equals(direction)) {
                                for (int j = yObstacles.size()-1; j >= 0; j--) {
                                    if (yObstacles.get(j) < robotLocationY && yObstacles.get(j) >= targetRobotLocationY) {
                                        if (robotLocationY - targetRobotLocationY > 1) {
                                            targetRobotLocationY = yObstacles.get(j) + 1;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        robotLocationY = targetRobotLocationY;
                    }

                    if ("E".equals(direction) || "W".equals(direction)) {
                        int targetRobotLocationX = robotLocationX + coefficientMap.get(direction) * commands[i];
                        if (xObstacleMap.containsKey(robotLocationY)) {
                            List<Integer> xObstacles = xObstacleMap.getOrDefault(robotLocationY, new ArrayList<>());
                            Collections.sort(xObstacles);
                            if ("E".equals(direction)) {
                                for (int j = 0; j < xObstacles.size(); j++) {
                                    if (xObstacles.get(j) > robotLocationX && xObstacles.get(j) <= targetRobotLocationX) {
                                        if (targetRobotLocationX - robotLocationX > 1) {
                                            targetRobotLocationX = xObstacles.get(j) - 1;
                                        }
                                        break;
                                    }
                                }
                            }
                            if ("W".equals(direction)) {
                                for (int j = xObstacles.size()-1; j >= 0; j--) {
                                    if (xObstacles.get(j) < robotLocationX && xObstacles.get(j) >= targetRobotLocationX) {
                                        if (robotLocationX - targetRobotLocationX > 1) {
                                            targetRobotLocationX = xObstacles.get(j) + 1;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        robotLocationX = targetRobotLocationX;
                    }
                    maxDistance = Math.max(maxDistance, robotLocationX * robotLocationX + robotLocationY * robotLocationY);
                }
            }
            return maxDistance;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
