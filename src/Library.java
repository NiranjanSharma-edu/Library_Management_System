package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Library {

    // Binary Search Tree node
    class Node {
        String key;
        Node left, right;

        Node(String item) {
            key = item;
            left = right = null;
        }
    }

    private Node root;
    private Map<String, Integer> hashMapping = new HashMap<>();
    private int[][] bookQuantities = new int[100][2];
    private int bookCount = 0;

    private Node insertRec(Node root, String key) {
        if (root == null)
            return new Node(key);

        if (key.compareTo(root.key) < 0)
            root.left = insertRec(root.left, key);
        else if (key.compareTo(root.key) > 0)
            root.right = insertRec(root.right, key);
        else
            System.out.println("Error: Book already exists in BST.");
        return root;
    }

    private boolean containsNodeRecursive(Node current, String key) {
        if (current == null) return false;
        if (key.equals(current.key)) return true;
        return key.compareTo(current.key) < 0
                ? containsNodeRecursive(current.left, key)
                : containsNodeRecursive(current.right, key);
    }

    private Node deleteRec(Node root, String key) {
        if (root == null) return root;

        if (key.compareTo(root.key) < 0)
            root.left = deleteRec(root.left, key);
        else if (key.compareTo(root.key) > 0)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            Node temp = minValueNode(root.right);
            root.key = temp.key;
            root.right = deleteRec(root.right, temp.key);
        }
        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private void printInorder(Node node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }

    private void printTreeRec(Node t, int space) {
        if (t == null) return;

        space += 5;
        printTreeRec(t.right, space);
        System.out.println();
        for (int i = 5; i < space; i++)
            System.out.print(" ");
        System.out.println("[" + t.key + "]");
        printTreeRec(t.left, space);
    }

    // --- Public methods ---

    public void insert(String key) { root = insertRec(root, key); }

    public boolean containsNode(String value) { return containsNodeRecursive(root, value); }

    public void deleteKey(String key) { root = deleteRec(root, key); }

    public void printInorder() { printInorder(root); }

    public void printTree() { printTreeRec(root, 0); }

    public void addBook(String name, int quantity) {
        if (containsNode(name)) {
            System.out.println("Book already exists.");
            return;
        }
        insert(name);
        hashMapping.put(name, bookCount);
        bookQuantities[bookCount][0] = quantity;
        bookQuantities[bookCount][1] = quantity;
        bookCount++;
    }

    public void deleteBook(String name) {
        if (!containsNode(name)) {
            System.out.println("Book does not exist.");
            return;
        }
        deleteKey(name);
        hashMapping.remove(name);
    }

    public void updateBook(String name, int quantity) {
        if (!containsNode(name)) {
            System.out.println("Book does not exist.");
            return;
        }
        int index = hashMapping.get(name);
        bookQuantities[index][0] += quantity;
        bookQuantities[index][1] += quantity;
    }

    public void printBookDetails() {
        for (Map.Entry<String, Integer> entry : hashMapping.entrySet()) {
            int index = entry.getValue();
            System.out.println("Name of book: " + entry.getKey());
            System.out.println("Total Quantity: " + bookQuantities[index][0]);
            System.out.println("Available Quantity: " + bookQuantities[index][1]);
            System.out.println();
        }
    }

    public boolean issueBook(Student student, String book) {
        if (student.book_no == 2) {
            System.out.println("You can't issue more than two books.");
            return false;
        }
        if (!containsNode(book)) {
            System.out.println("Book is not available.");
            return false;
        }
        int index = hashMapping.get(book);
        if (bookQuantities[index][1] <= 0) {
            System.out.println("Book is not available right now.");
            return false;
        }

        if (student.book1.isEmpty())
            student.book1 = book;
        else
            student.book2 = book;

        student.book_no++;
        bookQuantities[index][1]--;

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Book issued successfully on: " +
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return true;
    }

    public boolean returnBook(Student student, String book) {
        if (!containsNode(book)) {
            System.out.println("Book does not exist in the library.");
            return false;
        }
        if (student.book1.equals(book))
            student.book1 = "";
        else if (student.book2.equals(book))
            student.book2 = "";
        else {
            System.out.println("Book is not issued to the student.");
            return false;
        }

        int index = hashMapping.get(book);
        bookQuantities[index][1]++;
        student.book_no--;

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Book returned successfully on: " +
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return true;
    }
}
