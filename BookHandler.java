import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

//import Classwork.MyFileWriter;

public class BookHandler<E> {
	
	ArrayList<Books> bookList = new ArrayList();
	File bookListFile = new File("LibraryBooks.csv");
	MyFileWriter fw = new MyFileWriter();
	MyFileReader fr = new MyFileReader();
	Scanner input = new Scanner(System.in);
	
	public ArrayList<Books> getBooksList() {
		return bookList;
	}

	public void setBooksList(ArrayList<Books> bookList) {
		this.bookList = bookList;
	}

	public ArrayList<Books> createNewBooksArrayList() {
		
		ArrayList<Books> arrayOfBookss = new ArrayList();		
		arrayOfBookss.add(new Books("The Great Gatsby", "F. Scott Fitzgerlad", "Romance", "1"));
		arrayOfBookss.add(new Books("Looking for Alaska", "John Green", "Romance", "1"));
		arrayOfBookss.add(new Books("The Notebook", "Nicholas Sparks", "Romance", "1"));
		arrayOfBookss.add(new Books("The Fault in Our Stars", "John Green", "Romance", "1"));
		arrayOfBookss.add(new Books("C.J.'s Fate","Kay Hooper","Romance", "1"));
		
		
		arrayOfBookss.add(new Books("Game of Thrones", "George R.R. Martin", "Fantasy", "1"));
		arrayOfBookss.add(new Books("Harry Potter", "J.K. Rowling", "Fantasy", "1"));
		arrayOfBookss.add(new Books("Twilight", "Stephenie Myer", "Fantasy", "1"));
		arrayOfBookss.add(new Books("Fablehaven","Brandon Mull","Fantasy", "1"));
		
		
		arrayOfBookss.add(new Books("Hunger Games", "Suzanne Collins", "Adventure", "1"));
		arrayOfBookss.add(new Books("Divergent", "Veronica Roth", "Adventure", "1"));
		
		
		arrayOfBookss.add(new Books("Murder on the Orient Express", "Agatha Christie", "Mystery", "1"));
		arrayOfBookss.add(new Books("The Girl with the Dragon Tattoo", "Stieg Larsson", "Mystery", "1"));
		arrayOfBookss.add(new Books("The Sweetness At the Bottom of The Pie", "Alan Bradley", "Mystery", "1"));
		arrayOfBookss.add(new Books("The Cuckoo's Calling","J.K. Rowling","Mystery", "1"));
		
		
		arrayOfBookss.add(new Books("In Cold Blood", "Truman Capote", "Crime", "1"));
		arrayOfBookss.add(new Books("My Dark Places", "James Ellroy", "Crime", "1"));
		arrayOfBookss.add(new Books("Shot in the Heart", "Mikal Gilmore", "Crime", "1"));
		
		
		arrayOfBookss.add(new Books("The Giving Tree", "Shel Silverstein", "Children", "1"));
		arrayOfBookss.add(new Books("The Cat in The Hat", "Dr. Seuss", "Children", "1"));
		arrayOfBookss.add(new Books("The Very Hungry Caterpillar", "Eric Carle", "Children", "1"));
		arrayOfBookss.add(new Books("Matilda","Roald Dahl","Children", "1"));
		
		
		arrayOfBookss.add(new Books("The Book Thief","Markus Zusak","Historical Fiction", "1"));
		arrayOfBookss.add(new Books("True Grit","Charles Portis","Historical Fiction", "1"));
		arrayOfBookss.add(new Books("In the Time of the Butterflies","Julia Alvarez","Historical Fiction", "1"));
		
		bookList = arrayOfBookss;
		
		return arrayOfBookss;
	}
	
	public void createBooksListFile() {
		//Hard code and create BooksList
		if(bookListFile.exists()) {
			
			System.out.println("The LibraryBooks.csv file already exists !");
		} else {
			
			System.out.println("The LibraryBooks.csv file does not exist. Starting to create....!");
			bookList = createNewBooksArrayList();
			fw.writeListToFile(bookList);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("The LibraryBooks.csv file has been created.....!");
			
		}
		
	}
	
	
	public void addBooks(Books bookObject) {
		bookList.add(bookObject);
	}
	
	public void removeBooks(Books bookObject) {
		bookList.remove(bookObject);
	}
	
	
	
