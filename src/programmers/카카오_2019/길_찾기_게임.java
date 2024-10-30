package programmers.카카오_2019;

// 이진 트리. y축이 같다면 (행이 같다면) 같은 레벨

import java.util.*;

public class 길_찾기_게임 {
    public int[][] solution(int[][] nodeinfo) {

        Comparator<int[]> cmp = (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return b[1] - a[1];
        };

        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp);

        for (int i = 0; i < nodeinfo.length; i++) {
            pq.add(new int[] {nodeinfo[i][0], nodeinfo[i][1], i + 1});
        }

        Tree tree = new Tree();
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            tree.insert(now[0], now[2]);
        }

        int[][] answer = new int[2][nodeinfo.length];

        tree.idx = 0;
        tree.preOrder(tree.root, answer[0]);
        tree.idx = 0;
        tree.postOrder(tree.root, answer[1]);

        return answer;
    }
}

class Node {
    Node leftNode;
    Node rightNode;
    int x, val;

    public Node(int x, int val) {

        this.x = x;
        this.val = val;
        this.leftNode = null;
        this.rightNode = null;
    }
}

class Tree {
    Node root;
    int idx = 0;

    public void insert(int x, int val) {

        if (root == null) {
            root = new Node(x, val);
        } else {
            insertNode(root, x, val);
        }
    }

    private void insertNode(Node node, int x, int val) {

        if (node.x > x) {
            if (node.leftNode == null)
                node.leftNode = new Node(x, val);
            else
                insertNode(node.leftNode, x, val);
        }

        if (node.x < x) {
            if (node.rightNode == null)
                node.rightNode = new Node(x, val);
            else
                insertNode(node.rightNode, x, val);
        }
    }

    public void preOrder(Node root, int[] arr) {

        if (root != null) {
            arr[idx++] = root.val;
            preOrder(root.leftNode, arr);
            preOrder(root.rightNode, arr);
        }
    }

    public void postOrder(Node root, int[] arr) {

        if (root != null) {
            postOrder(root.leftNode, arr);
            postOrder(root.rightNode, arr);
            arr[idx++] = root.val;
        }
    }
}