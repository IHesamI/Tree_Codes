import java.util.Stack;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Scanner;

public class PostOrder {

    static class PrintPre {

        void Pre(int[] Inorder, int[] post_order) {
            int len = Inorder.length;
            postIndex = len - 1;
            Stack<Integer> stack = new Stack<Integer>();
            HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
            for (int i = 0; i < Inorder.length; i++)
                hm.put(Inorder[i], i);

            pre_order(Inorder, post_order, 0, len - 1, stack, hm);

            while (!stack.empty())
                System.out.print(stack.pop() + " ");
        }

        static int postIndex;

        void pre_order(int[] in, int[] post, int inStrt, int inEnd,
                Stack<Integer> s, HashMap<Integer, Integer> hm) {
            if (inStrt > inEnd)
                return;
            int val = post[postIndex];
            int inIndex = hm.get(val);
            postIndex--;

            pre_order(in, post, inIndex + 1, inEnd, s, hm);

            pre_order(in, post, inStrt, inIndex - 1, s, hm);

            s.push(val);
        }

    }

    public static void main(String ars[]) {
        Scanner scanner = new Scanner(System.in);
        String nodes_count = scanner.nextLine();
        String[] inorder_traver = scanner.nextLine().split(" ");
        String[] postorder_traver = scanner.nextLine().split(" ");

        int[] inorder = new int[inorder_traver.length];
        for (int i = 0; i < inorder.length; i++)
            inorder[i] = Integer.parseInt(inorder_traver[i]);

        int postorder[] = new int[postorder_traver.length];
        for (int i = 0; i < inorder.length; i++)
            postorder[i] = Integer.parseInt(postorder_traver[i]);

        PrintPre tree = new PrintPre();

        tree.Pre(inorder, postorder);

    }
}
