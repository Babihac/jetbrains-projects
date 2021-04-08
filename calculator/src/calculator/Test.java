package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		Deque<Integer> stack2 = new LinkedList<>();
		Deque<Integer> stack = new ArrayDeque<Integer>();
		Scanner sc  = new Scanner(System.in);
		int n = sc.nextInt();
		int max = 0;
		for (int i = 0; i < n; i++) {
			String[] arr = sc.nextLine().split(" ");
			String cmd = arr[0];
			//System.out.println(cmd);
			if (cmd.equals("push")) {
				stack.push(Integer.parseInt(arr[1]));
				if (stack2.isEmpty()) {
					stack2.push(Integer.parseInt(arr[1]));
					max = Integer.parseInt(arr[1]);
				} else {
					if (max < Integer.parseInt(arr[1])) {
						max = Integer.parseInt(arr[1]);
						stack2.push(Integer.parseInt(arr[1]));
					} else {
						stack.push(max);
					}
				}
			}
			else if (cmd.equals("pop")) {
				stack.pop();
				stack2.pop();
				max = stack2.peek();
			}
			if (cmd.equals("max")) {
				System.out.println(stack2.peek());
			}
			System.out.println(stack.size() + " " + stack2.size());
			
		}
	}

}
