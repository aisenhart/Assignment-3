/**
 * Enum representing the color of a node in the Red-Black Tree.
 */
enum Color {
    RED, BLACK;
}

/**
 * Class representing a node in the Red-Black Tree.
 */
class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    Color color;

    /**
     * Constructs a new node with the specified data.
     */
    Node(T data) {
        this.data = data;
        this.color = Color.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

/**
 * Class representing a Red-Black Tree.
 */
public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;
    private final Node<T> TNULL;

    /**
     * Constructs a new Red-Black Tree.
     */
    public RedBlackTree() {
        TNULL = new Node<>(null);
        TNULL.color = Color.BLACK;
        root = TNULL;
    }

    /**
     * Helper method for pre-order traversal of the tree.
     */
    private void preOrderHelper(Node<T> node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    /**
     * Performs a pre-order traversal of the tree.
     */
    public void preOrder() {
        preOrderHelper(this.root);
    }

    /**
     * Helper method for in-order traversal of the tree.
     */
    private void inOrderHelper(Node<T> node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    /**
     * Performs an in-order traversal of the tree.
     */
    public void inorder() {
        inOrderHelper(this.root);
    }

    /**
     * Helper method for post-order traversal of the tree.
     */
    private void postOrderHelper(Node<T> node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    /**
     * Performs a post-order traversal of the tree.
     */
    public void postOrder() {
        postOrderHelper(this.root);
    }

    /**
     * Performs a left rotation on the specified node.
     */
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * Performs a right rotation on the specified node.
     */
    private void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    /**
     * Inserts a new node with the specified key into the Red-Black Tree.
     */
    public void insert(T key) {
        Node<T> node = new Node<>(key);
        if (search(((Product) node.data).getId()) != null) {
            throw new IllegalArgumentException("Product with id " + ((Product) node.data).getId() + " already exists");
        }

        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED;

        Node<T> y = null;
        Node<T> x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data.compareTo(x.data) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data.compareTo(y.data) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    /**
     * Fixes the Red-Black Tree after an insertion to maintain its properties.
     */
    private void fixInsert(Node<T> k) {
        Node<T> u;
        while (k.parent != null && k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u != null && u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u != null && u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }

    /**
     * Searches for a product with the specified ID in the Red-Black Tree.
     * Returns the product with the specified ID, or null if not found
     */
    public Product search(String productID) {
        Node<T> temp = root;
        while (temp != TNULL) {
            if (productID.compareTo(((Product) temp.data).getId()) == 0) {
                return (Product) temp.data;
            }
            if (productID.compareTo(((Product) temp.data).getId()) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }


}