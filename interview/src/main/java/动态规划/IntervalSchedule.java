package 动态规划;

import java.util.Arrays;

/**
 * Description:
 * 区间调度
 *
 * @author:edgarding
 * @date:2021/2/20
 **/
public class IntervalSchedule {
    /**
     * 计算出这些区间中有多少个不相交的区间（最大不相交子集）
     * 类似于生活中，最多能参加多少个活动
     * <p>
     * 思路：
     * 1、每次从区间中选择一个区间x，是结束最早的
     * 2、把所有和x相交的区间从集合中删除
     * 3、重复1，2选出的x们就是最大不相交子集们
     *
     * @param intvs
     * @return
     */
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) {
            return 0;
        }
        // 按照最右边界排序
        Arrays.sort(intvs, (o1, o2) -> (o1[1] - o2[1]));
        // 至少有一个区间不相交
        int count = 1;
        int xEnd = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= xEnd) {
                // 找到下一个可以选择的区间
                count++;
                xEnd = interval[1];
            }
        }
        return count;
    }

    /**
     * 无重叠区间
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> (o1[1] - o2[1]));
        int end = intervals[0][1];
        int count = 1;
        for (int[] interval : intervals) {
            if (end <= interval[0]) {
                count++;
                end = interval[1];
            }
        }
        return n - count;
    }
}
