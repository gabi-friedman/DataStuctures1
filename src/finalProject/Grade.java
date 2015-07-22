package finalProject;

import java.io.Serializable;

public enum Grade  implements Serializable{
	A(4.0),B(3.0),C(2.0),D(1.0),F(0.0);

	private double gpaVal;

	Grade(double gpaVal){
		this.gpaVal = gpaVal;
	}

	public double getGpaVal() {
		return gpaVal;
	}
}
