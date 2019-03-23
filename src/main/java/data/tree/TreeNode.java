package data.tree;

import data.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<ID, Value> implements Node<ID, Value> {
    
    protected ID id;
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
    
    @Override
    public ID getId() {
        return this.id;
    }
    
    @Override
    public Value getValue() {
        return this.value;
    }
    
    public List<TreeNode<ID, Value>> getChildren() {
        return this.hasChildren() ? this.children : new ArrayList<>();
    }
    
    public boolean hasChildren() {
        return this.children != null && !this.children.isEmpty();
    }

}
