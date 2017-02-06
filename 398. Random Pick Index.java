398. Random Pick Index
变形版见最后
// https://leetcode.com/problems/random-pick-index/
ex.
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);
// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);
// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

Solution: Reservoir Sampling
// For the nth target, ++count is n. Then the probability that rnd.nextInt(++count)==0 is 1/n. Thus, the probability that return nth target is 1/n.
// For (n-1)th target, the probability of returning it is (n-1)/n * 1/(n-1)= 1/n......

public class Solution {
    int[] nums;
    Random r;
    public Solution(int[] nums) {
        r = new Random();
        this.nums = nums;
    }
    public int pick(int target) {
        int res = -1, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)  continue;
            if (r.nextInt(++count) == 0)
                res = i;
        }
        return res;
    }
}


变形版：randomly return one of the maximal elements indices

注意此题需要先找到cur max使其作为target同时更新res和count值。在求cur max时注意用"if (max == Integer.MIN_VALUE || nums[i] > max)"，因为数组中可能有Integer.MIN_VALUE

public class Solution {
    int[] nums;
    Random r;
    public Solution(int[] nums) {
        this.nums = nums;
        r = new Random();
    }   
    public int pick(int target) {
        int max = Integer.MIN_VALUE;
        int res = -1，count = 0;
        for (int i = 0; i < nums.length; i++) 
            if (max == Integer.MIN_VALUE || nums[i] > max) { //1st time meet the cur max, update res & count
                max = nums[i];
                res = i;
                count = 1;
            } else if (nums[i] == max) {
                if (rand.nextInt(++count) == 0) //later meet max, randomly pick
                    res = i;
            }
        return res;
    }
}