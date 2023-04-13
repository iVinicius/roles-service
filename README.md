# Roles-Service

Roles service enhances the Users and Teams, by giving the concept of team roles and the ability to associate them with team members.

## Installation

Inside source folder open the Terminal and execute the below commands sequentially.

```bash
./gradlew build
docker-compose build
docker-compose up
```

## Usage

```python

# use any tool to execute HTTP request like the below
curl -X POST "http://localhost:8080/roles" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"roleTypeName\": \"PROJECT_MANAGER\"}"

# or you can refer to Swagger UI and help yourself
http://localhost:8080/swagger-ui/index.html#/
```

## Technologies Used
1. Java 11 
2. MongoDB 
3. WebFlux/Reactor
4. Lombok 
5. Swagger 
6. JUnit/Mockito/Reactor-test
