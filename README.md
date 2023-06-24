# show-tracker-api

Uma api de operações CRUD para salvar a lista de filmes e séries assistidas!

O Show Tracker  é uma aplicação desenvolvida dentro das disciplinas de Programação Orientada a Objetos e Banco de Dados I do curso de Sistema e Mídias Digitais na UFC.
Essa API foi implementada utilizando Java 17 e usa SQLite como banco de dados.

## URL

A URL inicial da API está setada na porta 8088:

```
http://localhost:8088
```

Caso esta esteja ocupada na sua máquina, você pode alterar no aquirvo **application.properties**

## Users

### Create new User

Creates a new user in the API.

Create a new item in the collection.

* HTTP Method: **POST**
* Endpoint: /users/register
* Request Body:
```json
{
  "fisrtName": "Taylor",
  "lastName": "Smith",
  "email": "taylor.smith@example.com",
  "birthDate": "1995/09/12",
  "password": "taylor123"
}

```
* Success 200:
```json
{
  "fisrtName": "Taylor",
  "lastName": "Smith",
  "email": "taylor.smith@example.com",
  "birthDate": "1995/09/12",
  "password": "taylor123"
}

```


### Get User by email and password
