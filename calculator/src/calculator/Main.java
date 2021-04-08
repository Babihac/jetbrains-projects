package calculator;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		while(true) {
			Scanner sc = new Scanner(System.in);
			String i = sc.nextLine();
			if (i.length() == 0) continue;
			if(i.equals("/exit")) {
				break;
			}
			if(i.startsWith("/")) {
				//System.out.println(calc.isValid(i));
				if(calc.validCmd(i)) {
					System.out.println(calc.getCmd().get(i));
					continue;
				} else {
					System.out.println("Unknown command");
					continue;
				}
			}
			if(calc.checkForVar(i)) {
				if (calc.validateVar(i)) { 
					System.out.println();
					continue;
				} else {
					System.out.println("Invalid identifier");
					continue;
				}
			}
			if(!calc.isValid(i)) {
				System.out.println("Invalid expression");
				continue;
			}
			calc.evaluate(i);
			
		}
		System.out.println("Bye!");
	}

}
