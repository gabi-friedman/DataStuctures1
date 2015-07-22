package decimalToBinary;

import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
	
	private static Stack<String> numberStack;
	
	public DecimalToBinary(){
		numberStack = new Stack<String>();
	}
	
	public static void getBinary(Integer userNum){
		
		while(userNum != 0){
			int remainder = userNum % 2;
			numberStack.push(remainder + "");
			userNum = userNum / 2;
		}
		while(!numberStack.empty()){
			System.out.print(numberStack.pop());
		}
	}
	
	public static void main(String[] args) {
		DecimalToBinary newNum = new DecimalToBinary();
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a Decimal number to turn to Binary");
		Integer userNum = console.nextInt();
		
		newNum.getBinary(userNum);
		
		console.close();
	}

}
