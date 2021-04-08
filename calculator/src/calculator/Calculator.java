package calculator;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Calculator {
	private Stack<Integer> numbers;
	private Stack<Character> operators;
	private Set<Character> operator = new HashSet<>();
	private HashMap<String, String> cmd = new HashMap<String, String>();
	private HashMap<String, BigInteger> vars = new HashMap<String, BigInteger>();
	public Calculator() {
		this.numbers = new Stack<>();
		this.operators = new Stack<>();
		operator.add('+');
		cmd.put("/help", "The program calculates the sum of numbers");
		cmd.put("/exit", "break");
		//operator.add('-');
	}
	public HashMap<String, String> getCmd() {
		return cmd;
	};
	public boolean validCmd(String cmd) {
		return this.cmd.containsKey(cmd);
	}
	public void addNumber(int a) {
		numbers.add(a);
	}
	
	public boolean validParens(String expr) {
		int lefts = 0;
		int rights = 0;
		for(char i : expr.toCharArray()) {
			if(i == '(') lefts++;
			if(i == ')') rights++;
			
		}
		return lefts == rights;
	}
	
	public void addOperator(Character a) {
		operators.add(a);
	}
	private int add(int a, int b) {
		return a+b;
	}
	public void evaluate(String expr) {
		if(!validParens(expr)) {
			System.out.println("Invalid expression");
			return;
		}
		Deque<String> vals = new ArrayDeque<String>();
		expr = expr.replaceAll("\\-{2}", "+");
		expr = expr.replaceAll("\\++", " + ");
		expr = expr.replaceAll("\\-", " - ");
		expr = expr.replaceAll("\\*", " * ");
		expr = expr.replaceAll("\\/", " / ");
		expr = expr.replaceAll("^\\++", "");
		expr = expr.replaceAll("\\(", " ( ");
		expr = expr.replaceAll("\\)", " ) ");
		expr = expr.replaceAll("\\s+", " ").trim();
		//System.out.println(expr);
		//else System.out.println("Invalid");
		BigInteger cou = BigInteger.ZERO;
		int count = 0;
		try {
			//System.out.println(expr);
			expr = infixToPostfix(expr);
			String[] nums = expr.split("\\s+");
			for (int i = 0; i < nums.length; i++) {
				 if(nums[i].matches("\\w+")) {
					vals.push(parseVars(nums[i])); 
				 } else {
					 String a  = vals.pop();
					 String b = vals.isEmpty() ? "0" : vals.pop();
					 BigInteger c = new BigInteger(a);
					 BigInteger d = new BigInteger(b);
					 BigInteger res = BigInteger.ZERO;
					 if(nums[i].equals("+")) res = c.add(d);
					 else if(nums[i].equals("-")) {
						 //System.out.println(" a is " + a + " b is " + b);
						 res = d.subtract(c);
					 }
					 else if(nums[i].equals("*")) res = c.multiply(d);
					 else if(nums[i].equals("/")) res = d.divide(c);
					 //System.out.println("res is " + res + " a is " + a + " b is " + b);
					 vals.push(String.valueOf(res));
				 }
					
				//if(!val.equals("")) {}
				//count += Integer.parseInt(val);
			}
			System.out.println(vals.pop());
		} catch (Exception e) {
			System.out.println("Unknown variable");
		}
	}
	
	private String parseVars(String s ) {
		boolean minus = s.contains("-");
		 s = s.replaceAll("\\-", "");
		if (vars.containsKey(s)) { 
			return minus? "-" + String.valueOf(vars.get(s)) : String.valueOf(vars.get(s));
		}
		return minus ? "-" + s : s;
	}
	
	public boolean validateVar(String s) {
		String var = s.substring(0,s.indexOf("="));
		var = var.replaceAll("\\s+", "").trim();
		if (var.matches(".*\\d+.*")) return false;
		String val = s.substring(s.indexOf("=")+1);
		val = val.replaceAll("\\s+", "").trim();
		String matchNum = ".*\\d+.*";
		String matchLower = ".*\\D+.*";
		if (val.matches(matchNum) && val.matches(matchLower)) System.out.println("Invalid Assignment");
		//System.out.println(val);
		else { 
			try {
				val = parseVars(val);
				vars.put(var, new BigInteger(val));
			} catch(Exception e) {
				System.out.println("Unknown variable");
				return false;
			}
		}
		return true;
	}
	public boolean checkForVar(String s) {
		return s.matches(".*=.*");
		
	}
	
	public boolean isValid(String s) {
		String plusEnd = ".*[\\+$\\-$=$]";
		String e = ".*[^0-9a-zA-Z\\+\\-=\\*\\(\\)\\/]+";
		String x = "\\d+\\s\\d+";
		//System.out.println("testtest" + s.matches(e));
		return !(s.matches(plusEnd) || s.matches(e) || s.matches(x));
	}
	
	 private String infixToPostfix(String exp) 
	    { 
	        // initializing empty String for result 
	        String result = new String(""); 
	        String[] nums = exp.split("\\s+");
	        // initializing empty stack 
	        Stack<String> stack = new Stack<>(); 
	          
	        for (int i = 0; i < nums.length; i++) 
	        {            
	        	    
	             // If the scanned character is an operand, add it to output. 
	            if (nums[i].matches("\\d+") || nums[i].matches("\\w+")) {
	            	//System.out.println(nums[i]);
	            	 result += nums[i] + " ";
	            } 
	               
	            // If the scanned character is an '(', push it to the stack. 
	            else if (nums[i].equals("(")) 
	                stack.push(nums[i]); 
	              
	            //  If the scanned character is an ')', pop and output from the stack  
	            // until an '(' is encountered. 
	            else if (nums[i].equals(")")) 
	            { 
	                while (!stack.isEmpty() && !stack.peek().equals("(")) 
	                    result += (stack.pop() + " "); 
	                stack.pop(); 
	            } 
	            else // an operator is encountered 
	            { 
	                while (!stack.isEmpty() && Prec(nums[i]) <= Prec(stack.peek())){ 
	                    
	                    result += stack.pop() + " "; 
	             } 
	                stack.push(nums[i]); 
	            } 
	       
	        } 
	       
	        // pop all the operators from the stack 
	        while (!stack.isEmpty()){ 
	           
	            result += stack.pop() + " "; 
	         } 
	        return result.trim(); 
	    } 
	 
	 private int Prec(String ch) 
	    { 
	        switch (ch) 
	        { 
	        case "+": 
	        case "-": 
	            return 1; 
	       
	        case "*": 
	        case "/": 
	            return 2; 
	       
	        case "^": 
	            return 3; 
	        } 
	        return -1; 
	    } 
	
}
