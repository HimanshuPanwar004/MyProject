package assesment.model;

import java.io.Serializable;

/**
 * @author himanshupanwar
 *
 */
public class PaymentResponse implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String netPayable;
	
	private String errorMessage;
	
	private int errorCode;

	public PaymentResponse() {
		super();
	}

	/**
	 * @return the netPayable
	 */
	public String getNetPayable() {
		return netPayable;
	}

	/**
	 * @param netPayable the netPayable to set
	 */
	public void setNetPayable(String netPayable) {
		this.netPayable = netPayable;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "PaymentResponse [netPayable=" + netPayable + ", errorMessage=" + errorMessage + ", errorCode="
				+ errorCode + "]";
	}

}
