package entity;

import java.util.Calendar;
import java.util.Date;

public class Record implements Comparable<Record> {

	private String recordIDentification = (new Date().getTime()) + "";
	private String userIDentification = "swx123";
	private String bookIDentification = "9787302335801";
	private String startTime = 1 + "-" + 1 + "-" + 1;
	private String endTime = 2 + "-" + 2 + "-" + 2;
	private boolean notice;

	public String getRecordIDentification() {
		return recordIDentification;
	}

	public void setRecordIDentification(String recordIDentification) {
		this.recordIDentification = recordIDentification;
	}

	public String getUserIDentification() {
		return userIDentification;
	}

	public void setUserIDentification(String userIDentification) {
		this.userIDentification = userIDentification;
	}

	public String getBookIDentification() {
		return bookIDentification;
	}

	public void setBookIDentification(String bookIDentification) {
		this.bookIDentification = bookIDentification;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(int year, int month, int day) {
		this.startTime = year + "-" + month + "-" + day;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(int year, int month, int day) {

		this.endTime = year + "-" + month + "-" + day;
	}

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean notice) {
		this.notice = notice;
	}

	public Record(String recordIDentification, String userIDentification, String bookIDentification, String startTime,
			String endTime, boolean notice) {
		super();
		this.recordIDentification = recordIDentification;
		this.userIDentification = userIDentification;
		this.bookIDentification = bookIDentification;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notice = notice;
	}

	public Record(String recordIDentification, String userIDentification, String bookIDentification, boolean notice) {
		super();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		this.recordIDentification = recordIDentification;
		this.userIDentification = userIDentification;
		this.bookIDentification = bookIDentification;
		this.notice = notice;
		this.startTime = year + "-" + month + "-" + day;
		calendar.add(Calendar.DATE, 30);
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DATE);
		this.endTime = year + "-" + month + "-" + day;
	}

	public Record() {
		super();
	}

	@Override
	public String toString() {
		return recordIDentification + ":" + userIDentification + ":" + bookIDentification + ":" + startTime + ":"
				+ endTime + ":" + notice;
	}

	@Override
	public int compareTo(Record record) {
		if (record.startTime.equals(this.getStartTime())) {
			return 1;
		}
		return record.startTime.compareTo(this.getStartTime());
	}

}
