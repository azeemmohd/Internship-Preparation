56. Merge Interval
// https://leetcode.com/problems/insert-interval/
// Given [1,3],[2,6],[8,10],[15,18],
// return [1,6],[8,10],[15,18].

注意：1.别忘先sort 2.中间用if-else 3.loop后别忘add最后一个interval

public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1)  return intervals;
    List<Interval> res = new ArrayList<>();
    // important
    Collections.sort(intervals, new Comparator<Interval>(){
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
    });
    int start = intervals.get(0).start, end = intervals.get(0).end;
    for (Interval i : intervals) {
        if (i.start <= end)
            end = Math.max(end,i.end);
        else {
            res.add(new Interval(start, end));
            start = i.start;
            end = i.end;
        }
    }
    res.add(new Interval(start, end));
    return res;
}
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
