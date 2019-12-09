import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;


public class MainMenu {
	
	private static final String DELIMITER = "\t";
	
	static ArrayList<User> usersList = new ArrayList();
	static ArrayList<Books> bookList = new ArrayList();
	static MyFileReader fr = new MyFileReader();
	static MyFileWriter fw = new MyFileWriter();
	static BookHandler bh = new BookHandler();
	static UserHandler uh = new UserHandler();
	static User loggedin;
	
	// User file index (column headers for tab delimited file)
	private static final int NAME = 0;
	private static final int USERNAME = 1;
	private static final int PASS = 2;
	
	// Book file index (column headers for tab delimited file)
	private static final int TITLE = 0;
	private static final int AUTHOR = 1;
	private static final int GENRE = 2;
	private static final int AVAILABLE = 3;
	
	static Scanner input = new Scanner(System.in);
	
	private static final String ADMIN_USERNAME = "project";
	private static final String ADMIN_PASSWORD = "IS247";
	static String name = "";

	static String username = "";

	static String password = "";
	
	
	public MainMenu() {
		
	}

	public static void mainMenu() {
		//Login userLogin = new Login();
		UserHandler uh = new UserHandler();
		
		Scanner input = new Scanner(System.in);
				
		main: while (true) {
			System.out.println("----------------------------LIBRARY---------------------------");
			System.out.println("Please Choose One of the Options From Below:");
			System.out.println("\t1) User Login");
			System.out.println("\t2) Create an Account");
			System.out.println("\t3) Admin Portal");
			System.out.println("\t4) Quit");
			
			int option = input.nextInt();
			input.nextLine();
			
			switch(option) {
			case 1:
				uh.searchByUsername();
				break main;
			case 2:
				menu_userreg();
				break;
			case 3:
				admin_login();
				break main;
			case 4:
				System.out.println("Goodbye!");
				break main;
			default:
				System.out.println("Invalid Input. Please try again.\n");
			}
		}
	}
	
	public static void addUser(String name, String username, String pass) {
		usersList.add(new User(name, username, pass));
	}
	
