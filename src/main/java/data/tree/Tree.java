package data.tree;

import data.Node;

import java.util.List;

public interface Tree<ID, Value> {
    
    boolean isEmpty();
    
    <T extends TreeNode<ID, Value>> T getRoot();
    
    interface TreeNode<ID, Value> extends Node<ID, Value> {
        
        boolean hasChildren();
        
        <E extends TreeNode<ID, Value>, R extends List<E>> R getChildren();
        
        <R extends TreeNode<ID, Value>> R getOrAddChild(ID id);
    }
}
