253. Meeting Rooms II
// https://leetcode.com/problems/meeting-rooms-ii/
Given [[0, 30],[5, 10],[15, 20]],
return 2.

Solution 1: greedy
public int minMeetingRooms(Interval[] intervals) {
	int[] start = new int[intervals.length];
	int[] end = new int[intervals.length];
	for (int i = 0; i < intervals.length; i++) {
		start[i] = intervals[i].start;
		end[i] = intervals[i].end;
	}
	Arrays.sort(start);
	Arrays.sort(end);
	int endIdx = 0, res = 0;
	for (int i = 0; i < start.length; i++) {
		if (start[i] < end[endIdx])		res++;
		else	endIdx++;
	}
	return res;
}

Solution 2: PQ
public int minMeetingRooms(Interval[] intervals) {
	if (intervals == null || intervals.length == 0) return 0;
	Arrays.sort(intervals, new Comparator<Interval>(){
		public int compare(Interval i1, Interval i2) {
			return i1.start - i2.start;
		}
	});
	PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
		public int compare(Interval i1, Interval i2) {
			return i1.end - i2.end;
		}
	});
	pq.offer(intervals[0]);
	for (int i = 1; i < intervals.length; i++) {
		Interval interval = pq.poll();
		if (intervals[i].start >= interval.end) 
			interval.end = intervals[i].end;
		else
			pq.offer(intervals[i]);
		pq.offer(interval);
	}
	return pq.size();
}

**************Follow Up**************
返回每个房间 所有的会议 的开始时间和结束时间。

把所有的interval排序一下，建立一个priorityqueue<List<Interval>> 按照List<Interval>最后一个interval 的end来排序，如果新来的interval起点 
比最早结束的interval终点早，或者priorityqueue为空，create 一个新的List<Interval>， 否则， poll priorityqueue。 把新的interval 加进去就好了。 
one pass。 方法来自机经，非自创
// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=222745&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D2%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311



follow up是 将返回所有最大的时间段，以Range类的形式返回
根据扫描线做的，有点像merge interval. 遇到一个最新的max，就清空list,重新加入节点。count = max - 1的时候，取出list<RANGE>最后一个range，更新end节点
// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210175&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D2%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311



252. Meeting Rooms
// https://leetcode.com/problems/meeting-rooms/

public boolean canAttendMeetings(Interval[] intervals) {
    if (intervals == null)  return false;
    Arrays.sort(intervals, new Comparator<Interval>() {//sort by start time also works fine.
        public int compare(Interval i1, Interval i2) {
            return i1.end - i2.end;
        }
    });
    for (int i = 1; i< intervals.length; i++) 
        if (intervals[i].start < intervals[i - 1].end)    return false;
    return true;
}







