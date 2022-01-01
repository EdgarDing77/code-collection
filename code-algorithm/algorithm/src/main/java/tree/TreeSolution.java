package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/17
 **/
public class TreeSolution {
    /**
     * 652. 寻找重复子树
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        HashMap<String, Integer> rec = new HashMap<>(); //记录序列化出现次数
        if (root == null) {
            return res;
        }
        findDuplicateSubtrees(root, res, rec);
        return res;
    }

    public String findDuplicateSubtrees(TreeNode root,
                                        List<TreeNode> res,
                                        HashMap<String, Integer> rec) {
        if (root == null) {
            return "#";
        }
        String l = findDuplicateSubtrees(root.left, res, rec);
        String r = findDuplicateSubtrees(root.right, res, rec);
        String tmp = l + "," + r + "," + root.val;
        if (rec.getOrDefault(tmp, 0) == 1) {
            res.add(root);
        }
        rec.put(tmp, rec.getOrDefault(tmp, 0) + 1);
        return tmp;
    }

}
