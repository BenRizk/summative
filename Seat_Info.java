package summative;

public class Seat_Info {
	String name;
	int age;
	String address;
	String phoneNumber;
	String email;
	boolean full = false;
	public Seat_Info(String a, int b, String c, String d, String e, boolean f)
	{
		name = a;
		age = b;
		address = c;
		phoneNumber = d;
		email = e;		
		full = f;
	}
	
	public void wipe(Seat_Info[][] a , int b, int c)
	{
		
		a[b][c].name = "";
		a[b][c].age = 0;
		a[b][c].address = "";
		a[b][c].phoneNumber = "";
		a[b][c].email = "";		
		a[b][c].full = false;
	
	}
	
}
