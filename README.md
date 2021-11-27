ABN Amro Recipe application
===========================

Description:
------------

The application is a single module Spring Boot application, which exposes REST endpoints, uses basic authentication managing two roles with an in memory H2 db. 
It has a ThymeLeaf UI for demonstrating the usage of mentioned endpoints and provides and API documentation using Swagger UI.

Although the UI is not single page design, I still wanted to show the usage of the API with a user interface and it is
a convenient way of including the API documentation too.

I selected these frameworks and approaches for the sake of simplicity, in order to cover as many possible points in the assignment as effectively as possible.

Requirements:
-------------

- Java 8 (JAVA_HOME must be set for maven)
- Maven 3 (access to Maven Central)

Optional: 
- Docker

The zip file contains the source in the recipes folder. The project has to be built with maven. ("mvn clean install" - from recipes folder root)
Tests can be run using "mvn test".
In src/test/resources folder there is a recipes.feature file containing GivenWhenThen style test scenarios.

The application can be built into a docker image using maven and then started in a following way:
1. build image with "mvn spring-boot:build-image"
2. start with docker: docker run -it -d -p 8080:8080 recipes:1.0.0
3. application can be reached on localhost port 8080

If docker is not available the application can be started with the following spring boot maven goal:

mvn spring-boot:run

After this the UI can be reached on localhost port 8080

The spring boot actuator starter module provides endpoints for production related queries.

Notes:
------

The application exposes two set of endpoints, one for internal use and one for external. 
The external API can only be used with ADMIN role, while the internal also with USER.

Trying to access these endpoints without authentication will result in 401, while trying with the wrong role in a 403.
Credentials for these roles:

ADMIN - admin:admin
USER - user:user

The API documentation is available from the welcome page of the application.
Sample request to add a few lines of data is in sampleRequest_saverecipes.txt next to this file. (use /external/saverecipes POST endpoint)

Date format has to be in dd-MM-yyyy HH:mm format. (see sample request)

Limitations:
------------

- Adding new ingredients on the UI is not possible at the moment (removing and modifying them is working and they can be updated via the external endpoint properly)

Improvement points:

- Security is implemented in a very simple and definitely not the most sophisticated way, but I still wanted to secure the application at least a bit
- Fix adding of ingredients (due to the limitations of the template engine, adding new elements to a nested list is problematic without js hacks.)
- Create a separate client module using a dedicated javascript framework (Vue.js, React, etc)




