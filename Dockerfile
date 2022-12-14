FROM openjdk:17

COPY target/ttl-cache-implementation-0.0.1-SNAPSHOT.jar /ttl-cahce-impl.jar

CMD ["java", "-jar", "/ttl-cahce-impl.jar"]