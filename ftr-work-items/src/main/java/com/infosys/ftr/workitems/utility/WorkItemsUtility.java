package com.infosys.ftr.workitems.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkItemsUtility {
	
	public static String getNumericValueFromAlphanumeric(String str) {
		Pattern p = Pattern.compile("(\\d+)");
		Matcher m = p.matcher(str);
		while(m.find()) {
			return m.group(1);
		}
		return "";
	}
}
