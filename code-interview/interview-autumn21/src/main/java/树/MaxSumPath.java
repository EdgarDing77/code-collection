package 树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * @author:edgarding
 * @date:2021/7/10
 **/
public class MaxSumPath {


    int maxSum = Integer.MIN_VALUE;
    int max = Integer.MIN_VALUE;
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 正常获取最大路径
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    /**
     * 打印其路径
     *
     * @param root
     * @return
     */
    public int maxPathSum2(TreeNode root) {
        dfs(root);
        System.out.println(map.toString());
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftVal = dfs(root.left);//这个root节点的左分支能得到的最大sum
        int rightVal = dfs(root.right);//这个root节点右分支能得到最大的sum

        if (leftVal <= 0 && rightVal <= 0) {
            max = Math.max(root.val, max);
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            map.put(root.val, list);
            return root.val;
        } else if (leftVal <= 0) {
            int tempMax = max;
            max = Math.max(root.val + rightVal, max);
            if (max == tempMax) {
                //说明还是之前的最大值
            } else {
                List<Integer> list = map.get(rightVal);
                list.add(root.val);
                map.put(max, list);
            }

            List<Integer> list = map.get(rightVal);
            list.add(root.val);
            map.put(root.val + rightVal, list);

            return root.val + rightVal;
        } else if (rightVal <= 0) {
            int tempMax = max;
            max = Math.max(root.val + leftVal, max);
            if (tempMax == max) {

            } else {
                List<Integer> list = map.get(leftVal);
                list.add(root.val);
                map.put(max, list);
            }

            List<Integer> list = map.get(leftVal);
            list.add(root.val);
            map.put(root.val + leftVal, list);


            return root.val + leftVal;
        } else {
            int tempMax = max;
            max = Math.max(root.val + leftVal + rightVal, max);
            if (max == tempMax) {

            } else {
                List<Integer> list1 = map.get(leftVal);
                List<Integer> list2 = map.get(rightVal);
                list1.addAll(list2);
                list1.add(root.val);
                map.put(max, list1);
            }

            if (leftVal >= rightVal) {
                List<Integer> list = map.get(leftVal);
                list.add(root.val);
                map.put(root.val + leftVal, list);
            } else {
                List<Integer> list = map.get(rightVal);
                list.add(root.val);
                map.put(root.val + rightVal, list);
            }

            return root.val + Math.max(leftVal, rightVal);
        }

    }
}
