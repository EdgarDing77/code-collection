package queues;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/1/11
 **/
public class PriorityQueue {
    private int maxLen;
    private int[] queue;
    private int nItems;

    public PriorityQueue(int maxLen) {
        this.maxLen = maxLen;
        queue = new int[maxLen];
        nItems = 0;
    }

    public void add(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        } else {
            int j = nItems - 1;
            while (j >= 0 && queue[j] > value) {
                queue[j + 1] = queue[j];
                j--;
            }
            queue[j + 1] = value;
            nItems++;
        }
    }

    private boolean isFull() {
        return nItems == maxLen;
    }

}
