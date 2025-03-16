# EchoBoard

[//]: # ([![Quality Gate Status]&#40;https://sonarcloud.io/api/project_badges/measure?project=TODO-set-your-id&metric=alert_status&#41;]&#40;https://sonarcloud.io/dashboard?id=TODO-set-your-id&#41;)
[//]: # ([![Integration Tests]&#40;https://github.com/pagopa/<TODO-repo>/actions/workflows/ci_integration_test.yml/badge.svg?branch=main&#41;]&#40;https://github.com/pagopa/<TODO-repo>/actions/workflows/ci_integration_test.yml&#41;)

## Description
A platform to collect anonymous or public feedback between colleagues, promoting communication, growth and continuous improvement. 

**Your Voice, your impact – share, grow, thrive.**


---

## API Documentation 📖

See the [OpenApi 3 here.](https://editor.swagger.io/?url=https://raw.githubusercontent.com/aomegax/echoboard-be/main/openapi/openapi.json)

---

## Technology Stack

- Java 17
- Spring Boot 3
- Spring Security 6
- Hibernate
- JPA
- Redis
- 
- TODO

---

## Develop Locally 💻

### Prerequisites

- git
- gradle 8.13
- jdk-17

#### Redis
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 -v ~/Development/redis-data:/data redis/redis-stack:latest

#### Cert
- Generate a Private Key (RSA):  
`openssl genpkey -algorithm RSA -out private-key.pem`  
- Generate the related Public Key  
`openssl rsa -pubout -in private-key.pem -out public-key.pem`  
- Convert PCKS
`openssl pkcs8 -topk8 -inform PEM -outform PEM -in private-key.pem -out private-key-pkcs8.pem -nocrypt`


### Run the project

#### Install gradle
sdk install gradle 8.13

#### Install cassandra
docker pull cassandra:latest


## Contributors 👥

Made with ❤️ by myself.

### Maintainers

See `CODEOWNERS` file
