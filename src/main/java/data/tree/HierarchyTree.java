package data.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HierarchyTree<Hierarchy, ID, Value> extends BaseTree<ID, Value> {
    private LinkedList<Hierarchy>                   hierarchy;
    private HierarchyTreeNode<Hierarchy, ID, Value> root;
    
    public HierarchyTree(List<Hierarchy> hierarchy) {
        this.hierarchy = new LinkedList<>(hierarchy);
        this.root = new HierarchyTreeNode<>((Hierarchy) new Object(), (ID) new Object(), (Value) new Object());
    }
    
    private HierarchyTreeNode<Hierarchy, ID, Value> addNode(List<ID> ids) {
        HierarchyTreeNode<Hierarchy, ID, Value> currNode = this.root;
        //TODO: ID should not exceed Hierarchy
        Iterator<Hierarchy> hierarchyIterator = hierarchy.iterator();
        
        
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return this.getRoot() == null || !this.getRoot().hasChildren();
    }
    
    @Override
    public HierarchyTreeNode<Hierarchy, ID, Value> getRoot() {
        return this.root;
    }
    
    public List<Hierarchy> getHierarchy() {
        return this.hierarchy;
    }
}
