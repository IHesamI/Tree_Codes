import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Q2_score_85_ {
        static int index;
    
        static class Node {
    
            ArrayList<String> childs;
            String key;
    
            Node left, right;
            public Node(String item) {
                key = item;
                left = right = null;
            }
    
            public Node(ArrayList<String> childs) {
                this.childs = childs;
                key = "";
                left = right = null;
            }
    
            public Node(String item, ArrayList<String> childs) {
                this.childs = childs;
                key = item;
                left = right = null;
            }
    
        }
    
        static class BinaryTree {
            void arrayToBST(int[] arr, Node root) {
                if (root == null)
                    return;
    
                arrayToBST(arr, root.left);
    
                root.key = String.valueOf(arr[index]);
                index++;
    

                arrayToBST(arr, root.right);
            }
    
            Node root;
            BinaryTree(Node root, ArrayList<String> list, Map<String, ArrayList<String>> _map_) {
                this.root = root;
                make_a_proper_tree(root, list, _map_);
    
            }

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
            Arrays.sort(array_of_Tree_Nodes_data);
            String root = find_Root(Map_Childs_);
            BinaryTree tree = new BinaryTree(new Node(root), Node_and_Child_map.get(root), Node_and_Child_map);
            System.out.println(tree.printPreorder());
            tree.arrayToBST(array_of_Tree_Nodes_data, tree.root);
            System.out.println(tree.printInorder().trim());
            System.out.println(tree.printPostorder().trim());
    
            scanner.close();
        }
    }
