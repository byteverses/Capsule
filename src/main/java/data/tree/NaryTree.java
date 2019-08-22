package data.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;

public class NaryTree<ID, Value> implements Tree<ID, Value> {
    
    private NaryTreeNode<ID, Value> root;
    
    public void breadFirstTraversal(Consumer<NaryTreeNode<ID, Value>> consumer) {
        Objects.requireNonNull(consumer);
        
        Queue<NaryTreeNode<ID, Value>> queue = new LinkedList<>();
        queue.offer(this.getRoot());
        
        while(!queue.isEmpty()) {
            NaryTreeNode<ID, Value> currNode = queue.poll();
            if(currNode != null) {
                consumer.accept(currNode);
                currNode.getChildren().forEach(queue::offer);
            }
        }
    }
    
    public void depthFirstTraversal(Consumer<NaryTreeNode<ID, Value>> consumer) {
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
        this.getRoot().flatToString(stringBuilder, "|-");
        return "Tree={" + System.lineSeparator() + stringBuilder.toString() + '}';
    }
    
    @Override
    public NaryTreeNode<ID, Value> getRoot() {
        return this.root;
    }
}
