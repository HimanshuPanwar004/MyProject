package assesment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import assesment.model.PaymentDto;
import assesment.model.PaymentResponse;
import assesment.service.PaymentService;

@SpringBootTest
public class PaymentControllerTest {

	@InjectMocks
	private PaymentController controller;
	
	@Mock
	PaymentService service;
	
	@Mock
	BindingResult result;
	
	@Test
	public void getNetPaymentTest() throws Exception {
		
		when(service.getNetPayment(Mockito.any(PaymentDto.class))).thenReturn(getPaymentResponse());
		ResponseEntity<Object> response = controller.getNetPayment(getPaymentRequest(), result);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	private PaymentDto getPaymentRequest() {
		PaymentDto request = new PaymentDto();
		request.setIsGrocery("T");
		request.setPayment("$100");
		request.setUserStatus("isEmp");
		return request;
	}
	
	private PaymentResponse getPaymentResponse() {
		PaymentResponse response = new PaymentResponse();
		response.setErrorCode(0);
		response.setErrorMessage(null);
		response.setNetPayable("$66.50");
		return response;
	}
	
	
	
}
