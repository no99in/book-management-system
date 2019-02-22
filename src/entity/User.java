package entity;

public class User implements Comparable<User> {
	public User() {
		super();
	}

	public User(String userIDentification, String userPassword, String userName) {
		super();
		this.userIDentification = userIDentification;
		this.userPassword = userPassword;
		this.userName = userName;
	}

	public User(String userIDentification, String userPassword) {
		super();
		this.userIDentification = userIDentification;
		this.userPassword = userPassword;
	}

	private String userIDentification = "swx123";
	private String userPassword = "123456";
	private String userName = "宋文暄";
	private boolean userSex = true;
	private int userAge = 0;
	private String userADress = "中北大学";
	private String userDepartment = "软件学院";
	private int userLimit = 3;

	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}

	public int getUserLimit() {
		return userLimit;
	}

	public String getUserIDentification() {
		return userIDentification;
	}

	public void setUserIDentification(String userIDentification) {
		this.userIDentification = userIDentification;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isUserSex() {
		return userSex;
	}

	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserADress() {
		return userADress;
	}

	public void setUserADress(String userADress) {
		this.userADress = userADress;
	}

	public String getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}

	public User(String userIDentification, String userPassword, String userName, boolean userSex, int userAge,
			String userADress, String userDepartment, int userLimit) {

		this.userIDentification = userIDentification;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userADress = userADress;
		this.userDepartment = userDepartment;
		this.userLimit = userLimit;
	}

	@Override
	public String toString() {

		return userIDentification + ":" + userPassword + ":" + userName + ":" + userSex + ":" + userAge + ":"
				+ userADress + ":" + userDepartment + ":" + userLimit;
	}

	@Override
	public int compareTo(User user) {
		if (user.userIDentification.equals(this.getUserIDentification())) {
			return 1;
		}
		return user.userIDentification.compareTo(this.getUserIDentification());
	}

}
