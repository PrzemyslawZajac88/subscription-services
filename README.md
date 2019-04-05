
# HOW TO RUN
* cd "root-folder" 
* mvn clean install
* setup in docker-compose.yml:
  * AMAZON_ACCESS_KEY: "test"
  * AMAZON_SECRET_KEY: "test"
  * KAFKA_ADVERTISED_HOST_NAME: "localhost" or "127.0.0.1" or "10.0.75.1" (DockerNAT ip)
* docker-compose up

## Additional information
* Framework: Spring Boot 
* Database: AWS DynamoDb 
* Cache: EhCache (in memory)
* Architecture: Onion Architecture + DDD

