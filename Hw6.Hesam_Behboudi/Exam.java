
// // import java.util.ArrayList;
// // import java.util.Arrays;

// // public class Exam {
// //     public static void main(String[] args) {
// //         ArrayList<Integer> mylist = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
// //         System.out.println(Math.ceil(9.0 / 2.0));
// //             System.out.println(mylist.subList(0, (int) (Math.ceil((double) mylist.size() / 2.0))));
// //             System.out.println(
// //                     mylist.subList( (int) (Math.ceil((double) mylist.size() / 2.0)), mylist.size()));
        
// //     }

// // }
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Scanner;

// public class Qu1_fini {
//     static class Node {
//         ArrayList<String> childs;
//         String key;
//         Node left, right;

//         public Node(String item) {
//             key = item;
//             left = right = null;
//         }

//         // تمپلیت ها رو میسازه
//         public Node(List<String> childs) {
//             this.childs = (ArrayList) childs;
//             System.out.println(this.childs);
//             key = "";
//             left = right = null;
//         }
//         // اونایی که مقدار دارن رو میسازه میسازه

//         public Node(String item, ArrayList<String> childs) {
//             this.childs = childs;
//             key = item;
//             left = right = null;
//         }

//     }

//     static class BinaryTree {

//         Node root;

//         BinaryTree(Node root, ArrayList<String> list, Map<String, ArrayList<String>> _map_) {
//             this.root = root;
//             make_a_proper_tree(root, list, _map_);

//         }

//         void printPostorder(Node node) {
//             if (node == null)
//                 return;
//             printPostorder(node.left);
//             printPostorder(node.right);
//             System.out.print(node.key + " ");
//         }

//         void printInorder(Node node) {
//             if (node == null)
//                 return;

//             /* first recur on left child */
//             printInorder(node.left);

//             /* then print the data of node */
//             System.out.print(node.key + " ");

//             /* now recur on right child */
//             printInorder(node.right);
//         }

//         /* Given a binary tree, print its nodes in preorder */
//         void printPreorder(Node node) {
//             if (node == null)
//                 return;

//             /* first print data of node */
//             System.out.print(node.key + " ");

//             /* then recur on left subtree */
//             printPreorder(node.left);

//             /* now recur on right subtree */
//             printPreorder(node.right);
//         }

//         // Wrappers over above recursive functions
//         void printPostorder() {
//             printPostorder(root);
//         }

//         void printInorder() {
//             printInorder(root);
//         }

//         void printPreorder() {
//             printPreorder(root);
//         }

//         public void make_a_proper_tree(Node root_node, ArrayList<String> list_of_childs,
//                 Map<String, ArrayList<String>> map_of_childs) {
        
//             if (list_of_childs.size() <= 2) {
//                 System.out.println("IF <=2");
//                 System.out.println(root_node.key);
//                 System.out.println(list_of_childs);
//                 if (list_of_childs.size() == 1 && !root_node.key.equals("")) {
//                     Node template = new Node(new ArrayList<>(Arrays.asList(list_of_childs.get(0))));

//                     make_a_proper_tree(template, map_of_childs.get(template.childs.get(0)), map_of_childs);

//                     root_node.left = template;
//                 } else if (list_of_childs.size() == 1 && root_node.key.equals("")) {
//                     Node Node_with_value = new Node(new ArrayList<>(Arrays.asList(list_of_childs.get(0))));
//                     root_node.left = Node_with_value;
//                     if (map_of_childs.get(Node_with_value.key) == null)
//                         return;
//                     make_a_proper_tree(Node_with_value, map_of_childs.get(Node_with_value.key), map_of_childs);

//                 } else {
//                     if (root_node.key.equals("")!=true) {
//                         System.out.println("IF >2 and \"\" ");
//                         System.out.println(root_node.key);
//                         System.out.println(Arrays.asList(list_of_childs.get(0)));
//                         Node lefttemplate = new Node(new ArrayList<>(Arrays.asList(list_of_childs.get(0))));

