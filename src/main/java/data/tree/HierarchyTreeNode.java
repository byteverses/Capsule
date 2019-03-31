package data.tree;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HierarchyTreeNode<Hierarchy, ID, Value> extends BaseTreeNode<ID, Value> {
    private Hierarchy hierarchy;

    private Map<ID, HierarchyTreeNode<Hierarchy, ID, Value>> children = new HashMap<>();

    public HierarchyTreeNode(Hierarchy hierarchy, ID id) {
        super(id);
        this.hierarchy = hierarchy;
    }

    public HierarchyTreeNode(Hierarchy hierarchy, ID id, Value value) {
        super(id, value);
        this.hierarchy = hierarchy;
    }

    public HierarchyTreeNode<Hierarchy, ID, Value> getChild(ID id) {
        return this.children.get(id);
    }

    private void addChild(HierarchyTreeNode<Hierarchy, ID, Value> childNode) {
        this.children.put(childNode.id, childNode);
    }

    public HierarchyTreeNode<Hierarchy, ID, Value> getOrAddChild(Hierarchy hierarchy, ID id) {
        HierarchyTreeNode<Hierarchy, ID, Value> childNode = this.getChild(id);
        if (childNode == null) {
            childNode = new HierarchyTreeNode<>(hierarchy, id);
            this.addChild(childNode);
        }
        return childNode;
    }

    public HierarchyTreeNode<Hierarchy, ID, Value> filter(Map<Hierarchy, Set<ID>> filters) {
        HierarchyTreeNode<Hierarchy, ID, Value> node = new HierarchyTreeNode<>(this.hierarchy, this.id, this.value);
        if (!this.isLeaf()) {
            Set<ID> filterIds = filters.getOrDefault(this.hierarchy, this.children.keySet());
            for (HierarchyTreeNode<Hierarchy, ID, Value> childNode : this.children.values()) {
                if (filterIds.contains(childNode.getId())) {
                    HierarchyTreeNode<Hierarchy, ID, Value> filterNode = childNode.filter(filters);
                    node.addChild(filterNode);
                }
            }
        }

        return node;
    }

    @Override
    public List<HierarchyTreeNode<Hierarchy, ID, Value>> getChildren() {
        return this.isLeaf() ? new ArrayList<>() : new ArrayList<>(children.values());
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
        List<ID> childrenIds = getChildren().stream().map(BaseTreeNode::getId).collect(Collectors.toList());
        return "HierarchyTreeNode{" + "hierarchy=" + hierarchy + ", id=" + id + ", value=" + value + ", children=" + childrenIds + ")}";
    }

    @Override
    protected void flatString(StringBuilder stringBuilder, String indent) {
        stringBuilder.append(indent).append(this.toString()).append(System.lineSeparator());
        indent = "    " + indent;

        for (BaseTreeNode<ID, Value> childNode : this.getChildren()) {
            childNode.flatString(stringBuilder, indent);
        }
    }

}
