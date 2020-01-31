package assesment.service;

import assesment.model.PaymentDto;
import assesment.model.PaymentResponse;

public interface PaymentService {

	/**
	 * This method is used to return net amount after applying all valid offers.
	 * 
	 * @param payInfo
	 * @return
	 */
	public PaymentResponse getNetPayment(PaymentDto payInfo);

}
