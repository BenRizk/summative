package summative;

import java.io.IOException;

public class summative {
	
		public static void main(String[] args) {
			Seat_Info[][] plane = new Seat_Info[7][11]; 
			fill(plane);
			//using for creating txt documents
			String filePath[] = new String[7];
			 filePath[0] = "scr//plane1.txt";
			 filePath[1] = "scr//plane2.txt";
			 filePath[2] = "scr//plane3.txt";
			 filePath[3] = "scr//plane4.txt";							//think about potential of a class that can create planes each 
			 filePath[4] = "scr//plane5.txt";							// with their own info and txt document
			 filePath[5] = "scr//plane6.txt";
			 filePath[6] = "scr//plane7.txt";
			 String input;
			 //beginning of program
			 System.out.println("Welcome to the Fly-by-Night bookings software");
		do{
			System.out.println("Are you looking to book, cancel a reservation, cancel a flight, or get seat information");
			input = In.getString();
			
			if(input.equals("book"))
			{
				booking(plane, filePath);
			}
			
			if(input.equals("cancel reservation"))
			{
				cancel(plane);
			}
			
			if(input.equals("cancel flight"))
			{
				cancelFlight(plane);
			}
		
			/*
			if(input.equals("get info"));			//Problem: Goes to this method when input is wrong
			{
				getInfo(plane);
			}
			*/
			
			if(input.equals("print manifest"))
			{
				printManifest(plane);
			}
		
		}while(!input.equals("end"));
	System.out.println("Program ended");		
	}

		public static void booking(Seat_Info[][] plane, String[] filePath)
		{
			
			int num;
		do{
			//ask for booking
			System.out.println("Which flight would you like to book");
			num = In.getInt();
			//show which seats are available
			if(num > 7)
			{
				System.out.println();
				System.out.println("this plane doesn't exist");
				System.out.println();
			}
			if(num <= 7)
			{
				System.out.println("The seat(s): ");
				for(int i = 1; i <= 9; i++)
				{
					if(plane[num][i].full == false)
					{
					System.out.print(i);
					System.out.print(" is avalible");
					System.out.println();
					}
					if(plane[num][i].full == true)
					{
					System.out.print(i);
					System.out.print(" is not avalible");
					System.out.println();
					}
				}
			}
		}while(num > 7 || num == 0);		
			int num2;
		if(num <= 7 && num != 0)
		{
		do{															
			//ask which seat is being booked
			System.out.println("Which seat would you like to book(Enter 0 to return to main menu)");
			num2 = In.getInt();	
			//seat is taken
			if(plane[num][num2].full == true && num2 != 0)
			{
			System.out.println("This seat is taken");	
			}
			
			//if seat is open ask for information
			if(plane[num][num2].full == false && num2 != 0)
			{
			plane[num][num2].full = true;
			System.out.println("Please enter the required information");
			System.out.println();
			System.out.print("name: ");
			plane[num][num2].name = In.getString();
			System.out.println();
			System.out.print("address: ");
			plane[num][num2].address = In.getString();
			System.out.println();
			System.out.print("age: ");
			plane[num][num2].age = In.getInt();
			System.out.println();
			System.out.print("e-mail: ");
			plane[num][num2].email = In.getString();
			System.out.println();
			System.out.print("phone #: ");
			plane[num][num2].phoneNumber = In.getInt();
			//create a txt file to print
			//saveFile(plane, num, num2, filePath, plane[num][num2].name, plane[num][num2].age, plane[num][num2].address, plane[num][num2].phoneNumber, plane[num][num2].email );
			System.out.println("The seat is now booked");
			}
		}while(plane[num][num2].full == true || num2 != 0);	
	}//end of if statement
	}//end of booking
		
		public static void fill(Seat_Info[][] plane)
		{
			for(int i = 0; i <= 6; i++)
			{
				for(int j = 0; j <= 9; j++)
				{
					plane[i][j] = new Seat_Info("", 0, "", 0, "", false);
				}
			}
		}
		
