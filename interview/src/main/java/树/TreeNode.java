package 树;

/**
 * Description:
 * 树节点
 *
 * @author:edgarding
 * @date:2021/6/8
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}
