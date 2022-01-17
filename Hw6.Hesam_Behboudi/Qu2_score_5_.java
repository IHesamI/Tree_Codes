import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Qu2_score_5_ {
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * This function takes last element as pivot, places
     * the pivot element at its correct position in sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right
     * of pivot
     */
    static int partition(int[] arr, int low, int high) {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /*
     * The main function that implements QuickSort
     * arr[] --> Array to be sorted,
     * low --> Starting index,
     * high --> Ending index
     */
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int index;

    static class Node {

        ArrayList<String> childs;
        // دیتای هر نود
        int key;

        Node left, right;

        // کانستراکتر های کلاس
        public Node(int item) {
            key = item;
            left = right = null;
        }

        // تمپلیت ها رو میسازه
        // نوعی از نود که دیتاای ندارند و فقط فرزندان رو به ترتیب در درخت تقسیم میکند

        // اونایی که مقدار دارن رو میسازه میسازه

    }

    static class BinaryTree {
        void arrayToBST(int[] arr, Node root) {
            // Base Case
            if (root == null)
                return;

            /* first update the left subtree */
            arrayToBST(arr, root.left);

            /* Now update root's data and increment index */
            root.key = arr[index];
            index++;

            /* finally update the right subtree */
            arrayToBST(arr, root.right);
        }

        // کلاس درخت که ریشه را دارد
        Node root;

        // کانستراکتری که شامل ریشه
        // لیست فرزندان ریشه
        // و مپی از تمام فرزندان است

        BinaryTree(Node root, ArrayList<Integer> list, Map<Integer, ArrayList<Integer>> _map_) {
            this.root = root;
            make_a_proper_tree(root, list, _map_);

        }
        // تابعی که چیدمان درخت را مشخص میکند
        // نحوه کار تابع به این صورت است که
        //

        void make_a_proper_tree(Node root, ArrayList<Integer> list_of_childs,
                Map<Integer, ArrayList<Integer>> map_of_childs) {
            if (list_of_childs.size() >= 2) {

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
            ret += node.key + " ";

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

    static String find_Root(Map<Integer, Boolean> Map_of_child_value) {
        String root = "";
        root = Map_of_child_value.entrySet().stream().filter(entry -> entry.getValue() == false)
                .map(Map.Entry::getKey).findFirst().get().toString();
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NUMber_of_nodes = Integer.parseInt(scanner.nextLine());

        Map<Integer, ArrayList<Integer>> list_of_childs_and_parent = new LinkedHashMap<>();
        index = 0;
        int[] array_of_Tree_Nodes_data = new int[NUMber_of_nodes];

        int the_root;
        int parent = scanner.nextInt();
        int child = scanner.nextInt();

        the_root = parent;

        list_of_childs_and_parent.put(child, new ArrayList<>());

        ArrayList<Integer> arra = new ArrayList<>();
        arra.add(child);
        list_of_childs_and_parent.put(parent, arra);

        array_of_Tree_Nodes_data[index] = parent;
        index++;
        array_of_Tree_Nodes_data[index] = child;
        index++;

        while (NUMber_of_nodes > 2) {
            parent = scanner.nextInt();

            child = scanner.nextInt();

            if (child == the_root)
                the_root = parent;


            if (list_of_childs_and_parent.containsKey(parent)) {
                ArrayList<Integer> parentlist = list_of_childs_and_parent.get(parent);
                parentlist.add(child);
                list_of_childs_and_parent.put(parent, parentlist);
            } else {
                ArrayList<Integer> parentlist = new ArrayList<>();
                parentlist.add(child);
                list_of_childs_and_parent.put(parent, parentlist);
                array_of_Tree_Nodes_data[index] = parent;
                index++;

            }
            
            if (!list_of_childs_and_parent.containsKey(child)) {
                list_of_childs_and_parent.put(child, new ArrayList<>());
                array_of_Tree_Nodes_data[index] = child;
                index++;
            }
            NUMber_of_nodes--;
        }

        index = 0;
        // System.out.println(the_root +" "+list_of_childs_and_parent.get(the_root));
        quickSort(array_of_Tree_Nodes_data, 0, array_of_Tree_Nodes_data.length - 1);

        BinaryTree tree = new BinaryTree(new Node(the_root), list_of_childs_and_parent.get(the_root),
                list_of_childs_and_parent);
        System.out.println(tree.printPreorder());
        // System.out.println(tree.root.key);
        tree.arrayToBST(array_of_Tree_Nodes_data, tree.root);
        System.out.println(tree.printInorder().trim());
        System.out.println(tree.printPostorder().trim());

        scanner.close();
    }
}