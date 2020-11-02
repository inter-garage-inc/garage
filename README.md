# Garage Inc.

## Overview
 TODO Description
 
## Requirements
* Java 11+
* Gradle
* MySQL 8+
* Lombok

## Database 
Configure your database:
``` 
CREATE DATABASE garage;
CREATE USER 'garage'@'%' IDENTIFIED WITH mysql_native_password BY 'G@rAg3Inc';
GRANT ALL PRIVILEGES ON garage.* to 'garage'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE garage_test;
GRANT ALL PRIVILEGES ON garage_test.* to 'garage'@'%';
FLUSH PRIVILEGES;
```

## Running
To start this app:
* Install dependencies running './script/bootstrap'
* Start server running './script/server'
* Running test './script/test'


## How to test
Open you console and enter command below:

```
curl -X POST  http://localhost:8080/users -d '{"username": "foo","password": "bar", "role": "ADMIN"}' -H "Content-Type: application/json" | python -m json.tool

```

Response:
```
{
    "id": 1,
    "username": "foo",
    "password": "bar",
    "role": "ADMIN"
}
```


After running the application, open your browser:

`http://localhost:8080/monitoring/heart_beat`


## Keywords
* Server and Datasource: Undertow and HikariCp
* Frameworks and libs: SpringBoot, Jersey and Hibernate.
* Software design: Immutability, Optionals, and also at least 'trying' to follow CleanArchitecture and Single Responsibility.
