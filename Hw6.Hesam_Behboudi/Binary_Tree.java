
/**    IMAGINE YOU HAVE A TREE LIKE THIS :
 *                                  1
 *                                 /|\  
 *                                2 3 4
 *          AND YOU WANT TO MAKE A BALACNED BINARY TREE LIKE THIS:
 * 
 *                                   1
 *                                 /   \  
 *                                0     0 
 *                               / \   /
 *                              2   3 4
 */

import java.util.ArrayList;
import java.util.Map;
// THE PUBLIC CLASS FOR MAKING A BINARY TREE FROM A NON BINARY TREE

public class Binary_Tree {
    /**
     * CLASS NODE
     * EACH NODE FROM THE TREE
     * TO MAKE A BALANCED TREE WE NEED SOME TEMPORARY NODES WITCH HAVE NO KEY
     */
    static class Node {
        /**
         * THE ARRAYLIST OF CHILDS FOR EACH NODE
         */
        ArrayList<Integer> childs;
        /**
         * KEY OF NODE
         */
        Integer key;
        /**
         * RIGHT AND LEFT CHILDS
         */
        Node left, right;

        /**
         * CONSTRUCTOR FOR LEAVES
         */
        public Node(Integer item) {
            key = item;
            left = right = null;
        }

        /**
         * CONSTRUCTOR FOR TEMPORARY NODES
         * THESE NODES HAVE NO KEY
         * AND SPLIT THE CHILDS INTO LEFT AND RIGHT
         */

        public Node(ArrayList<Integer> childs) {
            this.childs = childs;
            key = null;
            left = right = null;
        }

        /**
         * CONSTRUCTOR FOR PARENT NODES
         */

        public Node(Integer item, ArrayList<Integer> childs) {
            this.childs = childs;
            key = item;
            left = right = null;
        }

    }

    /**
     * BinaryTree CLASS
     */
    static class BinaryTree {
        /**
         * ROOT OF BinaryTree
         */
        Node root;

        /**
         * THE CONSTRUCTOR OF THE BinaryTree
         * 
         * @params
         *         ROOT NODE
         *         LIST OF THE ROOT NODE CHILDS
         *         A MAP THAT HAS NODES AS KEYS AND TEH NODE CHILDS AS VALUES
         */

        BinaryTree(Node root, ArrayList<Integer> Root_Childs,
                Map<Integer, ArrayList<Integer>> Map_for_each_nodes_childs) {
            this.root = root;
            make_a_proper_tree(root, Root_Childs, Map_for_each_nodes_childs);

        }

        /**
         * THIS METHOD MAKE THE TREE
         * IT RECURSIVELY MAKE THE PROPER SHAPE AND BALANCED THE TREE
         * 
         * @params
         *         PARENT OR ROOT NODE
         *         PARENT LIST OF CHILDS
         *         A MAP THAT HAS NODES AS KEYS AND TEH NODE CHILDS AS VALUES
         *         FOR EACH NODE
         *         IF THE CHILDS ARE MORE THAN 2:
         *         WE NEED TEMPORARY NODES TO SPLIT THE CHILDS INTO RIGHT OR LEFT
         * 
         *         IF THRE ARE 2 : CHILDS WE CONNECT THEM TO THE PARENT
         * 
         *         IF THE NODE HAS NO CHILD : IT IS A LEAF NODE AND NOTHING HAPPEN
         */
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

    }

}
