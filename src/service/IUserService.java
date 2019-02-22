package service;

import java.util.Map;
import java.util.Set;

import entity.Book;
import entity.Record;
import entity.User;

public interface IUserService {

	public User getUser();

	public void setRecord(Record record);

	public void setUser(User user);

	public void setUser(String userid);

	public String userBorrow(Book book);

	public String userGiveBack(Book book);

	public boolean userUpdateInformation();

	public String touristLogin();

	public String touristRegister(User user);

	public Map<String, User> managerSeeAllUsers();

	public String managerAdd(User user);

	public boolean managerDelete(User user);

	public Map<String, Record> getRecords();

	public Map<String, Book> getBooks();

	public String userRenew(Book book);

	Set<User> getAllUsers();

	public boolean shutDownUserService();

}
