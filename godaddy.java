//1.sqrt(int n)
//binary search
    public int mySqrt(int x) {
        //if (x < 0)  return -1;// add test case: invalid input
        if (x == 0)     return x;
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid)     return mid;//division not multiple since might overflow
            else if (mid < x / mid) left = mid + 1;
            else    right = mid - 1;
        }
        return right; // since when jumped out of loop, right < left, so return right
    }
//newton
    public int mySqrt(int x) {
        long r = x; // long! since r * r might overflow
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

//2.Bulb Switcher
A bulb ends up on iff it is switched an odd number of times.
Call them bulb 1 to bulb n. Bulb i is switched in round d if and only if d divides i. So bulb i ends up on if and only if it has an odd number of divisors.
Divisors come in pairs, like i=12 has divisors 1 and 12, 2 and 6, and 3 and 4. Except when i is a square, like 36 has divisors 1 and 36, 2 and 18, 3 and 12, 4 and 9, and double divisor 6. So bulb i ends up on if and only if i is a square.
So just count the square numbers. Let R = int(sqrt(n)).
That's the root of the largest square in the range [1,n]. And 1 is the smallest root. So you have the roots from 1 to R, that's R roots. Which correspond to the R squares. So int(sqrt(n)) is the answer.
//https://leetcode.com/problems/bulb-switcher/
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

//3.Search a 2D Matrix I
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)     return false;
        int m = matrix.length, n = matrix[0].length;
        int begin = 0, end = m * n - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            int row = mid / n, col = mid % n;
            if (matrix[row][col] == target)     return true;
            else if (matrix[row][col] > target)     end = mid - 1;
            else    begin = mid + 1;
        }
        return false;
    }
//Search a 2D Matrix II
    public boolean searchMatrix(int[][] matrix, int target) {
        // d & c
        if (matrix.length == 0 || matrix[0].length == 0)    return false;
        return searchMatrix(matrix, new int[]{0, 0}, new int[]{matrix.length - 1, matrix[0].length - 1}, target);
        //O(m + n)
        if (matrix.length == 0 || matrix[0].length == 0)    return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) row++;
            else if (matrix[row][col] > target) col--;
            else return true;
        }
        return false;
    }
    private boolean searchMatrix(int[][] matrix, int[] topLeft, int[] bottomRight, int target) {
        if (topLeft[0] > bottomRight[0] || topLeft[1] > bottomRight[1])  return false;
        if (topLeft[0] == bottomRight[0] && topLeft[1] == bottomRight[1])
            return matrix[topLeft[0]][topLeft[1]] == target;
        int rowMid = (topLeft[0] + bottomRight[0]) >> 1;
        int colMid = (topLeft[1] + bottomRight[1]) >> 1;
        if (matrix[rowMid][colMid] > target)
            return searchMatrix(matrix, topLeft, new int[]{rowMid, colMid}, target)
            || searchMatrix(matrix, new int[]{topLeft[0], colMid + 1}, new int[]{rowMid, bottomRight[1]}, target)
            || searchMatrix(matrix, new int[]{rowMid + 1, topLeft[1]}, new int[]{bottomRight[0], colMid}, target);
        else if (matrix[rowMid][colMid] < target)
            return searchMatrix(matrix, new int[]{rowMid + 1, colMid + 1}, bottomRight, target)
            || searchMatrix(matrix, new int[]{topLeft[0], colMid + 1}, new int[]{rowMid, bottomRight[1]}, target)
            || searchMatrix(matrix, new int[]{rowMid + 1, topLeft[1]}, new int[]{bottomRight[0], colMid}, target);
        else    return true;
    }

//4.Add Binary
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int sum = 0;
        while (i >= 0 || j >= 0) {
            sum /= 2;
            if (i >= 0)     sum += a.charAt(i--) - '0';
            if (j >= 0)     sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
        }
        if (sum / 2 != 0)   sb.append(sum / 2);
        return sb.reverse().toString();
    }

