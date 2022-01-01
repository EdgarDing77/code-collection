package 动态规划;

/**
 * Description:
 * 最短编辑距离
 * <p>
 * 有用的算法，可以运用到生活中
 *
 * @author:edgarding
 * @date:2021/2/22
 **/
public class EditDistance {
    /**
     * 求出最小的操作数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 1、删除 2、插入 3、替换跳过
                    dp[i][j] = min(
                        dp[i - 1][j] + 1,
                        dp[i][j - 1] + 1,
                        dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * 求出其具体操作
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceResult(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        Node[][] dp = new Node[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            // w1 转换成 w2 只需要删除一个字符
            dp[i][0] = new Node(i, 2);
        }
        for (int j = 0; j <= n; j++) {
            // w1 转换成 w2 只需要插入一个字符
            dp[0][j] = new Node(j, 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    Node node = dp[i - 1][j - 1];
                    dp[i][j] = new Node(node.val, 0);
                } else {
                    dp[i][j] = minNode(
                        dp[i - 1][j],
                        dp[i][j - 1],
                        dp[i - 1][j - 1]
                    );
                    // 编辑距离+1
                    dp[i][j].val++;
                }
            }
        }
        printResult(dp, word1, word2);

        return dp[m][n].val;
    }

    private Node minNode(Node a, Node b, Node c) {
        Node res = new Node(a.val, 2);
        if (res.val > b.val) {
            res.val = b.val;
            res.choice = 1;
        }
        if (res.val > c.val) {
            res.val = c.val;
            res.choice = 3;
        }
        return res;
    }

    public void printResult(Node[][] dp, String s1, String s2) {
        int rows = dp.length;
        int cols = dp[0].length;
        int i = rows - 1, j = cols - 1;
        System.out.println("Change s1:" + s1 + "\tChange s2:" + s2);
        while (i != 0 && j != 0) {
            char c1 = s1.charAt(i - 1);
            char c2 = s2.charAt(j - 1);
            int choice = dp[i][j].choice;
            System.out.println("s1[" + (i - 1) + "]");
            switch (choice) {
                case 0:
                    System.out.println("skip " + c1);
                    i--;
                    j--;
                    break;
                case 1:
                    System.out.println("insert " + c2);
                    j--;
                    break;
                case 2:
                    System.out.println("delete " + c1);
                    i--;
                    break;
                case 3:
                    System.out.println("replace " + c1 + " with " + c2);
                    i--;
                    j--;
                    break;
                default:

            }
        }
        while (i > 0) {
            System.out.println("s1[" + (i - 1) + "]");
            System.out.println("delete " + s1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            System.out.println("s2[" + (j - 1) + "]");
            System.out.println("insert " + s2.charAt(j - 1));
            j--;
        }
    }
}

/**
 * 0 代表什么都不做
 * 1 代表插入
 * 2 代表删除
 * 3 代表替换
 */
class Node {
    int val;
    int choice;

    Node(int val, int choice) {
        this.val = val;
        this.choice = choice;
    }
}