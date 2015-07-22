package finalProject;

import java.io.Serializable;

public class InvalidDataException extends Exception  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException (){
		super ("Invalid Data Exception");
	}
	
	public InvalidDataException (String msg){
		super ("Invalid Data Exception:\n"+msg);
	}
}
