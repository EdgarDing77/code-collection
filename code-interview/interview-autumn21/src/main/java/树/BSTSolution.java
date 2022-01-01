package 树;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Description:
 * 关于二叉搜索树的一些问题
 *
 * @author:edgarding
 * @date:2021/6/8
 **/
public class BSTSolution {
    /**
     * 在二叉搜索树中查找第k大的节点
     * 二叉搜索树 -》 中序
     * 第k大 -》 反向中序
     *
     * @param root
     * @param k
     * @return
     */
    private int res = 0;

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     * 笨方法解决方法：已知后续 可以通过排序获得中序，通过中序和后续遍历构建二叉树，在进行递归判断是否是二叉搜索树
     * 递归分治理：已知二叉搜索树特性 根节点大于任何左子树的所有节点，小于任何右子树的所有节点 -》
     * 即判断每个子树 -》 在区间[left, right] 寻找第一个大于根节点的值下标为p 划分左子树[left, p - 1] 右子树[p, right】
     * 判断 左区间值是否全部小于根 右区间是否全部大于根
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int p = left;
        while (postorder[p] < postorder[right]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[right]) {
            p++;
        }
        return p == right && recur(postorder, left, m - 1) && recur(postorder, m, right - 1);
    }

    public boolean verifyPostorder2(int[] postorder) {
        // 二叉搜索树特性 左节点<根<右节点 根节点大于左子树中的任何一个节点，小于右子树中的任何一个节点
        // 后续遍历按判断 左右根
        // 反向后续 根->右->左
        if (postorder.length < 2) {
            return true;
        }
        int[] inorder = new int[postorder.length];
        System.arraycopy(postorder, 0, inorder, 0, postorder.length);
        Arrays.sort(inorder);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // 建树 -》 在验证是否是二叉搜索树 -》 内存开销大 -》 效率低
        // TreeNode root = buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
        // return verify(root);
        return isBuild(postorder, inorder, postorder.length - 1, 0, postorder.length - 1, map);
    }

    /**
     * 通过中序和后续遍历是否能构建树
     *
     * @param postorder
     * @param inorder
     * @param rootIndex
     * @param inLeft
     * @param inRight
     * @param map
     * @return
     */
    public boolean isBuild(int[] postorder, int[] inorder, int rootIndex, int inLeft, int inRight,
                           HashMap<Integer, Integer> map) {
        if (inLeft > inRight) {
            return true;
        }
        int index = map.get(postorder[rootIndex]);
        if (index > inRight || index < inLeft) {
            return false;
        }
        return
            isBuild(postorder, inorder, rootIndex - 1 - inRight + index, inLeft, index - 1, map) &&
                isBuild(postorder, inorder, rootIndex - 1, index + 1, inRight, map);
    }

    /**
     * 中序和后续遍历构建树
     *
     * @param inorder
     * @param inL
     * @param inR
     * @param postorder
     * @param postL
     * @param postR
     * @param map
     * @return
     */
    public TreeNode buildTree(int[] inorder, int inL, int inR, int[] postorder, int postL,
                              int postR, HashMap<Integer, Integer> map) {
        if (inL >= inR) {
            return null;
        }
        int rootVal = postorder[postR];
        int rootIndex = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left =
            buildTree(inorder, inL, rootIndex - 1, postorder, postL, postL + rootIndex - 1 - inL,
                map);
        root.right =
            buildTree(inorder, rootIndex + 1, inR, postorder, postL + rootIndex - inL, postR, map);
        return root;
    }

    /**
     * 验证是否是二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isBST(TreeNode root) {
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (min != null && root.val < min) {
            return false;
        } else if (max != null && root.val > max) {
            return false;
        }
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    public int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        findKthLargest(root, k);
        return res;
    }

    private void findKthLargest(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        findKthLargest(root.right, k - 1);
        if (k == 0) {
            res = root.val;
        }
        findKthLargest(root.left, k - 1);
    }

}
