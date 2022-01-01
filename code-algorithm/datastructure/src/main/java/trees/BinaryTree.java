package trees;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2020/11/1
 **/
public class BinaryTree {
    class Node {
        public int data;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    /**
     * The root of the binary tree
     */
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    /**
     * 通过值找到一个即节点，如果节点存在则返回，否则返回其父亲节点
     *
     * @param key
     * @return
     */
    public Node find(int key) {
        Node cur = root;
        while (cur != null) {
            if (key < cur.data) {
                if (cur.left == null) {
                    return cur; // the key isn't exist, returns the parent
                }
                cur = cur.left;
            } else if (key > cur.data) {
                if (cur.right == null) {
                    return cur;
                }
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    public void put(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            this.root = newNode;
        } else {
            Node parent = find(value);
            if (value < parent.data) {
                parent.left = newNode;
                parent.left.parent = parent;
            } else {
                parent.right = newNode;
                parent.right.parent = parent;
            }
        }
    }

    public boolean remove(int value) {
        Node temp = find(value);
        if (temp.data != value) {
            return false;
        }
        // no children / one children / two children
        if (isLeaf(temp)) {
            if (temp == this.root) {
                root = null;
            } else if (temp.parent.data < temp.data) {
                temp.parent.right = null;
            } else {
                temp.parent.left = null;
            }
            return true;
        } else if (temp.left != null && temp.right != null) {
            Node successor = findSuccessor(temp);

            successor.left = temp.left;
            successor.left.parent = successor;
            // 如果继承节点有右孩子，右孩子的祖父是新的父亲
            if (successor.parent != temp) {
                if (successor.right != null) {
                    successor.right.parent = successor.parent;
                    successor.parent.left = successor.right;
                } else {
                    successor.parent.left = null;
                }
                successor.right = temp.right;
                successor.right.parent = successor;
            }

            if (temp == root) {
                successor.parent = null;
                root = successor;
            } else {
                successor.parent = temp.parent;
                if (temp.parent.data > temp.data) {
                    temp.parent.right = successor;
                } else {
                    temp.parent.left = successor;
                }
            }
            return true;
        } else {
            if (temp.right != null) {
                if (temp == root) {
                    root = temp.right;
                    return true;
                }
                temp.right.parent = temp.parent;
                if (temp.data < temp.parent.data) {
                    temp.parent.left = temp.right;
                } else {
                    temp.parent.right = temp.right;
                }
            } else {
                if (temp == root) {
                    root = temp.left;
                    return true;
                }
                temp.left.parent = temp.parent;
                if (temp.data < temp.parent.data) {
                    temp.parent.left = temp.left;
                } else {
                    temp.parent.right = temp.left;
                }
            }
            return true;
        }
    }

    private Node findSuccessor(Node temp) {
        if (temp.right == null) {
            return temp;
        }
        Node cur = temp.right;
        Node parent = temp.right;
        while (cur != null) {
            parent = cur;
            cur = cur.left;
        }
        return parent;
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public Node getRoot() {
        return root;
    }

    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot + " ");
            preOrder(localRoot.left);
            preOrder(localRoot.right);
        }
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.left);
            System.out.println(localRoot + " ");
            inOrder(localRoot.right);
        }
    }

    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.left);
            postOrder(localRoot.right);
            System.out.println(localRoot + " ");
        }
    }

}
