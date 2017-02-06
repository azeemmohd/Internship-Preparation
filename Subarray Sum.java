Subarray Sum
只需要判断是否存在，返回boolean
hashset

public int maxSubArrayLen(int[] nums, int k) {
    int sum = 0;
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (sum == k || map.containsKey(sum - k))	return true;
        set.add(sum);
    }
    return false;
}


209. Minimum Size Subarray Sum

public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0)   return 0;
    int sum = 0, i = 0, j = 0, min = Integer.MAX_VALUE, len = 0;
    while (j < nums.length) {
        sum += nums[j++];
        while (sum >= s) {
            min = Math.min(min, j - i);
            sum -= nums[i++];
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}



325. Maximum Size Subarray Sum Equals k

public int maxSubArrayLen(int[] nums, int k) {
    int sum = 0, max = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (sum == k)   max = i + 1;
        else if (map.containsKey(sum - k))
            max = Math.max(max, i - map.get(sum - k));
        if (!map.containsKey(sum))  map.put(sum, i);
    }
    return max;
}