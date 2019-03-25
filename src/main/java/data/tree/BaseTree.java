package data.tree;

import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BaseTree<ID, Value> {
    
    private TreeNode<ID, Value> root;
    
    public void breadFirstTraversal(Consumer<TreeNode<ID, Value>> consumer) {
        Objects.requireNonNull(consumer);
        
        Queue<TreeNode<ID, Value>> queue = new LinkedList<>();
        queue.offer(root);
        
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
        root.traversal(consumer);
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    @Override
    public String toString() {
        if(isEmpty()) {
            return " [Empty Tree] ";
        }
        StringBuilder stringBuilder = new StringBuilder();
        root.flatString(stringBuilder, "|-");
        return "Tree={" + System.lineSeparator() + stringBuilder.toString() + '}';
    }
    
}
