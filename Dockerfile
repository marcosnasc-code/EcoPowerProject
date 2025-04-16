# Builda o ambiente
FROM openjdk:17-jdk-slim
VOLUME /tmp

# Expoe a porta usada pela aplicação
EXPOSE 8080

# Copia o arquivo .jar para dentro da imagem
ARG JAR_FILE=target/EcoPower-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Define o perfil properties a ser usado
ENV PROFILE=docker

# Define o comando de execução
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=${PROFILE}"]
