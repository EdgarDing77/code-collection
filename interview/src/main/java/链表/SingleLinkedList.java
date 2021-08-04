package 链表;

/**
 * Description:
 * 单链表实现
 * 单链表的反转实现
 *
 * @author:edgarding
 * @date:2021/6/3
 **/
public class SingleLinkedList implements List {
    private int size;
    Node head, tail;

    private static class Node {
        int item;
        Node next;

        Node(int item) {
            this.item = item;
            next = null;
        }
    }

    public SingleLinkedList() {
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(int val) {
        Node node = new Node(val);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, int val) {
        checkElementIndex(index);
        if (index == size - 1) {
            return add(val);
        }
        Node cur = this.head;
        while (cur != null && index-- != 0) {
            cur = cur.next;
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
        size++;
        return true;
    }

    @Override
    public int remove(int index) {
        checkElementIndex(index);
        Node cur = head, pre = head;
        while (cur != null && index-- != 0) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        int oldVal = cur.item;
        cur = null;
        size--;
        return oldVal;
    }

    /**
     * 移除值为item的节点
     *
     * @param item
     * @return 返回头节点
     */
    public Node removeElement(int item) {
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node cur = dummyNode;
        while (cur.next != null) {
            if (cur.next.item == item) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }

    @Override
    public int set(int index, int newVal) {
        checkElementIndex(index);
        Node cur = head;
        while (cur != null && index-- != 0) {
            cur = cur.next;
        }
        int oldVal = cur.item;
        cur.item = newVal;
        return oldVal;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public int indexOf(int val) {
        Node cur = head;
        int index = 0;
        while (cur != null && cur.item != val) {
            cur = cur.next;
            index++;
        }
        return index;
    }

    @Override
    public int get() {
        return tail.item;
    }

    @Override
    public int get(int index) {
        checkElementIndex(index);
        if (index == size - 1) {
            return get();
        }
        Node cur = head;
        while (index-- != 0) {
            cur = cur.next;
        }
        return cur.item;
    }


    private void checkElementIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index < 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 单链表反转
     */
    public void reverse() {
        Node cur = head, pre = null;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        tail = head;
        head = pre;
    }


    public void reverseV2() {
        Node dummy = new Node(0);
        dummy.next = head;
        Node cur = head;
        while (cur.next != null) {
            Node node = cur.next;
            cur.next = node.next;
            node.next = dummy.next;
            dummy.next = node;
        }
        tail = head;
        head = dummy.next;
    }

    @Override
    public void print() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
