314. Binary Tree Vertical Order Traversal

class TreeNodeWithCol {
    TreeNode treeNode;
    int col;
    public TreeNodeWithCol(TreeNode node, int col) {
        this.treeNode = node;
        this.col = col;
    }
}
public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)   return res;
    Map<Integer, List<Integer>> map = new HashMap<>();
    Queue<TreeNodeWithCol> bfs = new ArrayDeque<>();
    bfs.add(new TreeNodeWithCol(root, 0));
    int max = 0, min = 0;
    while (!bfs.isEmpty()) {
        TreeNodeWithCol node = bfs.poll();
        int col = node.col;
        if (!map.containsKey(col))  map.put(col, new ArrayList<>());
        map.get(col).add(node.treeNode.val);
        if (node.treeNode.left != null) {
            bfs.offer(new TreeNodeWithCol(node.treeNode.left, col - 1));
            min = Math.min(min, col - 1);
        }
        if (node.treeNode.right != null) {
            bfs.offer(new TreeNodeWithCol(node.treeNode.right, col + 1));
            max = Math.max(max, col + 1);
        }
    }
    for (int i = min; i <= max; i++) 
        res.add(map.get(i));
    return res;
}