import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Qu2 {

    static String preorder(Map<String, ArrayList<String>> Node_and_Child_map, String root) {
        String ret = "";
        if (Node_and_Child_map.get(root).size() != 0) {
            for (int i = 0; i < Node_and_Child_map.get(root).size(); i++)
                ret += preorder(Node_and_Child_map, Node_and_Child_map.get(root).get(i));

        } else
            return root;

        return ret;
    }

    static String find_Root(Map<String, String> Map_of_child_value) {
        String root = "";
        root = Map_of_child_value.entrySet().stream().filter(entry -> entry.getValue().equals(""))
                .map(Map.Entry::getKey).findFirst().get();
        return root;
    }

    static class Node {

        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    static class BinaryTree {

        static Node root;

        /*
         * A function that constructs Balanced Binary Search Tree
         * from a sorted array
         */
        Node sortedArrayToBST(int arr[], int start, int end) {

            /* Base Case */
            if (start > end) {
                return null;
            }

            /* Get the middle element and make it root */
            int mid = (start + end) / 2;
            Node node = new Node(arr[mid]);

            /*
             * Recursively construct the left subtree and make it
             * left child of root
             */
            node.left = sortedArrayToBST(arr, start, mid - 1);

            /*
             * Recursively construct the right subtree and make it
             * right child of root
             */
            node.right = sortedArrayToBST(arr, mid + 1, end);

            return node;
        }

        String printPostorder(Node node) {
            if (node == null)
                return "";
            String ret = "";
            ret += printPostorder(node.left);
            ret += printPostorder(node.right);
            ret += " " + node.data;
            return ret;
        }

        String printInorder(Node node) {
            String ret = "";
            if (node == null)
                return "";
            ret += printInorder(node.left);

            ret += " " + node.data;

            ret += printInorder(node.right);
            return ret;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int NUmber_of_nodes = Integer.parseInt(scanner.nextLine());
        Map<String, ArrayList<String>> Node_and_Child_map = new LinkedHashMap<>();
        Map<String, String> MAP_OF_CHILDS_AND_PARENT = new LinkedHashMap<>();
        Set<String> hashset = new HashSet<>();
        while (NUmber_of_nodes > 1) {
            String line = scanner.nextLine();
            String[] splittedline = line.split(" ");
            NUmber_of_nodes--;

        }
        System.out.println("trim()");
        System.out.println("tree.printInorder(root).trim()");
        System.out.println("tree.printPostorder(root).trim()");
        scanner.close();
    }
}
