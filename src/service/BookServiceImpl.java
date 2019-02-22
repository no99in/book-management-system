package service;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.BookDaoImpl;
import entity.Book;

public class BookServiceImpl implements IBookService {

	private Map<String, Book> books = new BookDaoImpl().getBooks();
	private Book book = new Book();

	@Override
	public String bookAddINventory(int number) {
		if (number > 0) {
			this.book.setBookINventory(this.book.getBookINventory() + number);
			return "Success";
		} else {
			return "Fail";
		}

	}

	@Override
	public String bookUpdateInformation() {
		if (this.book.getBookIDentification() != null) {
			books.remove(this.book.getBookIDentification());
			books.put(this.book.getBookIDentification(), this.book);
			return "更新成功";
		} else {
			return "不存在此书籍";
		}

	}

	@Override
	public String managerAdd(Book book) {

		// if (idIsValid(book.getBookIDentification())) {
		books.put(book.getBookIDentification(), book);
		return "Success";
		// } else {
		// return "书籍编号必须为b后八位数字，例：b00000000";
		// }

	}

	@Override
	public String managerDelete(Book book) {
		// if (idIsValid(book.getBookIDentification())) {
		books.remove(book.getBookIDentification(), book);
		return "删除书籍成功";
		// } else {
		// return "书籍编号必须为b后八位数字，例：b00000000";
		// }
	}

	@Override
	public Map<String, Book> userSeeAllBooks() {
		return this.books;
	}

	public boolean idIsValid(String id) {
		Pattern p = Pattern.compile("^[b]\\d{8}$");
		Matcher m = p.matcher(id);
		if (m.matches())
			return true;
		else
			return false;
	}

	@Override
	public boolean shutDownBookService() {
		new BookDaoImpl().updateBook(books);
		return true;
	}

	@Override
	public Set<Book> getBookList() {
		Set<Book> bookSet = new TreeSet<Book>();
		for (Book book : books.values()) {
			bookSet.add(book);
		}
		return bookSet;
	}

	public Map<String, Book> getBooks() {
		return books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setBook(String bid) {
		this.book = books.get(bid);
	}

	@Override
	public void setBooks(Map<String, Book> map) {
		this.books = map;

	}

}
