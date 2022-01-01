package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/13
 **/
public class Solution {
    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        int n;
        if ((n = nums.length) == 0) {
            return "";
        }
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroCnt = 0, diff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
            } else {
                if (nums[i] == nums[i + 1]) {
                    return false;
                }
                if (nums[i] + 1 != nums[i + 1]) {
                    diff += nums[i + 1] - nums[i] - 1;
                }
            }
        }
        return zeroCnt >= diff;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
//        Arrays.sort(arr);
//        System.arraycopy(arr, arr.length - k, res, 0, k);
        return res;
    }


}