		public static void cancel(Seat_Info[][] plane)
		{
			//for security purposes ask for all info before canceling a flight
			System.out.println("Please enter customer name, address, age, phone number, and e-mail");
			String name = In.getString();
			String address = In.getString();
			int age = In.getInt();
			int phone = In.getInt();
			String email = In.getString();
			
			for(int a = 0; a < plane.length; a++)
			{
				for(int b = 0; b < plane.length; b++)
				{
					if(name.equals(plane[a][b].name))
					{
						if(address.equals(plane[a][b].address))
						{
							if(age == plane[a][b].age)
							{
								if(phone == plane[a][b].phoneNumber)
								{
									if(email.equals(plane[a][b].email))
									{
										wipe(plane, a, b);
									}	
								}
							}
						}	
					}
				}
			}
		
		}
		
		public static void cancelFlight(Seat_Info[][] plane)
		{
			//ask which plane should be canceled
			System.out.println("Please enter the flight that you would like to cancel");
			int num = In.getInt();
			//get contact info
			for(int a = 1; a < 10; a++)
			{
				if(plane[num][a].full == true)
				{
					System.out.println("Name: " + plane[num][a].name);
					System.out.println("Address: " + plane[num][a].address);
					System.out.println("Age: " + plane[num][a].age);
					System.out.println("phone number: " + plane[num][a].phoneNumber);
					System.out.println("email: " + plane[num][a].email);
					System.out.println();
				}
			}
			//cancel the flight
			for(int i = 0; i < 10; i++)
			{
				wipe(plane, num, i);
			}
		}
		
		public static void getInfo(Seat_Info[][] plane)
		{
			//ask which seat you want info on
			System.out.println("Which seat would you like the information to?");
			System.out.print("plane: ");
			int num = In.getInt();
			System.out.println("seat: ");
			int num2 = In.getInt();
			//check if seat is full and get info if it is full
			if(plane[num][num2].full == true)
			{
			System.out.println("name: " + plane[num][num2].name);
			System.out.println("age: " + plane[num][num2].age);
			System.out.println("address: " + plane[num][num2].address);
			System.out.println("phone number: " + plane[num][num2].phoneNumber);
			System.out.println("email: " + plane[num][num2].email);
			}
			//if not full tell the user
			if(plane[num][num2].full == false)
			{
				System.out.println("This seat is not reserved");
			}
		}

		public static void printManifest(Seat_Info[][] plane)
		{
			//ask which sort they want
			System.out.println("Would you like to print numerically or alphabetically?");
			String input = In.getString();
			//sort numerically
			if(input.equals("numerically"))
			{
				System.out.println("Which flight would you like to print?");
				int num = In.getInt();
				for(int i = 0; i < 10; i++)
				{
					System.out.println("name: " + plane[num][i].name);
					System.out.println("age: " + plane[num][i].age);
					System.out.println("address: " + plane[num][i].address);
					System.out.println("phone number: " + plane[num][i].phoneNumber);
					System.out.println("email: " + plane[num][i].email);;
				}
			}
			//sort alphabetically
			if(input.equals("alphabetically"))
			{
				System.out.println("Which flight would you like to print?");
				int num = In.getInt();
			
			//sort the array alphabetically
				for (int top = 1; top < plane.length; top++)
				{
				Seat_Info item = plane[num][top]; // temporary storage of item
				int i = top;
					while (i > 0 && item.name.charAt(0) > plane[num][i-1].name.charAt(0))//change item < plane[i-1] to item > plane[i-1]
						{
						// shift larger items to the right by one
						plane[num][i] = plane[num][i-1];
						// prepare to check the next item to the left
						i--;
						}
					plane[num][i] = item; // put sorted item in open location
				}
			}
		
			
			
			
		}//end of print manifest
		
		public static void wipe(Seat_Info[][] a , int b, int c)
		{
			//easy way to delete data
			a[b][c].name = "";
			a[b][c].age = 0;
			a[b][c].address = "";
			a[b][c].phoneNumber = 0;
			a[b][c].email = "";		
			a[b][c].full = false;
		
		}	

		public static void saveFile(Seat_Info[][] plane, int planeNum, int seatNum, String[] filePath, String name, int age, String address, int phoneNumber, String email)
		{
			IO.createOutputFile(filePath[planeNum]);
			IO.println("Seat: " + seatNum);
			IO.println("Name; " + name);		
			IO.println("Age: " + age);			
			IO.println("Address: " + address);			
			IO.println("Phone #: " + phoneNumber);			
			IO.println("Email: " + email);		
		
			IO.closeOutputFile();
			
			IO.openInputFile(filePath[planeNum]);
			
			try {
				String n = IO.readLine();
				System.out.println(n);
				IO.closeInputFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}

}//end of class


