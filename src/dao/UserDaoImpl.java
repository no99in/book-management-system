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
import entity.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public Map<String, User> getUsers() {
		Map<String, User> us = new HashMap<String, User>();
		File f = new File(DataBaseConfigure.USER_FILE_PATH);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String usg;
			String[] usas;
			User ur;
			while ((usg = br.readLine()) != null) {
				if (usg.length() == 1) {
					continue;
				}
				usas = usg.split(":");

				String uss = usas[3];
				boolean usex = Boolean.parseBoolean(uss);

				Integer ua = new Integer(Integer.parseInt(usas[4]));
				Integer ul = new Integer(Integer.parseInt(usas[7]));

				ur = new User(usas[0], usas[1], usas[2], usex, ua, usas[5], usas[6], ul);
				us.put(usas[0], ur);
			}
		} catch (FileNotFoundException e) {
			System.out.println("异常:请维护UserDaoImpl!");
			return new HashMap<String, User>();
		} catch (IOException e) {
			System.out.println("异常:请维护UserDaoImpl!");
			return new HashMap<String, User>();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				System.out.println("异常:请维护UserDaoImpl!");
				return new HashMap<String, User>();
			}
		}
		return us;
	}

	@Override
	public boolean updateUser(Map<String, User> us) {
		File f = new File(DataBaseConfigure.USER_FILE_PATH);
		StringBuffer buffer = new StringBuffer();
		FileWriter writer = null;
		try {
			writer = new FileWriter(f, false);
			for (User ur : us.values()) {
				String usg = ur.toString();
				buffer.append(usg + System.getProperty("line.separator"));
			}
			writer.write(buffer.toString());
		} catch (IOException e) {
			System.out.println("异常:请维护UserDaoImpl!");
			return false;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("异常:请维护UserDaoImpl!");
				return false;
			}
		}
		return true;
	}

}
