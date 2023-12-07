import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static class Node {
        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }

        public Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        public void insert(int num) {
            if (this.num > num) {   // 좌
                if (left == null) {
                    left = new Node(num);
                } else {
                    left.insert(num);
                }
            } else {    // 우
                if (right == null) {
                    right = new Node(num);
                } else {
                    right.insert(num);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));
        String input = null;

        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        // post
        postOrder(root);
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }
}