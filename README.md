# API de Cursos

API REST desenvolvida como atividade prática integrada da disciplina de Back-End Frameworks.

O projeto será desenvolvido em Java 21 com Spring Boot, Spring Data JPA e PostgreSQL. A API permitirá cadastrar, consultar, atualizar e remover cursos.

## Aula 01 — Introdução ao Back-End e Frameworks

### Atividade 01 — Fluxo entre cliente e servidor

O cliente pode ser uma aplicação front-end, um navegador ou uma ferramenta de testes como Postman e Insomnia. O servidor é a aplicação back-end desenvolvida com Spring Boot.

Quando o cliente consulta os cursos, acontece o seguinte fluxo:

```text
Cliente
  -> Request HTTP GET /cursos
  -> Servidor back-end
  -> Processamento da solicitação
  -> Response HTTP com status 200 e os cursos em JSON
  -> Cliente
```

O cliente envia uma **request HTTP** utilizando o método `GET` para o endereço `/cursos`. O servidor recebe a requisição, realiza o processamento necessário para consultar os cursos cadastrados e devolve uma **response HTTP**.

Quando a consulta é realizada corretamente, a resposta contém o status `200 OK` e uma lista de cursos no formato JSON.

### Atividade 02 — Responsabilidades do back-end

Ao receber uma solicitação para cadastrar um curso, o back-end deverá:

1. Receber os dados enviados pelo cliente por meio de uma requisição HTTP.
2. Transformar o JSON recebido em um objeto Java da classe `Curso`.
3. Verificar se o nome do curso foi informado.
4. Verificar se a carga horária é maior que zero.
5. Salvar o curso no sistema.
6. Gerar automaticamente o identificador do curso.
7. Devolver ao cliente uma resposta HTTP com o curso cadastrado.

O back-end é responsável por receber, processar, validar e armazenar os dados. Portanto, ele não funciona apenas como uma tela de apresentação.

### Atividade 03 — Contrato inicial da API

A API disponibilizará os seguintes endpoints:

| Método HTTP | Endpoint       | Operação                     | Resposta esperada                   |
| ----------- | -------------- | ---------------------------- | ----------------------------------- |
| `GET`       | `/cursos`      | Listar todos os cursos       | `200 OK`                            |
| `GET`       | `/cursos/{id}` | Buscar um curso pelo ID      | `200 OK` ou `404 Not Found`         |
| `POST`      | `/cursos`      | Cadastrar um novo curso      | `201 Created`                       |
| `PUT`       | `/cursos/{id}` | Atualizar um curso existente | `200 OK` ou `404 Not Found`         |
| `DELETE`    | `/cursos/{id}` | Remover um curso             | `204 No Content` ou `404 Not Found` |

Exemplo de JSON utilizado para cadastrar ou atualizar um curso:

```json
{
  "nome": "Back-End Frameworks",
  "cargaHoraria": 60
}
```
