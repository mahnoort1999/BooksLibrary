import java.io.*;
import java.util.*;
import java.text.*;
import java.util.Map.*;

public class Login {
	
	private static final String DELIMITER = "\t";
	
	// User file index (column headers for tab delimited file)
	private static final int NAME = 0;
	private static final int EMAIL = 1;
	private static final int PASS = 2;
	
	static HashMap<String,User> users = new HashMap<>();
	static HashMap<String,Books> books = new HashMap<>();
	
	private static final String ADMIN_USERNAME = "project";
	private static final String ADMIN_PASSWORD = "IS247";
	
	String user, password;
	BookHandler searchingBooks;
	Scanner input = new Scanner(System.in);
	static User loggedin;
	
	Login(){
		
	}
	
	Login(String user, String password){
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void UserLoginInfo() {
		BookHandler searchingBooks = new BookHandler();
		
		System.out.println("------------------------User Login----------------------------");
		System.out.println("Welcome to the User Login page! Please enter your username and password below. \n");
		
		System.out.print("\tUsername: ");
		user = input.nextLine();
		
		System.out.print("\tPassword: ");
		password = input.nextLine();
		
		
		if (user.equals("project") && password.equals("IS247")) {
			System.out.println("\nLogging You in......");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			searchingBooks.transaction();
		}
		else {
			do {
				System.out.println("\nIncorrect. Please try again.");
				System.out.print("Username: ");
				user = input.nextLine();
				
				System.out.print("Password: ");
				password = input.nextLine();
			}
			while(!user.equals("project") || !password.equals("IS247"));
		}
		
		
	}
}
