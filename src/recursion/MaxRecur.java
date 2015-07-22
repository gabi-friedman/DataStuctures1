package recursion;

public class MaxRecur {
     public int max(int[] n, int size){
    	 if (size ==1) return n[0];
    	 else {
    		 if (n[size-1] > max(n,size-1))
    			 return n[size-1];
    		 else 
    			 return max(n,size-1);
    	 }
    	 
     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[] nums = new int[10];
        nums[0] =100;
        nums[1] = 200;
        nums[2]= 150;
        nums[3] = 80;
        nums[4] = 70;
        nums[5] = 300;
        nums[6]= 40;
        nums[7] = 10;
        nums[8] = 25;
        nums[9] = 400;
        int largest;
        MaxRecur myMax = new MaxRecur();
        largest = myMax.max(nums,nums.length);
        System.out.println(largest);
	}

}