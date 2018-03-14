# Player Management

Simple CRUD to manage football players info.

## Prerequisites

In order to run this app you need to install:

[Maven](https://maven.apache.org/)<br>
[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)<br>
[MySQL](https://dev.mysql.com/downloads/mysql/)

## Installing

Step by step in Linux terminal:

<b>1. Clone or download repository:</b>

```
git clone https://github.com/pnowak86/playerManagement.git
```

<b>2. Run SQL Script included in project:</b><br><br>
   In MySQL you will need to have registered user 'root' with password 'root', if required those data can be different, 
   but later in order to connect to database you will need to change database.user and database.password in playerManagement/src/main/resources/app.properties
```
mysql - root -p
Enter password:
```
   
```
mysql> path_to_app/../playerManagement/test_app.sql
```

```
mysql> exit
```

<b>3. Check Maven version to be sure you have it installed:</b>
```
mvn -version
```

<b>4. Run application:</b>
```
path_to_app/../playerManagement/mvn tomcat7:run
```

<b>5. Stopping application:</b>
```
Ctrl + C
```

## Access application
If app run correctly you can get access to it by opening your web browser and enter:

```
http://localhost:8080/player-management
```

Default username and password are shown on login page.

## Web Demo
If you don't want to run your app on your PC, there is a web demo up and running:

```
http://player-management.herokuapp.com/
```

Default username and password are shown on login page.

