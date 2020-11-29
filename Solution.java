import java.util.*;

public class Solution<Key extends Comparable<Key>, Value> {
    int size;
    static int nodeCount; //used in select function
    private Node root;
   
    public Solution() {
        size = 0;
        root = null;
    }
    // class name as Node
    class Node{
        Key key;
        Value val;
        Node left,right;
    // using constructor as same as class name
    public Node(Key key, Value val){
            this.key = key;
            this.val = val;
        }
    }
    // to check whether given size is true or false
    public boolean isEmpty() {
         if (size() == 0) {
            return true;
        }
        return false;
       
    }
    // to see the size of given keys
    public int size() {
      return size; 
    }

    // to check the contains of key
    public boolean contains(Key key) {
       Node curNode = root;
       if(key == null){
            throw new IllegalArgumentException("There is not key conatin in the root");
       }
       else{
            while(curNode.key != key){
                int cmp = key.compareTo(curNode.key);
                if(cmp < 0){
                    curNode = curNode.left;
                }
                else{
                    curNode = curNode.right;
                }
            }
            return true;
       }
    }

    // its to get the given value in keys
    public Value get(Key key) {
         Node curNode = root; 
         while(curNode.key != key){
            int cmp = key.compareTo(curNode.key);
            if(cmp < 0){
                curNode = curNode.left;
            }
            else if(cmp > 0){
                curNode = curNode.right;
            }
        }
        if (curNode.key == key) {
                    return curNode.val;
                }
            return null;
    }

    // to insert or put the value and key
    public void put(Key key, Value val) {
        if (key == null){
            System.out.println("Key Cannot be Null!!!");
            return;
        }

        Node newest = new Node(key, val);

        if (root == null){
            root = newest;
            size++;
        }
        else{
            Node curNode = root;
            Node parent;
            while (true){
                parent = curNode;
                int cmp = key.compareTo(curNode.key);
                if (cmp<0){
                    curNode = curNode.left;
                    if (curNode==null){
                        parent.left = newest;
                        size++;
                        return;
                    }
                    else if(curNode.key == key){
                        curNode.val = val;
                        return;
                    }
                }
                else if (cmp>0){
                    curNode = curNode.right;
                    if (curNode==null){
                        parent.right = newest;
                        size++;
                        return;
                    }
                    else if (curNode.key == key){
                        curNode.val = val;
                        return;
                    }
                }
            }
        }
    }

    // to check the minimum key in BST 
    public Key min() {
        if(isEmpty()){
            throw new NoSuchElementException("There is no element in the tree");
        }
        else{
            Node curNode = root;
            while(curNode.left != null){
                curNode = curNode.left;
            }
            return curNode.key;
       }
    }

    // to check the maximum key in BST
    public Key max() {
        if(isEmpty()){
            throw new NoSuchElementException("There is no element in the tree");
        }
        else{
            Node curNode = root;
            while(curNode.right != null){
                curNode = curNode.right;
            }
            return curNode.key;
       }
    }

        // to check the floor of given key  
    public Key floor(Key key){
        if(isEmpty()){
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node curNode = root;
        Node parent = null;
        while(curNode != null){
            parent = curNode;
            int cmp = key.compareTo(parent.key);
            if(key == null){
                throw new IllegalArgumentException("calls floor() with a null key");
            }
            if(cmp == 0){
                return parent.key;
            }
            if(cmp < 0){
                curNode = parent.left;
            }
           
            else if(cmp > 0){
                curNode = parent.right;
               
                int cmp1 = key.compareTo(curNode.key);
                if(cmp1 < 0 ){
                    return parent.key;
                }
                
                else{
                    curNode = parent.right;
                } 
            }
        }
        return parent.key;
    }

    // to select the given key with its size
    public Key select(int k) {
        nodeCount=0;
        if ((k<0) || k>=size()) {
            throw new IllegalArgumentException("Rank should be between 0 ans its size");
        }
        return select(root, k+1).key;
    }
    
    // Return key of rank k. 
     private Node select(Node x, int k) {
        Stack<Node> stack = new Stack<Node>();
        Node curNode = root;
        while (!stack.empty() || curNode != null)
        {
            if (curNode != null)
            {
                stack.push(curNode);
                curNode = curNode.left;
            }
            else
            {
                curNode = stack.pop();
                nodeCount++;
                if(nodeCount == k)
                    break;
                curNode = curNode.right;
            }
        }
        return curNode;
    } 



    // to iterate the keys  of maximum and minimum
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    // iterating the key of high and low
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }

        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }

        ArrayList<Key> arr = new ArrayList<Key>();
        for (Node x = root; x == lo; x = x.left) {
            arr.add(x.key);     
        }
        keys(root, arr, lo, hi);
        return arr;
        
    } 

    // creating void function as keys
    private void keys(Node x, ArrayList<Key> arr, Key lo, Key hi) { 
        if (x == null) return;

        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) {
            keys(x.left, arr, lo, hi); 
        }
        if (cmplo <= 0 && cmphi >= 0) {
            arr.add(x.key); 
        }
        if (cmphi > 0) {
            keys(x.right, arr, lo, hi); 
        }
    }  

}