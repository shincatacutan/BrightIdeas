package com.optum.operations.momlibrary.util;

import org.joda.time.LocalDate;

public class MomUtils {

	public static LocalDate dateParser(String date) {
		String parsed[] = date.split("/");
		int m = Integer.parseInt(parsed[0]);
		int d = Integer.parseInt(parsed[1]);
		int y = Integer.parseInt(parsed[2]);
		LocalDate cal = new LocalDate(y, m, d);
		return cal;
	}

}
