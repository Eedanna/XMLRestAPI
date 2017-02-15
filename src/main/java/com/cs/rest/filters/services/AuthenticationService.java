/**
 * 
 */
package com.cs.rest.filters.services;

import com.cs.rest.utils.Constants;
import com.cs.rest.utils.Helper;

/**
 * The Class AuthenticationService
 * 
 */
public class AuthenticationService {

	/**
	 * Authenticate.
	 *
	 * @param authToken
	 *            the auth token
	 * @return true, if successful
	 */
	public boolean authenticate(final String authToken) {

		if (Helper.isEmpty(authToken))
			return false;

		boolean authenticationStatus = Constants.CS_POC.equalsIgnoreCase(authToken);
		return authenticationStatus;
	}
}
