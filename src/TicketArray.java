/***********************************
 * Student Name : Thomas Khalil	   *		
 * ASSIGNMENT 3                    *
 ***********************************/

// TrainTicket class: encapsulate data related to a standard train ticket
//This class is responsible for encapsulating the array and limiting 
//access to its information, to maintain data integrity and security.


public class TicketArray
{

   private TrainTicket[] store;
   private int ticketCounter;
   
   // constructor that instantiates an object of the array .
   public TicketArray()
   {
	   this.store=new TrainTicket[100];
	   this.ticketCounter=0;
   }
   
   // add() method:
   // take the passed in TrainTicket object and attempt to store it in the
   // data structure. If the structure is full, or the seat of the given
   // TrainTicket has already been booked, the operation should return
   // false; otherwise return true.
   
   //This method is responsible for handling tickets that have been passed 
   //to it and evaluates whether or not its storage was successful.
   public boolean add(TrainTicket data)
   {
      
	   for (int i=0;i<this.ticketCounter;i++)
		   if (this.store[i]!=null)
		   if (this.store[i].getSeat()==data.getSeat()) return false;
	   if (this.size()!=100)
	   {
		   
		   this.store[this.ticketCounter]=data;
		   this.ticketCounter++;
		   return true;
	   }
	   else {
		  
		   for (int i=0;i<100;i++)
		   {
			   if (this.store[i]==null)
			   {
				   this.store[i]=data;
				   return true;
			   }
		   }
	   }
	   return false;
   }
   
   // retrieve() method:
   // take the passed in seat number and search for the corresponding
   // TrainTicket. If found, the operation should return the TrainTicket,
   // otherwise it returns null.
   
   //This method is called when a user chooses to view a booking from the 
   //main menu , it gets the seat number passed down to it
   //and after it runs its queries reports back either positively or 
   //negatively.
   public TrainTicket retrieve(int seat)
   {
     for (int i=0;i<this.ticketCounter;i++)
    	 if (this.store[i]!=null)
    	 if (this.store[i].getSeat()==seat) return this.store[i];
      return null;
   }
   
   // delete() method:
   // take the given seat number and attempt to delete the corresponding
   // TrainTicket. The operation should return whether or not the TrainTicket
   // was deleted (true if so, false if not present.)
   
   //This method is called when a user chooses to delete a booking from the 
   //main menu , it gets the seat number passed down to it
   //and proceeds with the deletion of the ticket.
   public boolean delete(int seat)
   {
	   for (int i=0;i<this.ticketCounter;i++){
		   if (this.store[i]!=null) 
		   if (this.store[i].getSeat()==seat)
		   {
			   this.store[i]=null;
			   return true;
		   }
	   }
      return false;
   }

   // summary() method:
   // Return an array of all TrainTickets in the data structure. This array
   // should be the same size as the number of tickets currently being stored,
   // with no gaps in the array structure itself. (For example, if there are
   // four tickets being stored in the array at the time, this method should
   // return a four-element TrainTicket array.)
   
   //This method is called when a user chooses to view all booking that exist
   //from the main menu queries the array and returns an array that holds  
   //all the tickets ready for printing.
   public TrainTicket[] summary()
   {
	   int[] o=new int[100];
	   int size=0;
	   
      for (int i=0;i<this.ticketCounter;i++)
      {
    	  if (this.store[i]!=null) 
    		  {
    		  o[i]=1;
    		  size++;
    		  }
    	  else o[i]=0;
      }
      
      TrainTicket[] resultant=new TrainTicket[size];
      size=0;
      for (int i=0;i<this.ticketCounter;i++)
      {
    	  if (o[i]==1) 
    	  {
    		  resultant[size]=this.store[i];
    		  size++;
    	  }
    	  
      }
      return resultant;
   }
   
   // size() method:
   // Returns the number of train tickets present in the data structure.
   public int size()
   {
	 int[] o=new int[100]; 
    int size=0;
    for (int i=0;i<this.ticketCounter;i++)
    {
  	  if (this.store[i]!=null) 
  		  {
  		  o[i]=1;
  		  size++;
  		  }
  	  else o[i]=0;
    }
  
      return size;
   }
}
