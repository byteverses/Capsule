package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NaryTreeNode<ID, Value> implements Tree.TreeNode<ID, Value> {
    
    protected ID    id;
    protected Value value;
    
    private Map<ID, NaryTreeNode<ID, Value>> children;
    
    public NaryTreeNode() {
    }
    
    public NaryTreeNode(ID id) {
        this.id = id;
    }
    
    public NaryTreeNode(ID id, Value value) {
        this.id = id;
        this.value = value;
    }
    
    public NaryTreeNode(ID id, Value value, List<NaryTreeNode<ID, Value>> children) {
        this.id = id;
        this.value = value;
        this.children = children.stream().collect(Collectors.toMap(NaryTreeNode::getId, Function.identity()));
    }
    
    protected void traversal(Consumer<NaryTreeNode<ID, Value>> consumer) {
        consumer.accept(this);
        for(NaryTreeNode<ID, Value> child : getChildren()) {
            child.traversal(consumer);
        }
    }
    
    @Override
    public ID getId() {
        return id;
    }
    
    @Override
    public Value getValue() {
        return value;
    }
    
    @Override
    public void setValue(Value value) {
        this.value = value;
    }
    
    @Override
    public List<? extends NaryTreeNode<ID, Value>> getChildren() {
        return isLeaf() ? new ArrayList<>() : new ArrayList<>(children.values());
    }
    
    @Override
    public boolean isLeaf() {
        return this.children == null || this.children.isEmpty();
    }
    
    @Override
    public String toString() {
        String childrenIds = getChildren().stream()
                                          .map(NaryTreeNode::getId)
                                          .map(String::valueOf)
                                          .collect(Collectors.joining(", "));
        return "NaryTreeNode{" + "id = " + id + ", value = " + value + ", children = [" + childrenIds + "])}";
    }
    
    protected void flatToString(StringBuilder stringBuilder, String indent) {
        stringBuilder.append(indent).append(this.toString()).append(System.lineSeparator());
        indent = "    " + indent;
        
        for(NaryTreeNode<ID, Value> childNode : this.getChildren()) {
            childNode.flatToString(stringBuilder, indent);
        }
    }
    
}
