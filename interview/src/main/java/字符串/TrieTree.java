package 字符串;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/18
 **/
public interface TrieTree {
    void insert(String word);

    boolean isSearch(String word);

    boolean startsWith(String prefix);

    TrieTree searchStarts(String word);
}
