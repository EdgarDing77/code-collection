package written_examination_questions.tencent_music;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/26
 **/
public class Test {
    public TreeNode pruneLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        cut(root.left, root);
        cut(root.right, root);
        return root;
    }

    private void cut(TreeNode now, TreeNode pre) {
        if (now == null) {
            return;
        }
        if (now.right != null) {
            cut(now.right, now);

            if (now.right.right == null && now.right.left == null) {
                now.right = null;
                return;
            }
        }
        if (now.left != null) {
            if (now.left.right == null && now.left.left == null) {
                now.left = null;
                return;
            }
            cut(now.left, now);
        }
    }

    /**
     * 第二题
     *
     * @param s
     * @param k
     * @return
     */
    public int maxValue(String s, int k) {

        if (s.length() == k) {
            return Integer.parseInt(s);
        }
        int left = 0, right = 0, res = 0, tmp = 0, count = 0;
        char[] chs = s.toCharArray();
        while (right < chs.length) {
            char ch = chs[right++];
            tmp = (int) (ch - '0') + tmp * 10;
            count++;
            while (count >= k) {
                tmp %= Math.pow(10, k);
                count--;
            }
            res = Math.max(res, tmp);
        }
        return res;
    }

    /**
     * 第三题
     *
     * @param a
     * @param k
     * @return
     */
    public int FillArray(int[] a, int k) {
        // write code here
        //正整数 > 1
        // k = 3 1 0 0
        // 1 1  1 2 3
        //   2  2 3
        //   3  3
        // 0 0 0 k = 3
        // x -> k - x + 1
        // 1 1 1|2|3
        // 1 2 2|3

        int n;
        if ((n = a.length) == 0) {
            return 0;
        }
        final int MOD = 1000_000_000 + 7;
        int res = 1;
        int zero = 0;
        int[] mem = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                if (i == n - 1) {
                    mem[i] = k - zero;
                } else {
                    if (a[i + 1] != 0) {
                        mem[i] = Math.min(k, a[i + 1]) - zero;
                    } else {
                        mem[i] = k - zero;
                    }
                }
                zero++;
            }
            if (mem[i] != 0) {
                res = (res % MOD) * (mem[i] % MOD) % MOD;
            }
        }
        return zero == 0 ? 0 : res;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