	public static void addBook(String title, String author, String genre, String copies) {
		bookList.add(new Books(title, author, genre, copies));
	}
	
	
	public static void menu_userreg() {
		Scanner input = new Scanner(System.in);
		
		registration: while (true) {
			
			System.out.println("------------Account Registration-------------");
			
			System.out.print("Enter your first and last name (0 to cancel): ");
			name = input.nextLine();
			
			if (name.equals("0"))
				break registration;
			
			while (name.matches(".*\\d+.*") || name.contains("\t")) {
				System.out.println("Name cannot contain numbers or tabs!");
			
				System.out.print("Enter your first and last name (0 to cancel): ");
				name = input.nextLine();
				
				if (name.equals("0"))
					break registration;
			}
			
			System.out.print("Enter a username (0 to cancel): ");
			username = input.nextLine();
			
			if (username.equals("0"))
				break registration;
			
			while (username.contains("\t") || username.contains("@") || username.contains(" ")) {
				System.out.println("Username cannot contain tabs, @ or spaces!");
			
				System.out.print("Enter your username (0 to cancel): ");
				username = input.nextLine();
				
				if (username.equals("0"))
					break registration;
			}
			
			System.out.print("Enter a password (0 to cancel): ");
			password = input.nextLine();
			
			if (password.equals("0"))
				break registration;
			
			while (password.contains("\t")) {
				System.out.println("Password cannot contain tabs!");
			
				System.out.print("Enter a password (0 to cancel): ");
				password = input.nextLine();
				
				if (password.equals("0"))
					break registration;
			}
			
			User accountReg = new User(name, username, password);
			uh.addUsers(accountReg);
			uh.addUserstoUsersFile(accountReg);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("\nAccount created!\n");
			break registration;
		}
	}
	
	
	public static void admin_login() {
		System.out.println("------------------------Admin Login----------------------------");
		System.out.println("Welcome to the Admin Login page! Please enter the username and password below. \n");
		
		System.out.print("\tUsername: ");
		String username = input.nextLine();
		
		System.out.print("\tPassword: ");
		String password = input.nextLine();
	
		if(username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
			menu_admin_panel();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("\nIncorrect Admin Username and/or Password!");
			admin_login();
		}
	}
	
	
	public static void menu_admin_panel() {
		Scanner input = new Scanner(System.in);
		
		admin: while(true) {
			System.out.println("\n------------------------Library ADMIN-------------------------");
			System.out.println(
					"Select an menu option: \n" +
					"\t1.) View users \n" +
					"\t2.) Add book \n" +
					"\t3.) Remove book \n" +
					"\t4.) Edit book \n" +
					"\t5.) View books \n" +
					"\t6.) Logout\n"
					);
			
			int option = input.nextInt();
			input.nextLine();
			
			switch (option) {
			case 1:
				uh.displayUsersFromFile();
				System.out.println("\nWould you like to add, remove, or modify a user? (Yes/No)");
				String answer = input.nextLine();
				if (answer.equalsIgnoreCase("Yes")) {
					chooseUserAction();
				}
				else {
					bh.goodbye();
				}
				break admin;
			case 2:
				menu_bookadd();
				break admin;
			case 3:
				menu_bookremove();
				break admin;
			case 4:
				bookOverview();
				break admin;
			case 5:
				bh.displayBookssFromFile();
				break admin;
			case 6:
				loggedin = null;
				System.out.println("Logging you out...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mainMenu();
				break admin;
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}
		
	public static void menu_userpanel() {
		Scanner input = new Scanner(System.in);
		
		user: while(true) {
			System.out.println("---------------Library --------------");
			System.out.println(
					"Select an menu option: \n" +
					"\t1.) Library Search \n" +
					"\t2.) Books Checked Out\n" +
					"\t3.) Check In Books\n" +
					"\t4.) User Settings\n" +
					"\t4.) Logout\n"
					);
			
			int option = input.nextInt();
			input.nextLine();
			
			switch (option) {
			case 1:
				bh.transaction();
				break;
			case 2:
				checkedOutOverview();
				break;
			case 3:
				checkInBook();
				break;
			case 4:
				userSettings(username, password);
				break;
			case 5:
				loggedin = null;
				break user;
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}
	
	public static void menu_bookadd() {
		System.out.print("Please enter title: ");
		String bookTitle = input.nextLine();
		System.out.print("Please enter author: ");
		String bookAuthor = input.nextLine();
		System.out.print("Please enter genre: ");
		String bookGenre = input.nextLine();
		System.out.print("Please enter number of copies: ");
		String bookCopies = input.nextLine();
		
		System.out.println("\n");
		
		Books bookObject = new Books(bookTitle, bookAuthor, bookGenre, bookCopies);
	
		bh.addBookstoBooksFile(bookObject);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Successfully added " + bookTitle + " to Library!\n");
		
	}
	
	public static void menu_bookremove() {
		System.out.println("Which book would you like to remove?");
		String answer = input.nextLine();
		bh.removeBooksByTitle(answer);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully removed " + answer + " from Library!\n");
		
	}
	
	
	public static void chooseUserAction() {
		System.out.println("\t1.) Add User"
						+ "\n\t2.) Remove User"
						+ "\n\t3.) Modify User");
		int option = input.nextInt();
		input.nextLine();
		
		actions: switch (option) {
		case 1:
			addUser();
			break actions;
		case 2:
			removeUser();
			break actions;
		case 3:
			modifyUser();
			break actions;
		default:
			System.out.println("Invalid option");
			break;
		}
	}
	
	
	public static void addUser() {
		System.out.print("\n\tPleae enter the user's name: ");
		String name = input.nextLine();
		System.out.print("\tPlease enter a username: ");
		String username = input.nextLine();
		System.out.print("\tPlease enter a password: ");
		String pw = input.nextLine();
		
		User newuser = new User(name, username, pw);
		uh.addUserstoUsersFile(newuser);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nAdded " + username + " to library!");
	}
	
	
	public static void removeUser() {
		System.out.print("Please enter the username of the user you would like to remove: ");
		String usertoRemove = input.nextLine();
		uh.removeUserByUsername(usertoRemove);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nRemoved " + usertoRemove + " from library!");
		
	}
	
	public static void modifyUser() {		
		usersList = fr.readUserFile("Users.csv");
		
		System.out.print("Enter user ID to search and modify : ");
		String userId = input .next();
		boolean userFound = false;
		int indexUser = -1;
		System.out.println("Search Results:");
		for (int i = 0; i < usersList.size(); i++) 
		{
			if(usersList.get(i).getUser().contains(userId))
			{
				System.out.println("\tName: " + usersList.get(i).getName() + "\n\tUser: " + usersList.get(i).getUser()
									+ "\n\tPassword: " + usersList.get(i).getPassword());
				userFound = true;
				indexUser = i;
			}
		}
		if(!userFound)
			System.out.println("No Record Found.");
		else
		{
			System.out.print("\nEnter New Name : ");
			input.nextLine();
			String newName = input.nextLine();
			//input.nextLine();
			System.out.print("Enter New Username: ");
			String newUser = input.nextLine();
			System.out.print("Enter New Password : ");
			String newPW = input.nextLine();
			usersList.get(indexUser).setName(newName);
			usersList.get(indexUser).setUser(newUser);
			usersList.get(indexUser).setPassword(newPW);
			
			System.out.println();
			fw.writeListToUserFile(usersList);
			uh.displayUsersFromFile();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nUpdated Successfully!");
		}
		

	}
	
	
	public static void checkedOutOverview() {
			if(UserHandler.getbook1() == null) {
				System.out.println("You don't have any books checked out!");
			}else {
				System.out.println("Here are the books you currently have checked out: ");
				System.out.println("\nBook 1: " + UserHandler.getbook1());
				if(UserHandler.getbook2()!=null) {
					System.out.println("\nBook 2: " + UserHandler.getbook2());
				}
				if(UserHandler.getbook3()!=null) {
					System.out.println("\nBook 3: " + UserHandler.getbook3());
				}
			}
	}
	
	
	
	public static void bookOverview() {
		bh.displayBookssFromFile();
		
		bookList = fr.readCsvFile("LibraryBooks.csv");
		System.out.print("\n\nEnter Book Title to search and modify : ");
		String bookTitle = input.nextLine();
		boolean bookFound = false;
		int indexBook = -1;
		System.out.println("Search Results:");
		for (int i = 0; i < bookList.size(); i++) 
		{
			if(bookList.get(i).getTitle().equalsIgnoreCase(bookTitle))
			{
				System.out.println("\tTitle: " + bookList.get(i).getTitle() + "\n\tAuthor: " + bookList.get(i).getAuthor()
									+ "\n\tGenre: " + bookList.get(i).getGenre());
				bookFound = true;
				indexBook = i;
			}
		}
		if(!bookFound)
			System.out.println("No Record Found.");
		else
		{
			System.out.print("\nEnter New Title : ");
			//input.nextLine();
			String newTitle = input.nextLine();
			//input.nextLine();
			System.out.print("Enter New Author: ");
			String newAuthor = input.nextLine();
			System.out.print("Enter New Genre : ");
			String newGenre = input.nextLine();
			
			bookList.get(indexBook).setTitle(newTitle);
			bookList.get(indexBook).setAuthor(newAuthor);
			bookList.get(indexBook).setGenre(newGenre);
			
			System.out.println();
			fw.writeListToFile(bookList);
			bh.displayBookssFromFile();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nUpdated Successfully!");
		}
		
	}
	
	public static void checkInBook() {
		bh.displayBookssFromFile();
		System.out.println("\nPlease Enter the Book Name you would like to check in!\n");
		String bookTitle2 = input.nextLine();
		boolean found = false;
		 String answer;
		 bookList = fr.readCsvFile("LibraryBooks.csv");
		for(int i = 0;i < bookList.size(); i++) {
			if(bookList.get(i).getTitle().equals(bookTitle2) && bookList.get(i).getCopies().equals("0")){
				found = true;
				
				boolean check = UserHandler.addBook(bookList.get(i).getTitle());
				if(check) {
					bookList.get(i).setCopies("1");
					System.out.println("The book was sucsessfully returned!");
					fw.writeListToFile(bookList);
				}
				break;
			}
		}
		if(!found) {
			System.out.println("That book is already in the Library!");
			bh.goodbye();
		}
	}
	
	
	public static void userSettings(String username2, String password) {
		usersList = fr.readUserFile("Users.csv");
		boolean userFound = false;
		int indexUser = -1;

		System.out.println("Search Results:");
		for (int i = 0; i < usersList.size(); i++) 
		{
			if(usersList.get(i).getUser().contains(username2))
			{
				System.out.println("\tName: " + usersList.get(i).getName() + "\n\tUser: " + usersList.get(i).getUser()
									+ "\n\tPassword: " + usersList.get(i).getPassword() + "\n");
				userFound = true;
				indexUser = i;
			}
		}
		if(!userFound)
			System.out.println("No Record Found.");
		else
		{
			System.out.println("Would you like to change:\n"+
								"\t1. Change Name\n" +
								"\t2. Change Username\n" +
								"\t3. Change Password");
			int option = input.nextInt();
			input.nextLine();
			actions: switch (option) {
			case 1:
				System.out.print("\nEnter New Name : ");
				input.nextLine();
				String newName = input.nextLine();
				usersList.get(indexUser).setName(newName);
				break actions;
			case 2:
				System.out.print("Enter New Username: ");
				String newUser = input.nextLine();
				usersList.get(indexUser).setUser(newUser);
				break actions;
			case 3:
				System.out.print("Enter New Password : ");
				String newPW = input.nextLine();
				usersList.get(indexUser).setPassword(newPW);
				break actions;
			default:
				System.out.println("Invalid option");
				break;
			}

			System.out.println();
			fw.writeListToUserFile(usersList);
			uh.displayUsersFromFile();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nUpdated Successfully!\n");
		}
	}
}
