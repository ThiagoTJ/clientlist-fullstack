# Client List - Fullstack Project

Projeto fullstack para estudo e prática de arquitetura frontend e backend.

## Tecnologias

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Swagger (springdoc-openapi)

### Frontend
- React
- Typescript
- Vite
- React Testing Library
- Vitest

---

## Funcionalidades

- Cadastro de clientes
- Listagem de clientes com paginação
- Validação de dados de entrada
- Tratamento global de erros
- Documentação automática da API
- Testes de hooks no frontend

---

## Arquitetura

### Backend
- Controller
- Service
- Repository
- DTO
- Entity
- Mapper
- Global Exception Handler

### Frontend
- Organização por páginas e hooks
- Camada de API
- Tratamento de loading e erro
- Testes automatizados

---

## Como executar

### Backend
```bash
cd backend/backend-java
mvn spring-boot:run
```

### Swagger
```bash
http://localhost:8080/swagger-ui.html
```

### H2
```bash
http://localhost:8080/h2-console
```

### Frontend
```bash
cd frontend/clientlist
npm i
npm run dev
```