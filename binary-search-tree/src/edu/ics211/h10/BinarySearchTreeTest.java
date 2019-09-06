package edu.ics211.h10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Represents a BinarySearchTreeTest.
 *
 * @author Cam Moore
 *
 */
public class BinarySearchTreeTest {
  
  private BinarySearchTree<Contact> tree;
  
  /**
   * Sets up a binary search tree.
   */
  @Before
  public void setUp() {
    this.tree = new BinarySearchTree<Contact>(new ContactComparator());
    tree.add(new Contact("Foo", "Moore", "808-555-1234"));
    tree.add(new Contact("Bar", "Foo", "808-555-2345"));
    tree.add(new Contact("Baz", "Foo", "808-555-3456"));
    tree.add(new Contact("Sir", "William", "123456"));
    tree.add(new Contact("Sir", "Stephen", "123457"));
    tree.add(new Contact("Sir", "Arthur", "123458"));
    tree.add(new Contact("Sir", "Matilda", "123459"));
    tree.add(new Contact("Sir", "Geoffrey", "123460"));
  }

  /**
   * Test method for {@link edu.ics211.h09.BinarySearchTree#contains(java.lang.Object)}.
   */
  @Test
  public void testContains() {
    assertTrue(tree.contains(new Contact("Sir", "William", "123456")));
    assertFalse(tree.contains(new Contact("Sir", "William I", "123456")));
  }


  /**
   * Test method for {@link edu.ics211.h09.BinarySearchTree#find(java.lang.Object)}.
   */
  @Test
  public void testFind() {
    Contact m = new Contact("Sir", "Matilda", "123459");
    assertEquals(m, tree.find(m));
    assertEquals(null, tree.find(new Contact("asd", "djg", "2")));
  }


  /**
   * Test method for {@link edu.ics211.h09.BinarySearchTree#delete(java.lang.Object)}.
   */
  @Test
  public void testDelete() {
    Contact m = new Contact("Sir", "Matilda", "123459");
    assertEquals(m, tree.delete(m));
    assertEquals(null, tree.delete(m));
  }


  /**
   * Test method for {@link edu.ics211.h09.BinarySearchTree#remove(java.lang.Object)}.
   */
  @Test
  public void testRemove() {
    Contact m = new Contact("Sir", "Matilda", "123459");
    assertTrue(tree.remove(m));
    assertFalse(tree.remove(m));
  }


  /**
   * Test method for {@link edu.ics211.h09.BinarySearchTree#inorder()}.
   */
  @Test
  public void testInorder() {
    List<Contact> contacts = tree.inorder();
    assertNotNull(contacts);
    assertEquals(contacts.size(), 8);
    assertTrue(contacts.get(0).equals(new Contact("Sir", "Arthur", "123458")));
    assertTrue(contacts.get(7).equals(new Contact("Sir", "William", "123456")));
    assertTrue(contacts.get(5).equals(new Contact("Foo", "Moore", "808-555-1234")));
  }
  
  /**
   * Test inorder method when items are deleted from BinarySearchTree.
   */
  @Test
  public void testInorderDelete() {
    // Given: A BinarySearchTree. 
    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(new IntegerComparator());
    
    // When: You add integers to the tree. 
    tree.add(7); 
    tree.add(6);
    tree.add(3);
    tree.add(1); 
    tree.add(10);
    
    // Then: If you inorder traverse the tree the integers should be 
    // in the order 1, 3, 6, 7, 10.
    List<Integer> treeData = tree.inorder(); 
    assertEquals(Integer.valueOf(1), treeData.get(0)); 
    assertEquals(Integer.valueOf(3), treeData.get(1)); 
    assertEquals(Integer.valueOf(6), treeData.get(2));
    assertEquals(Integer.valueOf(7), treeData.get(3)); 
    assertEquals(Integer.valueOf(10), treeData.get(4)); 
    
    // When: You delete the integer 7. 
    tree.delete(7); 
    
    // Then: If you inorder traverse the tree the integers should be 
    // in the order 1, 3, 6, 10.
    treeData = tree.inorder(); 
    assertEquals(Integer.valueOf(10), treeData.get(3)); 
    assertEquals(Integer.valueOf(6), treeData.get(2)); 
    
    // When: You delete the integer 1. 
    tree.delete(1); 
    
    // Then: If you inorder traverse the tree the integers should be 
    // in the order 3, 6, 10.
    treeData = tree.inorder(); 
    assertEquals(Integer.valueOf(3), treeData.get(0)); 
    assertEquals(Integer.valueOf(6), treeData.get(1)); 
    assertEquals(Integer.valueOf(10), treeData.get(2)); 
  }

}