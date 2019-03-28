package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HierarchyTreeNode<Hierarchy, ID, Value> extends BaseTreeNode<ID, Value> {
    private Hierarchy hierarchy;
    
    private Map<ID, HierarchyTreeNode<Hierarchy, ID, Value>> children;
    
    public HierarchyTreeNode(Hierarchy hierarchy, ID id, Value value) {
        super(id, value);
        this.hierarchy = hierarchy;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> getChild(ID id) {
        return this.children.get(id);
    }
    
    @Override
    public List<BaseTreeNode<ID, Value>> getChildren() {
        return this.hasChildren() ? new ArrayList<>(children.values()) : new ArrayList<>();
    }
    
    @Override
    public boolean hasChildren() {
        return this.children != null && !this.children.isEmpty();
    }
    
    @Override
    public String toString() {
        List<ID> childrenIds = getChildren().stream().map(BaseTreeNode::getId).collect(Collectors.toList());
        return "HierarchyTreeNode{" + "hierarchy=" + hierarchy + "id=" + id + ", value=" + value + ", children=" + childrenIds + ")}";
    }
    
    @Override
    protected void flatString(StringBuilder stringBuilder, String indent) {
        stringBuilder.append(indent).append(this.toString()).append(System.lineSeparator());
        indent = "    " + indent;
        
        for(BaseTreeNode<ID, Value> childNode : this.getChildren()) {
            childNode.flatString(stringBuilder, indent);
        }
    }
}
