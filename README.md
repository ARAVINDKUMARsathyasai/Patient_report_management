# Patient Report Management System
## Installation Steps 

* Ensure that you are using MySQL 8. If not, modify the following line in the configuration file (application.properties) to use the appropriate dialect for your   MySQL version: <br>
  **spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect**
* Check if SMTP port number 587 is available. If not, change it to port number 465. It is not recommended to use port number 25, as it is for plain text        communication.
*  Configure your email credentials in the application.properties file: <br>
     spring.mail.username=your-email@gmail.com <br>
     spring.mail.password=your-email-password <br>
     (*Note Note: It is recommended to enable two-step verification for your email account, generate an API password, and use that instead of your actual password.)
* Ensure that you are using Spring 3 or a newer version.
* Include the Spring Security 6 library in your project.
* Verify that the default port number 8080 is available for the Tomcat server. If not, change it using the following line in the application.properties file: <br>
  server.port=8081
            (*Note  port numbers 49152 and 65535 are considered safe to use for custom applications)

### Final steps

* Download the ZIP file of the project.
* Extract the ZIP file to your preferred location.
* Import the project into Eclipse or Spring Tool Suite.
* Allow the required dependencies to be downloaded automatically.
* Update your Maven file to resolve any missing dependencies.
* Ensure that no other servers are currently running.
* Start the server.

### Running the Application

* Once the application has started successfully, open any web browser on your machine.
* Enter the following address in the browser's address bar: **http://localhost:8080/**
* Follow the instructions provided in the user interface to proceed.


### Thank You
Thank you for using the Patient Report Management System. If you have any further questions or need assistance, please don't hesitate to reach out.
