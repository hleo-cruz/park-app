# 🅿️ Park App – Sistema de Estacionamento

Este projeto foi desenvolvido como parte de um **teste técnico de avaliação** para demonstrar domínio sobre **Java com Spring Boot**, arquitetura em camadas, integração com banco de dados via JDBC e JPA, utilização de Flyway para versionamento de schema, boas práticas de organização de código e testes unitários.

---

## 📌 Objetivo do Projeto

A aplicação simula o controle de um estacionamento, com funcionalidades como:

- Entrada e saída de veículos
- Alocação dinâmica de vagas 
- Cálculo de cobrança com base na lotação atual
- Consulta de status por placa ou localização
- Relatórios de receita por setor e data

---

## ⚙️ Tecnologias e Bibliotecas

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Web, JDBC, Data JPA**
- **Flyway** – versionamento do banco de dados
- **H2 Database** – usado para testes e dev local
- **PostgreSQL** – ambiente de produção
- **Swagger / OpenAPI** – documentação automática
- **Lombok** – geração de boilerplate
- **Commons Lang3** – utilidades
- **JUnit 5 + Mockito** – testes unitários e de integração

---

## 🗂️ Estrutura dos Pacotes

```text
com.br.park
├── api                     # Controllers e DTOs
├── services                # Regras de negócio e strategy chains
│   ├── webhook             # Estratégias baseadas em tipo de evento
│   ├── calculateprice      # Lógica de precificação
│   └── chain               # Estratégia para preço dinâmico por lotação
├── infrastructure
    └── client              # Client HTTP para consumo da api disponibilizada
│   └── repository          # Repositórios e entidades (JPA / JDBC)
├── mapper                  # Conversores entre Entidades e DTOs
├── config                  # Configurações de contexto, H2, startup
└── util                    # Utilitários de logging e helpers
```

---

## ▶️ Como Rodar Localmente

### Pré-requisitos:
- Java 21+
- Maven 3.8+
- PostgreSQL (opcional)
- Docker (opcional)

### Passos:

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/park-app.git
cd park-app

# 2. Rode os testes
mvn test

# 3. Execute o app
mvn spring-boot:run
```

A aplicação rodará em: `http://localhost:8080`

---

## 📈 Documentação da API

Após subir a aplicação, acesse a documentação interativa:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Testes

O projeto inclui testes:

- **Unitários**: para services, chain e cálculo de valores com `Mockito`
- **MockMvc (`@WebMvcTest`)**: cobertura dos controllers REST
- **Cobertura de chain of responsibility**: cada regra de lotação foi testada isoladamente

### Rodar testes:

```bash
mvn test
```

---

## 🧠 Estratégias Arquiteturais

- **Separação por responsabilidade (SRP)**: camadas bem definidas
- **Strategy + Chain of Responsibility**: usada para regras de precificação
- **Resolução dinâmica via ApplicationContext**: aplicada para eventos de webhook
- **Flyway**: versionamento e evolução controlada do schema
- **Testes em todas as camadas críticas**

---

## 🚀 Diferenciais do Projeto

- ✅ Padrões de projeto aplicados (Strategy, Chain, Factory)
- ✅ Estrutura limpa e escalável
- ✅ Cálculo de preço desacoplado da entidade
- ✅ Extensível para novos tipos de evento sem impacto no controller
- ✅ Configurações isoladas por ambiente (`application-dev.yml`, `application-conteiner.yml`)
- ✅ Uso correto de logs (`LogUtil` com padronização de verbosidade)

---


## 📄 Autor

Desenvolvido por **Léo** como parte de um processo seletivo.  

---
