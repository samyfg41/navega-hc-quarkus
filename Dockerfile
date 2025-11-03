# Use imagem com Maven e Java 17
FROM maven:3.9-eclipse-temurin-17-alpine AS build

# Cria e muda para o diretório da aplicação
WORKDIR /app

# Copia o pom.xml primeiro (para cache de dependências)
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o resto do código
COPY src ./src

# Compila a aplicação
RUN mvn clean package -DskipTests

# Segunda etapa - Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o JAR compilado da etapa anterior
COPY --from=build /app/target/quarkus-app/ ./

# Expõe a porta 8080
EXPOSE 8080

# Roda a aplicação
CMD ["java", "-jar", "quarkus-run.jar"]
