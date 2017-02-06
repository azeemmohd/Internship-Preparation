173. Binary Search Tree Iterator

写个BST 的 in-order iterator. 要写的function有 next() 和 all(), all() return所有剩下的。

public class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        pushAll(node.right);
        return node.val;
    }   
    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}


follow up是 改成 preorder 和 postorder。 我全 的stack 璁
