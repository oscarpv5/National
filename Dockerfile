FROM    alpine:3.19.1 AS build_ops
        RUN     apk add --no-cache openjdk17-jdk maven
        COPY    . /tmp/build
        RUN     cd /tmp/build \
                && mvn clean package -Dmaven.test.skip=true \
                && cd target \
                && mv $(ls *.jar) /tmp/build/app.jar
FROM    bellsoft/liberica-openjdk-alpine:17
        COPY    --from=build_ops /tmp/build/app.jar /main/app.jar
        EXPOSE  9000
        ENTRYPOINT ["java", "-jar"]
        CMD     ["/main/app.jar"]
