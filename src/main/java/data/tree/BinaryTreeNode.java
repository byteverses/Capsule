package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTreeNode<ID, Value> extends BaseTreeNode<ID, Value> {
    
    protected BinaryTreeNode<ID, Value> parent;
    protected BinaryTreeNode<ID, Value> left;
    protected BinaryTreeNode<ID, Value> right;
    
    public BinaryTreeNode(ID id, Value value, BinaryTreeNode<ID, Value> parent) {
        super(id, value);
        this.parent = parent;
    }
    
    protected void preOrderRecursiveTraversal(Consumer<BinaryTreeNode<ID, Value>> consumer) {
        consumer.accept(this);
        if(this.hasLeftChild()) {
            this.preOrderRecursiveTraversal(consumer);
        }
        if(this.hasRightChild()) {
            this.preOrderRecursiveTraversal(consumer);
        }
    }
    
    protected void inOrderRecursiveTraversal(Consumer<BinaryTreeNode<ID, Value>> consumer) {
        if(this.hasLeftChild()) {
            this.preOrderRecursiveTraversal(consumer);
        }
        consumer.accept(this);
        if(this.hasRightChild()) {
            this.preOrderRecursiveTraversal(consumer);
        }
    }
    
    protected void postOrderRecursiveTraversal(Consumer<BinaryTreeNode<ID, Value>> consumer) {
        if(this.hasLeftChild()) {
            this.preOrderRecursiveTraversal(consumer);
        }
        if(this.hasRightChild()) {
            this.preOrderRecursiveTraversal(consumer);
        }
        consumer.accept(this);
    }
    
    public boolean hasLeftChild() {
        return this.left != null;
    }
    
    public boolean hasRightChild() {
        return this.right != null;
    }
    
    @Override
    public List<BaseTreeNode<ID, Value>> getChildren() {
        ArrayList<BaseTreeNode<ID, Value>> children = new ArrayList<>();
        if(this.hasLeftChild()) {
            children.add(this.left);
        }
        if(this.hasRightChild()) {
            children.add(this.right);
        }
        return children;
    }
    
    @Override
    public boolean hasChildren() {
        return this.hasLeftChild() || this.hasRightChild();
    }
    
    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "id=" + id +
                ", value=" + value +
                ", (leftChild=" + (left == null ? "null" : left.id) +
                ", rightChild=" + (right == null ? "null" : right.id) +
                ")}";
    }
    
    @Override
    protected void flatString(StringBuilder stringBuilder, String indent) {
        stringBuilder.append(indent)
                     .append(this.toString())
                     .append(System.lineSeparator());
        indent = "    " + indent;
        if(this.hasLeftChild()) {
            this.left.flatString(stringBuilder, indent);
        }
        if(this.hasRightChild()) {
            this.right.flatString(stringBuilder, indent);
        }
    }
}
