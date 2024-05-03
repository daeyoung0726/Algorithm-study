package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Node {
    int key;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
    }
}
public class _5639 {
    public static Node createTree(Node root, List<Integer> list) {
        if (root == null)
            root = new Node(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            insertNode(root, list.get(i));
        }
        return root;
    }

    private static void insertNode(Node node, int key) {
        if (key < node.key) {
            if (node.left == null) {
                node.left = new Node(key);
            } else {
                insertNode(node.left, key);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(key);
            } else {
                insertNode(node.right, key);
            }
        }
    }

    public static void postOrder(Node node) {
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Node root = null;
            List<Integer> list = new ArrayList<>();
            String input;
            while((input = br.readLine()) != null && !input.isEmpty()) {
                list.add(Integer.parseInt(input));
            }
            root = createTree(root, list);
            postOrder(root);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
