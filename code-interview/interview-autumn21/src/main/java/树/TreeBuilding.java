package 树;

import java.util.HashMap;

/**
 * Description:
 * 前序和中序
 * 后序和中序
 *
 * @author:edgarding
 * @date:2021/6/24
 **/
public class TreeBuilding {

    /**
     * 前序和中序构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode preBuildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildInPreorder(map, preorder, 0, preorder.length - 1, inorder, 0,
            inorder.length - 1);
    }

    private TreeNode buildInPreorder(HashMap<Integer, Integer> map, int[] preorder, int pl, int pr,
                                     int[] inorder, int il, int ir) {
        if (il > ir || pl > pr) {
            return null;
        }
        int rootVal = preorder[pl];
        TreeNode root = new TreeNode(rootVal);
        if (il != ir) {
            int rootIndex = map.get(rootVal);
            int leftSize = rootIndex - il;
            int rightSize = ir - rootIndex;
            root.left =
                buildInPreorder(map, preorder, pl + 1, pl + leftSize, inorder, il, rootIndex - 1);
            root.right =
                buildInPreorder(map, preorder, pr - rightSize + 1, pr, inorder, rootIndex + 1, ir);
        }
        return root;
    }

    /**
     * 中序后续构建二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode postBuildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildInPostorder(map, postorder, 0, postorder.length - 1, inorder, 0,
            inorder.length - 1);
    }

    private TreeNode buildInPostorder(HashMap<Integer, Integer> map, int[] postorder, int pl,
                                      int pr, int[] inorder, int il, int ir) {
        if (pl > pr || il > ir) {
            return null;
        }
        int rootVal = postorder[pr];
        TreeNode root = new TreeNode(rootVal);
        if (il != ir) {
            int rootIndex = map.get(rootVal);
            int leftSize = rootIndex - il;
            int rightSize = ir - rootIndex;
            root.left =
                buildInPostorder(map, postorder, pl, pl + leftSize - 1, inorder, il, rootIndex - 1);
            root.right =
                buildInPostorder(map, postorder, pr - rightSize, pr - 1, inorder, rootIndex + 1,
                    ir);
        }
        return root;
    }

    /**
     * 前序后序构建二叉树
     * 注意只有每个节点度为2或者0的时候前序和后序才能唯一确定一颗二叉树，只有一个子节点是无法确定的，因为你无法判断他是左子树还是右子树
     *
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return build(map, pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private TreeNode build(HashMap<Integer, Integer> map, int[] pre, int preL, int preR, int[] post,
                           int postL, int postR) {
        if (preL > preR || postL > postR) {
            return null;
        }
        int rootVal = pre[preL];
        TreeNode root = new TreeNode(rootVal);
        if (preL != preR) {
            // 左子树的起始位置
            int index = map.get(pre[preL + 1]);
            int leftSize = index - postL;
            int rightSize = postR - index;
            root.left = build(map, pre, preL + 1, preL + 1 + leftSize, post, postL, index);
            root.right = build(map, pre, preR - rightSize + 2, preR, post, index + 1, postR - 1);
        }
        return root;
    }
}
