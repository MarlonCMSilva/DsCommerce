# DSCommerce: Plataforma de E-commerce (API RESTful)

## 🚀 Visão Geral do Projeto

O **DSCommerce** é uma solução de e-commerce robusta e moderna, desenvolvida como uma **API RESTful** completa utilizando o ecossistema **Spring Boot 3** e **Java 21**. Este projeto foi concebido para demonstrar proficiência em arquitetura de software, segurança, persistência de dados e implementação de regras de negócio complexas, sendo um ativo de destaque para qualquer portfólio técnico.

A aplicação simula um sistema de comércio eletrônico, gerenciando produtos, categorias, pedidos, itens de pedido e autenticação de usuários (clientes e administradores).

---

## ✨ Destaques Técnicos e Competências de Mercado

Esta seção detalha as tecnologias e os padrões de desenvolvimento aplicados, demonstrando a capacidade de construir sistemas de nível profissional.

| Área de Competência | Tecnologia/Padrão Aplicado | Descrição e Valor para o Mercado |
| :--- | :--- | :--- |
| **Backend Core** | **Java 21** e **Spring Boot 3.5.5** | Utilização da versão LTS mais recente do Java e do framework líder de mercado para desenvolvimento de APIs escaláveis e de alta performance. |
| **Arquitetura** | **API RESTful** e **Arquitetura em Camadas** | Implementação de endpoints REST seguindo os princípios HATEOAS (implícito), com separação clara entre `Controllers`, `Services` e `Repositories` (Padrão **MVC** / **Service Layer**). |
| **Segurança** | **Spring Security 6** e **OAuth 2.0 (Resource Server)** | Implementação de um fluxo de autenticação e autorização moderno e seguro baseado em **JWT (JSON Web Tokens)**, garantindo a proteção de rotas e dados sensíveis. |
| **Persistência** | **Spring Data JPA** e **Hibernate** | Mapeamento Objeto-Relacional (ORM) eficiente, utilizando entidades, DTOs e consultas otimizadas para manipulação de dados. |
| **Modelagem de Dados** | **Modelagem Relacional** e **Banco de Dados H2** | Design de um esquema de banco de dados relacional para e-commerce (produtos, pedidos, usuários, etc.), com uso de chaves primárias compostas (`@EmbeddedId`) para entidades de relacionamento (`OrderItemPK`). |
| **Boas Práticas** | **DTOs (Data Transfer Objects)** | Uso rigoroso de DTOs para desacoplar a camada de persistência da camada de apresentação, garantindo segurança e flexibilidade na comunicação. |
| **Tratamento de Erros** | **Tratamento Global de Exceções** | Implementação de um `ControllerAdvice` (`ControllerExceptionHandler`) para padronizar as respostas de erro (404 Not Found, 403 Forbidden, 400 Bad Request, 422 Unprocessable Entity), melhorando a experiência do desenvolvedor (DX). |
| **Validação** | **Bean Validation (Jakarta Validation)** | Validação de dados de entrada em DTOs usando anotações como `@NotBlank`, `@Positive`, e criação de validações customizadas (`FieldMessage`). |
| **Transações** | **Gerenciamento Transacional (`@Transactional`)** | Controle preciso do escopo transacional para garantir a atomicidade e consistência das operações de escrita no banco de dados. |
| **Padrões de Projeto** | **Injeção de Dependência** e **Paginacão (Pagination)** | Uso do contêiner IoC do Spring para gerenciar dependências e implementação de paginação de resultados (`Pageable`) para otimizar o desempenho em grandes volumes de dados. |

---

## 🛠️ Funcionalidades da API

A API oferece um conjunto completo de funcionalidades para gerenciar um e-commerce:

### 1. Gerenciamento de Produtos e Categorias
*   **CRUD Completo (Create, Read, Update, Delete)** para Produtos e Categorias.
*   Busca paginada e filtrada de produtos.

### 2. Autenticação e Autorização
*   **Login** via OAuth 2.0 (fluxo de credenciais de senha customizado).
*   **Roles (Perfis)** de usuário: `ROLE_CLIENT` e `ROLE_ADMIN`.
*   Controle de acesso refinado:
    *   **Administradores** podem gerenciar produtos e categorias.
    *   **Clientes** podem realizar pedidos e visualizar seus próprios dados.

### 3. Gerenciamento de Pedidos (Orders)
*   Criação de novos pedidos.
*   Busca de pedidos por ID.
*   Listagem de pedidos do usuário autenticado.
*   Entidades de Pedido, Item de Pedido e Pagamento modeladas de forma relacional.

---

## ⚙️ Configuração e Execução

### Pré-requisitos

*   **Java 21** (ou superior)
*   **Maven** (para gerenciamento de dependências)

### 1. Clonar o Repositório

```bash
git clone git@github.com:MarlonCMSilva/DsCommerce.git
cd dscommerce/dscommerce
```

### 2. Configurar Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para configurações de segurança e CORS. Essas variáveis são lidas diretamente do ambiente, conforme configurado no `application.properties`:

```properties
security.client-id=${CLIENT_ID:myclientid}
security.client-secret=${CLIENT_SECRET:myclientsecret}
security.jwt.duration=${JWT_DURATION:86400}
cors.origins=${CORS_ORIGINS:http://localhost:3000,http://localhost:5173}
```


| Variável | Descrição | Valor Padrão (para desenvolvimento) |
| :--- | :--- | :--- |
| `CLIENT_ID` | ID do cliente OAuth 2.0 | `myclientid` |
| `CLIENT_SECRET` | Segredo do cliente OAuth 2.0 | `myclientsecret` |
| `JWT_DURATION` | Duração do token JWT em segundos | `86400` (24 horas) |
| `CORS_ORIGINS` | Origens permitidas para CORS | `http://localhost:3000,http://localhost:5173` |

### 3. Executar a Aplicação

Utilize o Maven para construir e executar o projeto:

```bash
# Compilar o projeto
./mvnw clean install

# Executar a aplicação
./mvnw spring-boot:run
```

A API estará acessível em `http://localhost:8080`.

### 4. Acesso ao Banco de Dados (H2 Console)

Em ambiente de desenvolvimento, o banco de dados H2 pode ser acessado via console web:

*   **URL:** `http://localhost:8080/h2-console`
*   **JDBC URL:** `jdbc:h2:mem:dscommerce`
*   **Usuário:** `sa`
*   **Senha:** (Vazio)

### 5. Teste da API com Postman

Para facilitar os testes e a demonstração do fluxo de autenticação OAuth 2.0, é altamente recomendado utilizar o Postman.

1.  **Importe o Environment:** Importe o arquivo `DSCommerce env.postman_environment.json`.
2.  **Importe a Collection:** Importe a Collection do Postman `DsCommerce.postman_collection.json` para ter acesso a todos os endpoints.
3.  **Obtenha o Token:** Utilize o endpoint de Login para obter o `access_token` e armazená-lo na variável de ambiente do Postman.
4.  **Execute os Testes:** Agora você pode executar as requisições que exigem autenticação, como as rotas de `ROLE_CLIENT` e `ROLE_ADMIN`.

---

## 🧑‍💻 Autor

**Marlon Machado**

*   **GitHub:** https://github.com/MarlonCMSilva
*   **LinkedIn:** https://www.linkedin.com/in/marloncmachado
---
