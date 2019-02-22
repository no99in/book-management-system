package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import dao.RecordDaoImpl;
import entity.Record;
import entity.User;


public class RecordServiceImpl implements IRecordService {

	@SuppressWarnings("unused")
	private Record record = new Record();
	private Map<String,Record> records = new HashMap<String,Record>();
	private User user = new User();
	
	public void setRecords() {
		this.records = new RecordDaoImpl().getRecords();
	}
	public void setRecords(Map<String,Record> records) {
		this.records = records;
	}
	public Map<String, Record> getRecords() {
		return records;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setRecord(Record record) {
		this.record = record;
	}
	public Record getRecord(String id) {
		Record record = records.get(id);
		setRecord(record);
		return record;
	}
	
	@Override
	public boolean managerAdd(Record record) {
		records.put(record.getRecordIDentification(),record);
		return true;
	}

	@Override
	public boolean managerDelete(Record record) {
		records.remove(record.getRecordIDentification());
		return true;
	}

	@Override
	public Set<Record> userSeeAllRecords() {
		Set<Record> recordList = new TreeSet<Record>();
			for (Record record : records.values()) {  
				if(user.getUserIDentification().equals(record.getUserIDentification()) && !record.isNotice())
					recordList.add(record);
			}  
		return recordList;
	}
	
	@Override
	public Set<Record> userSeeAllRecordsEx() {
		Set<Record> recordList = new TreeSet<Record>();
			for (Record record : records.values()) {  
				if(user.getUserIDentification().equals(record.getUserIDentification()))
					recordList.add(record);
			}  
		return recordList;
	}

	@Override
	public Map<String, Record> managerSeeAllRecords() {
		return records;
	}

}