//5.Multiply binary
//https://leetcode.com/problems/multiply-strings/  最好用上一题实现的add完成这题
test:
// 1. if a or b is empty string => return 0
// 2. test for illegal input: negative, contains letter => check if a valid biary string first
public String multiplyBinary(String a, String b) {
    String res = "0";
    int len = b.length();
    for (int i = 0; i < len; i++)
        if (b.charAt(len - 1 - i) == '1') {
            res = addBinary(res, a);
            a = a + '0';
        }
    return res;
}

//6.Expression to Tokens
// An expression string will be a sequence of digits, operation characters, and space characters
// (' ').
// Convert this string into an array of strings for the tokens in the expression:
// A sequence of digits becomes a single "token". You can also assume there are no spaces
// inside a number.
// All other characters become their own "token", except the space character which you can
// ignore.
// There are no negative numbers. "-" is a token.
test cases:
// <null> ==> { } // check for null input
// <empty string> ==> { }
// 12345 ==> {"12345"}
// abc ==> {"a","b","c"}
// +-*/ ==> {"+","-","*","/"}
// 12 + 2 * 3+4 ==> {"12","+","2","*","3","+","4"} // check for space 1a2b3 ==> {"1","a","2","b","3"} // check for mix input
public List<String> expressionString(String input) {
		List<String> res = new ArrayList<>();
		if (input == null)	return res;
		char[] chrs = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chrs.length; i++) {
			if (chrs[i] == ' ')		continue;
			if (chrs[i] >= '0' && chrs[i] <= '9'){
				sb.append(chrs[i]);
			} else if (sb.length() > 0) {
				res.add(sb.toString());
				sb.setLength(0);
			}
			if (chrs[i] < '0' || chrs[i] > '9')
				res.add("" + chrs[i]);
		}
		if (sb.length() > 0)
			res.add(sb.toString());
		return res;
	}

//7.Sort Colors
  //Solution 1: counting sort (two pass)
  // A rather straight forward solution is a two-pass algorithm using counting sort.
  // First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
  void sortColors(int A[], int n) {
      int num0 = 0, num1 = 0, num2 = 0;
      for(int i = 0; i < n; i++) {
          if (A[i] == 0) ++num0;
          else if (A[i] == 1) ++num1;
          else if (A[i] == 2) ++num2;
      }
      for(int i = 0; i < num0; ++i) A[i] = 0;
      for(int i = 0; i < num1; ++i) A[num0+i] = 1;
      for(int i = 0; i < num2; ++i) A[num0+num1+i] = 2;
  }
  //Solution 2: two pointers (one pass)
      public void sortColors(int[] nums) {
          int zero = 0, two = nums.length - 1;
          for (int i = 0; i <= two; i++) {
              while (nums[i] == 2 && i < two)    swap(nums, i, two--);
              while (nums[i] == 0 && i > zero)    swap(nums, i, zero++);
          }
      }
      private void swap(int[] nums, int i, int j) {
          int tmp = nums[i];
          nums[i] = nums[j];
          nums[j] = tmp;
      }
      //另一种写法：
      public void sortColors(int[] nums) {
              int zero = 0, two = nums.length - 1, i = 0;
              while (i <= two) {
                  if (nums[i] == 0)   swap(nums, i++, zero++);
                  else if (nums[i] == 2)  swap(nums, i, two--);
                  else    i++;
              }
      }
//注意顺序，必须先swap 2，再swap 0。因为我们是从左往右遍历数组，swap 0是把最左边的元素（可能是0，1，2）换到当前0的位置，swap 2是把最右边的元素换到当前2的位置，
//所以不管swap 0时把什么元素换到中间，接下来一次循环如果先swap 2再swap 0，先swap 2保证了（已经排好序的部分）左边全是0没有2（因为从左往右遍历，前面遇到的2全被换到最右边了），
//所以不可能出现后面swap 0把2换到中间的情况。然后即使swap 2把0转到了中间，紧接着第二个while循环就会swap 0所以不会出错。
//如果先swap 0再swap 2，先swap 0保证了（已经排好序的部分）左边全是0，但swap 2时如果原来最右边是0，现在0换到中间，然后这一循环结束进入下一循环，也就是上一层被换到中间的0直接被忽略了。
//Because before the index i, the array has been sorted, which means that the element before is either 0 or 1,
//and there is no need to swap them first(the second swap will never be executed). But when we swap the element with the last,
//change may happen(index element may become 0) , and we need to swap the index element to the front