//                         make_a_proper_tree(lefttemplate, map_of_childs.get(lefttemplate.childs.get(0)), map_of_childs);

//                         Node right_template = new Node(new ArrayList<>(Arrays.asList(list_of_childs.get(1))));
//                         System.out.println(Arrays.asList(list_of_childs.get(1)));
//                         make_a_proper_tree(right_template, map_of_childs.get(right_template.childs.get(1)),
//                                 map_of_childs);

//                         root_node.left = lefttemplate;
//                         root_node.right = right_template;
//                     } else {
//                         System.out.println(root_node.key);
//                         System.out.println(list_of_childs);
//                         Node Node_with_value = new Node(list_of_childs.get(0));
//                         System.out.println(Node_with_value);
//                         root_node.left = Node_with_value;
//                         Node righNode_with_value = new Node(list_of_childs.get(1));
//                         System.out.println(righNode_with_value);
//                         root_node.right = righNode_with_value;

//                         if (map_of_childs.get(Node_with_value.key) != null
//                                 || map_of_childs.get(righNode_with_value.key) != null) {
//                             if (map_of_childs.get(Node_with_value.key) != null)
//                                 make_a_proper_tree(Node_with_value, map_of_childs.get(Node_with_value.key),
//                                         map_of_childs);
//                             if (map_of_childs.get(righNode_with_value.key) != null)
//                                 make_a_proper_tree(righNode_with_value, map_of_childs.get(righNode_with_value.key),
//                                         map_of_childs);

//                         }
//                     }

//                 }

//             } else {
//                 if (list_of_childs.size() % 2 == 0) {
//                     Node lefttemplate = new Node(
//                             list_of_childs.subList(0, (int) (Math.ceil((double) list_of_childs.size() / 2.0))));
//                     Node righttemplate = new Node(list_of_childs.subList(
//                             (list_of_childs.size() - (int) (Math.ceil((double) list_of_childs.size() / 2.0))),
//                             list_of_childs.size()));
//                     make_a_proper_tree(lefttemplate, lefttemplate.childs, map_of_childs);
//                     make_a_proper_tree(righttemplate, righttemplate.childs, map_of_childs);

//                 } else {
//                     {
//                         Node lefttemplate = new Node(
//                                 list_of_childs.subList(0, (int) (Math.ceil((double)list_of_childs.size() / 2.0)) + 1));
//                         Node righttemplate = new Node(list_of_childs.subList(
//                                 (list_of_childs.size() - (int) (Math.ceil((double)list_of_childs.size() / 2.0))),
//                                 list_of_childs.size()));
//                         make_a_proper_tree(lefttemplate, lefttemplate.childs, map_of_childs);
//                         make_a_proper_tree(righttemplate, righttemplate.childs, map_of_childs);

//                     }

//                 }

//             }

//         }

//     }

//     public static void main(String[] args) {

//         Scanner scanner = new Scanner(System.in);
//         int NUmber_of_nodes = Integer.parseInt(scanner.nextLine());
//         Map<String, ArrayList<String>> Node_and_Child_map = new LinkedHashMap<>();

//         while (NUmber_of_nodes > 1) {
//             String line = scanner.nextLine();
//             String[] splittedline = line.split(" ");
//             if (Node_and_Child_map.containsKey(splittedline[0])) {
//                 ArrayList<String> array = Node_and_Child_map.get(splittedline[0]);
//                 array.add(splittedline[1]);
//                 Node_and_Child_map.put(splittedline[0], array);
//             } else {
//                 ArrayList<String> array = new ArrayList<>();
//                 array.add(splittedline[1]);
//                 Node_and_Child_map.put(splittedline[0], array);
//             }
//             Node_and_Child_map.put(splittedline[1], new ArrayList<String>());
//             NUmber_of_nodes--;
//         }
//         String root = Node_and_Child_map.entrySet().iterator().next().getKey();
//         BinaryTree tree = new BinaryTree(new Node(root), Node_and_Child_map.get(root), Node_and_Child_map);
//         tree.printPreorder();
//         tree.printInorder();
//         tree.printPostorder();

//     }

// }
