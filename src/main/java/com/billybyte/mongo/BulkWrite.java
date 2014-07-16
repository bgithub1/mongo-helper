package com.billybyte.mongo;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class BulkWrite {
	public static final void writeList(String host, int port, String dbName, String  collName, List<DBObject> objectsToInsertList) throws UnknownHostException{
		MongoClient client = new MongoClient(host, port);
		DB db = client.getDB(dbName);
		DBCollection coll = db.getCollection(collName);
		coll.insert(objectsToInsertList);
//		BulkWriteOperation builder = coll.initializeOrderedBulkOperation();
//		for(DBObject dbo:objectsToInsertList){
//			builder.insert(dbo)	;
//		}
//		builder.execute();
	}
	
}