//8.Implement Queue using Stacks
public class MyQueue {
    Stack<Integer> input;
    Stack<Integer> output;
    /** Initialize your data structure here. */
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();//important
        return output.pop();
    }
    /** Get the front element. */
    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.empty() && output.empty();
    }
}

//9.Number of Islands
//Solution 1: dfs
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
//Solution 2: union-find
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
            if (root1 > root2) {
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
}

//10.Basic Calculator
/*
Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
Only 5 possible input we need to pay attention:
digit: it should be one digit from the current number
'+' / '-': number is over, we can add the previous number and start a new number
'(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
*/
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0, num = 0, sign = 1;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= '0' && chs[i] <= '9')
                num = num * 10 + chs[i] - '0';
            else if (chs[i] == '+') {
                res += sign * num;
                sign = 1;
                num = 0;
            } else if (chs[i] == '-') {
                res += sign * num;
                sign = -1;
                num = 0;
            } else if (chs[i] == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (chs[i] == ')') {
                res += sign * num;
                res *= stack.pop();
                res += stack.pop();
                num = 0;
            }
        }
        if (num != 0)   res += sign * num;
        return res;
    }

//Basic Calculator II
  public int calculate(String s) {
        if (s.length() == 0)  return 0;
        char[] chrs = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < chrs.length; i++) {
            if (chrs[i] >= '0' && chrs[i] <= '9')
                num = num * 10 + chrs[i] - '0';
            if ((chrs[i] < '0' || chrs[i] > '9') && chrs[i] != ' ' || i == chrs.length - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = chrs[i];
                num = 0;
            }
        }
        int res = 0;
        while (!stack.empty()) {
            res += stack.pop();
        }
        return res;
    }
