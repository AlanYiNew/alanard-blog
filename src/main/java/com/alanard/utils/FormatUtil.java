package com.alanard.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {
	public static boolean validEmail(String email) {
		// email pattern
		Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
