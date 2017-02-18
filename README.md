# XMLRestAPI
Rest Service to parse XML data
 
Steps to execute this project

1. Clone the code base from Github

2. Import Project as Maven in eclipse IDE

3. Execute below maven commands
  mvn clean
  mvn install
  
 4. Add all the libs to the Build Project
 
 5. Add Tomcat Server (8 / 9 versions is okay)
 
 6. Add project to the Tomcat
 
 Use any below tool to trigger the request 
 
   1. POSTMAN
   2. REST ADVANCED CLIENT
   3. SOAP UI
   
  7. Request URL for reference
  http://localhost:8080/XMLRestAPI/rest/employee/readXMLData
  
  Method Type : POST
  
  Sample XML Request
 ------------------------- 
  <?xml version="1.0" encoding="UTF-8"?>
<employee id="1">
<name>Test</name>
<age>25</age>
<address>BENG</address>
<city>BENG</city>
<state>KA</state>
<pincode>560045</pincode>
</employee> 

8. Basic Authentication has been added
Use the below token information for authentication in any of the tools.

toekn = Y3Nwb2M=
