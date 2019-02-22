package service;

import java.util.Map;
import java.util.Set;

import entity.Record;
import entity.User;

public interface IRecordService {
	public boolean managerAdd(Record record);

	public boolean managerDelete(Record record);

	public Set<Record> userSeeAllRecords();

	public Map<String, Record> managerSeeAllRecords();

	public void setUser(User user);

	public void setRecords();

	public Map<String, Record> getRecords();

	public Record getRecord(String id);

	public void setRecords(Map<String, Record> records);

	Set<Record> userSeeAllRecordsEx();
}
