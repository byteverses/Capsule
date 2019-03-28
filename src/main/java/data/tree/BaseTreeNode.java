package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseTreeNode<ID, Value> implements Tree.TreeNode<ID, Value> {
    
    protected ID    id;
    protected Value value;
    
    private Map<ID, BaseTreeNode<ID, Value>> children;
    
    public BaseTreeNode() {
    }

    public BaseTreeNode(ID id) {
        this.id = id;
    }
    
    public BaseTreeNode(ID id, Value value) {
        this.id = id;
        this.value = value;
    }
    
    public BaseTreeNode(ID id, Value value, List<BaseTreeNode<ID, Value>> children) {
        this.id = id;
        this.value = value;
        this.children = children.stream().collect(Collectors.toMap(BaseTreeNode::getId, Function.identity()));
    }
    
    protected void traversal(Consumer<BaseTreeNode<ID, Value>> consumer) {
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

    @Override
    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public List<? extends BaseTreeNode<ID, Value>> getChildren() {
        return isLeaf() ? new ArrayList<>() : new ArrayList<>(children.values());
    }

    @Override
    public boolean isLeaf() {
        return this.children != null && !this.children.isEmpty();
    }
    
    @Override
    public String toString() {
        List<ID> childrenIds = getChildren().stream().map(BaseTreeNode::getId).collect(Collectors.toList());
        return "BinaryTreeNode{" + "id=" + id + ", value=" + value + ", children=" + childrenIds + ")}";
    }
    
    protected void flatString(StringBuilder stringBuilder, String indent) {
        stringBuilder.append(indent).append(this.toString()).append(System.lineSeparator());
        indent = "    " + indent;
        
        for(BaseTreeNode<ID, Value> childNode : this.getChildren()) {
            childNode.flatString(stringBuilder, indent);
        }
    }
    
}
