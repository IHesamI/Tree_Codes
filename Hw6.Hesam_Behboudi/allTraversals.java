import java.util.*;

public class allTraversals {
    public static void main(String[] args) {
        Map<Integer, Node> allnodes = new HashMap<>();
        ArrayList<Integer> allExceptParent = new ArrayList<>();
        Scanner inp = new Scanner(System.in);
        int numOfnodes = inp.nextInt();
                 for (int i = 0; i < numOfnodes - 1; i++) {
            int p = inp.nextInt();
            int c = inp.nextInt();
            allExceptParent.add(c);

            if (allnodes.containsKey(p) && allnodes.containsKey(c))
                addChild(allnodes.get(p), allnodes.get(c));

            else if (allnodes.containsKey(p) && !allnodes.containsKey(c)) {
                Node tobeAdded = new Node(c);
                allnodes.put(c, tobeAdded);
                addChild(allnodes.get(p), allnodes.get(c));
            } else if (!allnodes.containsKey(p) && allnodes.containsKey(c)) {
                Node tobeAdded = new Node(p);
                allnodes.put(p, tobeAdded);
                addChild(allnodes.get(p), allnodes.get(c));
            } else {
                Node newnodep = new Node(p);
                Node newnodec = new Node(c);
                allnodes.put(p, newnodep);
                allnodes.put(c, newnodec);
                addChild(allnodes.get(p), allnodes.get(c));
            }

        }
        Node parent = null;
        for (Node x : allnodes.values()) {
            if (!allExceptParent.contains(x.data)) {
                parent = x;
            }
        }

        PreOrder(parent);
        System.out.println();
        InOrder(parent);
        System.out.println();
        PostOrder(parent);
        inp.close();

    }

    static class Node {
        int data;
        Node next = null;
        Node child = null;

        public Node(int data) {
            this.data = data;
        }

    }

    // Adds a sibling
    static public void AddSibling(Node n, Node data) {
        if (n == null)
            return;
        while (n.next != null)
            n = n.next;
        n.next = data;
    }

    // Add child Node to a Node
    static public void addChild(Node n, Node data) {
        if (n == null)
            return;

        // child should be empty if not:
        if (n.child != null)
            AddSibling(n.child, data);
        else
            n.child = data;
    }

    // root/left/right
    static public void PreOrder(Node root) {
        System.out.print(root.data + " ");
        if (root.child != null) {
            Node tmp = root.child;
            while (tmp != null) {
                PreOrder(tmp);
                tmp = tmp.next;
            }
        }
    }

    // left/right/root
    static public void PostOrder(Node root) {
        if (root.child != null) {
            Node tmp = root.child;
            while (tmp != null) {
                PostOrder(tmp);
                tmp = tmp.next;
            }
        }
        System.out.print(root.data + " ");
    }

    // left/root/right
    static public void InOrder(Node root) {
        int counter = 0;
        if (root.child != null) {
            Node t = root.child;
            while (t != null) {
                t = t.next;
                counter++;
            }
        }
        int firsthalf = 0;
        Node tmpsec = null;
        if (root.child != null) {
            Node tmp = root.child;
            while (tmp != null) {
                InOrder(tmp);
                firsthalf++;
                if (firsthalf ==(int) Math.ceil(((double)counter) / 2.0)) {
                    tmpsec = tmp.next;
                    break;
                }
                tmp = tmp.next;

            }
        }
        System.out.print(root.data + " ");
        if (tmpsec != null) {
            while (tmpsec != null) {
                InOrder(tmpsec);
                if (tmpsec != null)
                    tmpsec = tmpsec.next;
            }
        }
    }

}