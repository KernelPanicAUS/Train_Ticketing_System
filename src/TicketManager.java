/***********************************
 * Student Name : Thomas Khalil	   *		
 * ASSIGNMENT 3                    *
 ***********************************/

// main TicketManager (driver) class

// imports 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;


class TicketManager
{
	private static TicketArray tickets=new TicketArray();
	
	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) throws IOException
	{
		
		File F=new File("tickets.txt");
		if (F.exists())
		{ 
			Scanner IN=new Scanner(new File("tickets.txt"));
			int lines=0;
			while (IN.hasNextLine()&&lines<100)
			{

				String s=IN.nextLine();
				lines++;
				Pattern p = Pattern.compile("[:]+");
				String[] h=p.split(s);
				//checking the ticket type
				if (h.length==3) 
					tickets.add(new TrainTicket(Integer.parseInt(h[0]),
							h[1],Integer.parseInt(h[2])));
				//FirstClass tickets
				else {
					boolean b;
					if (h[3].charAt(0)=='Y') b=true;
					else b=false;
					tickets.add(new FirstClassTicket(Integer.parseInt(h[0])
							,h[1],Integer.parseInt(h[2]),b));
				}
			}

		}
		int menuOption;

		do
		{
			menuOption = mainMenu();

			switch (menuOption)
			{
			case 1:
				menuAdd();
				break;

			case 2:
				menuRetrieve();
				break;

			case 3:
				menuDelete();
				break;

			case 4:
				menuSummary();
				break;

			case 5:
				menuQuit();
				break;

			default:
				System.out.println("Invalid option, try again.");
			}
		}
		while (menuOption != 5);
	}

	// -------------------------------------------------------------
	// main menu helper methods
	// -------------------------------------------------------------

	// mainMenu(): print the main menu and get input
	private static int mainMenu() throws IOException
	{
		// initialise menu variables
		int result = -1;
		boolean valid = false;

		// repeat until valid input is given
		while (!valid)
		{
			// display menu
			System.out.println();
			System.out.println("TRANSRAIL Ticket System");
			System.out.println("=======================");
			System.out.println("1. Add a ticket");
			System.out.println("2. Retrieve a ticket");
			System.out.println("3. Delete a ticket");
			System.out.println("4. Print ticket summary");
			System.out.println("5. Save & Quit");
			System.out.println();
			System.out.print("Choose an option: ");

			// read an integer, and ensure it is valid and within range
			try
			{
				result = stdin.nextInt();
				if ((result < 1) || (result > 5))
					System.out.println("Invalid option. Try again.");
				else
					valid = true;
			}
			catch (NumberFormatException e)
			{
				System.out.println("Invalid option. Try again.");
			}
		}

		return result;
	}

	// menuAdd(): control add portion of menu system
	//This method is called when the user chooses to generate a ticket
	//Based on various inputs , the subroutine determines what type of 
	//ticket needs to be generated , and proceeds to create it.
	private static void menuAdd() throws IOException
	{
		stdin.nextLine(); 
		System.out.println("Please answer the following prompts for your " +
				"ticket to be issued");
		System.out.println();
		
		System.out.print("Enter a seat number : ");
		String seatNum=stdin.nextLine();
		
		System.out.print("Passenger Name: ");
		String name=stdin.nextLine();
		
		System.out.print("Journey duration: ");
		String duration=stdin.nextLine();
		
		System.out.println("Would you like to consider a First class upgrade " +
				"for an additional fee of $20 per segment ? ");
		System.out.println("Please Enter (Y/N): ");
		if (stdin.nextLine().charAt(0)=='N')
		{
			if (tickets.add(new TrainTicket(Integer.parseInt(seatNum),name,
					Integer.parseInt(duration))))
				System.out.println("Ticket number "+seatNum+
						"has been successfully booked");
			
			else 
				System.out.println("An error has occurred. Try another seat");
		}
		else 
		{
			boolean waiter=false;
			System.out.println("Would you like to consider a waiter service" +
					" for a flat $15 fee?");   
			System.out.print("Please enter Y/N ");
			
			if (stdin.nextLine().charAt(0)=='Y')
			
				waiter=true;
			
			
			if (tickets.add(new FirstClassTicket(Integer.parseInt(seatNum),
					name,Integer.parseInt(duration),waiter)))
			
				System.out.println("Ticket number "+seatNum+
						" has been successfully booked");
			
			else 
				System.out.println("An error has occurred. Try another seat");
			
								
		}
	}


	// menuRetrieve(): control retrieve portion of menu system
	//This subroutine is responsible for pulling out a particular booking
	// by querying the user for the ticket number he/she desires to view.
	private static void menuRetrieve() throws IOException
	{
		TrainTicket p;
		stdin.nextLine();
		System.out.print("To retrieve a ticket please enter a seat number: ");
		String holder=stdin.nextLine();
		
		//Printing the header
		System.out.println("Seat\t\tName\tDuration\tPrice\t\tExtras");
		System.out.println("----\t\t----\t--------\t-----\t\t------");
		//checking the result 
		if ((p=tickets.retrieve(Integer.parseInt(holder)))!=null)
		{
			System.out.format("%4s\t%13s\t\t%s\t%s\t\t", p.seatNo,p.name,
					p.duration,p.getPrice());
			if (p instanceof FirstClassTicket)
			{
				System.out.print("\tFirstClass");
				if (p.getPrice() > (29*p.duration) ){
					System.out.print("+Waiter");
				}
			}
		}
		else System.out.println("Not found!");
		System.out.println();
	}

	// menuDelete(): control delete portion of menu system
	// A rather self explanatory method that is called when a ticket 
	// is to be refunded and removed from the ticket memory structure.
	private static void menuDelete() throws IOException
	{
		TrainTicket p;
		stdin.nextLine();
		
		System.out.print("Retrieving a ticket\nEnter a seat number: ");
		
		String h=stdin.nextLine();
		
		if (tickets.delete(Integer.parseInt(h))){
			System.out.println("Deleted successfuly");
			}
		
		else {
			System.out.println("En error occured. Ticket was not deleted");
		}
	}

	// menuSummary(): print summary of data
	//When this method is called , basically all ticket objects that exist 
	//in the array are called and printed in a grid , it's aim is to show
	//all booking that have been loaded on startup and that have been
	//created after startup.
	
	private static void menuSummary() throws IOException
	{
		System.out.println("Seat\t\tName\tDuration\tPrice\t\tExtras");
		System.out.println("----\t\t----\t--------\t-----\t\t------");
		TrainTicket[] b=tickets.summary();
		for (int i=0;i<b.length;i++)
		{
			
			System.out.format("%4s\t%13s\t\t%s\t%s\t", b[i].getSeat(),
					b[i].name,b[i].duration,b[i].getPrice());
			if (b[i] instanceof FirstClassTicket)
			{
				
				System.out.print("\tFirstClass");
				if (b[i].getPrice() > (29*b[i].duration) ) 
					System.out.print("+Waiter");
			}
			System.out.println();
		}

	}

	// menuQuit(): control quit portion of menu system
	//This method is responsible for pulling all the ticket objects 
	//from the array and sending them one ticket at a time to a file called 
	//tickets.txt so that when the program restarts , the data structure
	//is able to be restored
	
	private static void menuQuit() throws IOException
	{
		FileWriter outFile = new FileWriter("tickets.txt");
		PrintWriter out = new PrintWriter(outFile);
				
		for (int i=0;i<tickets.summary().length;i++)
		{
			out.println(tickets.summary()[i]);
		}
				
		out.close();
		outFile.close();
		System.exit(0);
	}
}