//follow up:  +,-,*,/,()
public int calculate(String s) {
    s = s.replaceAll(" ", "");
    Stack<Character> sk = new Stack<>();
    String[] num = s.split("[-/+*()]");
    int index = 0;
    List<String> tokens = new ArrayList<>();
    for(int i = 0; i<s.length(); i++) {
        char cur = s.charAt(i);
        if(cur >= '0' && cur <= '9') {
        if(i > 0 && s.charAt(i-1) >= '0' && s.charAt(i-1) <= '9')   continue;
        //while(index < num.length && num[index].length() == 0)   index ++;
            tokens.add(num[index]);
            index ++;
        } else if(cur == '(') {
            sk.add(cur);
        } else if(cur == ')') {
            while(!sk.isEmpty() && sk.peek() != '(')
                tokens.add(String.valueOf(sk.pop()));
            sk.pop();
        } else {
            while(!sk.isEmpty() && largeOrEqual(sk.peek(), cur)) {
                tokens.add(String.valueOf(sk.pop()));
            sk.add(cur);
        }
    }
    while(!sk.isEmpty()) {
        char cur = sk.pop();
        if(cur != '(')
            tokens.add(String.valueOf(cur));
    }
    return evalRPN(tokens);
}
private boolean largeOrEqual(char prev, char cur) {
    if(prev == '(') {
      return false;
    } else if(cur == '+' || cur == '-') {
      return true;
    } else if(prev == '*' || prev == '/') {
      return true;
    }
    return false;
}


//Evaluate Reverse Polish Notation
//逆波兰表达式的解释器一般是基于堆栈的。解释过程一般是：操作数入栈；遇到操作符时，操作数出栈，求值，将结果入栈；当一遍后，栈顶就是表达式的值。因此逆波兰表达式的求值使用堆栈结构很容易实现，和能很快求值。
  public int evalRPN(String[] tokens) {
      Stack<Integer> stack = new Stack<>();
      for (int i = 0; i < tokens.length; i++) {
          if (tokens[i].equals("+")) {
              stack.push(stack.pop() + stack.pop());
          } else if (tokens[i].equals("-")) {
              int b = stack.pop();
              int a = stack.pop();
              stack.push(a - b);
          } else if (tokens[i].equals("*")) {
              stack.push(stack.pop() * stack.pop());
          } else if (tokens[i].equals("/")) {
              int b = stack.pop();
              int a = stack.pop();
              stack.push(a / b);
          } else {
              stack.push(Integer.valueOf(tokens[i]));
          }
      }
      return stack.pop();
  }

//11.Binary Palindrome
public static boolean isBinaryPalidrome(int a) {
		StringBuilder sb = new StringBuilder();
		while (a > 0) {
			sb.insert(0, a & 1);
			a >>= 1;
		}
		String s = sb.toString();
		// String s = Integer.toBinaryString(a);
		int low = 0, high = s.length() - 1;
		while (low <= high) {
			if (s.charAt(low) != s.charAt(high))
				return false;
			low++;
			high--;
		}
		return true;
	}

//Palindrome Number(10进制)
    public boolean isPalindrome(int x) {
        if (x < 0 || x != 0 && x % 10 == 0) return false;
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;
    }

//12.merge 2 sorted deque
Test:
1. whether both sort in the same order (acseding or decseding)
2. test no empty length
3. if deque1.getFirst(smallest) > deque2.getLast(largest): merger deque2 deque1
else if deque1.getLast < deque2.getFirst: merger deque1 deque2
else Sort as two queue

//13.给一个file system，
//要怎么把全部directory走过:
similar to tree traversal, DFS
//怎么找特定一个file:
BFS

//14.folder size
//给你 个function可以获得folder下的folder和file，还有个function可以获得file的size。写一个function求某个folder的size。
dfs: 先找到那个folder，再return all files size + dfs(sub folder)

//15.Search in Rotated Sorted Array
//Solution1
  public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) { // find min idx, when left == right return left
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else    right = mid;
        }
        if (target == nums[left])   return left; // performance improved
        int rotate = left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) { // = return
            int mid = left + (right - left) / 2;
            int newMid = (mid + rotate) % nums.length;
            if (target < nums[newMid])  right = mid - 1;
            else if (target > nums[newMid])   left = mid + 1;
            else    return newMid;
        }
        return -1;
    }
//Solution2
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) { // must find right idx within this loop : =
            int mid = (left + right) / 2;
            if (nums[mid] == target)   return mid;
            else if (nums[mid] < nums[right])
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else    right = mid - 1;
            else
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else    left = mid + 1;
        }
        return -1;
    }

//15.Number of palindrome
//找出字符串中所有的palindrome数量, e.g: abcba 就是2(abcba, bcb)
Test:
// 1. duplicate
// 2. test case: "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
// In such case the cost would be O(n) for sparse palidrome strings.
// For palindrome dense inputs it would be O(n^2) as each position cannot be
// extended more than the length of the array / 2.
public int countNumberOfPalindrome(String s) {
    int n = s.length();
    if(n < 2)   return s;
    Set<String> res = new HashSet<>(); // avoid duplicates
    for(int i = 1; i<n; i++) {
        if(s.charAt(i) == s.charAt(i-1))
            getPalindrome(s, i-1, i, res);
        if(i != n-1 && s.charAt(i+1) == s.charAt(i-1))
            getPalindrome(s, i-1, i+1, res);
    }
    return res.size();
}
private void getPalindrome(String s, int p0, int p1, Set<String> res) {
    while(p0 >= 0 && p1<s.length()) {
        if(s.charAt(p0) == s.charAt(p1)) {
            res.add(s.substring(p0, p1+1)); p0 --;
            p1 ++;
        } else     break;
    }
    return s.substring(p0+1, p1);
}
