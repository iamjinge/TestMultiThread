package net.bingyan.jinge.mydownload;

/**
 * Created on 2015/9/29.
 */
public class TaskBinTree {

    private Node root;
    private static TaskBinTree instance;

    public static TaskBinTree getInstance() {
        if (instance == null) {
            synchronized (TaskBinTree.class) {
                if (instance == null) {
                    instance = new TaskBinTree();
                }
            }
        }
        return instance;
    }

    public void addTask(DTask task) {
        synchronized (this) {
            Node newNode = new Node(task);
            if (root == null) {
                root = newNode;
            } else {

                Node current = root;
                Node temp;
                while (true) {
                    if (task.getPriority() > current.getData().getPriority()) {
                        temp = current.getLeft();
                        if (temp == null) {
                            current.setLeft(newNode);
                            break;
                        }
                    } else {

                        temp = current.getRight();
                        if (temp == null) {
                            current.setRight(newNode);
                            break;
                        }
                    }
                }

            }
        }

    }

    public DTask getTask() {
        synchronized (this) {
            Node temp;
            if (root == null) {
                return null;
            } else if (root.getLeft() == null) {
                temp = root;
                root = root.getRight();
            } else {
                Node current = root.getLeft();
                Node parent = root;
                while (true) {
                    if (current.getLeft() != null) {
                        parent = current;
                        current = current.getLeft();
                    } else {
                        temp = current;
                        parent.setLeft(current.getRight());
                        break;
                    }
                }
            }
            return temp.getData();
        }
    }


    public class Node {
        DTask data;
        Node left;
        Node right;

        public Node(DTask data) {
            this.data = data;
        }

        public DTask getData() {
            return data;
        }

        public void setData(DTask data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
