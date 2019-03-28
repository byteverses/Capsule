package data.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;

public class BaseTree<ID, Value> implements Tree<ID, Value> {
    
    private BaseTreeNode<ID, Value> root;
    
    public void breadFirstTraversal(Consumer<BaseTreeNode<ID, Value>> consumer) {
        Objects.requireNonNull(consumer);
        
        Queue<BaseTreeNode<ID, Value>> queue = new LinkedList<>();
        queue.offer(this.getRoot());
        
        while(!queue.isEmpty()) {
            BaseTreeNode<ID, Value> currNode = queue.poll();
            if(currNode != null) {
                consumer.accept(currNode);
                currNode.getChildren().forEach(queue::offer);
            }
        }
    }
    
    public void depthFirstTraversal(Consumer<BaseTreeNode<ID, Value>> consumer) {
        Objects.requireNonNull(consumer);
        this.getRoot().traversal(consumer);
    }
    
    @Override
    public boolean isEmpty() {
        return this.getRoot() == null;
    }
    
    @Override
    public String toString() {
        if(this.isEmpty()) {
            return " [Empty Tree] ";
        }
        StringBuilder stringBuilder = new StringBuilder();
        this.getRoot().flatString(stringBuilder, "|-");
        return "Tree={" + System.lineSeparator() + stringBuilder.toString() + '}';
    }
    
    @Override
    public BaseTreeNode<ID, Value> getRoot() {
        return this.root;
    }
}
