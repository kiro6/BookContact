/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookcontact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kokom
 */
public class AVL implements Tree {

    Node root;
    Queue queue;
    int numOfNodes ; 

    @Override
    public void insert(String name, String phone, String email, String adress) {
        
        root = insert(name, phone, email, adress, root);
        numOfNodes++  ;
    }

    private Node insert(String name, String phone, String email, String adress, Node node) {
        if (node == null) {
            System.out.println("contact inserted successfully");
            return new Node(name, phone, email, adress);
        }

        Node newNode = new Node(name, phone, email, adress);

        if (newNode.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(name, phone, email, adress, node.getLeftChild()));
        } else if (newNode.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(name, phone, email, adress, node.getRightChild()));
        } else {
            System.out.println("there is contact already with this name ");
            return node;
        }

        updateHeight(node);
        return applyRotation(node);

    }

    @Override
    public void insertAll(String name, String phone, String email, String adress) {
        root = insertAll(name, phone, email, adress, root);
         numOfNodes++  ;
    }

    private Node insertAll(String name, String phone, String email, String adress, Node node) {
        if (node == null) {

            return new Node(name, phone, email, adress);
        }

        Node newNode = new Node(name, phone, email, adress);

        if (newNode.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insertAll(name, phone, email, adress, node.getLeftChild()));
        } else if (newNode.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insertAll(name, phone, email, adress, node.getRightChild()));
        } else {

            return node;
        }

        updateHeight(node);
        return applyRotation(node);

    }

    @Override
    public void delete(String data) {
        root = delete(data, root);
    }

    private Node delete(String data, Node node) {
        if (node == null) {
            System.out.println("there is no contact with this name !!!! \n");
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            // One child or No children
            if (node.getLeftChild() == null) {
                numOfNodes--;

                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                numOfNodes--;

                return node.getLeftChild();
            }
            // Two children

            numOfNodes--;

            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    public void inOrderTraverse(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraverse(node.getLeftChild());
        System.out.println(node.getData());
        inOrderTraverse(node.getRightChild());

    }

    @Override
    public Queue getNewQueue() {
        queue = new Queue();
        getNewQueueValue(root);
        return queue;

    }

    public void getNewQueueValue(Node node) {

        if (node == null) {
            return;
        }

        getNewQueueValue(node.getLeftChild());
        queue.enqueue(node);
        getNewQueueValue(node.getRightChild());

    }

    @Override
    public String [] getMax() {
        if (isEmpty()) {
            return null;
        } else {
            return getMax(root);
        }

    }

    private String[] getMax(Node node) {
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        String data [] = {node.getData()  , node.getPhone() , node.getEmail() , node.getAddress()} ;  
        return data;
    }

    @Override
    public String[] getMin() {
        if (isEmpty()) {
            return null;
        } else {
            return getMin(root);
        }

    }

    public String[] getMin(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
          String data [] = {node.getData()  , node.getPhone() , node.getEmail() , node.getAddress()} ;  
        return data;
     

    }

    @Override
    public boolean isEmpty() {

        if (root == null) {
            return true;
        } else {
            return false;
        }

    }

    public int height(Node node) {
        return node != null ? node.getHeight() : 0;

    }

    public void updateHeight(Node node) {
        int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild()));

        node.setHeight(maxHeight + 1);
    }

    public int balance(Node node) {
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private Node applyRotation(Node node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.getLeftChild();
        Node centerNode = leftNode.getRightChild();
        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.getRightChild();
        Node centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    @Override
    public Node searchByDataKey(String key) {

        return searchByDataKeyin(root, key);

    }

    public Node searchByDataKeyin(Node node, String key) {
        if (node == null) {
            return null;
        }

        if (node.getData().contentEquals(key)) {
            return node;
        }

        if (key.compareTo(node.getData()) < 0) {
            return searchByDataKeyin(node.getLeftChild(), key);
        } else {
            return searchByDataKeyin(node.getRightChild(), key);
        }

    }

    @Override
    public Queue searchByPart(String key) {
        Queue elemnts = new Queue();

        if (root != null) {
            Queue q = new Queue();
            q.enqueue(root);
            while (!q.isEmpty()) {
                Node n = (Node) q.dequeue();
                if (n.getData().contains(key)) {
                    elemnts.enqueue(n);
                }

                if (n.getLeftChild() != null) {
                    q.enqueue(n.getLeftChild());
                }
                if (n.getRightChild() != null) {
                    q.enqueue(n.getRightChild());
                }

            }
        }

        return elemnts;
    }

    @Override
    public Queue searchByFirst(String key) {
        Queue elemnts = new Queue();

        if (root != null) {
            Queue q = new Queue();
            q.enqueue(root);
            while (!q.isEmpty()) {
                Node n = (Node) q.dequeue();
                if (n.getData().split(" ")[0].contentEquals(key)) {
                    elemnts.enqueue(n);
                }

                if (n.getLeftChild() != null) {
                    q.enqueue(n.getLeftChild());
                }
                if (n.getRightChild() != null) {
                    q.enqueue(n.getRightChild());
                }

            }
        }

        return elemnts;
    }

    @Override
    public Queue searchByLast(String key) {
        Queue elemnts = new Queue();

        if (root != null) {
            Queue q = new Queue();
            q.enqueue(root);
            while (!q.isEmpty()) {
                Node n = (Node) q.dequeue();
                if (n.getData().split(" ")[1].contentEquals(key)) {

                    elemnts.enqueue(n);
                }

                if (n.getLeftChild() != null) {
                    q.enqueue(n.getLeftChild());
                }
                if (n.getRightChild() != null) {
                    q.enqueue(n.getRightChild());
                }

            }
        }

        return elemnts;
    }


    
    

    public static void print(Node root)
    {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<Node> level = new ArrayList<Node>();
        List<Node> next = new ArrayList<Node>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getName();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeftChild());
                    next.add(n.getRightChild());

                    if (n.getLeftChild()!= null) nn++;
                    if (n.getRightChild()!= null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    
//        public static  void printNode(Node root) {
//        int maxLevel = AVL.maxLevel(root);
//
//        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
//    }
//
//    private static  void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
//        if (nodes.isEmpty() || AVL.isAllElementsNull(nodes))
//            return;
//
//        int floor = maxLevel - level;
//        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
//        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
//        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
//
//        AVL.printWhitespaces(firstSpaces);
//
//        List<Node> newNodes = new ArrayList<Node>();
//        for (Node node : nodes) {
//            if (node != null) {
//                System.out.print(node.getData());
//                newNodes.add(node.getLeftChild());
//                newNodes.add(node.getRightChild());
//            } else {
//                newNodes.add(null);
//                newNodes.add(null);
//                System.out.print(" ");
//            }
//
//            AVL.printWhitespaces(betweenSpaces);
//        }
//        System.out.println("");
//
//        for (int i = 1; i <= endgeLines; i++) {
//            for (int j = 0; j < nodes.size(); j++) {
//                AVL.printWhitespaces(firstSpaces - i);
//                if (nodes.get(j) == null) {
//                    AVL.printWhitespaces(endgeLines + endgeLines + i + 1);
//                    continue;
//                }
//
//                if (nodes.get(j).getLeftChild() != null)
//                    System.out.print("/");
//                else
//                    AVL.printWhitespaces(1);
//
//                AVL.printWhitespaces(i + i - 1);
//
//                if (nodes.get(j).getRightChild() != null)
//                    System.out.print("\\");
//                else
//                    AVL.printWhitespaces(1);
//
//                AVL.printWhitespaces(endgeLines + endgeLines - i);
//            }
//
//            System.out.println("");
//        }
//
//        printNodeInternal(newNodes, level + 1, maxLevel);
//    }
//
//    private static void printWhitespaces(int count) {
//        for (int i = 0; i < count; i++)
//            System.out.print(" ");
//    }
//
//    private static <T extends Comparable<T>> int maxLevel(Node node) {
//        if (node == null)
//            return 0;
//
//        return Math.max(AVL.maxLevel(node.getLeftChild()), AVL.maxLevel(node.getRightChild())) + 1;
//    }
//
//    private static <T> boolean isAllElementsNull(List<T> list) {
//        for (Object object : list) {
//            if (object != null)
//                return false;
//        }
//
//        return true;
//    }

    
}
