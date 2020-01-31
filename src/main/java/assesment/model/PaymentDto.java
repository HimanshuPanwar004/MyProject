package assesment.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author himanshupanwar
 *
 */
public class PaymentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String payment= null;

	@NotEmpty
	private String userStatus= null;

	@NotNull
	private String isGrocery = null;

	public PaymentDto() {
		super();
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the isGrocery
	 */
	public String getIsGrocery() {
		return isGrocery;
	}

	/**
	 * @param isGrocery the isGrocery to set
	 */
	public void setIsGrocery(String isGrocery) {
		this.isGrocery = isGrocery;
	}

	@Override
	public String toString() {
		return "PaymentDto [payment=" + payment + ", userStatus=" + userStatus + ", isGrocery=" + isGrocery + "]";
	}

}
