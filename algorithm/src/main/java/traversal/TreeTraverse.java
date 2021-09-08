package traversal;

import java.util.*;

/**
 * Description:
 * 树的遍历
 * 递归 和 迭代
 * 主要实现迭代的版本
 *
 * @author:edgarding
 * @date:2021/6/16
 **/
public class TreeTraverse {

    public void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序处理位置
        traversal(root.left);
        // 中序处理位置
        traversal(root.right);
        // 后续处理位置
    }

    /**
     * 根 -》 左 -》右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (TreeNode cur = root; cur != null || !stack.isEmpty(); cur = cur.right) {
            // left
            for (; cur != null; cur = cur.left) {
                stack.push(cur);
                res.add(cur.val);
            }
            // right
            cur = stack.pop();
        }
        return res;
    }

    /**
     * 左 -》 根 -》 右
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (TreeNode cur = root; cur != null || !stack.isEmpty(); cur = cur.right) {
            for (; cur != null; cur = cur.left) {
                stack.push(cur);
            }
            cur = stack.pop();
            res.add(cur.val);
        }
        return res;
    }

    /**
     * 左 -》 右 -》 根
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (TreeNode cur = root, pre = null; cur != null || !stack.isEmpty(); ) {
            // left
            for (; cur != null; cur = cur.left) {
                stack.push(cur);
            }
            cur = stack.pop();
            // 若无右节点 -》 记录
            // 若pre==cur -》 到根 -》 记录 （pre 记录当前子树的根节点）
            // 否则继续向右迭代
            if (cur.right == null || cur.right == pre) {
                res.add(cur.val);
                pre = cur;
                cur = null;
            } else {
                stack.push(cur);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderTraverse(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public void levelOrderRec(TreeNode root, int height, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (height >= res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        levelOrderRec(root.left, height + 1, res);
        levelOrderRec(root.right, height + 1, res);
    }

}
