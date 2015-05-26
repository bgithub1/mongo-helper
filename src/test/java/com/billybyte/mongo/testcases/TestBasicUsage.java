package com.billybyte.mongo.testcases;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import junit.framework.TestCase;

public class TestBasicUsage extends TestCase {
	private static final boolean dotests = false;
	private Mongo m=null;
	private DB db;
	@Override
	protected void setUp() throws Exception {
		if(!dotests)return;
		if(m==null){
			try {
				m = new Mongo( "localhost" );
				db = m.getDB( "mydb" );
				m.setWriteConcern(WriteConcern.SAFE);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
		assertNotNull(m);
		assertNotNull(db);

		super.setUp();
	}

	public void test1(){
		if(!dotests)return;

		boolean doWrite =false;
		
		DBCollection coll = db.getCollection("testCollection");
		
		if(doWrite){
		    BasicDBObject doc = new BasicDBObject();

	        doc.put("name", "MongoDB");
	        doc.put("type", "database");
	        doc.put("count", 1);

	        BasicDBObject info = new BasicDBObject();

	        info.put("x", 203);
	        info.put("y", 102);

	        doc.put("info", info);

	        coll.insert(doc);
		}   
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
		
		BasicDBObject query = new BasicDBObject();
		query.put("type", "database");
		DBCursor cursor = coll.find(query);
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	        
		
	}
	
	public void test2(){
		if(!dotests)return;

		DBCollection coll = db.getCollection("settles");
		JFrame frame = new JFrame();
		
		String key="NG.FUT.NYMEX.USD.201412";
		while(key.compareTo("   ")>0){
			key = MessageBox.MessageBoxNoChoices(frame, "Enter shortName", "settle getter", key);
			String[] parts = key.split(",");
			String shortName=key;
			Pattern p = null;
			if(parts.length>=3){
			    BasicDBObject doc = new BasicDBObject();
				shortName=parts[0];
		        doc.put("shortName", shortName);
		        doc.put("date", new BigDecimal(parts[1]));
		        doc.put("settle", new BigDecimal(parts[2]).doubleValue());
		        coll.insert(doc);
		        p = Pattern.compile(shortName.replace(".", "\\."));
			}else{
				p = Pattern.compile(shortName);
			}
			BasicDBObject query = new BasicDBObject("shortName",p);
			DBCursor cursor = coll.find(query);
			while(cursor.hasNext()){
				System.out.println(cursor.next());
			}
		}
	}
}
