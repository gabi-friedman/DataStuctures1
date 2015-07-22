package student;

public class NotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(){
		super("studentID not found");
	}

}
