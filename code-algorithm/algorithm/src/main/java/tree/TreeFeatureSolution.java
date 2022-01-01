package tree;

/**
 * Description:
 * 树类特征的问题
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/8
 **/
public class TreeFeatureSolution {
    /**
     * 二叉树的最近公共祖先 LCA问题
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        1. 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历方式。
        2. 在回溯的过程中，必然要遍历整颗二叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
        3. 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
         */
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 一个在左一个在右
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

}
