package entity;

public class Book implements Comparable<Book> {

	private String bookIDentification = "9787302335801";
	private String bookAuthor = "张跃平等";
	private String bookName = "Java面向对象程序设计";
	private int bookINventory = 1;
	private String bookPicture = "/res/book/bookTest.jpg";
	private String bookIntroduction = "《Java面向对象程序设计》是2003年清华大学出版社出版的图书,作者是...... ";

	public String getBookIntroduction() {
		return bookIntroduction;
	}

	public void setBookIntroduction(String bookIntroduction) {
		this.bookIntroduction = bookIntroduction;
	}

	private String bookFirstCategory = "工具类";
	private String bookSecondCategory = "编程开发";

	public String getBookIDentification() {
		return bookIDentification;
	}

	public void setBookIDentification(String bookIDentification) {
		this.bookIDentification = bookIDentification;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookINventory() {
		return bookINventory;
	}

	public void setBookINventory(int bookINventory) {
		this.bookINventory = bookINventory;
	}

	public String getBookPicture() {
		return bookPicture;
	}

	public void setBookPicture(String bookPicture) {
		this.bookPicture = bookPicture;
	}

	public String getBookFirstCategory() {
		return bookFirstCategory;
	}

	public void setBookFirstCategory(String bookFirstCategory) {
		this.bookFirstCategory = bookFirstCategory;
	}

	public String getBookSecondCategory() {
		return bookSecondCategory;
	}

	public void setBookSecondCategory(String bookSecondCategory) {
		this.bookSecondCategory = bookSecondCategory;
	}

	@Override
	public String toString() {
		return bookIDentification + ":" + bookAuthor + ":" + bookName + ":" + bookINventory + ":" + bookPicture + ":"
				+ bookFirstCategory + ":" + bookSecondCategory + ":" + bookIntroduction;
	}

	public Book(String bookIDentification, String bookAuthor, String bookName, int bookINventory, String bookPicture,
			String bookFirstCategory, String bookSecondCategory, String bookIntroduction) {
		super();
		this.bookIDentification = bookIDentification;
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.bookINventory = bookINventory;
		this.bookPicture = bookPicture;
		this.bookFirstCategory = bookFirstCategory;
		this.bookSecondCategory = bookSecondCategory;
		this.bookIntroduction = bookIntroduction;
	}

	public Book() {
		super();
	}

	@Override
	public int compareTo(Book book) {
		if (book.bookIDentification.equals(this.getBookIDentification())) {
			return 1;
		}
		return book.bookIDentification.compareTo(this.getBookIDentification());
	}

}