
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyFileReader {
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		
		//Student attributes index
		private static final int BOOK_NAME = 0;
		private static final int BOOK_AUTHOR  = 1;
		private static final int BOOK_GENRE = 2;
		private static final int BOOK_COPIES = 3;
		
		private static final int USER_NAME = 0;
		private static final int USER_USERNAME = 1;
		private static final int USER_PASSWORD = 2;
		
		
		public ArrayList<Books> readCsvFile(String filename) {

			ArrayList <Books> bookList = new ArrayList();
			BufferedReader fileReader = null;
	     
	        try {

	            String line = "";    
	            //Create the file reader
	            fileReader = new BufferedReader(new FileReader(filename));
	            
	            //Read the CSV file header to skip it
	            fileReader.readLine();
	            
	            //Read the file line by line starting from the second line
	           
	            while ((line = fileReader.readLine()) != null) {
	                //Get all tokens available in line
	                String[] tokens = line.split(COMMA_DELIMITER);
	                if (tokens.length > 0) {
	                
						Books book  = new Books();	
						book.setTitle(tokens[BOOK_NAME]);
						book.setAuthor(tokens[BOOK_AUTHOR]);
						book.setGenre(tokens[BOOK_GENRE]);
						book.setCopies(tokens[BOOK_COPIES]);
					    //book.setIsbn(Integer.parseInt(tokens[BOOK_ISBN]));
					    bookList.add(book);
					}
	            }
	            
	            
	           
	        }catch (Exception e) {
	        	    System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	            	System.out.println("Error while closing fileReader !!!");
	                e.printStackTrace();
	            }
	        }
			return bookList;
	        
	       

		}

		
		
		public ArrayList<User> readUserFile(String filename) {

			ArrayList <User> users = new ArrayList();
			BufferedReader fileReader = null;
	     
	        try {

	            String line = "";    
	            //Create the file reader
	            fileReader = new BufferedReader(new FileReader(filename));
	            
	            //Read the CSV file header to skip it
	            fileReader.readLine();
	            
	            //Read the file line by line starting from the second line
	           
	            while ((line = fileReader.readLine()) != null) {
	                //Get all tokens available in line
	                String[] tokens = line.split(COMMA_DELIMITER);
	                if (tokens.length > 0) {
	                
						User addUser  = new User();	
						addUser.setName(tokens[USER_NAME]);
						addUser.setUser(tokens[USER_USERNAME]);
						addUser.setPassword(tokens[USER_PASSWORD]);
					    //book.setIsbn(Integer.parseInt(tokens[BOOK_ISBN]));
					    users.add(addUser);
					}
	            }
	            
	            
	           
	        }catch (Exception e) {
	        	    System.out.println("Error in User CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	            	System.out.println("Error while closing User fileReader !!!");
	                e.printStackTrace();
	            }
	        }
			return users;
	        
	       

		}

}