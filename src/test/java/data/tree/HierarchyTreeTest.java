package data.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void putValue() {
        HierarchyTree<String, Integer, Double> hierarchyTree = new HierarchyTree<>(Arrays.asList("1","2","3"));

        HierarchyTreeNode<String, Integer, Double> node = hierarchyTree.putValue(Arrays.asList(1, 2, 3), 3.0);

        Assert.assertEquals(node.getValue().doubleValue(), 3.0, 0.0);
    }

    @Test
    public void findNode() {
        HierarchyTree<String, Integer, Double> hierarchyTree = new HierarchyTree<>(Arrays.asList("1","2","3"));
        hierarchyTree.putValue(Arrays.asList(1, 2, 3), 3.0);
        hierarchyTree.putValue(Arrays.asList(1, 2, 33), 33.0);
        hierarchyTree.putValue(Arrays.asList(1, 2), 2.0);

        HierarchyTreeNode<String, Integer, Double> node = hierarchyTree.findNode(Arrays.asList(1, 2, 33));

        Assert.assertEquals(node.getId(), Integer.valueOf(33));
        Assert.assertEquals(node.getValue(), Double.valueOf(33.0));
        Assert.assertEquals(node.getHierarchy(), "3");
    }

    @Test
    public void filter() {
        HierarchyTree<String, Integer, Double> hierarchyTree = new HierarchyTree<>(Arrays.asList("1","2","3"));
        hierarchyTree.putValue(Arrays.asList(1, 2, 3), 3.0);
        hierarchyTree.putValue(Arrays.asList(1, 2, 33), 33.0);
        hierarchyTree.putValue(Arrays.asList(1, 2), 2.0);

        Map<String, Collection<Integer>> filters = new HashMap<>();
        filters.put("1", Arrays.asList(1));
        filters.put("3", Arrays.asList(3));
        HierarchyTree<String, Integer, Double> filterTree = hierarchyTree.filter(filters);

        System.out.println("filterTree = " + filterTree);

        Assert.assertNull(filterTree.findNode(Arrays.asList(1,2,33)));

        HierarchyTreeNode<String, Integer, Double> node = filterTree.findNode(Arrays.asList(1, 2, 3));
        Assert.assertEquals(node.getId(), Integer.valueOf(3));
        Assert.assertEquals(node.getValue(), Double.valueOf(3.0));
        Assert.assertEquals(node.getHierarchy(), "3");
    }


}