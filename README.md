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
Java 11 (no real preference, I wasn't planning on using any feature from newer version)
MongoDB (makes complete sense with the the user/team domains to use a No-SQL option. Since this is domain service I would still opt for Mongo over Cassandra (free).
WebFlux/Reactor (most resource optimization JVM framework available for Webservices and completely compatible with No-SQL and Microservices)
Lombok (for speed of development and convenience)
Swagger (for API inbuilt documentation and facilitation of use)
JUnit/Mockito/Reactor-test (for testing. Just the default capabilities for Java unit testing)

## Observations
1. I could have added Liquidbase for database change management however is less painful to just call the API (it's very easy right)
2. Wanted to add Sleuth for out of the box Tracing but there something wrong with my local Gradle and I'm ok with the effort invested here already
3. Tests are implemented only partially for POC reference. Ideally I would do at least 100% condition coverage.
