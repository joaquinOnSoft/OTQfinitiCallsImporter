package com.opentext.qfiniti.importer.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
	/**
	 * Round a double to 2 decimal places
	 * @see https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 * @param value
	 * @param places
	 * @return
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
