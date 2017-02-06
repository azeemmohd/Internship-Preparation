200. Number of Islands
// https://leetcode.com/problems/number-of-islands/

Solution 1: DFS

public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0)   return 0;
    int m = grid.length, n = grid[0].length, res = 0;
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (grid[i][j] == '1') {
                DFSMarking(grid, i, j);
                res++;
            }
    return res;
}
public void DFSMarking(char[][] grid, int i, int j) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
        return;
    grid[i][j] = '0';
    DFSMarking(grid, i + 1, j);
    DFSMarking(grid, i, j + 1);
    DFSMarking(grid, i, j - 1);
    DFSMarking(grid, i - 1, j);
}
    

Solution 2: Union - Find

public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0)   return 0;
    int m = grid.length, n = grid[0].length;
    UnionFind uf = new UnionFind(grid, m, n);
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) 
            if (grid[i][j] == '1') {
                int p = i * n + j, q;
                if (i > 0 && grid[i - 1][j] == '1') {
                    q = p - n;
                    uf.union(p, q);
                } 
                if (j > 0 && grid[i][j - 1] == '1') {
                    q = p - 1;
                    uf.union(p, q);
                }
                if (i < m - 1 && grid[i + 1][j] == '1') {
                    q = p + n;
                    uf.union(p, q);
                }
                if (j < n - 1 && grid[i][j + 1] == '1') {
                    q = p + 1;
                    uf.union(p, q);
                }
            }
    return uf.getCount();
}
class UnionFind{
    int[] lands;
    int count;
    public UnionFind(char[][] grid, int m, int n) {
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1')  count++;
        lands = new int[m * n];
        for (int i = 0; i < m * n; i++) 
            lands[i] = -1;
    }
    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
    public int find(int i) {
        if (lands[i] < 0)
            return i;
        else { //path compression
            lands[i] = find(lands[i]);
            return lands[i];
        }
    }
    public void union(int i, int j) {
        int root1 = find(i), root2 = find(j);
        if (root1 == root2) return;
        if (lands[root1] > lands[root2]) {
            lands[root2] += lands[root1];
            lands[root1] = root2;
        } else {
            lands[root1] += lands[root2];
            lands[root2] = root1;
        }
        count--;
    }
    public int getCount() {
        return count;
    }
}