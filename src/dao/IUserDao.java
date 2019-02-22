package dao;

import java.util.Map;

import entity.User;

public interface IUserDao {

	public Map<String, User> getUsers();

	public boolean updateUser(Map<String, User> users);

}
