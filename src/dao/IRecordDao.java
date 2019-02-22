package dao;

import java.util.Map;

import entity.Record;

public interface IRecordDao {

	public Map<String, Record> getRecords();

	public boolean updateRecord(Map<String, Record> Record);

}
