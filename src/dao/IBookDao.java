package dao;

import java.util.Map;

import entity.Book;

public interface IBookDao {

	public Map<String, Book> getBooks();

	public boolean updateBook(Map<String, Book> books);

}
