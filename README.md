MessageBoardSpring
==================
A little bit more sophisticated Message Board Application using Spring and Apache CXF.

The Message Board Application supports both REST and SOAP using Java First annotations.



Install and test instructions:
--------------

mvn clean install

import the war file target/MessageBoardSpring.war into your favorite web container

or use the maven plugin part of the pom.xml for testing.

mvn jetty:run

URLs for both WADL and WSDL that are generated from the Java annotations.

REST WADL:
http://localhost:8080/services/rest?_wadl

SOAP WSDL:
http://localhost:8080/services/MessageBoard?wsdl


You can use curl to test REST

curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/services/rest/messageboard/createMessage --data '{"title":"sample title", "content": "sample content", "sender": "sample sender", "url": "http://google.com" }'

curl -v -H "Accept: application/json" http://localhost:8080/services/rest/messageboard/listMessages

curl -v -H "Accept: application/xml" http://localhost:8080/services/rest/messageboard/listMessages/v2

curl -v -H "Accept: application/json" http://localhost:8080/services/rest/messageboard/get?messageId=2

curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/services/rest/messageboard/update --data '{"id": "2", "title":"sample title 2", "content": "sample content 2", "sender": "sample sender", "url": "http://google.com" }'

curl -v -H "Accept: application/json" http://localhost:8080/services/rest/messageboard/remove?messageId=2

curl -v -H "Accept: application/json" http://localhost:8080/services/rest/messageboard/clear

For SOAP use for example sopaui and point it to the wsdl URL.


The soapui folder contains an soapui project file with test suites for both SOAP and REST that can be used for testing
with Soapui.


