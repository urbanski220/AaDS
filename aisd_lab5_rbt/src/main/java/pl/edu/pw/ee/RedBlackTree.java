package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    public Node<K, V> root;

    private static int rec;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        rec = -1;
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        rec++;
        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(isRed(node) ? BLACK : RED);
        node.getLeft().setColor(isRed(node) ? BLACK : RED);
        node.getRight().setColor(isRed(node) ? BLACK : RED);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }

    public void deleteMax(){
        if(root == null){
            throw new IllegalArgumentException("You cannot delete from an empty tree.");
        }
        
        root = deleteMax(root);

        if(root != null)
            root.setColor(BLACK);
    }

    private Node<K,V> deleteMax(Node<K,V> node){
        if(isRed(node.getLeft()))
            node = rotateRight(node);
        

        if(node.getRight() == null)
            return null;

        if(!isRed(node.getRight()) && !isRed(node.getRight().getLeft())){
            changeColors(node);

            if(isRed(node.getLeft().getLeft())){
                node = rotateRight(node);
                changeColors(node);
            }
        }

        node.setRight(deleteMax(node.getRight()));

        return reorganizeTree(node);
    }

    public String getPreOrder(){
		String p = getPreOrder(root);
        return p;
        
    }

    private String getPreOrder(Node<K,V> node){
        if (node == null)
			return null;
        
		
		String l = getPreOrder(node.getLeft());
        String r = getPreOrder(node.getRight());
        String p = (String.valueOf(node.getKey())) + ":" + (String.valueOf(node.getValue()));

        if (l != null)
            p = p + " " + l;

        if( r != null)
            p = p + " " + r;

         return p;

    }

    public String getInOrder(){
        String i = getInOrder(root);
        return i;
        
    }

    private String getInOrder(Node<K,V> node){
        if (node == null)
			return null;
        
		
		String l = getInOrder(node.getLeft());
        String r = getInOrder(node.getRight());
        String i = (String.valueOf(node.getKey())) + ":" + (String.valueOf(node.getValue()));

        if (l != null)
            i = l + " " + i;

        if( r != null)
            i = i + " " + r;

         return i;
    }

    public String getPostOrder(){
        String p = getPostOrder(root);
        return p;
        
    }

    private String getPostOrder(Node<K,V> node){
        if (node == null)
			return null;
        
        
		String tmp = "";
		String l = getPostOrder(node.getLeft());
        String r = getPostOrder(node.getRight());
        String p = (String.valueOf(node.getKey())) + ":" + (String.valueOf(node.getValue()));

        if(l != null && r != null)
            tmp = l + " " + r + " " + p;
        else if (l != null)
            tmp = l + " " + p;
        else if( r != null)
            tmp = r + " " + p;
        else
            tmp = p;
        

         return tmp;
    }

    public int getrec(){
        return rec;
    }
}
