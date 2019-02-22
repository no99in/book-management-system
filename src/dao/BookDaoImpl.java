package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import db.DataBaseConfigure;
import entity.Book;

public class BookDaoImpl implements IBookDao {

	@Override
	public boolean updateBook(Map<String, Book> bs) {
		File bf = new File(DataBaseConfigure.BOOK_FILE_PATH);
		StringBuffer bsb = new StringBuffer();
		FileWriter bfw = null;
		try {
			bfw = new FileWriter(bf, false);
			for (Book b : bs.values()) {
				String s = b.toString();
				bsb.append(s + System.getProperty("line.separator"));
			}
			bfw.write(bsb.toString());
		} catch (IOException e) {
			System.out.println("异常:请维护BookDaoImpl!");
			return false;
		} finally {
			try {
				bfw.close();
			} catch (IOException e) {
				System.out.println("异常:请维护BookDaoImpl!");
				return false;
			}
		}
		return true;
	}

	@Override
	public Map<String, Book> getBooks() {
		Map<String, Book> bs = new HashMap<String, Book>();
		File bf = new File(DataBaseConfigure.BOOK_FILE_PATH);
		FileReader bfr = null;
		BufferedReader bbr = null;
		try {
			bfr = new FileReader(bf);
			bbr = new BufferedReader(bfr);
			String bts = null;
			String[] bais;
			Book b;
			while ((bts = bbr.readLine()) != null) {
				if (bts.length() == 1) {
					continue;
				}
				bais = bts.split(":");
				Integer bin = new Integer(Integer.parseInt(bais[3]));
				b = new Book(bais[0], bais[1], bais[2], bin, bais[4], bais[5], bais[6], bais[7]);
				bs.put(bais[0], b);
			}
		} catch (FileNotFoundException e) {
			System.out.println("异常:请维护BookDaoImpl!");
			return new HashMap<String, Book>();
		} catch (IOException e) {
			System.out.println("异常:请维护BookDaoImpl!");
			return new HashMap<String, Book>();
		} finally {
			try {
				bfr.close();
				bbr.close();
			} catch (IOException e) {
				System.out.println("异常:请维护BookDaoImpl!");
				return new HashMap<String, Book>();
			}
		}
		return bs;
	}

}
