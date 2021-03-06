package summative;


import java.io.IOException;

public class summative {
	
		public static void main(String[] args) {
			Seat_Info[][] plane = new Seat_Info[7][11]; 
			fill(plane); //this method fills all nulls so the array can be accessed
			
			//using for creating txt documents
			String filePath[] = new String[8];
			 //the reason the array is off by one is so the loop can 
			// make all the txt files without an error
			 filePath[0] = "src//plane1.txt";
			 filePath[1] = "src//plane2.txt";
			 filePath[2] = "src//plane3.txt";
			 filePath[3] = "src//plane4.txt";							
			 filePath[4] = "src//plane5.txt";							
			 filePath[5] = "src//plane6.txt";
			 filePath[6] = "src//plane7.txt";
			 filePath[7] = "src//manifest.txt";
			 //This method a fills the array with any previous data in the txt document
			 //readTxt(plane, filePath);
		
			 String input;
			 //beginning of program
			 System.out.println("Welcome to the Fly-by-Night bookings software");
		do{
			System.out.println("1.Are you looking to BOOK 2.CANCEL a RESERVATION 3.CANCEL a FLIGHT 4.GET seat INFO or 5.PRINT MANIFEST");
			System.out.println("Enter end to end the program");
			input = In.getString();
			input.toLowerCase();	//set it to lower case to prevent capital errors
			//book a seat
			if(input.equals("book") || input.equals("1"))
			{
				booking(plane, filePath);
			}
			//cancel a seat
			if(input.equals("cancel reservation") || input.equals("2"))
			{
				cancel(plane, filePath);
			}
			//cancel a flight
			if(input.equals("cancel flight") || input.equals("3"))
			{
				cancelFlight(plane, filePath);
			}
		
			//get seat info
		    if(input.equals("get info") || input.equals("4"))			
			{
				getInfo(plane);
			}
			
			//print manifest(sort and print)
			if(input.equals("print manifest") || input.equals("5"))
			{
				printManifest(plane, filePath);
			}
			
		}while(!input.equals("end"));	//end when end is entered
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
				System.out.printf("%n");
				System.out.println("this plane doesn't exist");
				System.out.printf("%n");
			}
			if(num <= 7)
			{
				System.out.println("The seat(s): ");
				for(int i = 1; i <= 10; i++)
				{
					if(plane[num][i].full == false)
					{
					System.out.print(i);
					System.out.print(" is avalible");
					System.out.printf("%n");
					}
					if(plane[num][i].full == true)
					{
					System.out.print(i);
					System.out.print(" is not avalible");
					System.out.printf("%n");
					}
				}
			}
		}while(num > 7 || num == 0); //do while statement to catch any errors		
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
			System.out.printf("%n");
			System.out.print("Name: ");
			plane[num][num2].name = In.getString();
			System.out.printf("%n");
			System.out.print("Address: ");
			plane[num][num2].address = In.getString();
			System.out.printf("%n");
			System.out.print("Age: ");
			plane[num][num2].age = In.getInt();
			System.out.printf("%n");
			System.out.print("E-mail: ");
			plane[num][num2].email = In.getString();
			System.out.printf("%n");
			System.out.print("Phone #: ");
			plane[num][num2].phoneNumber = In.getString();
			//create a txt file to print
			saveFile(plane, num, num2, filePath);
			System.out.println("The seat is now booked");
			}
		}while(plane[num][num2].full == true || num2 != 0);	//made to end the method "booking"
	}//end of if statement
	}//end of booking
		
		public static void fill(Seat_Info[][] plane)
		{
			for(int i = 0; i <= 6; i++)
			{
				for(int j = 0; j <= 10; j++)
				{
					plane[i][j] = new Seat_Info("", 0, "", "", "", false);
				}
			}
		}
		
		public static void cancel(Seat_Info[][] plane, String[] filePath)
		{
			//for security purposes ask for all info before canceling a flight
			System.out.println("Please enter customer name, address, age, phone number, and e-mail");
			System.out.print("Name: ");
			String name = In.getString();
			System.out.printf("%n");
			System.out.print("Address: ");
			String address = In.getString();
			System.out.printf("%n");
			System.out.print("Age: ");
			int age = In.getInt();
			System.out.printf("%n");
			System.out.print("Phone Number: ");
			String phone = In.getString();
			System.out.printf("%n");
			System.out.print("Email: ");
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
										System.out.println("This seat has been cancelled");
										saveFile(plane, a, b, filePath);
									}	
								}
							}
						}	
					}
				}
			}
		}
		
		public static void cancelFlight(Seat_Info[][] plane, String[] filePath)
		{
			//ask which plane should be canceled
			System.out.println("Please enter the flight you would like to cancel");
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
					System.out.printf("%n");
				}
			}
			//cancel the flight
			for(int i = 0; i < 10; i++)
			{
				wipe(plane, num, i);
				saveFile(plane, num, i, filePath);
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

		public static void printManifest(Seat_Info[][] plane, String[] filePath)		//have it print to the txt document 
		{																			
			//ask which sort they want
			System.out.println("Would you like to print numerically or alphabetically?");
			String input = In.getString();
			//sort numerically
			if(input.equals("numerically"))
			{
				System.out.println("Which flight would you like to print?");
				int num = In.getInt();
				
					IO.openOutputFile(filePath[7]);	// filepath[7] is the filpath to a specific document		
					
					for(int i = 1; i <= 10; i++)
					{
						if(plane[num][i].full == true)
						{
						IO.println("Seat: " + i);
						IO.println("Name: " + plane[num][i].name);			
						IO.println("Age: " + plane[num][i].age);			
						IO.println("Address: " + plane[num][i].address);			
						IO.println("Phone #: " +plane[num][i].phoneNumber);			
						IO.println("Email: " + plane[num][i].email);
						IO.println("");
						}
					}
					IO.closeOutputFile();
				
			}
			//sort alphabetically
			if(input.equals("alphabetically"))
			{
				System.out.println("Which flight would you like to print?");
				int num = In.getInt();
			
			//sort the array alphabetically
				for (int top = 1; top <= 10; top++)
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
				//print the plane info
					IO.openOutputFile(filePath[7]);			
					
					for(int i = 1; i <= 10; i++)
					{
						if(plane[num][i].full == true)
						{
						IO.println("Seat: " + i);
						IO.println("Name: " + plane[num][i].name);			
						IO.println("Age: " + plane[num][i].age);			
						IO.println("Address: " + plane[num][i].address);			
						IO.println("Phone #: " +plane[num][i].phoneNumber);			
						IO.println("Email: " + plane[num][i].email);
						IO.println("");
						}
					}
					IO.closeOutputFile();
			}	
		}//end of print manifest
		
		public static void wipe(Seat_Info[][] a , int b, int c)
		{
			//easy way to delete data
			a[b][c].name = "";
			a[b][c].age = 0;
			a[b][c].address = "";
			a[b][c].phoneNumber = "";
			a[b][c].email = "";		
			a[b][c].full = false;
		
		}	

		public static void saveFile(Seat_Info[][] plane, int planeNum, int seatNum, String[] filePath)
		{
			
			IO.openOutputFile(filePath[(planeNum-1)]);	// the reason it is subtracted by one is to solve a problem at the beginning		
			
			for(int i = 1; i <= 10; i++)
			{
				if(plane[planeNum][i].full == true)
				{
				IO.println("Seat: " + i);
				IO.println("Name: " + plane[planeNum][i].name);			
				IO.println("Age: " + plane[planeNum][i].age);			
				IO.println("Address: " + plane[planeNum][i].address);			
				IO.println("Phone #: " +plane[planeNum][i].phoneNumber);			
				IO.println("Email: " + plane[planeNum][i].email);
				IO.println("");
				}
			}
			IO.closeOutputFile();
		}

		public static void deleteFile(Seat_Info[][] plane, int a, int b, String[] filePath)
		{
			IO.createOutputFile(filePath[(a-1)]);		// the reason it is subtracted by one is to 		
														// make it function with the pathFile array (Look at the beginning for context)
			IO.println("Seat: " + b);
			IO.println("Name: " + plane[a][b].name);		
			IO.println("Age: " + plane[a][b].age);			
			IO.println("Address: " + plane[a][b].address);			
			IO.println("Phone #: " + plane[a][b].phoneNumber);			
			IO.println("Email: " + plane[a][b]. email);		
		
			IO.closeOutputFile();
			
			;
		}

		public static void readTxt(Seat_Info[][] plane, String[] filePath)
		{
			//this method is to read the txt documents before the program starts and try to store the data from them to the program
			
			for(int i = 0; i <= 6; i++)//for loop for plane number
			{
				IO.openOutputFile(filePath[i]);
				String hold = "ok";
			 do{
					for(int k = 1; k <= 10; k++)// for loop for the amount of potential seats on a plane needed to be read
					{
						for(int j = 1; j <= 6; j++)//for loop for the amount of times needed to read all info from one seat
						{
							try {
								String n = IO.readLine();
								hold = n;
									if(!n.equals(""))
									{
										checkRead(plane, j, i,  n);
									}	
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
								}
						}
					}
				}while(!hold.equals(""));
			IO.closeOutputFile();
			}
		}

		public static void checkRead(Seat_Info[][] plane, int a, int i, String n)
		{
			//This method works with readTxt to try and convert the info fromt he txt document to the program
			int seatNum = 1;
			if(a == 1)
			{
				String temp = n.substring(6);
				seatNum = Integer.parseInt(temp);
			}
		
			if(a == 2)
			{
				String temp = n.substring(6);
				plane[i][seatNum].name = temp;
			}
			
			if(a == 3)
			{
				String temp = n.substring(5);
				int age = Integer.parseInt(temp);
				plane[i][seatNum].age = age;
			}
		
			if(a == 4)
			{
				String temp = n.substring(9);
				plane[i][seatNum].address = temp;
			}
			
			if(a == 5)
			{
				String temp = n.substring(9);
				plane[i][seatNum].phoneNumber = temp;
			}
			
			if(a == 6)
			{
				String temp = n.substring(7);
				plane[i][seatNum].email = temp;
				plane[i][seatNum].full = true;
			}
			
			
			
		}
}//end of class

