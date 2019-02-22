package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.BookDaoImpl;
import dao.RecordDaoImpl;
import dao.UserDaoImpl;
import entity.Book;
import entity.Record;
import entity.User;

public class UserServiceImpl implements IUserService {
	private Map<String, User> users = new UserDaoImpl().getUsers();
	public User user = new User();
	private Map<String, Book> books = new BookDaoImpl().getBooks();
	private Book book = new Book();
	private Map<String, Record> records = new RecordDaoImpl().getRecords();
	private Record record = new Record();

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public void setUser(String userid) {
		user = users.get(userid);
	}

	public Map<String, Book> getBooks() {
		return books;
	}

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Map<String, Record> getRecords() {
		return records;
	}

	public void setRecords(Map<String, Record> records) {
		this.records = records;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	@Override
	public String userBorrow(Book book) {
		if (book.getBookINventory() <= 0) {
			return "Fail-1";
		} else if (user.getUserLimit() <= 0) {
			return "Fail-2";
		} else {
			record = new Record((new Date().getTime()) + user.getUserIDentification(), user.getUserIDentification(),
					book.getBookIDentification(), false);
			records.put(record.getRecordIDentification(), record);
			book.setBookINventory(book.getBookINventory() - 1);
			books.remove(book.getBookIDentification());
			books.put(book.getBookIDentification(), book);
			user.setUserLimit(user.getUserLimit() - 1);
			return "Success";
		}
	}

	@Override
	public String userGiveBack(Book book) {
		records.remove(record.getRecordIDentification());
		record.setNotice(true);
		records.put(record.getRecordIDentification(), record);

		book.setBookINventory(book.getBookINventory() + 1);
		books.remove(book.getBookIDentification());
		books.put(book.getBookIDentification(), book);
		user.setUserLimit(user.getUserLimit() + 1);
		return "Success";
	}

	@Override
	public String userRenew(Book book) {

		String endTime = record.getEndTime();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int off = 0;
		try {
			Date date = format.parse(endTime);
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			if (year1 != year2) {
				int timeDistance = 0;
				for (int i = year1; i < year2; i++) {
					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
						timeDistance += 366;
					} else {
						timeDistance += 365;
					}
				}
				off = timeDistance + (day2 - day1);
			} else {
				off = day2 - day1;
			}
		} catch (ParseException e) {
			System.out.println("时间格式转换错误");
			return "Fail-1";
		}
		if (off < 0) {
			return "Fail-2";
		} else if (off > 10) {
			return "Fail-3";
		} else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DATE);
			record.setEndTime(year, month, day);
			records.remove(record.getRecordIDentification());
			records.put(record.getRecordIDentification(), record);
			return "Success";
		}

	}

	@Override
	public boolean userUpdateInformation() {
		users.remove(user.getUserIDentification());
		users.put(user.getUserIDentification(), user);
		return true;
	}

	@Override
	public String touristLogin() {

		String userIDentification = user.getUserIDentification();
		String userPassword = user.getUserPassword();
		if (users.containsKey(userIDentification)) {
			if (users.get(userIDentification).getUserPassword().equals(userPassword)) {
				setUser(users.get(userIDentification));
				return "Success";
			}
		}
		return "Fail";
	}

	@Override
	public String touristRegister(User user) {
		Pattern p = Pattern.compile("^\\w{6,12}$");
		Pattern p1 = Pattern.compile("^\\d{6}$");
		Matcher m = p.matcher(user.getUserIDentification());
		Matcher m1 = p1.matcher(user.getUserPassword());
		if (m.matches() && m1.matches()) {
			users.put(user.getUserIDentification(), user);
			setUser(user);
			return "Success";
		}
		return "Fail";
	}

	@Override
	public Map<String, User> managerSeeAllUsers() {
		return users;
	}

	@Override
	public String managerAdd(User user) {
		String res = touristRegister(user);
		if (res.equals("Success")) {
			users.put(user.getUserIDentification(), user);
			return "Success";
		}

		return "Fail";
	}

	@Override
	public boolean managerDelete(User user) {
		users.remove(user.getUserIDentification());
		return false;
	}

	@Override
	public boolean shutDownUserService() {
		new UserDaoImpl().updateUser(users);
		new RecordDaoImpl().updateRecord(records);
		new BookDaoImpl().updateBook(books);
		return true;
	}

	@Override
	public Set<User> getAllUsers() {
		Set<User> userSet = new TreeSet<User>();
		for (User user : users.values()) {
			userSet.add(user);
		}
		return userSet;
	}

}
