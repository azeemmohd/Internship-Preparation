257. Binary Tree Paths 
follow up: 1. 如果所有的node在 条线上，时间复杂度。2 full binary tree 时间复杂度。3 优化时间复杂度

问我时间复杂度,我说O(n) 他说 对， 要考虑print path 的时间复杂度，ok depth * 2^depth => O(nlog(n)) 

public List<String> binaryTreePaths(TreeNode root) {
	List<String> res = new ArrayList<>();
	dfs(res, root, "");
	return res;
}
private void dfs(List<String> res, TreeNode root, String tmp) {
	if (root == null)	return;
	if (root.left == null && root.right == null)
		res.add(tmp + root.val);
	dfs(res, root.left, tmp + root.val + "->");
	dfs(res, root.right, tmp + root.val + "->");
}


