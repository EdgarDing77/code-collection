package 树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * 二叉树的一些操作
 *
 * @author:edgarding
 * @date:2021/6/28
 **/
public class TreeSolution {
    /**
     * 反转二叉树
     * 后序
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }

    /**
     * 目的：打印二叉树中节点值的和为target的所有路径
     * 约束：根节点 -》 叶子的路径
     * dfs
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        backtrack(res, new ArrayList<>(), root, target);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> record, TreeNode root,
                           int target) {
        if (root == null) {
            return;
        }
        record.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(record));
        } else {
            backtrack(res, record, root.left, target);
            backtrack(res, record, root.right, target);
        }
        record.remove(record.size() - 1);
    }

    /**
     * 求二叉树的深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        // 平衡二叉树 -》 二叉树中任意节点的左右子树的深度不超过1
        if (root == null) {
            return true;
        }
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += String.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

        return root;
    }

}
