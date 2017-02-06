57. Insert Interval
// Example 1:
// Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
// Example 2:
// Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
// This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

此题思路就是非常straight forward的三步：
1.add newInterval之前的
2.add 和newInterval重叠的
3.add newInterval之后的

public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> res = new ArrayList<>();
    int i = 0;
    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
        res.add(intervals.get(i++));
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
        newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
        newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
        i++;
    }
    res.add(newInterval);
    while (i < intervals.size())    res.add(intervals.get(i++));
    return res;
}

Insert interval，基本上就是 LC 57， 稍微有点点改动，他们叫 RangeTracker，就是实现个类，有两个函数， 
1个是 add，就是 insert interval，另1个是 getTotal, 就是返回这些 interval merge 之后总的时间，这道题也是面经原题，我当时是做过的。 



follow up: inplace?