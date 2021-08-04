package 链表;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/3
 **/
public class ListUtils {

    public static void checkElementIndex(int index, int size) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index < 0) {
            throw new IllegalArgumentException();
        }
    }
}
