package br.com.alura.challenges.shopping.list.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyFormatUtil {

	public String toFormat(final BigDecimal value) {
		var localePT_BR = new Locale("pt", "BR");
		var currencyFmt = new DecimalFormat("#,##0.00");

		currencyFmt.setCurrency(Currency.getInstance(localePT_BR));
		currencyFmt.setNegativePrefix("-R$ ");
		currencyFmt.setPositivePrefix("R$ ");

		return currencyFmt.format(value);
	}

}
