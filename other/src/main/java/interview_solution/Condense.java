package interview_solution;

import java.util.Collections;
import java.util.Scanner;

/**
 * Description:
 * <p>
 * 输入第一行包含一个字符串s，代表压缩后的字符串。
 * S的长度<=1000;
 * S仅包含大写字母、[、]、|;
 * 解压后的字符串长度不超过100000;
 * 压缩递归层数不超过10层;
 * <p>
 * 输出一个字符串，代表解压后的字符串。
 * <p>
 * 输入例子1:
 * HG[3|B[2|CA]]F
 * 输出例子1:
 * HGBCACABCACABCACAF
 *
 * @author:edgarding
 * @date:2021/3/3
 **/
public class Condense {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        System.out.println(decode(str));
    }

    public static String decode(String words) {
        while (words.contains("]")) {
            int right = words.indexOf("]");
            int left = words.lastIndexOf("[", right);
            String repeatStr = words.substring(left + 1, right);
            String[] split = repeatStr.split("\\|");
            words = words.replace("[" + repeatStr + "]",
                    String.join("", Collections.nCopies(Integer.parseInt(split[0]), split[1])));
        }
        return words;
    }
}
