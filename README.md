# AMGE Backend

Backend do projeto **AMGE (Aplicativo Móvel de Gestão de Estoque)** desenvolvido como parte do projeto de imersão profissional da faculdade.

A aplicação consiste em uma **API RESTful** responsável por gerenciar operações relacionadas ao estoque, fornecendo endpoints para comunicação com o aplicativo mobile.

O backend foi desenvolvido utilizando **Spring Boot**, com persistência de dados via **Hibernate/JPA** e banco de dados **MySQL**.

---

#  Tecnologias Utilizadas

* Java / Kotlin
* Spring Boot
* Spring Web
* Hibernate / JPA
* MySQL
* Maven
* IntelliJ IDEA

---

#  Arquitetura da Aplicação

O projeto segue uma arquitetura em camadas para separar responsabilidades e facilitar manutenção.

Estrutura geral:

```
Controller
Service
Repository
Entity
DTO
```

Cada camada possui uma responsabilidade específica dentro da aplicação.

---

#  API REST

A aplicação expõe uma **API RESTful** que permite que o aplicativo mobile realize operações de gerenciamento de estoque.

Principais operações disponíveis:

* Criar registros
* Consultar dados
* Atualizar informações
* Remover itens

Essas operações seguem o padrão **CRUD** através de endpoints HTTP.

Exemplo de estrutura de endpoints:

```
GET     /api/items/dashboard
POST    /api/items/add
GET     /api/items/stock
PATCH   /api/items/stock
DELETE  /api/items/delete
```

---

# 📦 Persistência de Dados

A persistência é realizada utilizando **Hibernate (JPA)** para realizar o mapeamento objeto-relacional.

Cada entidade da aplicação representa uma tabela no banco de dados.

Exemplo simplificado:

```
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String itemCode;
    private Integer quantity;
    private Double price;
    private String type;


}
```

O banco de dados utilizado é o **MySQL**.

---

#  Data Transfer Objects (DTO)

O projeto utiliza **DTOs (Data Transfer Objects)** para controlar os dados enviados e recebidos pela API.

Isso evita:

* Exposição direta das entidades
* Vazamento de informações sensíveis
* Acoplamento entre camadas

Os DTOs são responsáveis por transportar apenas os dados necessários entre cliente e servidor.

---

#  Service Layer

A camada de **Service** centraliza as regras de negócio da aplicação.

Responsabilidades da Service Layer:

* Processamento de regras de negócio
* Comunicação entre Controller e Repository
* Validação de dados
* Manipulação de entidades

Fluxo típico de execução:

```
Controller → Service → Repository → Database
```

---

#  Repository Layer

A camada de **Repository** é responsável pelo acesso ao banco de dados.

Ela utiliza **Spring Data JPA**, permitindo criar consultas de forma simples através de interfaces.

Exemplo:

```
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
```

---

# Padronização de Respostas

A API utiliza um padrão de resposta estruturado para facilitar a comunicação com o frontend.

Estrutura da resposta:

```
ApiResponse<T>
```

Campos:

* `status`
* `message`
* `data`
* `timestamp`

Isso garante consistência em todas as respostas da API.

---

# Tratamento de Exceções via ControllerAdvice

O projeto utiliza **Global Exception Handling** para centralizar o tratamento de erros da aplicação.

Isso permite:

* Respostas de erro padronizadas
* Melhor controle sobre falhas
* Melhor experiência para o cliente da API

Exemplo de tratamento:

```
@ExceptionHandler(RegisterItemException.class)
    public ResponseEntity<ApiResponse<Void>> handleInventoryException(RegisterItemException ex) {
        
    }
```

---

#  Comunicação com o Aplicativo Mobile

O backend fornece dados ao aplicativo Android através de requisições **HTTP com payload JSON**.

Durante o desenvolvimento, o aplicativo acessa o backend utilizando o endereço:

```
10.0.2.2
```

Esse endereço permite que o **emulador Android acesse o localhost da máquina host**.

---

#  Ambiente de Desenvolvimento

IDE utilizada:

* IntelliJ IDEA

Build e gerenciamento de dependências:

* Maven

Banco de dados:

* MySQL


---

# Integração com Frontend Mobile

O backend foi desenvolvido para servir dados ao aplicativo mobile construído com:

* Kotlin
* Jetpack Compose
* Retrofit

A aplicação mobile consome os endpoints REST para realizar operações de estoque e exibir dados na interface.

---

# Objetivo do Projeto

O objetivo do projeto foi desenvolver uma aplicação completa envolvendo:

* Desenvolvimento de API REST
* Persistência de dados
* Integração com aplicativo mobile
* Arquitetura em camadas
* Comunicação cliente-servidor

Esse projeto serviu como exercício prático de integração entre **backend e desenvolvimento mobile**.

---

# Autor

**Kayck Arcanjo**

Estudante de Análise e Desenvolvimento de Sistemas focado em backend, APIs e integração de sistemas.
