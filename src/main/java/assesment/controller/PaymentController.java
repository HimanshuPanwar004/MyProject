package assesment.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import assesment.model.PaymentDto;
import assesment.model.PaymentResponse;
import assesment.service.PaymentService;
import assesment.util.AppUtility;
import assesment.util.Constants;

/**
 * @author himanshupanwar
 *
 */
@RestController
public class PaymentController {

	@Autowired
	AppUtility utility;
	
	@Autowired
	PaymentService service;
	
	@PostMapping("/check")
	public ResponseEntity<Object> getNetPayment(@RequestBody @Valid PaymentDto payInfo, BindingResult result) {
		
		PaymentResponse response = new PaymentResponse();
		if(result.hasErrors()) {
			response.setErrorMessage(result.getFieldError().getField()+Constants.SPACE+result.getFieldError().getDefaultMessage());
			response.setErrorCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		response = service.getNetPayment(payInfo);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}


	@PostMapping("/netPay")
	public ResponseEntity<Object> getNetPayment(@Valid PaymentDto payInfo){

		String payableRequestInit = payInfo.getPayment();
		String userStatus = payInfo.getUserStatus();
		String isItemGrocery = payInfo.getIsGrocery();

		try {
			String payableRequest[] = payableRequestInit.split("\\$");
			String payable = payableRequest[1];
			BigDecimal netPayable = null;
			BigDecimal tempPay = new BigDecimal(payable);
			
			if(isItemGrocery.equals("T")) {
				if(tempPay.intValue()==100 || tempPay.intValue()>100) {
					netPayable = tempPay.subtract(tempPay.multiply(BigDecimal.valueOf(0.05)));
					String finalAmount =  utility.getCurrencyFormatter(netPayable.doubleValue());
					return new ResponseEntity<Object>(finalAmount, HttpStatus.OK);
				}
				return new ResponseEntity<Object>(payableRequestInit, HttpStatus.OK);
			}
			else {

				if(userStatus.equals("isEmp")) {
					if(tempPay.intValue()>100) {
						netPayable = tempPay.subtract(tempPay.multiply(BigDecimal.valueOf(0.05)));	
						tempPay = netPayable;
					}
					int percentage = 30;
					netPayable = tempPay.multiply(BigDecimal.valueOf((double)percentage/100));
					String finalAmount =  utility.getCurrencyFormatter(netPayable.doubleValue());
					return new ResponseEntity<Object>(finalAmount, HttpStatus.OK);
				}
				else if(userStatus.equals("isAffl")) {
					if(tempPay.intValue()>100) {
						netPayable = tempPay.subtract(tempPay.multiply(BigDecimal.valueOf(0.05)));	
						tempPay = netPayable;
					}
					int percentage = 10;
					netPayable = tempPay.multiply(BigDecimal.valueOf((double)percentage/100));
					String finalAmount =  utility.getCurrencyFormatter(netPayable.doubleValue());
					return new ResponseEntity<Object>(finalAmount, HttpStatus.OK);

				}
				else if(userStatus.equals("isCstmrYr2")) {
					if(tempPay.intValue()>100) {
						netPayable = tempPay.subtract(tempPay.multiply(BigDecimal.valueOf(0.05)));	
						tempPay = netPayable;
					}
					int percentage = 5;
					netPayable = tempPay.multiply(BigDecimal.valueOf((double)percentage/100));
					String finalAmount =  utility.getCurrencyFormatter(netPayable.doubleValue());
					return new ResponseEntity<Object>(finalAmount, HttpStatus.OK);

				}
				else {
					return new ResponseEntity<Object>(payableRequestInit, HttpStatus.OK);

				}		
			}
		}
		catch(Exception ex) {
			System.out.println("dsds");
		}
		return new ResponseEntity<Object>(payableRequestInit, HttpStatus.OK);

	}

	public static void main(String args[]) {

		String amount ="430.543322";
		BigDecimal amounts = new BigDecimal(amount);
		int percentage = 50;
		BigDecimal percentageAmount = amounts.multiply(BigDecimal.valueOf((double)percentage/100));
		double db = percentageAmount.doubleValue();
		System.out.println(db);

		String as = "350.99";
		BigDecimal am = new BigDecimal(as);
		if(am.intValue()>100) {
			BigDecimal percentageAmounts = am.subtract(am.multiply(BigDecimal.valueOf(0.05)));	

			System.out.println(percentageAmounts.toString());
		}

	}

}
