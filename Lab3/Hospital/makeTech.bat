javac -d . -cp .;../../libs/amqp-client-4.0.2.jar;../../libs/amqp-client-4.1.0-javadoc;../../libs/slf4j-api-1.7.21.jar;../../libs/slf4j-simple-1.7.22.jar ../../src/hospital/tech/Tech.java
java -cp .;;../../libs/amqp-client-4.0.2.jar;../../libs/amqp-client-4.1.0-javadoc;../../libs/slf4j-api-1.7.21.jar;../../libs/slf4j-simple-1.7.22.jar hospital.tech.Tech %1 %2
