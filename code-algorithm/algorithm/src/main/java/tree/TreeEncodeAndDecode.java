package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * 二叉树的序列化和反序列化
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/11/17
 **/
public class TreeEncodeAndDecode {
    String SEP = ",";
    String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return deserialize(nodes);
    }

    public TreeNode deserialize(List<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String node = nodes.remove(0);
        if (NULL.equals(node)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}
