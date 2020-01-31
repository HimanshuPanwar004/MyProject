package assesment.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferUtility {
	
	@Autowired
	AppUtility utility;
	
	
	public String getNetOnHundredOnEveryBill(BigDecimal tempPay) {
		BigDecimal netPayable = tempPay.subtract(tempPay.multiply(BigDecimal.valueOf(0.05)));
		return utility.getCurrencyFormatter(netPayable.doubleValue());
	}
	
	public BigDecimal getNetOnHundredOnEveryBillBD(BigDecimal tempPay) {
		return tempPay.subtract(tempPay.multiply(BigDecimal.valueOf(0.05)));
	}
	

	public String getBillOnPercentageOffer(BigDecimal tempPay, int percentOffer) {
		BigDecimal netPayable = tempPay.subtract(tempPay.multiply(BigDecimal.valueOf((double)percentOffer/100)));
		return utility.getCurrencyFormatter(netPayable.doubleValue());
	}

}
