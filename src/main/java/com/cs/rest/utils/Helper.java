/**
 * 
 */
package com.cs.rest.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cs.rest.dto.EmployeeTO;

/**
 * The Class Helper.
 *
 */
public class Helper {

	/**
	 * Checks if is empty.
	 *
	 * @param inputString
	 *            the input string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(final String inputString) {
		return inputString == null || inputString.equals(Constants.EMPTY_STRING) ? true : false;
	}

	/**
	 * Gets the object from XML.
	 *
	 * @param xml
	 *            the xml
	 * @param classType
	 *            the class type
	 * @return the object from XML
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static EmployeeTO getObjectFromXML(final String xml, final Class<EmployeeTO> classType) throws IOException {
		JAXBContext jaxbContext;
		EmployeeTO employee = null;
		try {
			jaxbContext = JAXBContext.newInstance(EmployeeTO.class);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			final StringReader reader = new StringReader(xml);
			employee = (EmployeeTO) unmarshaller.unmarshal(reader);
		} catch (final JAXBException e) {
			e.printStackTrace();
		}
		return employee;
	}

}
