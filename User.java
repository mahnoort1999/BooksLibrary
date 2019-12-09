import java.util.*;

public class User {

	private String name, user, password;
	private TreeMap<String,Books> booksCheckedOut = new TreeMap<>();
	private TreeMap<Date,Books> booksCheckedHistory = new TreeMap<>();
	
	static ArrayList<User> users = new ArrayList<>();
	
	public User(String name, String user, String password) {
		this.name = name;
		this.user = user;
		this.password = password;
	}
	
	public User() {
		
	}

	public TreeMap<String, Books> getBooksCheckedOut() {
		return booksCheckedOut;
	}

	public TreeMap<Date, Books> getBooksCheckedHistory() {
		return booksCheckedHistory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public void checkIn(Books book) {
		//Remove book to checkout and add available books
		if (booksCheckedOut.containsKey(book.getTitle())) {
			booksCheckedOut.remove(book.getTitle());
		}
		else{
			System.out.println("You don't have this book checked out!");
		}
	}
	
	public void checkOut(Books book, boolean ghost) {
		if (ghost) {
			booksCheckedOut.put(book.getTitle(), book);
		}
	}
	
}
