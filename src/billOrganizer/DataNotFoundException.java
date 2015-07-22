package billOrganizer;

public class DataNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataNotFoundException (){
		super ("data not found");
	}
}
