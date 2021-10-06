package 字符串;

/**
 * Description:
 * 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/9/17
 **/
public class WordSearch {


    class WordTrie {
        TrieNode root = new TrieNode();

        void insert(String s) {
            TrieNode cur = root;
            for (char ch : s.toCharArray()) {
                // 判断是否存在
                if (cur.child[ch - 'a'] == null) {
                    cur.child[ch - 'a'] = new TrieNode();
                    cur = cur.child[ch - 'a'];
                } else {
                    cur = cur.child[ch - 'a'];
                }
            }
            cur.isEnd = true;
            cur.val = s;
        }
    }

    class TrieNode {
        String val;
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
    }

}
