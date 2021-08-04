package 树;

/**
 * Description:
 * 二叉树最近公共祖先
 *
 * @author:edgarding
 * @date:2021/2/19
 **/
public class LowestCommonAncestor {
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 1. p q为相互的祖先 -> 前序
     * 2. p q位于同一子树
     * 3. p q位于不同的子树
     * 百度百科中最近公共祖先的定义为：
     * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 前序，如果p或q存在于root中，则LCA就为其中之一
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        if (left == null && right == null) {
            return null;
        }
        return left == null ? right : left;
    }

    /**
     * 寻找二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 如果两个节点值都比当前节点值小，则说明最近公共祖先在左边
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }
        return root;
    }
}
