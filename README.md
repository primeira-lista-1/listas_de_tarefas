# Projeto: Gerenciamento de Tarefas (Task Manager)

Este projeto é uma API RESTful desenvolvida em **Java com Spring Boot** para gerenciar tarefas. Ele permite a criação, leitura, atualização e exclusão (CRUD) de tarefas, utilizando um banco de dados em memória **H2** para armazenamento. A API segue as boas práticas de desenvolvimento e é documentada com o **Swagger** para facilitar o teste e a integração.

---

## Funcionalidades Principais

- **Criação de Tarefas**: Crie novas tarefas com título, descrição e status de conclusão.
- **Listagem de Tarefas**: Obtenha uma lista de todas as tarefas cadastradas.
- **Busca de Tarefa por ID**: Recupere os detalhes de uma tarefa específica pelo seu ID.
- **Atualização de Tarefas**: Atualize os detalhes de uma tarefa existente.
- **Exclusão de Tarefas**: Remova uma tarefa do sistema.

---

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento rápido de aplicações Java.
- **Spring Data JPA**: Camada de persistência para interação com o banco de dados.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Swagger (springdoc-openapi)**: Documentação automática da API.
- **Maven**: Gerenciamento de dependências e build do projeto.
- **Docker**: Para conteinerização da aplicação.
- **Docker Compose**: Para gerenciar o container da aplicação e do banco de dados.

---

## Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto localmente.

### Pré-requisitos

- Java 21 instalado.
- Maven instalado.
- Git instalado (opcional, para clonar o repositório).
- Docker e Docker Compose instalados (para execução com contêineres).

### Passos

#### Executar sem Docker

1. **Clone o Repositório**:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio
```

2. **Compile o Projeto**:

```bash
mvn clean package -DskipTests
Execute o Projeto:
```

3. **Execute o Projeto**:

```bash
mvn spring-boot:run
```
4. **Acesse a API**:

Acesse o Swagger UI em http://localhost:9000/swagger-ui/index.html#/ para testar os endpoints.

<!-- 5. **Banco de Dados H2**:

O console do H2 pode ser acessado em http://localhost:8080/h2-console.

Use as credenciais configuradas no application.properties (usuário sa, senha password). -->

## 🐳 Executando com Docker-Compose  

Caso prefira rodar a aplicação dentro de um contêiner Docker, siga os passos abaixo:  

### 1️⃣ Clone o Repositório  

#### Se ainda não clonou o repositório, execute  

### 2️⃣. Gere o arquivo .jar localmente (se estiver usando Maven)
```bash
./mvnw clean package -DskipTests
```
### 4️⃣. Verifique se o .jar foi criado
```bash
ls -l target/*.jar
```
### 3️⃣ Construa a Imagem Docker

```bash
docker-compose build --no-cache
```
### 5️⃣ Execute o Contêiner
```bash
docker-compose up -d
```
### 6️⃣ Limpeza do Ambiente (Se Necessário)
```bash
docker-compose down -v
```
### 7️⃣ Acesse a API

#### Após o contêiner estar rodando, você pode acessar os seguintes links:

- Swagger UI: http://localhost:9000/swagger-ui/index.html#/




## Endpoints da API

A API oferece os seguintes endpoints:

| **Método HTTP** | **Endpoint**         | **Descrição**                               |
|------------------|----------------------|---------------------------------------------|
| GET              | /api/tasks          | Retorna todas as tarefas.                  |
| GET              | /api/tasks/{id}     | Retorna uma tarefa específica pelo ID.     |
| POST             | /api/tasks          | Cria uma nova tarefa.                      |
| PUT              | /api/tasks/{id}     | Atualiza uma tarefa existente.             |
| DELETE           | /api/tasks/{id}     | Exclui uma tarefa pelo ID.                 |

# Exemplos de Uso

## Criar uma Tarefa

### Requisição:

```bash
POST /api/tasks
Content-Type: application/json

{
  "title": "Estudar Spring Boot",
  "description": "Aprender a criar APIs RESTful com Spring Boot.",
  "completed": false
}
```

### Resposta:

```bash
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Aprender a criar APIs RESTful com Spring Boot.",
  "completed": false
}
```
### Listar Todas as Tarefas
## Requisição:

```bash
GET /api/tasks
```
### Resposta:

```bash
[
  {
    "id": 1,
    "title": "Estudar Spring Boot",
    "description": "Aprender a criar APIs RESTful com Spring Boot.",
    "completed": false
  }
]
```
### Atualizar uma Tarefa
### Requisição:

```bash
Copy
PUT /api/tasks/1
Content-Type: application/json

{
  "title": "Estudar Spring Boot e JPA",
  "description": "Aprender a criar APIs RESTful e usar JPA com Spring Boot.",
  "completed": true
}
```
### Resposta:

```bash
{
  "id": 1,
  "title": "Estudar Spring Boot e JPA",
  "description": "Aprender a criar APIs RESTful e usar JPA com Spring Boot.",
  "completed": true
}
```

# Estrutura do Projeto
```bash
src/main/java  
└── com/primeira_lista/primeiro  
    ├── controller  
    │   └── TaskController.java  
    ├── model  
    │   └── Task.java  
    ├── repository  
    │   └── TaskRepository.java  
    └── PrimeiroApplication.java
```

# Configurações do Projeto
#### application.properties
**properties**

```bash
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
Dependências do Maven (pom.xml)
xml
Copy
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.8.5</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

# Como Contribuir

1. **Faça um fork do repositório**.

2. **Crie um branch para sua feature**:  
```bash
   git checkout -b feature/nova-feature
```
3. **Faça commit das suas alterações**:
```bash
   git commit -m 'Adiciona nova feature'
```
4. **Envie o branch para o repositório remoto**:
```bash
git push origin feature/nova-feature
```
5. **Abra um pull request**.
    Com tudo organizado e confira se todos os arquivos foram enviados corretamente.