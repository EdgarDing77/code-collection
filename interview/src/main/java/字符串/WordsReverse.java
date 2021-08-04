package 字符串;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Description:
 * 单词翻转
 *
 * @author:edgarding
 * @date:2021/7/7
 **/
public class WordsReverse {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] words = s.trim().split(" ");
        Deque<String> stack = new ArrayDeque<>();
        for (String word : words) {
            stack.push(word.trim());
        }
        StringBuilder sb = new StringBuilder();
        for (; !stack.isEmpty(); ) {
            String word = stack.pop();
            sb.append(word).append(" ");
        }

        return sb.toString().trim();
    }
}
