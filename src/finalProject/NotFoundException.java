package finalProject;

import java.io.Serializable;

public class NotFoundException extends Exception  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(){
		super("Course Not Found");
	}
	
	public NotFoundException(String msg){
		super("Course Not Found\n" +msg);
	}
}
