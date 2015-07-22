package billOrganizer;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Bill implements Serializable, Comparable<Bill>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer billID;
	private String vendorName;
	private Double amtDue;
	private GregorianCalendar dateDue;
	private BillType type;
	private String temp;
	private String[] ui;
	//the IDs from the file read in are ignored.  the next ID is assigned to each Bill as the user/file puts it in
	
	
	public Bill(Integer billID, String line){
			Scanner inf = new Scanner(line);
			inf.next();
			this.billID = billID;
			this.vendorName = inf.next();
			this.amtDue = Double.parseDouble(inf.next());
			this.temp = inf.next();
			ui = temp.split("/");
			dateDue = new GregorianCalendar();
			this.dateDue.set(Integer.parseInt(ui[2]), Integer.parseInt(ui[0])-1, Integer.parseInt(ui[1]));
			this.type = BillType.valueOf(inf.next().toUpperCase());			
	}
	
	public Bill(Integer billID, String vendorName, double amtDue, GregorianCalendar dateDue, String type){
		this.billID = billID;
		this.vendorName = vendorName;
		this.amtDue = amtDue;		
		this.dateDue = dateDue;
		this.type = BillType.valueOf(type.toUpperCase());
	}
	
	public int getBillID(){
		return this.billID;
	}
	
	public String getVendorName(){
		return this.vendorName;
	}
	
	public Double getAmtDue(){
		return this.amtDue;
	}
	
	public GregorianCalendar getDateDue(){
		return this.dateDue;
	}
	
	public BillType getBillType(){
		return this.type;
	}
	
	public void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}
	
	public void setAmtDue(double amtDue){
		this.amtDue = amtDue;
	}
	
	public void setDateDue(GregorianCalendar dateDue){
		this.dateDue = dateDue;
	}
	
	public void setBillType(String type){
		this.type = BillType.valueOf(type);
	}
	
	@Override
	public int compareTo(Bill bill) {
		return this.billID.compareTo(bill.getBillID());
	}
	
	public String toString(){
		StringBuilder build = new StringBuilder();
		//using the same format as in the sample file
		DecimalFormat dec = new DecimalFormat("$0.00");
		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		build.append(billID + " " + vendorName + " " + dec.format(amtDue) + " " + date.format(dateDue.getTime()) + " " + type.toString() + "\n");
		return build.toString();
	}
	
	
}
