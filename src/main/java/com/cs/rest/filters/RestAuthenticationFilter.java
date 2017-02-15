package com.cs.rest.filters;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cs.rest.filters.services.AuthenticationService;
import com.cs.rest.utils.Constants;

/**
 * The Class RestAuthenticationFilter.
 */
public class RestAuthenticationFilter implements Filter {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(RestAuthenticationFilter.class);

	/** The Constant AUTHENTICATION_HEADER. */
	public static final String AUTHENTICATION_HEADER = "token";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authToken = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

			try {
				final byte[] decodedBytes = Base64.getDecoder().decode(authToken);
				authToken = new String(decodedBytes, Constants.STR_UTF_8);
			} catch (IOException e) {
				logger.error(":: Decoding the string failed ::");
			}

			final AuthenticationService authenticationService = new AuthenticationService();

			final boolean authenticationStatus = authenticationService.authenticate(authToken);

			if (authenticationStatus) {
				filter.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
