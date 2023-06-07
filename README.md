
## Cabana - A Real Estate Search Engine - Project by Group 14 


## Tech stack used 

``` 
Crawler: Python script

Backend: Java + Spring Boot + JDBCTemplate 

GUI: Angular + HTML + CSS + TypeScript

Database: MySQL 

Working: We have used Spring JDBCTemplate along with Java to create the backend application. 
Once the server is up and running we connect the GUI with the backend server using REST API calls.
The user can perform rental search by entering the point of interest in the home page input field. 
``` 


## Starting backend server 

``` 
1. Download the file: source_code.zip uploaded in our group submission. 

2. Go to the directory “<path-of-folder-where-zip-file-is-downloaded>/AIC_Cabana/backend_service/src/main/java/com/aic/cabana/spring/files/excel/Main.java” 

on your IDE (either Eclipse or Intellij) 

3. Right Click on the Main.java and select "Run Application". 

4. The server should start at this point. 
``` 


## Starting the GUI 

``` 
1. Once the application is running, goto the following path "AIC_Cabana/angular". 

2. Run the command "npm run install" in order to download all the required dependencies. 

3. Run the command "ng serve --open". 

4. You should see a new page opening in your browser at "http://localhost:4200/". 
``` 


## MySQL DB Details 

``` 
1. MySQL username/password is set in the application.properties file in the following path  

"AIC_Cabana/backend_service/src/main/resources/application.properties" 

2. Update the username/password based on your local mysql 
``` 


 
