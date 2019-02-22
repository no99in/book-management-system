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
import entity.Record;

public class RecordDaoImpl implements IRecordDao {

	@Override
	public Map<String, Record> getRecords() {
		Map<String, Record> rs = new HashMap<String, Record>();
		File rf = new File(DataBaseConfigure.RECORD_FILE_PATH);
		FileReader rfr = null;
		BufferedReader rbr = null;
		try {
			rfr = new FileReader(rf);
			rbr = new BufferedReader(rfr);
			String rts;
			String[] rai;
			Record rd;
			while ((rts = rbr.readLine()) != null) {
				if (rts.length() == 1) {
					continue;
				}
				rai = rts.split(":");
				String rns = rai[5];
				boolean rn = Boolean.parseBoolean(rns);
				rd = new Record(rai[0], rai[1], rai[2], rai[3], rai[4], rn);
				rs.put(rai[0], rd);
			}
		} catch (FileNotFoundException e) {
			System.out.println("异常:请维护RecordDaoImpl!");
			return new HashMap<String, Record>();
		} catch (IOException e) {
			System.out.println("异常:请维护RecordDaoImpl!");
			return new HashMap<String, Record>();
		} finally {
			try {
				rfr.close();
				rbr.close();
			} catch (IOException e) {
				System.out.println("异常:请维护RecordDaoImpl!");
				return new HashMap<String, Record>();
			}
		}
		return rs;
	}

	@Override
	public boolean updateRecord(Map<String, Record> rs) {
		File f = new File(DataBaseConfigure.RECORD_FILE_PATH);
		StringBuffer buffer = new StringBuffer();
		FileWriter writer = null;
		try {
			writer = new FileWriter(f, false);
			for (Record rd : rs.values()) {
				String rsg = rd.toString();
				buffer.append(rsg + System.getProperty("line.separator"));
			}
			writer.write(buffer.toString());
		} catch (IOException e) {
			System.out.println("异常:请维护RecordDaoImpl!");
			return false;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("异常:请维护RecordDaoImpl!");
				return false;
			}
		}
		return true;
	}

}
