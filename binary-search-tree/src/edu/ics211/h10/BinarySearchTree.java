package edu.ics211.h10;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/** A BinarySearchTree that holds a generic type. 
 * @author IoaneOmerod with help from Troy Wooton 
 *     and Professor Cam Moore from his screen cast video
 *     found here: https://www.youtube.com/watch?v=41bT6h_XZzo&t=582s. 
 * @param <E> The generic type of the BinarySearchTree. 
 */
public class BinarySearchTree<E> implements SearchTree<E>, InOrder<E> {
  
  private Comparator<E> comp; 
  protected boolean addReturn; 
  protected E deleteReturn; 
  protected Node<E> root; 
  protected boolean removeReturn; 
  private List<E> orderList; 
  
  /** Private inner node class.
   * @param <E> The generic type of the node.  
   */
  @SuppressWarnings("serial")
  protected static class Node<E> implements Serializable { 
    protected E data; 
    protected Node<E> left; 
    protected Node<E> right; 
    
    public Node(E data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
    
    public String toString() {
      return this.data.toString();
    }
  }
  
  /** Constructor of BinarySearchTree. 
   * @param c The comparator to compare items in tree. 
   */
  public BinarySearchTree(Comparator<E> c) {
    this.comp = c; 
    root = null;
  }

  @Override
  public List<E> inorder() {
    orderList = new LinkedList<E>();
    return inorderHelper(root); 
  }
  
  /** Recursive helper method for inorder method.
   * 
   * @param orderRoot The root node of the BinarySearchTree.
   * @return Returns BinarySearchTree data as an ordered list.
   */
  public List<E> inorderHelper(Node<E> orderRoot) {
    if (orderRoot == null) {
      return orderList; 
    }
    inorderHelper(orderRoot.left);
    orderList.add(orderRoot.data); 
    inorderHelper(orderRoot.right); 
    return orderList; 
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean add(E item) {
    root = addHelper(root, item);
    return addReturn;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Node addHelper(Node<E> addRoot, E item) {
    if (addRoot == null) {
      addReturn = true; 
      return new Node<E>(item); 
    }
    int compare = comp.compare(addRoot.data, item); 
    if (compare == 0) {
      addReturn = false; 
      return addRoot; 
    } else if (compare > 0) {
      addRoot.left = addHelper(addRoot.left, item); 
      return addRoot; 
    } else {
      addRoot.right = addHelper(addRoot.right, item);
      return addRoot; 
    }
  }

  @Override
  public boolean contains(E item) {
    return containHelper(root, item);
  }
  
  private boolean containHelper(Node<E> containRoot, E item) {
    if (containRoot == null) {
      return false; 
    }
    int compare = comp.compare(item, containRoot.data); 
    if (compare == 0) {
      return true; 
    } else if (compare < 0) {
      return containHelper(containRoot.left, item); 
    } else {
      return containHelper(containRoot.right, item);
    } 
  }

  @Override
  public E find(E target) {
    return findHelper(root, target);
  }
  
  private E findHelper(Node<E> findRoot, E target) {
    if (findRoot == null) {
      return null; 
    }
    int compare = comp.compare(target, findRoot.data); 
    if (compare == 0) {
      return findRoot.data; 
    } else if (compare < 0) {
      return findHelper(findRoot.left, target); 
    } else {
      return findHelper(findRoot.right, target);
    }
  }

  @Override
  public E delete(E target) {
    root = deleteHelper(root, target); 
    return deleteReturn;
  }
  
  private Node<E> deleteHelper(Node<E> deleteRoot, E target) {
    if (deleteRoot == null) {
      deleteReturn = null; 
      removeReturn = false; 
      return deleteRoot; 
    }
    int compare = comp.compare(target, deleteRoot.data); 
    if (compare == 0) {
      removeReturn = true; 
      return replaceNode(deleteRoot); 
    } else if (compare < 0) {
      deleteRoot.left = deleteHelper(deleteRoot.left, target); 
      return deleteRoot; 
    } else {
      deleteRoot.right = deleteHelper(deleteRoot.right, target);
      return deleteRoot; 
    } 
  }
  
  private Node<E> replaceNode(Node<E> replaceRoot) {
    deleteReturn = replaceRoot.data; 
    if (replaceRoot.left == null) {
      return replaceRoot.right; 
    } else if (replaceRoot.right == null) {
      return replaceRoot.left; 
    } else {
      if (replaceRoot.left.right == null) {
        replaceRoot.data = replaceRoot.left.data; 
        replaceRoot.left = replaceRoot.left.left; 
        return replaceRoot; 
      } else {
        replaceRoot.data = findLargestChild(replaceRoot.left); 
        return replaceRoot; 
      }
    }
  }
  
  private E findLargestChild(Node<E> parent) {
    if (parent.right.right == null) {
      E returnValue = parent.right.data; 
      parent.right = parent.right.left; 
      return returnValue; 
    } else {
      return findLargestChild(parent.right); 
    }
  }

  @Override
  public boolean remove(E target) {
    root = deleteHelper(root, target);
    return removeReturn;
  }

}
