package assesment.util;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class AppUtility {

	public String getCurrencyFormatter(double amount) {
		NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.US);
		return GBP.format(amount);
	}
	
}
