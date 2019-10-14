/**
 * 
 */
package com.cg.frs.exception;

/**
 * @author NAVYA
 *
 */
public class FlightExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public FlightExceptions(String errMsg) {

		this.errMsg = errMsg;
	}

}
