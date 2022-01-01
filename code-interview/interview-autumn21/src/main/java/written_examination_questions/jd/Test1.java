package written_examination_questions.jd;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/11
 **/
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        // ascii 33 ~ 126

        // 移动一格时间消耗 x
        // 转向（换行）消耗 y
        // 键盘敲击时间 z
        char[][] chs = new char[n][m];
        for (int i = 0; i < n; i++) {
            chs[i] = sc.next().toCharArray();
        }
        char[] target = sc.next().toCharArray();
        HashMap<Character, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map.put(chs[i][j], new Node(chs[i][j], i, j));
            }
        }
        // 键盘敲击的个数
        long res = 0;
        int preX = 0, preY = 0;
        int turn = 0;
        res += (long) z * target.length;
        for (int i = 0; i < target.length; i++) {
            char t = target[i];
            Node node = map.get(t);
            if (preX == node.x && preY == node.y) {
                continue;
            } else {
                turn++;
            }
            res += (long) Math.abs(node.x + node.y - preX - preY) * x;
            preX = node.x;
            preY = node.y;
        }
        res += (long) (turn == 0 ? 0 : (turn - 1)) * y;
        System.out.println(res);
    }

    static class Node {
        public int val;
        public int x;
        public int y;

        public Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
}
