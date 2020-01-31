package assesment.controller;

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
	
	@PostMapping("/getNetPay")
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

}
