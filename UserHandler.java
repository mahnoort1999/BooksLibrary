import java.io.*;
import java.util.*;


public class UserHandler<E> extends MainMenu {
	
	ArrayList<User> usersList = new ArrayList();
	File UsersListFile = new File("Users.csv");
	MyFileWriter fw = new MyFileWriter();
	MyFileReader fr = new MyFileReader();
	Scanner input = new Scanner(System.in);
	String name, username, password;
	BookHandler bh = new BookHandler();
	MainMenu mm = new MainMenu();
	static HashMap<String,User> users = new HashMap<>();
	
	private static String book1;
    private static String book2;
    private static String book3;
    
    UserHandler(){
    	
    }
    public UserHandler(String username, String password){
        this.username = username;
        this.password = password;
    
    }
    
    public UserHandler(String username, String password, String name, String book1, String book2, String book3) {
    	this.username = username;
    	this.password = password;
    	this.name = name;
    	this.book1 = book1;
    	this.book2 = book2;
    	this.book3 = book3;
    }
	
	
	public ArrayList<User> getUsersList() {
		return usersList;
	}

	
	
	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}

	
	
	public ArrayList<User> createNewUsersArrayList() {
		
		ArrayList<User> arrayOfUsers = new ArrayList();		
		arrayOfUsers.add(new User("Mahnoor Tariq", "mtariq2", "password"));
		arrayOfUsers.add(new User("Maaz Amin", "mamin1", "password"));
		arrayOfUsers.add(new User("Miji Mathews", "miji1", "java"));
		
		usersList = arrayOfUsers;
		
		return arrayOfUsers;
	}
	
	
	
	public void createUsersListFile() {
		//Hard code and create BooksList
		if(UsersListFile.exists()) {
			
			System.out.println("The Users.csv file already exists !");
		} else {
			
			System.out.println("The Users.csv file does not exist. Starting to create....!");
			usersList = createNewUsersArrayList();
			fw.writeListToUserFile(usersList);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("The Users.csv file has been created.....!");
			
		}
		
	}
	
	
	public void addUsers(User userObject) {
		usersList.add(userObject);
	}
	
	
	
	public void removeUsers(User userObject) {
		usersList.remove(userObject);
	}
	
	
	
	public void displayUsers() {
		
		Iterator<User> it = usersList.iterator(); 
		// System.out.println("***********START DISPLAY*************\n");
		 
		 while(it.hasNext()) {
			 User users = it.next();
              System.out.println(users.getName() + ", "+ users.getUser());	
		 }
		 
		// System.out.println("\n***********END DISPLAY*************\n\n");
		
	}
	
	
	
	public void displayUsersFromFile() {
		usersList = fr.readUserFile("Users.csv");
		Iterator<User> it = usersList.iterator(); 
		 //System.out.println("***********START DISPLAY*************\n");
		 
		int i = 0;
		 while(it.hasNext()) {
			 i++;
			User users = it.next();
              System.out.println(i + ".) " + users.getName() + ", " + users.getUser());	
		 }
		 
		 //System.out.println("\n***********END DISPLAY*************\n");
		
	}

	
	
	public void addUserstoUsersFile(User userObject) {
		// Before I add a Books I need to do load all the books from the file to the bookList
		usersList = fr.readUserFile("Users.csv");
		System.out.println(" Current UserList size" + usersList.size());
		// Check to see if the Books is a Duplicate and if I just to add a copy of the book	
		//Add a book
		usersList.add(userObject);
		System.out.println(" New UserList size" + usersList.size());
		// Write the ArrayList to the File 
		fw.writeListToUserFile(usersList);
	}

	
	
	public void removeUserByUsername(String username2) {		 
		 String answer;
		 usersList = fr.readUserFile("Users.csv");
		 Iterator<User> it = usersList.iterator(); 
					
			while(it.hasNext()) {
				User users = it.next();
				if(users.getUser().toLowerCase().equals(username2.toLowerCase())) {
					System.out.println();
					System.out.println("Is this the correct user?\n" + "Username: " +users.getUser());
					answer = input.nextLine();
					if (answer.equals("Yes") || answer.equals("yes")) {
						System.out.println("Removing " + username2);
						it.remove();
						fw.writeListToUserFile(usersList);
					}
				}
			}
	}
	
	
	public void goodbye() {
		System.out.println("Thank you for using our library!");
	}
	
	
	
	public void searchByUsername() {
		System.out.println("------------------------User Login----------------------------");
		System.out.println("Welcome to the User Login page! Please enter your username and password below. \n");
		
		System.out.print("\tPlease Enter Username (0 to cancel): ");
		username = input.nextLine();
		
		if (username.equals("0")) {
			System.out.println();
			return;
		}
		
		System.out.print("\tPlease Enter Password: ");
		password = input.nextLine();
		
		boolean foundUser = false;
		usersList = fr.readUserFile("Users.csv");
		Iterator<User> it = usersList.iterator(); 
				
		while(it.hasNext()) {
			User users1 = it.next();
			if(users1.getUser().equals(username) && users1.getPassword().equals(password)) {
				foundUser = true;
				System.out.println("\nLogging You In...\n");
				mm.userSettings(username, password);
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mm.menu_userpanel();
			}
			if (foundUser){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\nIncorrent Username and/or Password. Please enter it again.\n");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				searchByUsername();
			}
	
		}
		
	}
	
	public static boolean addBook(String bookTitle) {
    	boolean checked = true;
    	if(book1 == null) {
    		book1 = bookTitle;
    	}
    	else if(book2 == null) {
    		book2 = bookTitle;
    	}
    	else if(book3 == null) {
    		book3 = bookTitle;
    	}
    	else {
    		System.out.println("You already have the maximum three books checked out!");
    		checked = false;
    	}
    	
    	return checked;
    }
	    public static String getbook1() {
	    	return book1;
	    }
	    
	    public static String getbook2() {
	    	return book2;
	    }
	    
	    public static String getbook3() {
	    	return book3;
	    }
	    
	    public void getbooks() {
	    	if(book1 == null && book2 == null && book3 == null) {
	    		System.out.println("You do not have any books checked out!");
	    	}else {
	    		System.out.println("Here are the books you have checked out: ");
	    		
	    	if(book1 !=null) {
	    		System.out.print(" " + book1);
	    	}
	    	if(book2 !=null) {
	    		System.out.print(", " + book2);
	    	}
	    	if(book3 !=null) {
	    		System.out.print(", " + book3);
	    	}
	    	}
	    	
	    }
}