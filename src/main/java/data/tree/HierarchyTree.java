package data.tree;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class HierarchyTree<Hierarchy, ID, Value> extends NaryTree<ID, Value> {
    private LinkedList<Hierarchy>                   hierarchies;
    private HierarchyTreeNode<Hierarchy, ID, Value> root;
    
    public HierarchyTree(List<Hierarchy> hierarchy) {
        this.hierarchies = new LinkedList<>(hierarchy);
        this.root = new HierarchyTreeNode<>();
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> putValue(List<ID> hierarchyIds, Value value) {
        HierarchyTreeNode<Hierarchy, ID, Value> node = this.addNode(hierarchyIds);
        node.setValue(value);
        
        return node;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> putValue(List<ID> hierarchyIds,
                                                            Value value,
                                                            BinaryOperator<Value> valueMergger) {
        HierarchyTreeNode<Hierarchy, ID, Value> node = this.addNode(hierarchyIds);
        node.setValue(valueMergger.apply(node.value, value));
        
        return node;
    }
    
    public HierarchyTreeNode<Hierarchy, ID, Value> findNode(List<ID> hierarchyIds) {
        Objects.requireNonNull(hierarchyIds);
        HierarchyTreeNode<Hierarchy, ID, Value> currNode = this.root;
        
        for(ID hierarchyId : hierarchyIds) {
            HierarchyTreeNode<Hierarchy, ID, Value> childNode = currNode.getChild(hierarchyId);
            if(childNode == null) {
                return null;
            }
            currNode = childNode;
        }
        
        return currNode;
    }
    
    private HierarchyTreeNode<Hierarchy, ID, Value> addNode(List<ID> ids) {
        Objects.requireNonNull(ids);
        HierarchyTreeNode<Hierarchy, ID, Value> currNode = this.root;
        //TODO: ID should not exceed Hierarchy
        Iterator<Hierarchy> hierarchyIterator = hierarchies.iterator();
        
        for(ID id : ids) {
            Hierarchy hierarchy = hierarchyIterator.next();
            currNode = currNode.getOrAddChild(hierarchy, id);
        }
        
        return currNode;
    }
    
    public HierarchyTree<Hierarchy, ID, Value> filter(Map<Hierarchy, Collection<ID>> filters) {
        Objects.requireNonNull(filters);
        HierarchyTree<Hierarchy, ID, Value> filterTree = new HierarchyTree<>(this.hierarchies);
        
        Map<Hierarchy, Set<ID>> filterMap = filters.entrySet()
                                                   .stream()
                                                   .filter(entry -> entry.getValue() != null)
                                                   .collect(Collectors.toMap(Map.Entry::getKey,
                                                                             entry -> new HashSet<>(entry.getValue())));
        
        filterTree.root = this.root.filter(filterMap);
        
        return filterTree;
    }
    
    @Override
    public boolean isEmpty() {
        return this.getRoot() == null || this.getRoot().isLeaf();
    }
    
    @Override
    public HierarchyTreeNode<Hierarchy, ID, Value> getRoot() {
        return this.root;
    }
    
    public List<Hierarchy> getHierarchies() {
        return this.hierarchies;
    }
}
