package data.tree;

import data.Node;

import java.util.List;

public interface Tree<ID, Value> {
    
    boolean isEmpty();
    
    <T extends TreeNode<ID, Value>> T getRoot();
    
    interface TreeNode<ID, Value> extends Node<ID, Value> {

        ID getId();

        Value getValue();

        void setValue(Value value);

        boolean isLeaf();
        
        List<? extends TreeNode<ID, Value>> getChildren();
        
    }
}
