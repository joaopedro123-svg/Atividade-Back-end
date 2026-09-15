# API de Cursos

API REST desenvolvida como atividade prática integrada da disciplina de Back-End Frameworks.

O sistema permite cadastrar, listar, consultar, atualizar e remover cursos. Os dados são validados pela aplicação e armazenados em um banco PostgreSQL.

## Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Postman

## Modelo de curso

Cada curso possui os seguintes atributos:

| Campo          | Tipo      | Regra                                 |
| -------------- | --------- | ------------------------------------- |
| `id`           | `Long`    | Gerado automaticamente                |
| `nome`         | `String`  | Obrigatório e não pode estar vazio    |
| `cargaHoraria` | `Integer` | Obrigatória e deve ser maior que zero |

Exemplo:

```json
{
  "id": 1,
  "nome": "Back-End Frameworks",
  "cargaHoraria": 60
}
```

## Arquitetura da aplicação

A aplicação utiliza separação em camadas:

```text
Cliente
  → Controller
  → Service
  → Repository
  → PostgreSQL
```

* **Controller:** recebe as requisições HTTP e devolve as respostas.
* **Service:** contém as regras de negócio e validações.
* **Repository:** realiza as operações de persistência.
* **Model:** representa a entidade `Curso`.
* **PostgreSQL:** armazena permanentemente os cursos.

O `Controller` não acessa diretamente o banco de dados.

## Endpoints

| Método   | Endpoint       | Operação               | Respostas                                      |
| -------- | -------------- | ---------------------- | ---------------------------------------------- |
| `GET`    | `/cursos`      | Listar todos os cursos | `200 OK`                                       |
| `GET`    | `/cursos/{id}` | Buscar um curso por ID | `200 OK` ou `404 Not Found`                    |
| `POST`   | `/cursos`      | Cadastrar um curso     | `201 Created` ou `400 Bad Request`             |
| `PUT`    | `/cursos/{id}` | Atualizar um curso     | `200 OK`, `400 Bad Request` ou `404 Not Found` |
| `DELETE` | `/cursos/{id}` | Excluir um curso       | `204 No Content` ou `404 Not Found`            |

## Validações

A aplicação não permite:

* nome nulo, vazio ou composto somente por espaços;
* carga horária nula, igual a zero ou negativa.

Exemplo de resposta para dados inválidos:

```json
{
  "erro": "A carga horária deve ser maior que zero."
}
```

## Estrutura principal

```text
src/main/java/br/edu/nassau/apicursos
├── Application.java
├── controller
│   └── CursoController.java
├── model
│   └── Curso.java
├── repository
│   └── CursoRepository.java
└── service
    └── CursoService.java
```

## Configuração do PostgreSQL

Crie um banco de dados chamado:

```text
api_cursos
```

A aplicação utiliza a seguinte conexão:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_cursos
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

A senha não deve ser escrita diretamente no código nem enviada ao GitHub.

No IntelliJ, abra **Run → Edit Configurations** e adicione a variável de ambiente:

```text
DB_PASSWORD=sua_senha_do_postgresql
```

## Como executar

Clone o repositório:

```bash
git clone https://github.com/joaopedro123-svg/Atividade-Back-end.git
```

Entre na pasta:

```bash
cd Atividade-Back-end
```

No Windows PowerShell, defina a senha apenas para a sessão atual:

```powershell
$env:DB_PASSWORD = "SUA_SENHA"
```

Execute a aplicação:

```powershell
.\mvnw.cmd spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Como executar os testes

Com a variável `DB_PASSWORD` configurada, execute:

```powershell
.\mvnw.cmd test
```

O resultado esperado é:

```text
BUILD SUCCESS
```

## Testes com Postman

As requisições utilizadas nos testes estão armazenadas na pasta:

```text
postman/collections/API de Cursos
```

Foram testados os seguintes cenários:

* cadastro de curso válido;
* listagem de cursos;
* busca por ID;
* atualização de curso;
* exclusão de curso;
* busca por ID inexistente;
* atualização e exclusão de curso inexistente;
* cadastro com nome vazio;
* cadastro com carga horária inválida;
* atualização com dados inválidos.

## Evidência de persistência

A persistência foi comprovada da seguinte forma:

1. um curso foi cadastrado pelo Postman;
2. a aplicação foi encerrada;
3. a aplicação foi iniciada novamente;
4. o curso continuou sendo retornado por `GET /cursos`;
5. o registro também foi consultado diretamente na tabela `cursos` pelo pgAdmin.

## Evolução da atividade

* **Aula 01:** fluxo cliente-servidor, responsabilidades do back-end e contrato da API.
* **Aula 02:** criação do projeto Spring Boot, Controller, Service e injeção de dependência.
* **Aula 03:** modelo `Curso`, conversão JSON e CRUD em memória.
* **Aula 04:** criação do Repository, separação em camadas e validações.
* **Aula 05:** integração com PostgreSQL e Spring Data JPA.

## Evidências dos testes

| Teste realizado | Evidência |
| --- | --- |
| Cadastro de curso — `201 Created` | [Visualizar](docs/evidencias/01-post-cadastrar-curso-201.jpeg) |
| Listagem de cursos — `200 OK` | [Visualizar](docs/evidencias/02-get-listar-cursos-200.jpeg) |
| Busca por ID — `200 OK` | [Visualizar](docs/evidencias/03-get-buscar-curso-por-id-200.jpeg) |
| Atualização de curso — `200 OK` | [Visualizar](docs/evidencias/04-put-atualizar-curso-200.jpeg) |
| Busca de curso inexistente — `404 Not Found` | [Visualizar](docs/evidencias/05-get-curso-inexistente-404.jpeg) |
| Atualização de curso inexistente — `404 Not Found` | [Visualizar](docs/evidencias/06-put-curso-inexistente-404.jpeg) |
| Exclusão de curso inexistente — `404 Not Found` | [Visualizar](docs/evidencias/07-delete-curso-inexistente-404.jpeg) |
| Cadastro com carga horária inválida — `400 Bad Request` | [Visualizar](docs/evidencias/08-post-carga-horaria-invalida-400.jpeg) |
| Cadastro válido após validações — `201 Created` | [Visualizar](docs/evidencias/09-post-cadastrar-curso-valido-201.jpeg) |
| Atualização com dados inválidos — `400 Bad Request` | [Visualizar](docs/evidencias/10-put-dados-invalidos-400.jpeg) |
| Persistência após reiniciar a API — `200 OK` | [Visualizar](docs/evidencias/11-get-persistencia-apos-reinicio-200.jpeg) |
| Registro armazenado no PostgreSQL | [Visualizar](docs/evidencias/12-pgadmin-registro-persistido.jpeg) |

## Autor

João Pedro Rodrigues Araújo
