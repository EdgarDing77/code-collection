package written_examination_questions.tencent;

import java.util.Scanner;

/**
 * Description:
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/22
 **/
public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String in = sc.next();
        char[] s = in.toCharArray();
        StringBuilder sb = new StringBuilder();
//        char ch = 'a';
//        for(int i=s.length-1; i>=0; i--) {
//            if(s[i] >= ch || k > 0) {
//                if(k == 0) {
//                    break;
//                }
//                sb.append(s[i]);
//                ch = s[i];
//                k--;
//            }
//        }
//        System.out.println(sb.reverse());


        int sum = k, pos = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = pos; j < n - sum + 1; j++) {
                if (s[pos] < s[j]) {
                    pos = j;
                }
            }
            sb.append(s[pos]);
            pos++;
            sum--;
        }
        System.out.println(sb.toString());
    }


}
