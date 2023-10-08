# Spring Phones REST - Phones
Simple demo Spring REST backend app for phones

## Running locally

### With maven command line

```shell
git clone https://github.com/bledari/spring-phones-rest.git
cd spring-phones-rest
./mvnw spring-boot:run
```

## Routes

You can then access phones here: [http://localhost:9001/phones/](http://localhost:9001/phones/)

There is an actuator health check route:
* [http://localhost:9001/phones/actuator/health](http://localhost:9001/phones/actuator/health)

### REST endpoints

```text
GET 'http://localhost:9001/phones/api/phones/'
PUT 'http://localhost:9001/phones/api/phones/book/<phoneID>'
PUT 'http://localhost:9001/phones/api/phones/return/<phoneID>'
```

## Test

### cURL on the command line

List phones
```shell
curl --location 'http://localhost:9001/phones/api/phones/' --header 'Accept: application/json'
```
Book a phone
```shell
curl --location --request PUT 'http://localhost:9001/phones/api/phones/book/1' --header 'Content-Type: application/json' --header 'Accept: application/json' --data '{
  "booker": "New Owner"
}'
```
Return a phone
```shell
curl --location --request PUT 'http://localhost:9001/phones/api/phones/return/1' --header 'Content-Type: application/json' --header 'Accept: application/json'
```

Hint: Import the cURL commands in Postman or similar to be able to test there.

## Database configuration

In its default configuration, Phones uses an in-memory database (HSQLDB) which
gets populated at startup with data.

### Other databases
Work in progress


## Security 

### Security configuration
In its default configuration, Phones doesn't have authentication and authorization enabled.

#### Basic Authentication
Work in progress

### Validation
Various validations are baked into the app.  

## QA / Testing
There are some tests but it is still work in progress

## Working with IDES

### Steps:

1) In the command line
```
git clone https://github.com/bledari/spring-phones-rest.git
```
2) Inside IDEs

   See specific guidelines on importing Maven project for each IDE


## Publishing a Docker image

Testing phase. Work in progress

Command line to test and report issues:
```
mvn compile jib:build -X -DjibSerialize=true -Djib.to.auth.username=xxx -Djib.to.auth.password=xxxxx
```


