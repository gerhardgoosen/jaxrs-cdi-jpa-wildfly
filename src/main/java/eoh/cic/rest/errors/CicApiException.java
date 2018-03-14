package eoh.cic.rest.errors;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CicApiException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5631611512948616688L;
	private static final Logger log = Logger.getLogger(CicApiException.class.getName());
	
	public CicApiException(Exception e) {
		log.log(Level.SEVERE,"CicApiException [ {0} ]", e.getMessage());
		this.setStackTrace(e.getStackTrace());
	}

}
