package 堆;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/22
 **/
public interface Heap {
    /**
     * 插入元素
     *
     * @param val
     * @return
     */
    boolean offer(int val);

    /**
     * 获取并删除队首的元素
     *
     * @return
     */
    int poll();

    int peek();

    boolean isEmpty();

}
