75. Sort Colors
// https://leetcode.com/problems/sort-colors/

// 0,0,1,1,2,1,0,2,2

public void sortColors(int[] nums) {
    int zero = 0, two = nums.length - 1, i = 0;
    while (i <= two) {
        if (nums[i] == 0)   swap(nums, i++, zero++);
        else if (nums[i] == 2)  swap(nums, i, two--);
        else    i++;
    }
}
private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}

另一种写法
!!!!!注意!!!!!
for-loop 里是while不是if!
for-loop 条件是<= right不是length!

public void sortColors(int[] nums) {
    int left = 0, right = nums.length - 1;
    for (int i = 0; i <= right; i++) { // important: <= right, not < nums.length
        while (nums[i] == 2 && i < right)   swap(nums, i, right--); //important! while not if !! e.x. [0,1,0] -> [0,1,0] wrong, [0,0,1] correct
        while (nums[i] == 0 && i > left)    swap(nums, i, left++);
    }
}



******************************变种*******************************

给定一个API getCategory(int n)， return {L| M| H} 三种category

第一问 --- 给定一个Array， [4,2,5,7,8,9], 对于每一个int，有一个category，sort them by category

public void sortByCategory(int[] nums) {
    int l = 0, h = nums.length - 1, i = 0;
    while (i <= h) {
        if (getCategory(nums[i]) == 'L')
            swap(nums, i++, l++);
        else if (getCategory(nums[i]) == 'R')
            swap(nums, i, r--);
        else    i++;
    }
}


Follow Up: 如果这个时候有K个category， 应该怎么办？

顺着上一题的思路，我的想法是将（0,1，。。。，k-1） category 分成（0）--> L, (1, k-2) -->M, (k-1) --> H， 然后相同的思想继续call之前的function，
然后reduce为 （1，k-2）的range，重复之前的事情
之前的sortCategory也可以处理只有两种Category的case，不用担心， 直接call



注意:为什么不要increase i, 会问你的!!!!!!

public void sortKCategory(int[] nums, int k){
    if (nums == null || k <= 0)     return;
    int categStart = 0, categEnd = k - 1;
    while (categStart < categEnd) // stop when only 2-3 categories left
        sortCategory(nums, categStart++, categEnd);
}

private void sortCategory(int[] nums, int l, int h){
    if (nums == null)   return;
    int start =0, end = nums.length - 1;
    while (getCategory(nums[start]) < low)  start++;
    while (getCategory(nums[end]) > end)    end--;
    int i = start; //注意loop条件
    while (i <= end) {
        if (getCategory(nums[i]) == l)
            swap(nums, i++, l++); //注意为什么要increase i
        else if (getCategory(nums[i]) == r)
            swap(nums, i, r--); //注意为什么不要increase i, 会问你的!!!!!!
        else    i++;
    }
}

































