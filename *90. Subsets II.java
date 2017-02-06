90. Subsets II
// https://leetcode.com/problems/subsets-ii/
If nums = [1,2,2], a solution is:
[ [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []]

public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    dfs(res, new ArrayList<>(), nums, 0);
    return res;
}
public void dfs(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start) {
    res.add(new ArrayList<>(tmp));
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1])    continue;
        tmp.add(nums[i]);
        dfs(res, tmp, nums, i + 1);
        tmp.remove(tmp.size() - 1);
    }
}






2.subsets 我  递归写的，写完解释正确性 时间复杂度。
3.subsets  递归再写 遍。。解释正确性
4.follow up 有 个class   有个 法next(), 每次调 next()输出上 subsets中的 个

楼主是不是用bit操作。比如[1,2,3] 然后生成[0,7个数字]，然后调用next时候，随机拿出来一个数字，生成一个subset？
如果不要求subset是随机的，没必要用随机数，用一个counter就可以了把