	public void displayBookss() {
		Iterator<Books> it = bookList.iterator(); 
		 //System.out.println("***********START DISPLAY*************\n");
		 
		 while(it.hasNext()) {
			Books book = it.next();
              System.out.println(book.getTitle() + ", "+ book.getAuthor() + ", "+ book.getGenre() + ", " + book.getCopies());	
		 }
		 
		// System.out.println("\n***********END DISPLAY*************\n\n");
		
	}
	
	public void displayBookssFromFile() {
		bookList = fr.readCsvFile("LibraryBooks.csv");
		Iterator<Books> it = bookList.iterator(); 
		// System.out.println("***********START DISPLAY*************\n");
		int i = 1;
		 while(it.hasNext()) {
			Books book = it.next();
              System.out.printf( i + ".) " + book.getTitle() + ", "+ book.getAuthor() + ", "+ book.getGenre() + ", " + book.getCopies());	
              i++;
              System.out.println();
		 }
		 
		// System.out.println("\n***********END DISPLAY*************\n");
		
	}

	
	public void transaction() {
		Scanner input = new Scanner(System.in);
		
		searches: while(true) {
			System.out.println("\n-------------------------Book Search---------------------------");
			System.out.println("What would you like to search by?");
			System.out.println("\t1. Title\n" 
							+ "\t2. Author\n" +
							"\t3. Genre\n" +
							"\t4. Show All Books\n" +
							"\t5. Quit\n");	
			
			int option = input.nextInt();
			
			switch (option){
			case 1:
				System.out.println("--------------------Searching By Title------------------------");
				System.out.print("Enter a Title of a Book (Or a part of a title): ");
				String inputTitle = input.next();
				System.out.println("\nSearching......\n");
				searchByTitle(bookList, inputTitle);
				break searches;
			case 2:
				System.out.println("--------------------Searching by Author-----------------------");
				System.out.println("Enter an Author (Or part of the name:) ");
				String inputAuthor = input.next();
				System.out.println("\nSearching......\n");
				searchByAuthor(inputAuthor);
				break searches;
			case 3:
				System.out.println("---------------------Searching by Genre-----------------------");
				System.out.print("Enter a Genre: ");
				String inputGenre = input.next();
				System.out.println("\nSearching......\n");
				searchByGenre(inputGenre);
				break searches;
			case 4:
				System.out.println("--------------------Showing All Books-------------------------");
				displayBookssFromFile();
				break;
			case 5:
				goodbye();
				break searches;
			default:
				System.out.println("Invalid Input. Please Try Again.");
			}	
		}
	}
	
	
	
	public void searchByAuthor(String authorName) {
		/*for (int x = 0; x < bookList.size(); x++) {
			if (bookList.get(x).getAuthor().toLowerCase().contains(authorName.toLowerCase())){
				System.out.println(bookList.get(x));
			}
		}*/
		bookList = fr.readCsvFile("LibraryBooks.csv");
		Iterator<Books> it = bookList.iterator(); 
				
		while(it.hasNext()) {
			Books book = it.next();
			if(book.getAuthor().toLowerCase().contains(authorName.toLowerCase()) && book.getCopies().equals("1")) {
				System.out.println("Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() 
									+ "\nGenre: " + book.getGenre() + "\nCopies: " + book.getCopies());
				System.out.println();
			}
		}
		System.out.println("Would you like to check out a book?");
		String option = input.nextLine();
		
		if(option.contentEquals("Yes") || option.contentEquals("yes")){
			System.out.println("Which book would you like to check out?");
			String bookRemove = input.nextLine();
			removeBooksByTitle(bookRemove);
		}
		else {
			goodbye();
		}
		
	}
	
	public void searchByTitle(ArrayList<Books> bookList, String bookTitle) {
		/*for(int x = 0; x < bookList.size(); x++) {
			if (bookList.get(x).getTitle().toLowerCase().contains(bookTitle.toLowerCase())) {
				System.out.println(bookList.get(x));
			}
		}*/
		bookList = fr.readCsvFile("LibraryBooks.csv");
		Iterator<Books> it = bookList.iterator(); 
				
		while(it.hasNext()) {
			Books book = it.next();
			if(book.getTitle().toLowerCase().contains(bookTitle.toLowerCase()) && book.getCopies().equals("1")) {
				System.out.println("Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() 
									+ "\nGenre: " + book.getGenre() + "\nCopies: " + book.getCopies());
				System.out.println();
			}
		}
		System.out.println("Would you like to check out a book?");
		String option = input.nextLine();
		
		if(option.contentEquals("Yes") || option.contentEquals("yes")){
			System.out.println("Which book would you like to check out?");
			String bookRemove = input.nextLine();
			removeBooksByTitle(bookRemove);
		}
		else {
			goodbye();
		}
		
	}
	
