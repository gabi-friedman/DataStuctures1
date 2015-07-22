package finalProject;

import java.io.Serializable;

public class DuplicateDataException extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateDataException (){
		super ("Duplicate Data");
	}
	
	public DuplicateDataException (String msg){
		super ("Duplicate Data\n"+msg);
	}
}
