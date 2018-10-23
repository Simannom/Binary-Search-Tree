/**
 * Created by Simannom on 16.05.2017.
 */

package algojava;

import java.util.ArrayList;

public class BST <Key extends Comparable<Key>, Value>
{
    public Node root;   // root of BST
    public BST (Node root)
    {
        this.root = root;
    }

    public BST (Key key, Value val, int N)
    {
        this.root = new Node (key, val, N);
    }

    public class Node
    {
        private Key key; // key
        private Value val; // associated value
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }

        public Key getK() {return key; }
    }

    //ðàçìåð ïîääåðåâà ýòîé âåðøèíû
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    //ðàçìåð ïîääåðåâà ñ âåðøèíîé â êîðíå, ò.å. ðàçìåð âñåãî äåðåâà
    public int size() {return size(root);}

    public Value get(Key key) { return get(root, key); }

    // Âîçâðàùàåò çíà÷åíèå çàäàííîãî êëþ÷à èç ïîääåðåâà x;
    // Âîçâðàùàåò null åñëè êëþ÷à íåò â ïîääåðåâå x.
    private Value get(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public Node getN(Key key) { return getN(root, key); }

    // Âîçâðàùàåò çíà÷åíèå çàäàííîãî êëþ÷à èç ïîääåðåâà x;
    // Âîçâðàùàåò null åñëè êëþ÷à íåò â ïîääåðåâå x.
    private Node getN(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return getN(x.left, key);
        else if (cmp > 0) return getN(x.right, key);
        else return x;
    }

    // Change key’s value to val if key in subtree rooted at x.
    // Otherwise, add new node to subtree associating key with val.
    private Node put(Node x, Key key, Value val){
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // Search for key. Update value if found; grow table if new.
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Key min() { return min(root).key; }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() { return max(root).key; }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key)    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) { return select(root, k).key;  }

    // Return Node containing key of rank k.
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key) { return rank(key, root); }

    // Return number of keys less than x.key in the subtree rooted at x.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {root = deleteMin(root);  }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(Key key) { root = delete(root, key); }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    public void draw (Node x){
        draw(x, 0);
    }

    private void draw(Node x, int level) {
        for (int i = 0; i<level; i++){
            System.out.print("   ");
        }

        if (x == null) {
            System.out.println("null");
            return;
        }
        if (x.left==null && x.right == null){
            System.out.println(x.key);
            return;
        }
        System.out.println(x.key);
        draw(x.left, level+1);
        draw(x.right, level+1);
    }

// Example:                               //
//                  10                    //
//                /    \                  //
//             5          15              //
//           /   \      /    \            //
//        null    7   null    null        //
//               /  \                     //
//            null  null                  //
// is represented as                      //
// 10
//    5
//      null
//      7
//         null
//         null
//    15
//      null
//      null

/*
    private Node search(Node x, Key key) {
        if (x == null || key == x.key)
            return x;
        if (key.compareTo(x.key)<0)
            return search(x.left, key);
        else
            return search(x.right, key);
    }
*/

// ñîáñòâåííî ïîèñê âñåõ êëþ÷åé ìåæäó äâóìÿ çàäàííûìè
    public void search(Key key1, Key key2, ArrayList<Node> y){
        if (key1.compareTo(key2)>0){
            Key tmp;
            tmp = key1;
            key1 = key2;
            key2 = tmp;
        }
        search(root, key1, key2, y);
    }

    private void search(Node x, Key key1, Key key2, ArrayList<Node> list) {
        if (key1.compareTo(key2)==0)
            return;

        if (x == null ) return;
        if (key1.compareTo(x.key)<0 && key2.compareTo(x.key)>0) {
            list.add(x);
            search(x.right, key1, key2, list);
            search(x.left, key1, key2, list);
            return;
        }

        if (key1.compareTo(x.key)<0) {
            search(x.left, key1, key2, list);
            return;
        }
        if (key2.compareTo(x.key)>0) {
            search(x.right, key1, key2, list);
            return;
        }
    }



}
