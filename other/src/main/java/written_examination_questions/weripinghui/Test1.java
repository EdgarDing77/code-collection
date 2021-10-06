package written_examination_questions.weripinghui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * 求不包含相同字符的两个单词中的共包含不同字符的总数；
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/16
 **/
public class Test1 {
    public static int maxSum(String[] words) {
        // write code here
        List<Set<Character>> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            // 添加每个单词里的字符数
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < words[i].length(); j++) {
                set.add(words[i].charAt(j));
            }
            list.add(set);
        }
        int max = 0, now = 0;
        // 两两比较
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                // 利用set集合过滤相同的，得出交集个数，即
                Set<Character> set = new HashSet<>();
                set.addAll(list.get(i));
                set.addAll(list.get(j));
                now = set.size();
                if (now == list.get(i).size() + list.get(j).size()) {
                    max = Math.max(max, now);
                }
            }
        }
        return max;
    }
}
