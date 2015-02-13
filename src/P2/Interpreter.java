package P2;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Interpreter {
	/* This class reads S, the stack based intermediate code, from standard input
	* interprets it and executes it "on the fly". The result is is written to standard
	* output.
	*/
	
	private static Stack<String> stack;
	private static HashMap<String, Integer> map;

	private static Scanner in = new Scanner(System.in);

	public Interpreter() {
		this.stack = new Stack<String>();
		this.map = new HashMap<String, Integer>();
	}

	public static void execute() {
		String nextLine;

		while (in.hasNext()) {
			String next = in.next();

			String temp = "";

			int a = 0, b = 0;
			if (next.equals("PUSH")) {
				temp = in.next();
				stack.push(temp);
			} else if (next.equals("ADD")) {
				try {
					temp = stack.pop();
				}catch(EmptyStackException ex) {
					ex("+");
				}
				
				if (isID(temp)) {
					try {
						a = map.get(temp);
					} catch (NullPointerException ex) {
						ex("+");
					}
				} else {
					a = Integer.parseInt(temp);
				}
				
				try {
					temp = stack.pop();	
				} catch(EmptyStackException ex) {
					ex("+");
				}
				
				
				if (isID(temp)) {
					try {
						b = map.get(temp);
					} catch(NullPointerException ex) {
						ex("+");
					}
				} else {
					b = Integer.parseInt(temp);
				}

				Integer sum = a + b;
				
				stack.push(sum.toString());
				
			} else if (next.equals("SUB")) {
				
				try {
					temp = stack.pop();
				}catch(EmptyStackException ex) {
					ex("-");
				}
				
				if (isID(temp)) {
					try {
						a = map.get(temp);
					} catch(NullPointerException ex) {
						ex("-");
					}
				} else {
					a = Integer.parseInt(temp);
				}

				try {
					temp = stack.pop();
				} catch(EmptyStackException ex) {
					ex("-");
				}
				
				if (isID(temp)) {
					try {
						b = map.get(temp);
					} catch(NullPointerException ex) {
						ex("-");
					}
				} else {
					b = Integer.parseInt(temp);
				}

				Integer sum = b - a;

				stack.push(sum.toString());

			} else if (next.equals("MULT")) {
				try {
					temp = stack.pop();
				} catch(EmptyStackException ex) {
					ex("*");
				}
				
				if (isID(temp)) {
					try {
						a = map.get(temp);
					} catch(NullPointerException ex) {
						ex("*");
					}
				} else {
					a = Integer.parseInt(temp);
				}
				
				try {
					temp = stack.pop();
				} catch(EmptyStackException ex) {
					ex("*");
				}
				
				if (isID(temp)) {
					try {
						b = map.get(temp);
					} catch(NullPointerException ex) {
						ex("*");
					}
				} else {
					b = Integer.parseInt(temp);
				}

				Integer sum = a * b;

				stack.push(sum.toString());

			} else if (next.equals("ASSIGN")) {
				
				try{
					temp = stack.pop();
				
				} catch(EmptyStackException ex) {
					ex("=");
				}
				
				if (isID(temp)) {
					try {
						a = map.get(temp);
					} catch(NullPointerException ex) {
						ex("=");
					}
				} else {
					a = Integer.parseInt(temp);
				}
				
				try {
					temp = stack.pop();
				} catch(EmptyStackException ex) {
					ex("=");
				}
				
				map.put(temp, a);

			} else if (next.equals("PRINT")) {
				temp = stack.peek();
				
				if (isID(temp)) {
					System.out.println(map.get(temp));
				} else {
					System.out.println(Integer.parseInt(temp));
				}
			} else {
				ex(next);
			}
		}
		
 	}
	
	private static void ex(String ex) {
		System.out.println("Error for operator: " + ex);
		System.exit(0);
	}

 	private static boolean isID(String word) {
 		return word.matches("[A-Za-z]+");
 	}
	
	public static void main(String[] args) {
		/* Should correspond to the general function of an interpreter, i.e. the 
		 * fetch-decode-execute-style
		 */
		Interpreter interMilan = new Interpreter();
		interMilan.execute();
	}
}
