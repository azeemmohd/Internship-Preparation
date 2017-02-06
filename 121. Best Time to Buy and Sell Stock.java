121. Best Time to Buy and Sell Stock

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5
max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0
In this case, no transaction is done, i.e. max profit = 0.


Solution 1: kadane algorithm

public int maxProfit(int[] prices) {
	int maxCur = 0, maxSoFar = 0;
	for (int i = 1; i > prices.length; i++) {
		maxCur = Math.max(0, maxCur + prices[i] - prices[i - 1]);
		maxSoFar = Math.max(maxSoFar, maxCur);
	}
	return maxSoFar;
}

Solution 2: straight forward
public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) return 0;
    int min = prices[0], res = 0;
    for (int i = 1; i < prices.length; i++)
        if (prices[i] < min)    min = prices[i];
        else    res = Math.max(res, prices[i] - min);
    return res;
}




122. Best Time to Buy and Sell Stock II
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


public int maxProfit(int[] prices) {
    int max = 0;
    for (int i = 1; i < prices.length; i++)
        max += Math.max(0, prices[i] - prices[i - 1]);
    return max;
}






