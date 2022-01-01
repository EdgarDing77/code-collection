package search_algorithem;

/**
 * Description:
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/25
 **/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize(root, new StringBuilder());
    }

    public String serialize(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("n,");
        } else {
            str.append(root.val).append(",");
            serialize(root.left, str);
            serialize(root.right, str);
        }
        return str.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        return deserialize(dataArray, new int[] {0});
    }

    public TreeNode deserialize(String[] nodes, int[] i) {
        if (i[0] >= nodes.length || "n".equals(nodes[i[0]])) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[i[0]++]));
        root.left = deserialize(nodes, i);
        i[0]++;
        root.right = deserialize(nodes, i);
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
