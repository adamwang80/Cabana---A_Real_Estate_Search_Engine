
## Cabana - A Real Estate Search Engine
<img width="1292" alt="image" src="https://github.com/adamwang80/Cabana---A_Real_Estate_Search_Engine/assets/67961987/4d0f09f8-2764-499b-806d-cfdc0e9a8869">
<img width="1163" alt="image" src="https://github.com/adamwang80/Cabana---A_Real_Estate_Search_Engine/assets/67961987/a05e6ff5-c0aa-4b3a-a7d3-105fa2e7825d">
<img width="719" alt="image" src="https://github.com/adamwang80/Cabana---A_Real_Estate_Search_Engine/assets/67961987/ec56342c-5766-46b7-b7dc-77db79c17e87">
![image](https://github.com/adamwang80/Cabana---A_Real_Estate_Search_Engine/assets/67961987/74668d1d-1316-4931-b973-b70b0d168ab3)


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


 
