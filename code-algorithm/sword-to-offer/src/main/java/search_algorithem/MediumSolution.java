package search_algorithem;

/**
 * Description:
 * 中等难度
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/10/6
 **/
public class MediumSolution {


    private Node head = null, pre = null;

    /**
     * 剑指 Offer 04. 二维数组中的查找
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length, n;
        if (m == 0 || (n = matrix[0].length) == 0) {
            return false;
        }
        int i = 0, j = n - 1;
        for (; i < m && j >= 0; ) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int n;
        if ((n = numbers.length) == 0) {
            return -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[r]) {
                r = mid;
            } else {
                r--;
            }
        }
        return numbers[l];
    }

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int n = s.length();
        if (n == 0) {
            return ' ';
        }
        int[] rec = new int[26];
        for (char ch : s.toCharArray()) {
            rec[ch - 'a']++;
        }
        for (char ch : s.toCharArray()) {
            if (rec[ch - 'a'] == 1) {
                return ch;
            }
        }
        return ' ';
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m, n;
        if (board == null || (m = board.length) == 0 || (n = board[0].length) == 0) {
            return false;
        }
        boolean[][] isVisited = new boolean[m][n];
        char[] chs = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == chs[0] && exist(board, i, j, isVisited, chs, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int i, int j, boolean[][] isVisited, char[] chs,
                          int index) {
        if (index == chs.length) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || isVisited[i][j] ||
            board[i][j] != chs[index]) {
            return false;
        }
        isVisited[i][j] = true;
        boolean res = exist(board, i + 1, j, isVisited, chs, index + 1)
            || exist(board, i - 1, j, isVisited, chs, index + 1)
            || exist(board, i, j + 1, isVisited, chs, index + 1)
            || exist(board, i, j - 1, isVisited, chs, index + 1);
        isVisited[i][j] = false;
        return res;
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        return movingCount(m, n, 0, 0, k, new boolean[m][n]);
    }

    private int movingCount(int m, int n, int i, int j, int k, boolean[][] isVisited) {
        if (i < 0 || j < 0 || i >= m || j >= n || isVisited[i][j] ||
            movingCount(i) + movingCount(j) > k) {
            return 0;
        }
        isVisited[i][j] = true;
        return movingCount(m, n, i + 1, j, k, isVisited) +
            movingCount(m, n, i, j + 1, k, isVisited) +
            movingCount(m, n, i - 1, j, k, isVisited) +
            movingCount(m, n, i, j - 1, k, isVisited) + 1;
    }

    private int movingCount(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        treeToDoublyList2(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void treeToDoublyList2(Node root) {
        if (root == null) {
            return;
        }
        treeToDoublyList2(root.left);
        if (pre == null) {
            head = root;
        } else if (pre != null) {
            pre.right = root;
        }
        root.left = pre;
        pre = root;
        treeToDoublyList2(root.right);
    }

    class Node {
        int val;
        Node left, right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
