/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 *
 * @author Лариса Конина 
 * 23501/3
 */


import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
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
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }
    
 public Node <T> minimumElement(Node<T> root) {
     
         if (root.left == null) return root;
         
         else  return minimumElement(root.left);
 
    }

   
private boolean remove(Node <T> t) {
      
         if (t == null)  return false;


 // if nodeToBeDeleted have both children
         if (t.left != null && t.right != null) {
                Node<T> temp = (Node<T>) t;
 // Finding minimum element from right
                Node <T> minNodeForRight = minimumElement(temp.right);
 // Replacing current node with minimum node from right subtree
                t= minNodeForRight;
                Node <T> newMinNodeForRight = minimumElement(t.right);
 // Deleting minimum node from right now
                remove(newMinNodeForRight);
         }
 
 // if nodeToBeDeleted has only left child
         else if (t.left != null) {
             
             t = t.left;     
         }

 // if nodeToBeDeleted has only right child NO ROOT LEFT
         else if (t.right != null) {
             
             t = t.right;
         }


 // if nodeToBeDeleted do not have child
         else    t = null;
 
         size--;
         
         return true;
 
 }
 
    

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

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
            if (current == null) throw new NoSuchElementException();
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

