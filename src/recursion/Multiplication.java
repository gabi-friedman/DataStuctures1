package recursion;

public class Multiplication {
	
	static int multiply (int value, int times){
	    
		if (times ==0) return 0;
		if (times ==1) return value;
		
		else {
			if (value < 0  )
				if (times < 0 ) return Math.abs(value) + multiply(Math.abs(value),Math.abs(times)-1);
				else return -1 * ( value +  multiply(value,Math.abs(times)-1));
			else 
				if (times < 0) return -1 * ( value +  multiply(value,Math.abs(times)-1)); 
				else return value + multiply(value,Math.abs(times)-1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(multiply(-2,-2));
		System.out.println(multiply(5,2));
		System.out.println(multiply(5,-2));
		
		// TODO Auto-generated method stub

	}

}