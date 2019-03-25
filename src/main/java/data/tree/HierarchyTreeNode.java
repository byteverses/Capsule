package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HierarchyTreeNode<Hierarchy, ID, Value> extends TreeNode<ID, Value> {
    private Hierarchy hierarchy;
    
    private Map<ID, HierarchyTreeNode<Hierarchy, ID, Value>> children;
    
    public HierarchyTreeNode(Hierarchy hierarchy, ID id, Value value) {
        super(id, value);
        this.hierarchy = hierarchy;
    }
    
    @Override public List<TreeNode<ID, Value>> getChildren() {
        return this.hasChildren() ? new ArrayList<>(children.values()) : new ArrayList<>();
    }
    
    @Override public boolean hasChildren() {
        return this.children != null && !this.children.isEmpty();
    }
}
