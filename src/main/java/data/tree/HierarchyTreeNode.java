package data.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class HierarchyTreeNode<Hierarchy, ID, Value> extends NaryTreeNode<ID, Value> {
    private Hierarchy                                        hierarchy;
    private int                                              depth    = 0;
    private HierarchyTreeNode<Hierarchy, ID, Value>          parent;
    private Map<ID, HierarchyTreeNode<Hierarchy, ID, Value>> children = new HashMap<>();
    
    HierarchyTreeNode() {
        super();
    }
    
    public HierarchyTreeNode(Hierarchy hierarchy, ID id) {
        super(id);
        this.hierarchy = hierarchy;
    }
    
    public HierarchyTreeNode(Hierarchy hierarchy, ID id, Value value) {
        super(id, value);
        this.hierarchy = hierarchy;
    }
    
    private void addChild(HierarchyTreeNode<Hierarchy, ID, Value> childNode) {
        Objects.requireNonNull(childNode);
        this.children.put(childNode.id, childNode);
        childNode.depth = this.depth + 1;
        childNode.parent = this;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> getOrAddChild(Hierarchy hierarchy, ID id) {
        HierarchyTreeNode<Hierarchy, ID, Value> childNode = this.getChild(id);
        if(childNode == null) {
            childNode = new HierarchyTreeNode<>(hierarchy, id);
            this.addChild(childNode);
        }
        return childNode;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> filter(Map<Hierarchy, Set<ID>> filters) {
        HierarchyTreeNode<Hierarchy, ID, Value> node = new HierarchyTreeNode<>(this.hierarchy, this.id, this.value);
        if(!this.isLeaf()) {
            for(Map.Entry<ID, HierarchyTreeNode<Hierarchy, ID, Value>> nodeEntry : this.children.entrySet()) {
                ID nodeId = nodeEntry.getKey();
                HierarchyTreeNode<Hierarchy, ID, Value> childNode = nodeEntry.getValue();
                Set<ID> filterIds = filters.get(childNode.hierarchy);
                if(filterIds == null || filterIds.contains(nodeId)) {
                    HierarchyTreeNode<Hierarchy, ID, Value> filterChild = childNode.filter(filters);
                    node.addChild(filterChild);
                }
            }
        }
        
        return node;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> getParent() {
        return this.parent;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> getChild(ID id) {
        return this.children.get(id);
    }
    
    @Override
    public List<HierarchyTreeNode<Hierarchy, ID, Value>> getChildren() {
        return this.isLeaf() ? new ArrayList<>() : new ArrayList<>(children.values());
    }
    
    public boolean isRoot() {
        return this.parent == null;
    }
    
    @Override
    public boolean isLeaf() {
        return this.children == null || this.children.isEmpty();
    }
    
    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }
    
    @Override
    public String toString() {
        String childrenIds = getChildren().stream()
                                          .map(NaryTreeNode::getId)
                                          .map(String::valueOf)
                                          .collect(Collectors.joining(", "));
        return "HierarchyTreeNode{" + "hierarchy = " + hierarchy + ", id = " + id + ", value=" + value + ", children = [" + childrenIds + "])}";
    }
    
    @Override
    protected void flatToString(StringBuilder stringBuilder, String indent) {
        stringBuilder.append(indent).append(this.toString()).append(System.lineSeparator());
        indent = "    " + indent;
        
        for(NaryTreeNode<ID, Value> childNode : this.getChildren()) {
            childNode.flatToString(stringBuilder, indent);
        }
    }
    
}
