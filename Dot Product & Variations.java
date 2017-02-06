Dot Product & Variations

1.Basic Dot Product
// assume a, b have same length
public int dotProduct(int[] a, int[] b){
	int res = 0;
	for (int i = 0; i < a.length; i++)
		res += a[i] * b[i];
	return res;
}

2.Dot product of sparse vector
You have 2 sparse vectors (large number of 0’s). First tell me a way to represent and store them, and then find the dot product.Sparce, long and short
class Node {
	int idx, val;
	public Node(int idx, int val) {
		this.idx = idx;
		this.val = val;
	}
}
// O(m + n) - like merge sort
public int SparseVectorProduct(int[] a,int[] b){
	List<Node> l1 = new ArrayList<>();
	List<Node> l2 = new ArrayList<>();
	for (int i = 0; i < a.length; i++
		if (a[i] != 0)		l1.add(new Node(i, a[i]));
	for (int i = 0; i < b.length; i++
		if (b[i] != 0)		l2.add(new Node(i, b[i]));
	int res = 0, i = 0, j = 0;
	while (i < l1.size() && j < l2.size()) {
		while (l1.get(i).idx < l2.get(j).idx) 	i++;
		if (l1.get(i).idx == l2.get(j).idx) 
			res += l1.get(i++).val * l2.get(j++).val;
		while (l1.get(i).idx > l2.get(j).idx)	j++;
	}
	return res;
}
// O(m * n) - brute force
public int SparseVectorProduct(int[] a,int[] b){
	List<Node> l1 = new ArrayList<>();
	List<Node> l2 = new ArrayList<>();
	for (int i = 0; i < a.length; i++
		if (a[i] != 0)		l1.add(new Node(i, a[i]));
	for (int i = 0; i < b.length; i++
		if (b[i] != 0)		l1.add(new Node(i, b[i]));
	int res = 0;
	for (Node n1 : l1)
		for (Node n2 : l2) {
			if (n1.idx == n2.idx)	res += n1.val * n2.val;
		}
	return res;
}


给你两个sparse vector(含有  的0)，问你如何做dot product
楼主:  双循环，skip 0.
•  试官: 急着写，你想想有 么好办法存vector?
• 琢磨 好久，说要 我们 hashmap存value和index
•  试官继续追问，hashmap会有空的空间，我们有memory限制，你怎么办
• 楼主:那 arraylist存pair?
•  试官:这个还差 多，那你打算怎么求解?
• 楼主:排序，two pointer?
•  试官:好，你写吧。写完后追问 时间复杂度





3.long & short vector
如果一个 vector 比另一个大很多怎么办，答对于小的 vector 里 每一个(index， value)，在大的里 binary search。然后问了复杂度。

Time: O(n*logm) 

public int SparseVectorProduct(int[] a,int[] b){
	List<Node> l1 = new ArrayList<>();
	List<Node> l2 = new ArrayList<>();
	for (int i = 0; i < a.length; i++
		if (a[i] != 0)		l1.add(new Node(i, a[i]));
	for (int i = 0; i < b.length; i++
		if (b[i] != 0)		l1.add(new Node(i, b[i]));
	if (l1.size() > l2.size()) {
		List<Node> tmp = l1;
		l1 = l2;
		l2 = tmp;
	}
	int i = 0, j = l2.size() - 1;
	for (Node n1 : l1) 
    	while (i <= j) {
        	int mid = (j - i) / 2 + i;
       		if (l2.get(mid).x == n1.x) 	res += n1.val * l2.get(mid).val;
        	if (l2.get(mid) < n1.x) 	j = mid;
        	else 	i = mid + 1;
    	}
	return res;
}
