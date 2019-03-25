package data.tree;

import java.util.LinkedList;
import java.util.List;

public class HierarchyTree<Hierarchy, ID, Value> extends BaseTree<ID, Value> {
    private LinkedList<Hierarchy>                   hierarchy;
    private HierarchyTreeNode<Hierarchy, ID, Value> root;
    
    public HierarchyTree(List<Hierarchy> hierarchy) {
        this.hierarchy = new LinkedList<>(hierarchy);
        this.root = new HierarchyTreeNode<>((Hierarchy) new Object(), (ID) new Object(), (Value) new Object());
    }
    
    
    
    @Override
    public boolean isEmpty() {
        return this.root == null || !this.root.hasChildren();
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> getRoot() {
        return this.root;
    }
}
