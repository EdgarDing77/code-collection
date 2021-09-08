package written_examination_questions.meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Description:
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 小美给小团n张数字卡片，问小团在可以任意排列的情况下，能排列出多少种不同的排列，并按字典序写出每种排列方式。
 * <p>
 * 举例：
 * <p>
 * 小美给小团 [1, 2, 3]，则共有6种排列方式，分别是
 * <p>
 * [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]。
 * <p>
 * 小美给小团 [2, 2, 3]，则共有3种排列方式，分别是
 * <p>
 * [2, 2, 3], [2, 3, 2], [3, 2 2]。
 * <p>
 * 我们认为两个排列不同，当且仅当至少存在一个位置pos，两个排列在那个位置的数是不相同的。
 * <p>
 * 我们认为一个排列字典序比另一个字典序小，当且仅当存在一个位置pos，在pos之前两个排列所有的数字相同，而前者在那个位置的数比后者在那个位置的数小，那么前者字典序比后者小。
 * <p>
 * <p>
 * <p>
 * 输入描述
 * 第一行是一个数字n，表示小美一共给了n张数字卡片。
 * <p>
 * 接下来一行n个空格隔开的正整数。
 * <p>
 * 对于60%的数据，数字卡片张数1<= n <= 3。
 * <p>
 * 对于100%的数据，数字卡片张数1<= n <= 6。
 * <p>
 * 数字卡片上的数字x满足1 <= x <= n。
 * <p>
 * 输出描述
 * 第一行输出一个数ans，表示所有不同的排列数。
 * <p>
 * 下面ans行，每行输出一种排列，且排列需要按字典序输出。
 * <p>
 * <p>
 * 样例输入
 * 3
 * 1 1 2
 * 样例输出
 * 3
 * 1 1 2
 * 1 2 1
 * 2 1 1
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/21
 **/
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        List<List<Integer>> res = test1(nums);
        System.out.println(res.size());
        for (List<Integer> list : res) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> test1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;
    }

    private static void dfs(int[] nums, List<Integer> rec, List<List<Integer>> res, boolean[] isVisited) {
        if (rec.size() == nums.length) {
            res.add(new ArrayList<>(rec));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isVisited[i] || (i > 0 && nums[i] == nums[i - 1] && isVisited[i - 1])) {
                continue;
            }
            isVisited[i] = true;
            rec.add(nums[i]);
            dfs(nums, rec, res, isVisited);
            rec.remove(rec.size() - 1);
            isVisited[i] = false;
        }
    }
}
