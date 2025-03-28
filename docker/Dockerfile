# Estágio de build
FROM amazoncorretto:21 AS builder

# 1. Instala as dependências necessárias
RUN yum install -y tar gzip findutils \
    && yum clean all

# 2. Configura o workspace
WORKDIR /app

# 3. Copia primeiro os arquivos de build
COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .

# 4. Dá permissões e baixa dependências
RUN chmod +x mvnw \
    && ./mvnw dependency:go-offline -B

# 5. Copia o restante do código
COPY src ./src

# 6. Executa o build
RUN ./mvnw clean package -DskipTests

# Estágio de execução
FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]