package 动态规划;

/**
 * Description:
 * 跳跃游戏
 *
 * @author:edgarding
 * @date:2021/2/20
 **/
public class JumpGame {
    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * @param nums
     * @return
     */
    public boolean clanJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= n - 1;
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 示例:
     * <p>
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * <p>
     * <p>
     * <p>
     * 题目中最少，因思考动态规划
     * 状态就是跳跃的下标
     * 选择就是可以跳跃的步数
     * <p>
     * 贪心：在当前i的时候选择能跳到的最远的 索引（i～i+nums[i]）
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        // 站在索引i，最多能跳到索引end
        int end = 0;
        // 从索引i到end能跳到最远的距离
        int farthest = 0;
        int jumpCount = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            // farthest记录了从i 到 end能跳到的最远距离
            if (end == i) {
                jumpCount++;
                end = farthest;
            }
        }
        return jumpCount;
    }

    /**
     * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
     * <p>
     * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
     * <p>
     * 注意，不管是什么情况下，你都无法跳到数组之外。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [4,2,3,0,3,1,2], start = 5
     * 输出：true
     * 解释：
     * 到达值为 0 的下标 3 有以下可能方案：
     * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
     * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        if (start >= arr.length || start < 0) {
            return false;
        }
        if (arr[start] < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        int l = arr[start] + start;
        int r = start - arr[start];
        arr[start] *= -1;
        return canReach(arr, l) || canReach(arr, r);
    }
}
