/***********************************
 * Student Name : Thomas Khalil	   *		
 * ASSIGNMENT 3                    *
 ***********************************/

public class TrainTicket
{
   protected int seatNo;
   protected String name;
   protected int duration;
   
   public static final double LEGPRICE = 9.0;
   
   public TrainTicket(int seatNo, String name, int duration)
   {
      this.seatNo = seatNo;
      this.name = name;
      this.duration = duration;
   }
   
   public int getSeat()
   {
      return this.seatNo;
   }
   
   
   public String getName()
   {
      return this.name;
   }
   

   public int getDuration()
   {
      return this.duration;
   }

   public double getPrice()
   {
      return this.duration * LEGPRICE;
   }
   

   public String toString()
   {
      return seatNo + ":" + name + ":" + duration;
   }
}
