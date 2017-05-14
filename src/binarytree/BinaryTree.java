/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 *
 * @author Лариса Конина 23501/3
 */
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
// Attention: comparable supported but comparator is not

@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> {

    private static class Node<T> {

        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) {
            return false;
        }
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private Node<T> minimumElement(Node<T> t) {

        if (t.left == null) {
            return t;
        } else {
            return minimumElement(t.left);
        }

    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        if (t == null) {
            return false;
        }
        int comparison = t.compareTo(root.value);

        if (comparison == 0) {
            // if nodeToBeDeleted have both children
            if (root.left != null && root.right != null) {
                Node<T> temp = (Node<T>) t;

                // Finding minimum element from right
                Node<T> minNodeForRight = minimumElement(temp.right);
                // Replacing current node with minimum node from right subtree
                root = minNodeForRight;

                Node<T> newMinNodeForRight = minimumElement(root.right);
                // Deleting minimum node from right now
                remove(newMinNodeForRight);

            } // if nodeToBeDeleted has only left child
            else if (root.left != null) {

                root = root.left;

            } // if nodeToBeDeleted has only right child NO ROOT LEFT
            else if (root.right != null) {

                root = root.right;

            } // if nodeToBeDeleted do not have child
            else {
                root = null;
            }

        } else if (findNode(root, t, comparison)) {
            return true;
        }

        return false;
    }

    private boolean findNode(Node<T> root, T value, int comparison) {
        //root.value < t, root in left subtree
        int check_comparison;
        if (comparison < 0) {

            if (root.left != null) {

                check_comparison = value.compareTo(root.left.value);
                if (check_comparison == 0) {
                    return removeNode(root, -1);
                } else if (findNode(root.left, value, check_comparison)) {
                    return true;
                }

            }
        } //root.value > t, root in right subtree
        else if (comparison > 0) {

            if (root.right != null) {

                check_comparison = value.compareTo(root.right.value);
                if (check_comparison == 0) {
                    return removeNode(root, 1);
                } else if (findNode(root.right, value, check_comparison)) {
                    return true;
                }

            }
        }

        return false;
    }

    public boolean removeNode(Node<T> t, int check) {     //almost the same as void REMOVE

        //check means in which subtree of t is Node to be deleted
        // check = 1 - right subtree
        // check = -1 - left subtree
        if (check == 1) {
            if (t.right.left == null) {
                t.right = t.right.right;
            } else {
                Node<T> right = t.right.right;
                t.right = t.right.left;
                Node<T> lowest = minimumElement(t.right);
                lowest.right = right;

            }

            size--;
            return true;
        } else if (check == -1) {
            if (t.left.left == null) {
                t.left = t.left.right;
            } else {
                Node<T> right = t.left.right;
                t.left = t.left.left;
                Node<T> lowest = minimumElement(t.left);
                lowest.right = right;

            }

            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) {
            return null;
        }
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) {
                return start;
            }
            return find(start.left, value);
        } else {
            if (start.right == null) {
                return start;
            }
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) {
                throw new NoSuchElementException();
            }
            return current.value;
        }
    }

    // @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {

    }
}
