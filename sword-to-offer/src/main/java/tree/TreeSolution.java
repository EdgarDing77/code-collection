package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/6
 **/
public class TreeSolution {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     *
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[] {};
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                res.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        levelOrder2(root, res, 0);
        return res;
    }

    // 递归版本
    private void levelOrder2(TreeNode root, List<List<Integer>> res, int k) {
        if (root == null) {
            return;
        }
        if (res.size() <= k) {
            res.add(new ArrayList<>());
        }
        res.get(k).add(root.val);
        levelOrder2(root.left, res, k + 1);
        levelOrder2(root.right, res, k + 1);
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        levelOrder3(root, res, 0);
        return res;
    }

    private void levelOrder3(TreeNode root, List<List<Integer>> res, int k) {
        if (root == null) {
            return;
        }
        if (res.size() <= k) {
            res.add(new ArrayList<>());
        }
        if (k % 2 == 0) {
            res.get(k).add(root.val);
        } else {
            res.get(k).add(0, root.val);
        }
        levelOrder3(root.left, res, k + 1);
        levelOrder3(root.right, res, k + 1);
    }


    /**
     * 剑指 Offer 26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <p>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSub(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && isSub(A.left, B.left) && isSub(A.right, B.right);
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isSymmetric(left.left, right.right) &&
            isSymmetric(left.right, right.left);
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        traverse(res, root, target, new ArrayList<>());
        return res;
    }

    // 根节点到某一节点
    private void traverse(List<List<Integer>> res, TreeNode root, int target, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            traverse(res, root.left, target, path);
            traverse(res, root.right, target, path);
        }
        path.remove(path.size() - 1);
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        kthLargestHelper(root, k);
        return ans;
    }

    private int ans = 0, cnt = 0;

    private void kthLargestHelper(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthLargestHelper(root.right, k);
        if (++cnt == k) {
            ans = root.val;
        }
        kthLargestHelper(root.left, k);
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        int left = maxDepth(root.left), right = maxDepth(root.right);
        if(Math.abs(left - right) < 2) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        if(root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }
        if(left == null && right == null) {
            return null;
        }
        return left == null ? right : left;
    }


    /**
     * 剑指 Offer 07. 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     *
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,inorder, 0, inorder.length - 1, map);
    }

    private TreeNode build(int[] pre, int preL, int preR, int[] in, int inL, int inR,
                           HashMap<Integer, Integer> map) {
        if(preL > preR || inL > inR) {
            return null;
        }
        int rootVal = pre[preL];
        TreeNode node = new TreeNode(rootVal);
        if(preL == preR) {
            return node;
        }
        int rootIndex = map.get(rootVal);
        int leftSize = rootIndex - inL;
        int rightSize = inR - rootIndex;
        node.left = build(pre, preL + 1, preL + leftSize, in, inL, rootIndex - 1, map);
        node.right = build(pre, preR - rightSize + 1, preR, in, rootIndex + 1, inR, map);
        return node;
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        // 后序 左右根
        int n;
        if((n = postorder.length) == 0) {
            return true;
        }
        return verifyPostorder(postorder, 0, n- 1);
    }

    private boolean verifyPostorder(int[] postorder, int l, int r) {
        if(l >= r) {
            return true;
        }
        int root = postorder[r];
        int k = l;
        // 第一个大于k的
        for(;k < r && postorder[k] < root; k++){}
        // 判断右半边是否都是大于root
        for(int i=k;i < r; i++) {
            if(postorder[i] < root) {
                return false;
            }
        }
        return verifyPostorder(postorder, l, k-1) && verifyPostorder(postorder, k , r-1);
    }
}
