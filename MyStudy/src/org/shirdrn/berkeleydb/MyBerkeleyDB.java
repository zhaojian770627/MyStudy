package org.shirdrn.berkeleydb;

import java.io.File;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class MyBerkeleyDB {

	private Environment env;
	private Database db;

	public MyBerkeleyDB() {

	}

	public void setUp(String path, long cacheSize) {
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setCacheSize(cacheSize);
		try {
			env = new Environment(new File(path), envConfig);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public void open(String dbName) {
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setAllowCreate(true);
		try {
			db = env.openDatabase(null, dbName, dbConfig);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (db != null) {
				db.close();
			}
			if (env != null) {
				env.close();
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public String get(String key) throws Exception {
		DatabaseEntry queryKey = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		queryKey.setData(key.getBytes("UTF-8"));
		OperationStatus status = db
				.get(null, queryKey, value, LockMode.DEFAULT);
		if (status == OperationStatus.SUCCESS) {
			return new String(value.getData());
		}
		return null;
	}

	public boolean put(String key, String value) throws Exception {
		byte[] theKey = key.getBytes("UTF-8");
		byte[] theValue = value.getBytes("UTF-8");
		OperationStatus status = db.put(null, new DatabaseEntry(theKey),
				new DatabaseEntry(theValue));
		if (status == OperationStatus.SUCCESS) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		MyBerkeleyDB mbdb = new MyBerkeleyDB();
		mbdb.setUp("d:\\bdb", 1000000);
		mbdb.open("myDB");
		System.out.println("开始向Berkeley DB中存入数据...");
		for (int i = 0; i < 20; i++) {
			try {
				String key = "myKey" + i;
				String value = "myValue" + i;
				System.out.println("[" + key + ":" + value + "]");
				mbdb.put(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mbdb.close();
	}
}
