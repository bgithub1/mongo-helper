package com.billybyte.mongo;

import java.lang.reflect.Field;

import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.billybyte.commonstaticmethods.CollectionsStaticMethods;
import com.billybyte.commonstaticmethods.Reflection;
import com.mongodb.DBObject;

public class BasicObject implements DBObject{
	private String key;
	private Object parent;
	
	public BasicObject(String key, Object parent) {
		super();
		this.key = key.replace(".", "__");
		this.parent = parent;
	}
	@Override
	public boolean containsField(String arg0) {
		try {
			Field f = parent.getClass().getField(arg0);
			if(f!=null)return true;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
		}
		return false;
	}
	
	public String convertKey(){
		return key.replace("__", ".");
	}
	
	@Override
	@Deprecated
	public boolean containsKey(String arg0) {
		return (arg0.compareTo(key)==0)?true:false;
	}
	@Override
	public Object get(String arg0) {
		return Reflection.getFieldByFieldName(parent, arg0, null);
	}
	@Override
	public Set<String> keySet() {
		return CollectionsStaticMethods.setFromArray(new String[]{key});
	}
	@Override
	public Object put(String arg0, Object arg1) {
		return arg1;
	}
	@Override
	public void putAll(BSONObject arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object removeField(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map toMap() {
		return CollectionsStaticMethods.mapInitFromArray(new String[]{key}, new Object[]{parent});
	}
	@Override
	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void markAsPartialObject() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return convertKey()+","+parent.toString();
	}
	
	
	
}
