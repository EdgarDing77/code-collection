package 递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * 一个数组集合的全排列
 *
 * @author:edgarding
 * @date:2021/6/7
 **/
public class NumsPermute {

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        boolean[] isVisited = new boolean[nums.length];
        dfs(nums, temp, isVisited, res);
        return res;
    }

    private void dfs(int[] nums, List<Integer> temp, boolean[] isVisited, List<List<Integer>> res) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (isVisited[j]) {
                continue;
            }
            temp.add(nums[j]);
            isVisited[j] = true;
            dfs(nums, temp, isVisited, res);
            temp.remove(temp.size() - 1);
            isVisited[j] = false;
        }
    }

    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfsUnique(nums, new boolean[n], new ArrayList<>(), res);
        return res;
    }

    private void dfsUnique(int[] nums, boolean[] isVisited, List<Integer> tmp, List<List<Integer>> res) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1]) {
                continue;
            }
            isVisited[i] = true;
            tmp.add(nums[i]);
            dfsUnique(nums, isVisited, tmp, res);
            tmp.remove(tmp.size() - 1);
            isVisited[i] = false;
        }
    }

}
