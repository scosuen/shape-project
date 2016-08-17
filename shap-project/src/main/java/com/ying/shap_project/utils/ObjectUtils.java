package com.ying.shap_project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ObjectUtils {

	public static boolean isNumber(String number) {
		String pattern = "-?[0-9]+(.[0-9]+)?";

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(number);
		return m.matches();
	}

}
