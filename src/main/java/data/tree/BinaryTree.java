package data.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<ID, Value> extends BaseTree<ID, Value> {
    
    private BinaryTreeNode<ID, Value> root;
    
    public BinaryTree() {
    }
    
    public List<BinaryTreeNode<ID, Value>> preOrderLoopTraversal() {
        List<BinaryTreeNode<ID, Value>> nodes = new ArrayList<>();
        Stack<BinaryTreeNode<ID, Value>> stack = new Stack<>();
        stack.push(this.root);
        
        while(!stack.isEmpty()) {
            BinaryTreeNode<ID, Value> currNode = stack.pop();
            if(currNode != null) {
                nodes.add(currNode);
                stack.push(currNode.right);
                stack.push(currNode.left);
            }
        }
        
        return nodes;
    }
    
    public List<BinaryTreeNode<ID, Value>> inOrderLoopTraversal() {
        List<BinaryTreeNode<ID, Value>> nodes = new ArrayList<>();
        Stack<BinaryTreeNode<ID, Value>> stack = new Stack<>();
        
        BinaryTreeNode<ID, Value> currNode = this.root;
        while(currNode != null || !stack.isEmpty()) {
            while(currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.pop();
            nodes.add(currNode);
            currNode = currNode.right;
        }
        
        return nodes;
    }
    
    public List<BinaryTreeNode<ID, Value>> postOrderLoopTraversal() {
        LinkedList<BinaryTreeNode<ID, Value>> nodes = new LinkedList<>();
        Stack<BinaryTreeNode<ID, Value>> stack = new Stack<>();
        
        BinaryTreeNode<ID, Value> currNode = root;
        while(currNode != null || !stack.isEmpty()) {
            if(currNode != null) {
                stack.push(currNode);
                nodes.addFirst(currNode);
                currNode = currNode.right;
            }
            else {
                currNode = stack.pop().left;
            }
        }
        return nodes;
    }
    
    public void preOrderRecursiveTraversal(Consumer<BinaryTreeNode<ID, Value>> consumer) {
        if(!this.isEmpty()) {
            this.root.preOrderRecursiveTraversal(consumer);
        }
    }
    
    public void inOrderRecursiveTraversal(Consumer<BinaryTreeNode<ID, Value>> consumer) {
        if(!this.isEmpty()) {
            this.root.inOrderRecursiveTraversal(consumer);
        }
    }
    
    public void postOrderRecursiveTraversal(Consumer<BinaryTreeNode<ID, Value>> consumer) {
        if(!this.isEmpty()) {
            this.root.postOrderRecursiveTraversal(consumer);
        }
    }
    
    @Override
    public BinaryTreeNode<ID, Value> getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        if(this.isEmpty()) {
            return " [Empty Tree] ";
        }
        StringBuilder stringBuilder = new StringBuilder();
        this.root.flatString(stringBuilder, "|-");
        return "BinaryTree={" + System.lineSeparator() + stringBuilder.toString() + '}';
    }
    
}
