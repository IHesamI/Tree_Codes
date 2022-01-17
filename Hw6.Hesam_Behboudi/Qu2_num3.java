import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Qu2_num3 {
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

        public void add_left(Node left) {
            this.left = left;
        }

        public void add_right(Node righ) {
            this.right = righ;
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
            if (list_of_childs.size() > 2) {
                Node lefttemplate = new Node(new ArrayList<>(
                        list_of_childs.subList(0, (int) ((double) Math.ceil(list_of_childs.size() / 2.0)))));
                Node righttemplate = new Node(new ArrayList<>(list_of_childs
                        .subList(((int) ((double) Math.ceil(list_of_childs.size() / 2.0))), list_of_childs.size())));
                root.left = lefttemplate;
                root.right = righttemplate;
                make_a_proper_tree(lefttemplate, lefttemplate.childs, map_of_childs);
                make_a_proper_tree(righttemplate, righttemplate.childs, map_of_childs);
            } else {
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

    static String find_Root(Map<String, String> Map_of_child_value) {
        String root = "";
        root = Map_of_child_value.entrySet().stream().filter(entry -> entry.getValue().equals(""))
                .map(Map.Entry::getKey).findFirst().get();
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
            if (!Map_Childs_.containsKey(parent))
                Map_Childs_.put(parent, false);
            if (Node_and_Child_map.containsKey(String.valueOf(parent))) {
                ArrayList<String> array = Node_and_Child_map.get(String.valueOf(parent));
                array.add(String.valueOf(child));
                Node_and_Child_map.put(String.valueOf(parent), array);
            } else {
                ArrayList<String> array = new ArrayList<>();
                array.add(String.valueOf(child));
                Node_and_Child_map.put(String.valueOf(parent), array);
                array_of_Tree_Nodes_data[index] = parent;
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
        Arrays.sort(array_of_Tree_Nodes_data);
        String root = "";
        for (Map.Entry<Integer, Boolean> entry : Map_Childs_.entrySet()) {
            if (!entry.getValue()) {
                root = String.valueOf(entry.getKey());
                break;
            }
        }
        BinaryTree tree = new BinaryTree(new Node(root), Node_and_Child_map.get(root), Node_and_Child_map);
        System.out.println(tree.printPreorder());
        tree.arrayToBST(array_of_Tree_Nodes_data, tree.root);
        System.out.println(tree.printInorder().trim());
        System.out.println(tree.printPostorder().trim());

        scanner.close();
    }
}