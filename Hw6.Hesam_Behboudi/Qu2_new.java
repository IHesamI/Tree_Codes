import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Qu2_new {
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

    static int index;

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
        public Node(ArrayList<String> childs) {
            this.childs = childs;
            key = "";
            left = right = null;
        }
        // اونایی که مقدار دارن رو میسازه میسازه

        public Node(String item, ArrayList<String> childs) {
            this.childs = childs;
            key = item;
            left = right = null;
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

        BinaryTree(Node root, ArrayList<String> list, Map<String, ArrayList<String>> _map_) {
            this.root = root;
            make_a_proper_tree(root, list, _map_);

        }
        // تابعی که چیدمان درخت را مشخص میکند
        // نحوه کار تابع به این صورت است که
        //

        void make_a_proper_tree(Node root, ArrayList<String> list_of_childs,
                Map<String, ArrayList<String>> map_of_childs) {

            if (list_of_childs.size() == 2) {
                Node leftNode = new Node(list_of_childs.get(0));

                root.left = leftNode;
                make_a_proper_tree(leftNode, map_of_childs.get(leftNode.key), map_of_childs);
                Node rightNode = new Node(list_of_childs.get(1));
                root.right = rightNode;
                make_a_proper_tree(rightNode, map_of_childs.get(rightNode.key), map_of_childs);
            } else if (list_of_childs.size() == 1) {
                Node leftNode = new Node(list_of_childs.get(0));
                root.left = leftNode;
                make_a_proper_tree(leftNode, map_of_childs.get(leftNode.key), map_of_childs);
            } else {
                return;
            }

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

            if (node.key != "")

                ret += node.key + " ";
            else
                ret += node.key;
            ret += printInorder(node.right);
            return ret;
        }

        String printPreorder(Node node) {
            if (node == null)
                return "";
            String ret = "";
            if (node.left == null && node.right == null) {
                if (node.key != "")
                    ret += node.key + " ";
                else
                    ret += node.key;
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

    static String find_Root(Map<Integer, Boolean> Map_of_child_value) {
        String root = "";
        root = Map_of_child_value.entrySet().stream().filter(entry -> entry.getValue() == false)
                .map(Map.Entry::getKey).findFirst().get().toString();
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NUmber_of_nodes = Integer.parseInt(scanner.nextLine());

        Map<String, ArrayList<String>> Node_and_Child_map = new LinkedHashMap<>();

        int[] array_of_Tree_Nodes_data = new int[NUmber_of_nodes];

        Map<Integer, Boolean> Map_Childs_ = new LinkedHashMap<>();

        index = 0;
        while (NUmber_of_nodes > 1) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();
            Map_Childs_.put(child, true);
            if (Node_and_Child_map.containsKey(String.valueOf(parent))) {
                ArrayList<String> array = Node_and_Child_map.get(String.valueOf(parent));
                array.add(String.valueOf(child));
                Node_and_Child_map.put(String.valueOf(parent), array);
            } else {
                ArrayList<String> array = new ArrayList<>();
                array.add(String.valueOf(child));
                Node_and_Child_map.put(String.valueOf(parent), array);
                array_of_Tree_Nodes_data[index] = parent;
                Map_Childs_.put(parent, false);

                index++;
            }
            if (!Node_and_Child_map.containsKey(String.valueOf(child))) {
                Node_and_Child_map.put(String.valueOf(child), new ArrayList<String>());
                array_of_Tree_Nodes_data[index] = child;
                index++;
            }

            NUmber_of_nodes--;
        }

        index = 0;
        HeapSort hps = new HeapSort();
        hps.sort(array_of_Tree_Nodes_data);
        String root = find_Root(Map_Childs_);
        BinaryTree tree = new BinaryTree(new Node(root), Node_and_Child_map.get(root), Node_and_Child_map);
        System.out.println(tree.printPreorder());
        tree.arrayToBST(array_of_Tree_Nodes_data, tree.root);
        System.out.println(tree.printInorder().trim());
        System.out.println(tree.printPostorder().trim());

        scanner.close();
    }
}