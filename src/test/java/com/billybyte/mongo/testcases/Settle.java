package com.billybyte.mongo.testcases;

import java.math.BigDecimal;
import java.util.Calendar;

public class Settle {
	private final String shortName;
	private final Calendar date;
	private final BigDecimal settle;
	public Settle(String shortName, Calendar date, BigDecimal settle) {
		super();
		this.shortName = shortName;
		this.date = date;
		this.settle = settle;
	}
	public String getShortName() {
		return shortName;
	}
	public Calendar getDate() {
		return date;
	}
	public BigDecimal getSettle() {
		return settle;
	}
	@Override
	public String toString() {
		return shortName + ", " + date.getTime().toString() + ", " + settle.toString();
	}
	
}
