package data.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HierarchyTreeTest {
    
    @Test public void isEmpty() {
        HierarchyTree<Integer, Double, List<Integer>> hierarchyTree = new HierarchyTree<>(Arrays.asList(1, 2, 3, 4));
    
        Assert.assertTrue(hierarchyTree.isEmpty());
    }
    
    @Test
    public void flatString() {
        HierarchyTree<Integer, Double, List<Integer>> hierarchyTree = new HierarchyTree<>(Arrays.asList(1, 2, 3, 4));
        
        Assert.assertEquals(hierarchyTree.toString().trim(), "[Empty Tree]");
    }
}