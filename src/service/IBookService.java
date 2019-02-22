package service;

import java.util.Map;
import java.util.Set;

import entity.Book;

public interface IBookService {

	String bookAddINventory(int number);

	String bookUpdateInformation();

	Map<String, Book> userSeeAllBooks();

	String managerAdd(Book book);

	String managerDelete(Book book);

	boolean shutDownBookService();

	Set<Book> getBookList();

	Book getBook();

	void setBook(String bid);

	void setBooks(Map<String, Book> map);

}