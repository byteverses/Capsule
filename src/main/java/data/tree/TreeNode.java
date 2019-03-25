package data.tree;

import data.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TreeNode<ID, Value> implements Node<ID, Value> {
    
    protected ID    id;
    protected Value value;
    
    private List<TreeNode<ID, Value>> children;
    
    public TreeNode() {
    }
    
    public TreeNode(ID id, Value value) {
        this.id = id;
        this.value = value;
    }
    
    public TreeNode(ID id, Value value, List<TreeNode<ID, Value>> children) {
        this.id = id;
        this.value = value;
        this.children = children;
    }
    
    protected void traversal(Consumer<TreeNode<ID, Value>> consumer) {
        consumer.accept(this);
        getChildren().forEach(child -> traversal(consumer));
    }
    
    @Override
    public ID getId() {
        return id;
    }
    
    @Override
    public Value getValue() {
        return value;
    }
    
    public List<TreeNode<ID, Value>> getChildren() {
        return hasChildren() ? children : new ArrayList<>();
    }
    
    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }
    
    @Override
    public String toString() {
        return "BinaryTreeNode{" + "id=" + id + ", value=" + value + ", children=" + getChildren().stream()
                                                                                                  .map(TreeNode::getId)
                                                                                                  .collect(Collectors.toList()) + ")}";
    }
    
    protected void flatString(StringBuilder stringBuilder, String prefix) {
        stringBuilder.append(prefix).append(toString()).append(System.lineSeparator());
        prefix = "    " + prefix;
        
        for(TreeNode<ID, Value> childNode : getChildren()) {
            childNode.flatString(stringBuilder, prefix);
        }
    }
}
