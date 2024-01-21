# Campus Management System

## Introduction
This is a simple demo project that demonstrates the use of a 
frontend-backend separation architecture with Spring (backend) and 
Vue (frontend). The demo aims to provide a basic understanding of how 
a real-world business website can be structured using this architecture.
This is a demo project where it involves the CRUD 
(Create, Read, Update, Delete) operation to MySQL databases, a web page for 
those operations and the corresponding RESTful APIs. 

For quick view online, please go to www.allendu.me

## Technology Stack
Backend tools: SpringBoot 2.x, JDK 1.8, Maven, MyBatis

Database: MySQL 8.0

Front-end tools: Vue.js 3.0, node.js 20.x.x

## Run the Program on Docker
After downloading the files, you can either use docker build and docker run to create the docker images
and docker containers on your own computer, or get the docker images directly from docker hub. 
Here is the link: https://dockerhub/XiandaDu. 

There are three images, ons is for the web display which 
dockerized the vue part. Another one dockerized the RESTful APIs. The last one dockerized some initial 
data in the MySQL databases. 

Either way you created the docker images, you need to do several things to get started.
1. RESTful APIs and the MySQL database is connected by docker network. Thus, remember to create a docker network
and connect RESTful APIs and the database correctly.
(assume you are in the readme folder)
````
docker network create springboot-mysql-net
docker run --name mysqldb --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=1234567890 springserver
docker run --name spring-container --network springboot-mysql-net mysql
````

2. We need to add data into the mysql image
copy the sql script into the container
````
docker cp ./Backend_CRUD/database/all_db.sql mysqldb:/docker-entrypoint-initdb.d/
````
turn on the container terminal
````
docker exec -it mysqldb -u root -p
````
input the password, the default password is 1234567890. 

run the sql script to import the initial data
````
mysql ./docker-entrypoint-initdb.d/all_db.sql
````
Then first_db should have been created.

3. Check if connected successfully
````
curl http://localhost:8081/student/hello
````
If you see "Hello world", it means that the RESTful APIs are working.

## Run the Program in Development Environment
You should have at least maven, node.js, and MySQL on your computer before proceeding. 
1. Goto Views/curd_project_views. Enter in terminal "npm run dev"
2. Open Backend_CRUD/src/main/resources/application.properties. 
Change *spring.datasource.url=jdbc:mysql://mysqldb:3306/first_db....* 
into *spring.datasource.url=jdbc:mysql://localhost:3306/first_db....* Then you can run Spring
3. Run the all_db.sql script under Backend_CRUD/database to create the required table
and some initial data. 
4. Navigate to http://localhost:5170/welcome on your web browser to access the demo.
