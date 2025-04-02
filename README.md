# OpenWeather API

API para consulta de dados climáticos utilizando a API OpenWeather.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.3
- PostgreSQL
- Docker
- OpenWeather API
- Swagger/OpenAPI

## Requisitos

- Java 17
- Docker e Docker Compose
- Chave de API do OpenWeather

## Configuração

1. Clone o repositório
2. Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:
   ```
   OPENWEATHER_API_KEY=
   SPRING_DATASOURCE_URL=
   SPRING_DATASOURCE_USERNAME=
   SPRING_DATASOURCE_PASSWORD=
   ```

## Executando a Aplicação

1. Construa a imagem Docker:
   ```bash
   docker-compose build
   ```

2. Inicie os containers:
   ```bash
   docker-compose up
   ```

A aplicação estará disponível em `http://localhost:8080`

## Endpoints Disponíveis

### 1. Buscar Dados Climáticos Atuais
```
GET /api/clima/{cidade}
```
Retorna os dados climáticos atuais de uma cidade específica.

### 2. Buscar Previsão do Tempo
```
GET /api/clima/previsao/{cidade}
```
Retorna a previsão do tempo para uma cidade específica.

### 3. Listar Histórico de Consultas
```
GET /api/clima/historico
```
Retorna o histórico de todas as consultas climáticas realizadas.

## Documentação da API

A documentação completa da API está disponível através do Swagger UI em:
```
http://localhost:8080/swagger-ui
``` 