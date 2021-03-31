package tp3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    private BinaryNode<ValueType> root;

    public AvlTree() { }

    /**
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * @param value value to add to the tree
     */
    public void insert(ValueType value) {
        if (root == null) {
            root = new BinaryNode<ValueType>(value, null);
        } else {
            insert(value, root);
        }
    }

    /**
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * @param value value to add to the tree
     */
    public void remove(ValueType value){
        remove(value, root);
    }

    /**
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        return contains(value, root);
    }

    /**
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        return getLevelCount(root) - 1;
    }

    /**
     * Returns the minimal value contained in our root tree
     * @return Minimal value contained in our root tree
     */
    public ValueType findMin() {
        BinaryNode<ValueType> minimalNode = findMin(root);
        if (minimalNode == null) return null;
        return minimalNode.value;
    }

    /**
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {
        List<ValueType> items = new LinkedList<ValueType>();
        infixOrder(root, items);
        return items;
    }

    /**
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        List<ValueType> items = new LinkedList<ValueType>();

        ArrayDeque<BinaryNode<ValueType>> nodesToCheck = new ArrayDeque<BinaryNode<ValueType>>();

        if (root != null){
            nodesToCheck.push(root);
            levelOrder(nodesToCheck, items);
        }

        return items;
    }

    /** TODO O( log n )
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     * @param currentNode Node currently being accessed in this recursive method
     * @return if parent node should balance
     */
    private boolean insert (ValueType value, BinaryNode<ValueType> currentNode){
        BinaryNode<ValueType> NodeToInsert = new BinaryNode<ValueType>(value,currentNode);

        int difference = value.compareTo(currentNode.value);

        if(difference < 0){
            if (currentNode.left == null){
                currentNode.left = NodeToInsert;
                balance(currentNode.parent);
                return true;
            }
            return insert(value,currentNode.left);
        }

        if(difference > 0){
            if(currentNode.right == null){
                currentNode.right = NodeToInsert;
                balance(currentNode.parent);
                return true;
            }
            return insert(value,currentNode.right);
        }
        return false;
    }

    /** TODO O ( log n )
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     * @param value value to remove from the tree
     * @param currentNode Node currently being accessed in this recursive method
     * @return if parent node should balance
     */
    private boolean remove(ValueType value, BinaryNode<ValueType> currentNode) {

        int compareResult = value.compareTo(currentNode.value);
        if (currentNode == null) {
            return false;
        }
        if (compareResult < 0) return remove(value, currentNode.left);

        if (compareResult > 0) return remove(value, currentNode.right);

        BinaryNode<ValueType> childNode = (currentNode.left != null) ? currentNode.left : currentNode.right;

        // both child not null
        if (currentNode.left != null && currentNode.right != null) {
            currentNode.value = findMin(currentNode.right).value;
            remove(currentNode.value, currentNode.right);
        }

        // top of the tree no parent
        if (currentNode.parent == null) {
            currentNode = childNode;
            this.root = childNode;
            return true;
        }
        // both child null
        if (childNode == null) {
            if (currentNode.parent.left == currentNode) {
                currentNode.parent.left = null;
            } else currentNode.parent.right = null;
            balance(currentNode.parent);
            return true;
        }
        // One child not null
        currentNode.value = childNode.value;
        currentNode.left = null;
        currentNode.right = null;
        balance(currentNode.parent);
        return true;
    }

    /** TODO O( n )
     * Balances the subTree
     * @param subTree SubTree currently being accessed to verify if it respects the AVL balancing rule
     */
    private void balance(BinaryNode<ValueType> subTree) {
        if(subTree == null) return;

        if(getLevelCount(subTree.left) - getLevelCount(subTree.right) > 1) {
            if(getLevelCount(subTree.left.left) >= getLevelCount(subTree.left.right)) {
                rotateRight(subTree);
            }
            else doubleRotateOnRightChild(subTree);
        }

        else if((getLevelCount(subTree.right) - getLevelCount(subTree.left)) > 1) {
            if(getLevelCount(subTree.right.right) >= getLevelCount(subTree.right.left)) {
                rotateLeft(subTree);
            }
            else doubleRotateOnLeftChild(subTree);
        }
        else balance(subTree.parent);
    }

    /** TODO O( 1 )
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> rightChildNode1 = node1.right;
        // Link le rightChildNode1 selon la position de la node1
        if(node1.parent != null) {

            if(node1.parent.left.value == node1.value)
                node1.parent.left = rightChildNode1;
            else
                node1.parent.right = rightChildNode1;
        }
        // change le root si la node a rotate etait le root
        else{
            this.root = rightChildNode1;
        }
        rightChildNode1.parent = node1.parent;
        node1.right = rightChildNode1.left;
        node1.parent = rightChildNode1;
        rightChildNode1.left = node1;
    }

    /** TODO O( 1 )
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> leftChildNode = node1.left;
        // Link le leftChildNode selon la position de la node1
        if(node1.parent != null) {
            if(node1.parent.left.value == node1.value)
                node1.parent.left = leftChildNode;
            else
                node1.parent.right = leftChildNode;
        }
        // change le root si la node a rotate etait le root
        else{
            this.root = leftChildNode;
        }
        leftChildNode.parent = node1.parent;
        node1.left = leftChildNode.right;
        node1.parent = leftChildNode;
        leftChildNode.right = node1;
    }

    /** TODO O( 1 )
     * Double rotation on left child, AVR Algorithm
     * @param node1 Node to become child of the right child of its left child
     */
    private void doubleRotateOnLeftChild(BinaryNode<ValueType> node1){
        rotateRight(node1.right);
        rotateLeft(node1);
    }

    /** TODO O( 1 )
     * Double rotation on right child, AVR Algorithm
     * @param node1 Node to become child of the left child of its right child
     */
    private void doubleRotateOnRightChild(BinaryNode<ValueType> node1){
        rotateLeft(node1.left);
        rotateRight(node1);
    }

    /** TODO O( log n )
     * Verifies if the root tree contains value
     * @param value value to verify
     * @param currentNode Node currently being accessed in this recursive method
     * @return if value already exists in the root tree
     */
    private boolean contains(ValueType value, BinaryNode<ValueType> currentNode){

        if (currentNode ==null) return false;

        int difference = value.compareTo(currentNode.value);

        if(difference < 0) return contains(value, currentNode.left);

        if(difference > 0) return contains(value,currentNode.right);

        return true;

    }

    /** TODO O( n )
     * Returns the number of level contained in subTree including subTree node level
     * @return Number of level contained in subTree including subTree node level
     */
    private int getLevelCount(BinaryNode<ValueType> subTree){
        if(subTree == null) return 0;
        return Math.max(getLevelCount(subTree.left), getLevelCount(subTree.right)) + 1;
    }

    /** TODO O( log n )
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    private BinaryNode<ValueType> findMin(BinaryNode<ValueType> currentNode) {
        if(currentNode == null) return null;

        if(currentNode.left == null) // la node a ete trouver
            return currentNode;

        return findMin(currentNode.left); // prend toujours le chemin a gauche

    }

    /** TODO O( n )
     * Builds items which should contain all values within the root tree in ascending order
     * @param currentNode Node currently being accessed in this recursive method
     * @param items List being modified to contain all values in the root tree in ascending order
     */
    private void infixOrder(BinaryNode<ValueType> currentNode, List<ValueType> items){
        if(currentNode != null) {
            infixOrder(currentNode.left, items);
            items.add(currentNode.value);
            infixOrder(currentNode.right, items);
        }
    }

    /** TODO O( n )
     * Builds items which should contain all values within the root tree in level order from top to bottom
     * @param nodesToCheck Queue for non-recursive algorithm
     * @param items List being modified to contain all values in the root tree in level order from top to bottom
     */
    private void levelOrder(ArrayDeque<BinaryNode<ValueType>> nodesToCheck, List<ValueType> items) {
        while(!nodesToCheck.isEmpty()) {
            BinaryNode<ValueType> frontNode = nodesToCheck.poll(); // return and remove the element at the front of the ArrayDeque

            items.add(frontNode.value);// met la valeur de la node dans la list
            // Les nodes sont placer en ordre de niveau dans l ArrayDeque
            if(frontNode.left != null) {
                nodesToCheck.add(frontNode.left);
            }
            if(frontNode.right != null) {
                nodesToCheck.add(frontNode.right);
            }
        }
    }
    
    static class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }
}