package com.springflame.commissionapp.service.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalFormatter {
	public static String bigDecimalToDefaultPrecisionString(BigDecimal bigDecimal) {
		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(false);

		return df.format(bigDecimal);
	}
}
