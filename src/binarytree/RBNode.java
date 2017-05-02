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

@SuppressWarnings("WeakerAccess")
public interface RBNode<T> {

    T getValue();
    default boolean isRed() {
        return true;
    }

    RBNode<T> getLeft();
    RBNode<T> getRight();

    // Optional
    void setLeft(RBNode<T> left);
    void setRight(RBNode<T> right);
    void setColor(boolean isRed);
}
