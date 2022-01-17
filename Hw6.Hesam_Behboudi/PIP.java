import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PIP {

    static class Node {

        ArrayList<Integer> childs;
        Integer key;

        Node left, right;

        public Node(Integer item) {
            key = item;
            left = right = null;
        }

        public Node(ArrayList<Integer> childs) {
            this.childs = childs;
            key = null;
            left = right = null;
        }

        public Node(Integer item, ArrayList<Integer> childs) {
            this.childs = childs;
            key = item;
            left = right = null;
        }

    }

    static class BinaryTree {
        Node root;

        BinaryTree(Node root, ArrayList<Integer> list, Map<Integer, ArrayList<Integer>> _map_) {
            this.root = root;
            make_a_proper_tree(root, list, _map_);

        }

        void make_a_proper_tree(Node root, ArrayList<Integer> list_of_childs,
                Map<Integer, ArrayList<Integer>> map_of_childs) {
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
                    if (map_of_childs.get(leftNode.key).size() != 0)
                        make_a_proper_tree(leftNode, map_of_childs.get(leftNode.key), map_of_childs);
                    Node rightNode = new Node(list_of_childs.get(1));
                    root.right = rightNode;
                    if (map_of_childs.get(rightNode.key).size() != 0)
                        make_a_proper_tree(rightNode, map_of_childs.get(rightNode.key), map_of_childs);
                } else if (list_of_childs.size() == 1) {
                    Node leftNode = new Node(list_of_childs.get(0));
                    root.left = leftNode;
                    if (map_of_childs.get(leftNode.key).size() != 0)
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
            if (node.key != null)
                ret += node.key + " ";
            else
                ret += "";
            return ret;
        }

        String printInorder(Node node) {
            String ret = "";
            if (node == null)
                return "";
            ret += printInorder(node.left);

            if (node.key != null)
                ret += node.key + " ";
            else
                ret += "";

            ret += printInorder(node.right);
            return ret;
        }

        String printPreorder(Node node) {
            if (node == null)
                return "";
            String ret = "";
            if (node.key != null)
                ret += node.key + " ";
            else
                ret += "";
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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int NUmber_of_nodes = Integer.parseInt(scanner.nextLine());
        Map<Integer, ArrayList<Integer>> Node_and_Child_map = new LinkedHashMap<>();
        Map<Integer, Boolean> MAP_OF_CHILDS_AND_PARENT = new LinkedHashMap<>();

        while (NUmber_of_nodes > 1) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();
            if (Node_and_Child_map.containsKey(parent)) {
                ArrayList<Integer> array = Node_and_Child_map.get(parent);
                array.add(child);
                Node_and_Child_map.put(parent, array);
            } else {
                ArrayList<Integer> array = new ArrayList<>();
                array.add(child);
                Node_and_Child_map.put(parent, array);
            }
            if (!Node_and_Child_map.containsKey(child))
                Node_and_Child_map.put(child, new ArrayList<Integer>());
            if (!MAP_OF_CHILDS_AND_PARENT.containsKey(parent))
                MAP_OF_CHILDS_AND_PARENT.put(parent, true);
            MAP_OF_CHILDS_AND_PARENT.put(child, false);

            NUmber_of_nodes--;
        }
        int root = root_finer(MAP_OF_CHILDS_AND_PARENT);

        BinaryTree tree = new BinaryTree(new Node(root), Node_and_Child_map.get(root), Node_and_Child_map);

        System.out.println(tree.printPreorder().trim());

        System.out.println(tree.printInorder().trim());

        System.out.println(tree.printPostorder().trim());
        scanner.close();

    }

    static Integer root_finer(Map<Integer, Boolean> map_of_nodes_that_are_child) {
        for (Map.Entry<Integer, Boolean> entry : map_of_nodes_that_are_child.entrySet()) {
            if (entry.getValue())
                return entry.getKey();
        }
        return 0;
    }

}
