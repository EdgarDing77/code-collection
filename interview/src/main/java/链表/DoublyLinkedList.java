package 链表;

import static 链表.ListUtils.checkElementIndex;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/3
 **/
public class DoublyLinkedList implements List {
    private Node first, last;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    public boolean addLast(int val) {
        Node newNode = new Node(val);
        Node l = last;
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = last;
            last.pre = l;
        }
        size++;
        return true;
    }

    public boolean addFirst(int val) {
        Node newNode = new Node(val);
        Node f = first;
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.pre = first;
            first.next = f;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int val) {
        return addLast(val);
    }

    /**
     * 算法优化 -》 根据index在中点的左右来判断从前遍历和从后遍历
     * <p>
     * 设计有点蠢了，应该通过find函数进行节点查找，不然代码冗余
     *
     * @param index
     * @param val
     * @return
     */
    @Override
    public boolean add(int index, int val) {
        checkElementIndex(index, size);
        if (index == size - 1) {
            return addLast(val);
        } else if (index == 0) {
            return addFirst(val);
        }
        Node cur = find(index);
        Node newNode = new Node(val);
        newNode.next = cur;
        newNode.pre = cur.pre;
        cur.pre.next = newNode;
        cur.pre = newNode;
        size++;
        return true;
    }

    public int removeLast() {
        Node oldNode = this.last;
        this.last = oldNode.pre;
        int oldVal = oldNode.item;
        oldNode = null;
        size--;
        return oldVal;
    }

    public int removeFirst() {
        Node oldNode = this.first;
        this.first = oldNode.next;
        int oldVal = oldNode.item;
        oldNode = null;
        size--;
        return oldVal;
    }

    /**
     * 节点查找
     *
     * @param index
     * @return
     */
    public Node find(int index) {
        checkElementIndex(index, this.size);
        if (index == 0) {
            return this.first;
        } else if (index == size - 1) {
            return this.last;
        }
        Node cur;
        if (index >= (size >> 1)) {
            cur = this.last;
            while (index++ != size - 1) {
                cur = cur.pre;
            }
        } else {
            cur = this.first;
            while (index-- != 0) {
                cur = cur.next;
            }
        }
        return cur;
    }

    @Override
    public int remove(int index) {
        checkElementIndex(index, this.size);
        if (index == size - 1) {
            return removeLast();
        } else if (index == 0) {
            return removeFirst();
        }
        Node removeNode = find(index);
        int oldVal = removeNode.item;
        removeNode.pre.next = removeNode.next;
        removeNode.next.pre = removeNode.pre;
        removeNode = null;
        size--;
        return oldVal;
    }

    @Override
    public int set(int index, int newVal) {
        checkElementIndex(index, this.size);
        Node node = find(index);
        int oldVal = node.item;
        node.item = newVal;
        return oldVal;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(int val) {
        Node cur = this.first;
        int index = 0;
        while (cur != null && cur.item != val) {
            index++;
            cur = cur.next;
        }
        return index;
    }

    @Override
    public int get() {
        return this.last.item;
    }

    @Override
    public int get(int index) {
        checkElementIndex(index, this.size);
        return find(index).item;
    }

    @Override
    public void print() {
        Node cur = first;
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    private static class Node {
        int item;
        private Node pre, next;

        public Node(int item) {
            this.item = item;
            this.pre = null;
            this.next = null;
        }
    }
}
