package finalProject;

import java.io.Serializable;

public class MissingDataException extends Exception  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingDataException (){
		super ("Missing Data. Fill in all fields");
	}
	
	public MissingDataException (String msg){
		super ("Missing Data. Fill in all fields \n" + msg);
	}
}
