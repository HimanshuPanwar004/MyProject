package assesment.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import assesment.model.PaymentDto;
import assesment.model.PaymentResponse;
import assesment.util.Constants;
import assesment.util.OfferUtility;

/**
 * @author himanshupanwar
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	OfferUtility offerUtility;

	@Override
	public PaymentResponse getNetPayment(PaymentDto payInfo) {

		String payableRequestInit = payInfo.getPayment();
		String isItemGrocery = payInfo.getIsGrocery();
		PaymentResponse response = new PaymentResponse();

		try {
			String payableRequest [] = payableRequestInit.split(Constants.DOLLAR_SPLITTER);
			String payable = payableRequest[1];
			BigDecimal tempPay = new BigDecimal(payable);

			if(isItemGrocery.equals(Constants.IS_GROCERIES)) {
				if(tempPay.intValue()==100 || tempPay.intValue()>100) {
					response.setNetPayable(offerUtility.getNetOnHundredOnEveryBill(tempPay));
					return response;
				}
				response.setNetPayable(payInfo.getPayment());
				return response;
			}
			else {
				return processNetPercentOffer(payInfo, tempPay);
					
			}
		}
		catch(Exception ex) {

			response.setErrorMessage(ex.getMessage());
			response.setErrorCode(HttpStatus.BAD_REQUEST.value());
			return response;
		}

	}
	
	/**
	 * This method is used to find the net amount after applying percent offer.
	 * 
	 * @param payInfo
	 * @param tempPay
	 * @return
	 * @throws Exception
	 */
	private PaymentResponse processNetPercentOffer(PaymentDto payInfo, BigDecimal tempPay) {
		
		BigDecimal netPayable = null;
		String userStatus = payInfo.getUserStatus();
		PaymentResponse response = new PaymentResponse();
		
		if(userStatus.equals(Constants.IS_EMPLOYEE)) {
			if(tempPay.intValue()==100 || tempPay.intValue()>100) {
				netPayable = offerUtility.getNetOnHundredOnEveryBillBD(tempPay);
			}
			response.setNetPayable(offerUtility.getBillOnPercentageOffer(netPayable, Constants.THIRTY_PERCENT));
			return response;
		}

		else if(userStatus.equals(Constants.IS_AFFILIATE)) {
			if(tempPay.intValue()==100 || tempPay.intValue()>100) {
				netPayable = offerUtility.getNetOnHundredOnEveryBillBD(tempPay);
			}
			response.setNetPayable(offerUtility.getBillOnPercentageOffer(netPayable, Constants.TEN_PERCENT));
			return response;
		}
		else if(userStatus.equals(Constants.IS_CUSTOMER_2_YRS)) {
			if(tempPay.intValue()==100 || tempPay.intValue()>100) {
				netPayable = offerUtility.getNetOnHundredOnEveryBillBD(tempPay);
			}
			response.setNetPayable(offerUtility.getBillOnPercentageOffer(netPayable, Constants.FIVE_PERCENT));
			return response;
		}
		else {
			response.setNetPayable(payInfo.getPayment());
			return response;

		}	
		
	}

}
