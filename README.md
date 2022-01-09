# crypto
In this application, algorithms of number theory and their use in cryptographic methods are  implemented. In the back-end part of the application, a number of RESTful APIs have been created that are consumed by the front-end part of the application. The principle of modularity and layering is respected in the application by dividing the main part of the application into three modules / layers. The first layer, ie the Controller layer, contains a set of end-points (URIs) that are mapped to the appropriate methods used to call the RESTful API. The second layer, which consists of an interface called CriptoService, lists the method declarations that are implemented in the third layer, called CriptoServiceImpl, which fully implemented the previously mentioned interface. In essence, all implementation of cryptographic algorithms takes place in the third layer. The connection between the first and the second layer is realized through dependency injection, which is one of the main characteristics of the Spring framework. 
Assumption is made that user (developer) has java 8 or higher, maven, git. Make sure you have installed all there prerequisites on your development machine.

In order to start everything up, follow the next steps:
1.	git clone https://github.com/vedad-varupa/crypto.git
This will clone the latest version of the crypto.
2.	mvn clean install This command is used to resolve dependencies that are listed in our pom.xml
3.	mvn spring-boot:run This command runs the application.

The client side in this application is created using Angular development technology. 
1. git clone https://github.com/vedad-varupa/crypto-frontend.git
