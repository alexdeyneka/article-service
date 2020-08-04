## Article Service

### HTTP interfaces:

- User Controller
```
http://${host}:${port}/user/age (GET)
http://${host}:${port}/user/color (GET)
http://${host}:${port}/user/quantity (GET)
http://${host}:${port}/user/save (POST)
```
- Article Controller
```
http://${host}:${port}/article/save (POST)
```
- Web Authentication Controller
```
http://${host}:${port}/authenticate (POST)
```
- H2 DataBase Console
```
http://${host}:${port}/h2-console
Example: http://localhost:8081/h2-console

Login: admin
Password: <no password>
```
	
### Building the service
Run the following maven command:  
- ```mvn clean install```

### Usage
```
To launch the application 
1) Start ArticleServiceApplication.java: open it in Intellij IDEA, and press Ctrl + Shift + F10
2) Import resources/article-service.postman_collection.json to Postman
3) You are welcome to run Postman requests with default or custom payload
```

