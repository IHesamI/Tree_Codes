import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
class node {
    int data = 0;
    node parent = null;
    //node[] childList = new node[10000];
    List<node> childList = new ArrayList<>();
    int numofChildes = 0;
}

class Tree {
    node[] nodes = new node[20000];
    node grandfather = null;

    public void insert(int a, int b) {

        if (nodes[b] == null) {
            nodes[b] = new node();
            nodes[b].data = b;
        }

        if (nodes[a] != null) {
            nodes[b].parent = nodes[a];
            //nodes[a].childList[nodes[a].numofChildes++] = nodes[b];
            nodes[a].childList.add(nodes[b]);
            nodes[a].numofChildes++;
        } else if (nodes[a] == null) {
            nodes[b].parent = nodes[a];
            nodes[a] = new node();
            nodes[a].data = a;
            //nodes[a].childList[nodes[a].numofChildes++] = nodes[b];
            nodes[a].childList.add(nodes[b]);
            nodes[a].numofChildes++;
        }
    }

    node findGrandfather() {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                if (nodes[i].parent == null) {
                    grandfather = nodes[i];
                    break;
                }
            }
        }
        return grandfather;
    }

    void printPreorder(node node) {
        System.out.print(node.data + " ");
        for (int i = 0; i < node.numofChildes; i++) {
            printPreorder(node.childList.get(i));
        }
    }

    void printInorder(node node) {
        for (int i = 0; i < (int) Math.ceil((double) (node.numofChildes) / 2.0); i++) {
            printInorder(node.childList.get(i));
        }
        System.out.print(node.data + " ");
        for (int i = (int) Math.ceil((double) (node.numofChildes) / 2.0); i < node.numofChildes; i++) {
            printInorder(node.childList.get(i));
        }
    }

    void printPostorder(node node) {
        for (int i = 0; i < node.numofChildes; i++) {
            printPostorder(node.childList.get(i));
        }
        System.out.print(node.data + " ");
    }
}

public class hana {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        node grandfather;
        Tree tree = new Tree();
        int n = scanner.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            tree.insert(x, y);
        }

        grandfather = tree.findGrandfather();

        tree.printPreorder(grandfather);
        System.out.println();
        tree.printInorder(grandfather);
        System.out.println();
        tree.printPostorder(grandfather);
        scanner.close();
    }
}