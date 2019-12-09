
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MyFileWriter {
	
	public void writeListToFile(){
		
		//Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		final String FILE_HEADER = "BookAuthor,Genre,Copies";
		FileWriter fileWriter = null;
					
		try {
			fileWriter = new FileWriter("LibraryBooks.csv");
		
				//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
				//Add a new line separator after the header
			 fileWriter.append(NEW_LINE_SEPARATOR);
			 fileWriter.append("Gone with the Wind");
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append("Romance");
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append("12234566");
			 /*	
				*/
				//System.out.println("Books CSV file was created successfully !!!");
				fileWriter.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		//USERS
		final String USER_HEADER = "Name, Username, Password";
		try {
			fileWriter = new FileWriter("Users.csv");
			fileWriter.append(USER_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("Mahnoor Tariq");
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("mtariq2");
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("project");
			
			System.out.println("User CSV File was created successfully !!!");
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//BOOKS CHECKEDOUT
		final String CHECKEDOUT_HEADER = "User, Book, Title, Genre, Copies";
		try {
			fileWriter = new FileWriter("CheckedOutBooks.csv");
			fileWriter.append(CHECKEDOUT_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("mtariq2");
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("This Is Kind of Hard");
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("Technology");
			
			System.out.println("CheckedOut Books CSV File was created successfully !!!");
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void writeListToFile(Books book){
		
		//Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		final String FILE_HEADER = "BookAuthor,Genre,Copies";
		FileWriter fileWriter = null;
					
		try {
			fileWriter = new FileWriter("LibraryBooks.csv");
		
				//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
				//Add a new line separator after the header
			 fileWriter.append(NEW_LINE_SEPARATOR);
			 fileWriter.append(book.getTitle());
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append(book.getAuthor());
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append(book.getGenre());
			 fileWriter.append(COMMA_DELIMITER);
			 fileWriter.append(book.getCopies());
			 /*	
				*/
				//System.out.println("Book CSV file was created successfully !!!");
				fileWriter.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public void writeListToUserFile(User user) {
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		FileWriter fileWriter = null;
		
		final String USER_HEADER = "Name, Username, Password";
		
		try {
		fileWriter = new FileWriter("Users.csv");
		
		//Write the CSV file header
		fileWriter.append(USER_HEADER.toString());
		
		//Add a new line separator after the header
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append(user.getName());
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(user.getUser());
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(user.getPassword());
		/*	
		*/
		System.out.println("User CSV file was created successfully !!!");
		fileWriter.close();
		
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void writeListToFile(ArrayList<Books> bookList){
		
		//Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		final String FILE_HEADER = "BookTitle,BookAuthor,Genre,Copies";
		FileWriter fileWriter = null;
					
		try {
			fileWriter = new FileWriter("LibraryBooks.csv");
		
				//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
				//Add a new line separator after the header
			 fileWriter.append(NEW_LINE_SEPARATOR);
			 
			 for (Books book : bookList) {
				    fileWriter.append(book.getTitle());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(book.getAuthor());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(book.getGenre());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(book.getCopies());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
					
				}

			 /*	
				*/
				//System.out.println("Books CSV file was created successfully !!!");
				fileWriter.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public void writeListToUserFile(ArrayList<User> users){
		
		//Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		final String USER_HEADER = "Name, Username, Password";
		FileWriter fileWriter = null;
					
		try {
			fileWriter = new FileWriter("Users.csv");
		
				//Write the CSV file header
			fileWriter.append(USER_HEADER.toString());
			
				//Add a new line separator after the header
			 fileWriter.append(NEW_LINE_SEPARATOR);
			 
			 for (User user : users) {
				    fileWriter.append(user.getName());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(user.getUser());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(user.getPassword());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}

			 /*	
				*/
				System.out.println("Users CSV file was created successfully !!!");
				fileWriter.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


}