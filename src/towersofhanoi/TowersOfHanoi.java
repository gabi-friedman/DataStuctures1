package towersofhanoi;

public class TowersOfHanoi {
	private int numCalls;
	public TowersOfHanoi(){numCalls=0;}
	public void MoveTowersOfHanoi(int count, char source, char destination, char spare){

		++numCalls;
		System.out.println ( "\nCall NO. " +  numCalls);
		System.out.println( "  I must move " +  count  +  " ring(s) from  "  +  source + "  to  "  +  destination  +
		 " using  " + spare + " as a spare");

		if (count == 1)    //this is the base case
		{
		   System.out.println( "                   Move ring from  "  +  source  +  "  to  "  + destination);
		 }
		else{
		//get rid of all the top disks and move them to the spare disk
		MoveTowersOfHanoi(count-1, source, spare, destination);
		System.out.println( "    Returning back source is  " +  source  +  "  destination is  "  + destination
		   +  " spare is " + spare);  
		//now you have only one disk left , so move it from the source to the destination peg
		MoveTowersOfHanoi(1,source,destination,spare);
		//move the remaining disks from the spare peg to the destination, using the original source
		//as a spare
		System.out.println( " Process remaining rings");
		MoveTowersOfHanoi(count-1, spare,destination,source);
		}
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       TowersOfHanoi myTower = new TowersOfHanoi();
	   myTower.MoveTowersOfHanoi(3,'A','B','C');
	}
}