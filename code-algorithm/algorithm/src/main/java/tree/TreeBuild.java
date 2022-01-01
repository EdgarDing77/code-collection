package tree;

import java.util.HashMap;

/**
 * Description:
 * 二叉树的构建
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/16
 **/
public class TreeBuild {

    /**
     * 前中序构建二叉树
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
        return preBuildTree(map, preorder, 0, preorder.length - 1,
            inorder, 0, inorder.length - 1);
    }

    private TreeNode preBuildTree(HashMap<Integer, Integer> map,
                                  int[] preorder, int pl, int pr,
                                  int[] inorder, int il, int ir) {
        if (il > ir || pl > pr) {
            return null;
        }
        int rootVal = preorder[pl];
        TreeNode root = new TreeNode(rootVal);
        if (il != ir) {
            int rootIndex = map.get(root.val);
            int leftSize = rootIndex - il;
            int rightSize = ir - rootIndex;
            root.left = preBuildTree(map, preorder, pl + 1, pl + leftSize,
                inorder, il, rootIndex - 1);
            root.right = preBuildTree(map, preorder, pr - rightSize + 1, pr,
                inorder, rootIndex + 1, ir);
        }
        return root;
    }


    /**
     * 中后序构建二叉树
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
        return postBuildTree(map, inorder, 0, inorder.length - 1,
            postorder, 0, postorder.length - 1);
    }

    private TreeNode postBuildTree(HashMap<Integer, Integer> map, int[] inorder, int il, int ir,
                                   int[] postorder, int pl, int pr) {
        if (il > ir || pl > pr) {
            return null;
        }
        int rootVal = postorder[pr];
        TreeNode root = new TreeNode(rootVal);
        if (il != ir) {
            int rootIndex = map.get(root.val);
            int leftSize = rootIndex - il;
            int rightSize = ir - rootIndex;
            root.left = postBuildTree(map, inorder, il, rootIndex - 1,
                postorder, pl, pl + leftSize - 1);
            root.right = postBuildTree(map, inorder, rootIndex + 1, ir,
                postorder, pr - rightSize, pr - 1);
        }
        return root;
    }

    /**
     * 根据前后序构建二叉树
     *
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        return constructFromPrePost(map, preorder, 0, preorder.length - 1,
            postorder, 0, postorder.length - 1);
    }

    private TreeNode constructFromPrePost(HashMap<Integer, Integer> map, int[] preorder, int preL,
                                          int preR,
                                          int[] postorder, int postL, int postR) {
        if (preL > preR || postL > postR) {
            return null;
        }
        int rootVal = preorder[preL];
        TreeNode root = new TreeNode(rootVal);
        if (preL != preR) {
            int rootIndex = map.get(preorder[preL + 1]); // 前序中是左子树的起始位置 后序中+1是右子树的开始位置
            int leftSize = rootIndex - postL;
            int rightSize = postR - 1 - rootIndex;
            root.left = constructFromPrePost(map, preorder, preL + 1, preL + leftSize + 1,
                postorder, postL, rootIndex);
            root.right = constructFromPrePost(map, preorder, preR - rightSize + 1, preR,
                postorder, rootIndex + 1, postR - 1);
        }
        return root;
    }


}
