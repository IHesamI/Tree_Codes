import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Qu2_Final {
    static int index;

    // Java program for implementation of Heap Sort
    static class HeapSort {
        public void sort(int arr[]) {
            int n = arr.length;

            // Build heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // One by one extract an element from heap
            for (int i = n - 1; i > 0; i--) {
                // Move current root to end
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // call max heapify on the reduced heap
                heapify(arr, i, 0);
            }
        }

        // To heapify a subtree rooted with node i which is
        // an index in arr[]. n is size of heap
        void heapify(int arr[], int n, int i) {
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r] > arr[largest])
                largest = r;

            // If largest is not root
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest);
            }
        }

    }

    static class Node {

        ArrayList<String> childs;
        // دیتای هر نود
        String key;

        Node left, right;

        // کانستراکتر های کلاس
        public Node(String item) {
            key = item;
            left = right = null;
        }

        // تمپلیت ها رو میسازه
        // نوعی از نود که دیتاای ندارند و فقط فرزندان رو به ترتیب در درخت تقسیم میکند

        public void add_left(Node left) {
            this.left = left;
        }

        public void add_right(Node right) {
            this.right = right;
        }

    }

    static class BinaryTree {
        void arrayToBST(int[] arr, Node root) {
            // Base Case
            if (root == null)
                return;

            /* first update the left subtree */
            arrayToBST(arr, root.left);

            /* Now update root's data and increment index */
            root.key = String.valueOf(arr[index]);
            index++;

            /* finally update the right subtree */
            arrayToBST(arr, root.right);
        }

        // کلاس درخت که ریشه را دارد
        Node root;

        // کانستراکتری که شامل ریشه
        // لیست فرزندان ریشه
        // و مپی از تمام فرزندان است
        BinaryTree(Node root) {
            this.root = root;
        }

        BinaryTree(Node root, ArrayList<String> list, Map<String, ArrayList<String>> _map_) {
            this.root = root;
        }

        String printPostorder(Node node) {
            if (node == null)
                return "";
            String ret = "";
            ret += printPostorder(node.left);
            ret += printPostorder(node.right);
            if (node.key != "")
                ret += node.key + " ";
            else
                ret += node.key;
            return ret;
        }

        String printInorder(Node node) {
            String ret = "";
            if (node == null)
                return "";
            ret += printInorder(node.left);

            ret += node.key + " ";

            ret += printInorder(node.right);
            return ret;
        }

        String printPreorder(Node node) {
            if (node == null)
                return "";
            String ret = "";
            if (node.left == null && node.right == null) {
                ret += node.key + " ";
            }
            ret += printPreorder(node.left);
            ret += printPreorder(node.right);
            return ret;
        }

        String printPostorder() {
            return printPostorder(root);
        }

        String printInorder() {
            return printInorder(root);
        }

        String printPreorder() {
            return printPreorder(root);
        }

    }

    static String find_Root(Map<String, String> Map_of_child_value) {
        String root = "";
        root = Map_of_child_value.entrySet().stream().filter(entry -> entry.getValue().equals(""))
                .map(Map.Entry::getKey).findFirst().get();
        return root;
    }
    static void printinorder(int [] Array){
        for (int i : Array) {
            System.out.print(i + " ");
        }
        
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NUmber_of_nodes = Integer.parseInt(scanner.nextLine());

        int[] array_of_Tree_Nodes_data = new int[NUmber_of_nodes];

        Map<Integer, Boolean> Map_Childs_ = new LinkedHashMap<>();

        Map<Integer, Node> nodes_with_childs = new LinkedHashMap<>();

        index = 0;
        while (NUmber_of_nodes > 1) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();

            Map_Childs_.put(child, true);

            if (!nodes_with_childs.containsKey(child)) {
                nodes_with_childs.put(child, new Node(String.valueOf(child)));
                array_of_Tree_Nodes_data[index] = child;
                index++;
            }

            if (!nodes_with_childs.containsKey(parent)) {
                Node parent_node = new Node(String.valueOf(parent));
                parent_node.left = nodes_with_childs.get(child);
                nodes_with_childs.put(parent, parent_node);
                array_of_Tree_Nodes_data[index] = parent;
                index++;
                Map_Childs_.put(parent, false);
            }

            else if (nodes_with_childs.containsKey(parent)) {
                Node node_parent = nodes_with_childs.get(parent);
                if (node_parent.left != null)
                    node_parent.right = nodes_with_childs.get(child);
                else
                    node_parent.left = nodes_with_childs.get(child);

                nodes_with_childs.put(parent, node_parent);

            }

            NUmber_of_nodes--;
        }
        int the_root = 0;
        for (Map.Entry<Integer, Boolean> entry : Map_Childs_.entrySet()) {
            if (!entry.getValue()) {
                the_root = entry.getKey();
                break;
            }
        }

        index = 0;
        new HeapSort().sort(array_of_Tree_Nodes_data);

        BinaryTree tree = new BinaryTree(nodes_with_childs.get(the_root));
        System.out.println(tree.printPreorder());
        tree.arrayToBST(array_of_Tree_Nodes_data, tree.root);
        printinorder(array_of_Tree_Nodes_data);
        System.out.println(tree.printPostorder().trim());

        scanner.close();
    }
}