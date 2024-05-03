package baekjoon;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Node_1991 {
    char data;
    Node_1991 left;
    Node_1991 right;

    public Node_1991(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class _1991 {
    private static StringBuilder sb = new StringBuilder();
    public static Node_1991 add(Node_1991 root, char head, char left_data, char right_data) {
        if(root == null) {
            root = new Node_1991(head);
        }
        if(root.data == head) {
            if(left_data != '.')
                root.left = new Node_1991(left_data);
            if(right_data != '.')
                root.right = new Node_1991(right_data);
        }
        else {
            if(root.left != null) add(root.left, head, left_data, right_data);
            if(root.right != null) add(root.right, head, left_data, right_data);
        }
        return root;
    }

    public static void preorder(Node_1991 root) {
        if(root != null) {
            sb.append(root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void inorder(Node_1991 root) {
        if(root != null) {
            inorder(root.left);
            sb.append(root.data);
            inorder(root.right);
        }
    }

    public static void postorder(Node_1991 root) {
        if(root != null) {
            postorder(root.left);
            postorder(root.right);
            sb.append(root.data);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node_1991 root = null;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char head = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            root = add(root, head, left, right);
        }

        preorder(root);
        System.out.println(sb);
        sb.setLength(0);

        inorder(root);
        System.out.println(sb);
        sb.setLength(0);

        postorder(root);
        System.out.println(sb);

    }
}
