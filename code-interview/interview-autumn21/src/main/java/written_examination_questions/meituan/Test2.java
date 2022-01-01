package written_examination_questions.meituan;

import java.util.Scanner;

/**
 * Description:
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 小美最近学到很多新的汉字，想要展示一下自己的汉字功底，于是她开始写作文了。
 * <p>
 * 她写着写着会担心一下她有没有好好展示出自己丰富的汉字词汇量，于是她想知道对于某个位置(pos1)的汉字x，最近的另外一个与其相同汉字的位置(pos2)的距离是多少，即|pos1-pos2|。
 * <p>
 * 为了简化，我们认为小美只学了26个汉字，将它们用a ~ z代表。小美默认有一个仅包含a ~ z的字符串s，每次操作小美要么往字符串s末尾增加一个指定字母。要么给出一个位置pos1（1 <= pos1 <= 现在字符串s的长度），询问min{ |pos2-pos1| }，其中pos2满足s[pos2] = s[pos1]，pos2≠pos1，即不同于pos1的最近的另外一个pos2，使得这两个位置的字母相等。
 * <p>
 * s[pos]表示的意思是s字符串中第pos个位置的字符。
 * <p>
 * <p>
 * <p>
 * 输入描述
 * 第一行一个字符串仅包含小写字母的字符串s。
 * <p>
 * 第二行一个整数n，表示接下来有n次操作。
 * <p>
 * 接下来n行，每行第一个整数opt，表示这一次是什么操作。如果opt=1，接下来有一个小写字母，表示小美将要往s末尾添加该小写字母。如果opt=2，表示一次问题描述中的询问。
 * <p>
 * 操作次数n满足1 <= n <= 500。
 * <p>
 * 其中，对于60%数据，保证字符串长度len满足1 <= len <= 1,000。
 * <p>
 * 对于100%数据，保证操作次数n满足1 <= n <= 100,000。
 * <p>
 * 对于操作2，可能没有答案，此时输出-1。
 * <p>
 * 输出描述
 * 对于每一个opt=2的操作，输出答案。
 * <p>
 * <p>
 * 样例输入
 * asdgfas
 * 5
 * 2 6
 * 1 a
 * 1 b
 * 2 6
 * 2 4
 * 样例输出
 * 5
 * 2
 * -1
 * <p>
 * 提示
 * 第一次2号询问，离第6位的a最近的a是第1位的a，距离为 |6 -1| = 5
 * <p>
 * 第二次2号询问，中间插入了一些字母，字符串已经变成了asdgfasab
 * <p>
 * 那么距离第6位的a最近的a变成了第8位的a，距离位|6-8| = 2
 * <p>
 * 第三次2号询问，没有第4位的g最近的g，因此输出-1.
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/22
 **/
public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 增加一个指定字母
        //
        String str = sc.nextLine();
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < n; i++) {
            int opt = sc.nextInt();
            if (opt == 1) {
                sb.append(sc.next());
            } else if (opt == 2) {
                int pos = sc.nextInt();
                System.out.println(query(sb.toString(), pos));
            }
        }
    }

    private static int query(String str, int pos) {
        char ch = str.charAt(pos - 1);
        int right = str.indexOf(ch, pos);
        int left = str.substring(0, pos - 1).lastIndexOf(ch);
        int len1 = -1, len2 = -1;
        if (right != -1) {
            len1 = right - (pos - 1);
        }
        if (left != -1) {
            len2 = pos - 1 - left;
        }
        if (len1 == -1 && len2 == -1) {
            return -1;
        } else if (len1 == -1 || len2 == -1) {
            return Math.max(len1, len2);
        } else {
            return Math.min(len1, len2);
        }

    }
}
