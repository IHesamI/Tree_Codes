
import java.util.Scanner;

public class Qu4 {
    static class Node {
        int data;
        int size_left;
        int size_right;

        Node left, right;

        public Node(int data) {
            this.data = data;
            size_left = 0;
            size_right = 0;
        }

        public void add_left() {
            size_left++;
        }

        public void add_right() {
            size_right++;
        }

    }

    static class Optimize_BTS_Horizonal {
        Node root;

        Optimize_BTS_Horizonal(Node root) {
            this.root = root;
        }

        public void add_Node(Node thenode) {

            Node Nownode = root;
            Node prev = null;
            while (Nownode != null) {

                if (thenode.data > Nownode.data) {
                    prev = Nownode;
                    Nownode.size_right++;
                    Nownode = Nownode.right;

                } else {
                    prev = Nownode;
                    Nownode = Nownode.left;

                }
            }
            if (prev.data > thenode.data)
                prev.left = thenode;
            else
                prev.right = thenode;
        }

        public int find_the_node(int digits) {
           return Inorder(digits, root);
            
        }

        static int sum = 0;

        int Inorder(int digits, Node node) {
            if (node == null)
                return 0;

            /* first recur on left child */
            
            if (node.data > digits)
                return      Inorder(digits, node.left) + 1 +node.size_right;
          
            else
           return Inorder(digits, node.right);
        }

        void printInorder(Node node) {
            if (node == null)
                return;

            /* first recur on left child */
            printInorder(node.left);

            /* then print the data of node */
            System.out.print(node.data + " ");

            /* now recur on right child */
            printInorder(node.right);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NUmber_of_nodes = scanner.nextInt();

        int number_of_digits = scanner.nextInt();
        int nodes_num = NUmber_of_nodes;

        while (NUmber_of_nodes > 1) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();
            NUmber_of_nodes--;
        }
        int data = scanner.nextInt();
        Optimize_BTS_Horizonal tree = new Optimize_BTS_Horizonal(new Node(data));

        while (nodes_num >= 2) {
            data = scanner.nextInt();
            tree.add_Node(new Node(data));
            nodes_num--;
        }
        for (int i = 1; i <= number_of_digits; i++) {
            int index = scanner.nextInt();
            System.out.println(tree.find_the_node(index));
            Optimize_BTS_Horizonal.sum = 0;

        }

        scanner.close();
    }

}
