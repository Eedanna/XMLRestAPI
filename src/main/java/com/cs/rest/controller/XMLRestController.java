package com.cs.rest.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.cs.rest.dto.EmployeeTO;
import com.cs.rest.kafka.KafkaProduce;
import com.cs.rest.utils.Constants;
import com.cs.rest.utils.Helper;

/**
 * 
 * The Class XMLRestController.
 */
@Component
@Path("/employee")
public class XMLRestController {

	private static final Logger LOGGER = Logger.getLogger(XMLRestController.class);

	/**
	 * Read XML data.
	 *
	 * @param xml
	 *            the xml
	 */
	@POST
	@Path("/readXMLData")
	public void readXMLData(@RequestBody final String xml) {
		try {
			final EmployeeTO employee = Helper.getObjectFromXML(xml, EmployeeTO.class);
			LOGGER.info(employee.getId() + " ::: " + employee.getName());

			StringBuilder query = new StringBuilder();
			query.append("Select * from employee where id=" + employee.getId());
			query.append("' and name='" + employee.getName());
			query.append("';");

			LOGGER.info("SB Query >>>> " + query.toString());

			// push query string to Kafka
			KafkaProduce.sendMessageToProducer(Constants.TOPIC_NAME, query.toString());

		} catch (Exception ex) {
			LOGGER.error("Exception Occured :: XMLRestController :: readXMLData  due to  >> " + ex.getStackTrace());
		}
	}

}
