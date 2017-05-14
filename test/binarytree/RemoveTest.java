package binarytree;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveTest {
    @Test
    public void test() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer root = 2;
        
        tree.add(root);
        tree.add(1);
        tree.add(3);
        
        assertEquals(3, tree.size());
        /*
             2
           /   \
        1        3
                   \
        
                     4
        
        
        */
        tree.add(4);
      
        assertEquals(4, tree.size());
        
       tree.remove(3);
        assertEquals(3, tree.size());
       
         
         
             /*
             2
           /   \
        1         4
        
        
        */
         

       assertFalse(tree.contains(3));
       assertTrue(tree.contains(2));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(4));
        
        tree.remove(1);
        assertEquals(2, tree.size() );
        
        tree.remove(4);
        assertEquals(1, tree.size());
        
        tree.remove(2);
        assertFalse(tree.contains(2));
        
    }
    
}