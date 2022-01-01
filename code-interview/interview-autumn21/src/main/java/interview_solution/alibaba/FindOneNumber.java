package interview_solution.alibaba;

/**
 * Description:
 * 找出数组大于一半的那个数
 * 复杂度
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/11
 **/
public class FindOneNumber {
    public int findDuplication(int[] arr) {
        int n;
        if ((n = arr.length) == 0) {
            return 0;
        }
        // 暴力
        // Arrays.sort(arr);
        // return arr[arr.length / 2];
        int vote = 1;
        int res = arr[0];
        for (int i = 1; i < n; i++) {
            if (vote == 0) {
                res = arr[i];
                vote++;
            } else {
                if (res == arr[i]) {
                    vote++;
                } else {
                    vote--;
                }
            }
        }
        return res;
    }
}