	public void searchByGenre(String genreName) {
		/*for (int x = 0; x < bookList.size(); x++) {
			if (bookList.get(x).getGenre().equalsIgnoreCase(input)) {
					System.out.println(bookList.get(x));
				}
			}*/
		bookList = fr.readCsvFile("LibraryBooks.csv");
		Iterator<Books> it = bookList.iterator(); 
				
		while(it.hasNext()) {
			Books book = it.next();
			if(book.getGenre().equalsIgnoreCase(genreName) && book.getCopies().equals("1")) {
				System.out.println("Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() 
									+ "\nGenre: " + book.getGenre() + "\nCopies: " + book.getCopies());
				System.out.println();
			}
		}
		System.out.println("Would you like to check out a book?");
		String option = input.nextLine();
		
		if(option.contentEquals("Yes") || option.contentEquals("yes")){
			System.out.println("Which book would you like to check out?");
			String bookRemove = input.nextLine();
			removeBooksByTitle(bookRemove);
		}
		else {
			goodbye();
		}
		
	}

	/*public void showAllBooks() {
		System.out.printf("%-20s%-20s%-12s%n", "Title", "Author", "Genre", "Copies");
		int i = 1;
		for(Entry<String, Books> e : ) {
			System.out.printf("%-20s%-20s%-20s%-12s%n", i + ".) " + e.getValue().getTitle(), e.getValue().getAuthor(),
															e.getValue().getGenre() + e.getValue().getCopies());
			i++;
		}
	}*/
	
	public void addBookstoBooksFile(Books bookObject) {
		// Before I add a Books I need to do load all the books from the file to the bookList
		bookList = fr.readCsvFile("LibraryBooks.csv");
		//System.out.println("Current BooksList size" +bookList.size());
		// Check to see if the Books is a Duplicate and if I just to add a copy of the book	
		//Add a book
		bookList.add(bookObject);
		//System.out.println("New BooksList size" +bookList.size());
		// Write the ArrayList to the File 
		fw.writeListToFile(bookList);
	}

	
	public void removeBooksByTitle(String bookTitle2) {		
		boolean found = false;
		 String answer;
		 bookList = fr.readCsvFile("LibraryBooks.csv");
		/* Iterator<Books> it = bookList.iterator(); 
					
			while(it.hasNext()) {
				Books book = it.next();
				if(book.getTitle().toLowerCase().equals(bookTitle2.toLowerCase()) && book.getCopies().equals("1")) {
					found = true;
					System.out.println();
					System.out.println("Is this the correct book?\n" + book.getTitle() + " by " + book.getAuthor());
					answer = input.nextLine();
					if (answer.equals("Yes") || answer.equals("yes")) {
						System.out.println("Removing " + bookTitle2 + "...");
						it.remove();
						fw.writeListToFile(bookList);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							boolean check = UserHandler.addBook(bookList.get(i).getTitle());
							if(check) {
								bookSearchList.get(i).setAvailable("0");
								System.out.println("The book was sucsessfully checked out!");
								uw.updateFile();
							}
					}
					else {
						System.out.print("Please enter the book title name again (it would help if you are more specific): ");
						bookTitle2 = input.nextLine();
						removeBooksByTitle(bookTitle2);
					}
				}
			}*/
		 for(int i = 0;i<bookList.size(); i++) {
				if(bookList.get(i).getTitle().equals(bookTitle2)){
					found = true;
					
					boolean check = UserHandler.addBook(bookList.get(i).getTitle());
					if(check) {
						bookList.get(i).setCopies("0");
						System.out.println("\nThe Book has been successfully checked out!");
						fw.writeListToFile(bookList);
					}
					break;
				}
			}
			if(!found) {
				System.out.println("That book was not found in the available books.");
			}
	}
	
	public void goodbye() {
		System.out.println("Thank you for using the IS247 library! Goodbye!");
	}
	
	
}