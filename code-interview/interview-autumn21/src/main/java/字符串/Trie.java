package 字符串;

/**
 * Description:
 * 实现一个字符集，只包含a～z这26个英文字母的Trie树
 * 前缀树实现
 *
 * @author:edgarding
 * @date:2021/6/18
 **/
public class Trie implements TrieTree {
    private static final int MAX_LEN = 26;
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[MAX_LEN];
    }


    @Override
    public void insert(String word) {
        Trie trie = this;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (trie.children[index] == null) {
                trie.children[index] = new Trie();
            }
            trie = trie.children[index];
        }
        isEnd = true;
    }

    @Override
    public boolean isSearch(String word) {
        Trie trie = searchStarts(word);
        return trie != null && trie.isEnd;
    }

    @Override
    public boolean startsWith(String prefix) {
        return searchStarts(prefix) != null;
    }

    @Override
    public Trie searchStarts(String word) {
        Trie trie = this;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (trie.children[index] == null) {
                return null;
            }
            trie = trie.children[index];
        }
        return trie;
    }
}
