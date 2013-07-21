/***********************************
 * Student Name : Thomas Khalil	   *		
 * ASSIGNMENT 3                    *
 ***********************************/

//This class inherits is basic structure from the main TrainTicket class
//and extends it's capability to handle the creation of First Class Tickets.

public class FirstClassTicket extends TrainTicket {
	
	private boolean WaiterFlag;
	public static final int LEGPRICE = 29;

	//FirstClassTicket constructor
	
	public FirstClassTicket(int seatNo, String name, int duration, boolean WaiterFlag){
	super(seatNo,name,duration);
	this.WaiterFlag=WaiterFlag;
}
	
	@Override
	//Returns the value of the trip inclusive of the waiter fee if applicable.
	 public double getPrice()
	   {
	      if (this.WaiterFlag) {
	    	  return this.duration * LEGPRICE+15;
	      }
	      return this.duration * LEGPRICE;
	   }
	
	@Override
	//Controls the notation of the ticket objects at the output stage,
	//based on whether or not a waiter is included.
	
	public String toString()
	   {
		if (this.WaiterFlag==true)
	      return this.getSeat() + ":" + this.name + ":" + this.duration+ ":Y";
		else
			return this.getSeat() + ":" + this.seatNo + ":" + this.duration+ ":N";
	   }
	

}
