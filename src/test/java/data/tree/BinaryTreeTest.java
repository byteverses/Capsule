package data.tree;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeTest {
    
    @Test
    public void flatString() {
        BinaryTree<Long, Long> binaryTree = new BinaryTree<>();
    
        Assert.assertEquals(binaryTree.toString().trim(), "[Empty Tree]");
    }
}