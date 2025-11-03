# Use Eclipse Temurin Java 17 (igual ao pom.xml)
FROM eclipse-temurin:17-jdk-alpine

# Cria e muda para o diretório da aplicação
WORKDIR /app

# Copia todo o código local para o container
COPY . ./

# Dá permissão de execução no mvnw
RUN chmod +x mvnw

# Compila a aplicação
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Expõe a porta 8080
EXPOSE 8080

# Roda a aplicação Quarkus
CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]
