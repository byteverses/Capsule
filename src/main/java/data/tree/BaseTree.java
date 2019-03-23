package data.tree;

import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BaseTree<ID, Value> {
    
    private TreeNode<ID, Value> root;
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public void breadFirstTraversal(Consumer<TreeNode<ID, Value>> consumer) {
        Objects.requireNonNull(consumer);
        
        Queue<TreeNode<ID, Value>> queue = new LinkedList<>();
        queue.offer(this.root);
    
        while(!queue.isEmpty()) {
            TreeNode<ID, Value> currNode = queue.poll();
            if(currNode != null) {
                consumer.accept(currNode);
                currNode.getChildren().forEach(queue::offer);
                
            }
        }
    }
    
    public void depthFirstTraversal(Consumer<TreeNode<ID, Value>> consumer) {
        Objects.requireNonNull(consumer);
        this.depthFirstTraversal(this.root, consumer);
    }
    
    private void depthFirstTraversal(TreeNode<ID, Value> currNode, Consumer<TreeNode<ID, Value>> consumer) {
        if(currNode != null) {
            consumer.accept(currNode);
            currNode.getChildren().forEach(child -> this.depthFirstTraversal(child, consumer));
        }
    }
}
