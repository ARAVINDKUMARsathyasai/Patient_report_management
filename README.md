# Patient Report Management System
## steps to install 

* Make sure your are using MYSql8 **( if not  "spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect" change this to MySQL5Dialect or any other dialect sutable for your SQL version )**
*  Make sure you SMPT port number **587** is free if not change that to port number **465** it is not recomended to use Port number 25 it is for the plain text
*  spring.mail.username=sanjanasupermart@gmail.com, <br>
   spring.mail.password=unilespupkvksahb these are my mail creadentials it preferd to use your own mail id <br>
       (*Note insted of adding the password rather you enable your mail two step verification, generate the api password and use that even this works fine.)
* Make sure you are using spring 3 or above version
* Include the Spring security6
* Make sure your port number 8080 is free for tomcat server change that using **server.port=8081** set your own preferd port number <br>
            (*Note  port numbers 49152 and 65535 are considered safe to use for custom applications)

### Final steps

* download the zip file 
* extract the zip file 
* load the file to eclipse or Spring tool suite 
* let the required dependies download 
* Once its done update your maven file 
* Make sure no servers are running
* Start the server

### How to run the Application 
* Once application is started 
* Open any browser in your machine
* enter **http://localhost:8080/** in the address bar
* For there follow the UI info

###### Thank You
