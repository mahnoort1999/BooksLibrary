import java.util.ArrayList;

public class Books {
	String title, author, genre, copies;
	static ArrayList<Books> bookList = new ArrayList<>();
	
	Books(){
		
	}
	
	Books(String title, String author, String genre, String copies){
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.copies = copies;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/*public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}*/
	

	public String toString() {
		String answer = "";
		answer += "Title: " + title + "\n";
		answer += "Author: " + author + "\n";
		answer += "Genre: " + genre + "\n";
		answer += "Number of Copies: " + copies + "\n";
		return answer;
	}
	
}
