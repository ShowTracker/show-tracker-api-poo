<p align="center">
  <img src="https://github.com/ShowTracker/show-tracker-api-poo/assets/86852231/55ced9aa-c025-458f-abdb-4c0e9a91e8e6" width="100px">
</p>

# Show Traker API

O **Show Tracker** é uma API desenvolvida para permitir que você salve e gerencie sua lista de filmes e séries assistidos. Essa aplicação foi criada como parte das disciplinas de Programação Orientada a Objetos e Banco de Dados I do curso de Sistema e Mídias Digitais da UFC. A API foi desenvolvida utilizando `Java`, `Spring Boot` e `SQLite` como banco de dados.

Com o Show Tracker API, você pode facilmente adicionar, visualizar e excluir filmes e séries da sua lista de acompanhamento. A API oferece recursos completos de CRUD _(Create, Read, Update, Delete)_ de forma clara e pática.

# Como utilizar
A aplicação não está hospedada em nenhum servidor, porntanto, para utilizá-la você deve instalá-la localmente seguindo os seguintes passos.
### Clone o repositório localmente
```
git clone https://github.com/ShowTracker/show-tracker-api-poo.git
```
### Abra e execute o projeto em uma IDE
`Eclipse`, `NetBeans` e `InteliJ` são algumas opções de IDE que rodam JAVA

### A URL inicial da API está setada na porta 8088:
```
http://localhost:8088
```
Caso esta esteja ocupada na sua máquina, você pode alterar no aquirvo **application.properties**

# Endpoints

## USERS
  ### Create User
  Create a new user in the collection.
  
  **Request**
  * HTTP Method: **POST**
  * Endpoint: `/users/register`
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
  **Response**
  
  `Success 200`
  ```json
  {
    "fisrtName": "Taylor",
    "lastName": "Smith",
    "email": "taylor.smith@example.com",
    "birthDate": "1995/09/12",
    "password": "taylor123"
  }
  
  ```

### Get User
  Get a user by email.
  
  **Request**
  * HTTP Method: **GET**
  * Endpoint: `/users`
  * Request Body:
  ```json
    {
      "email": "taylor.smith@example.com",
      "password": "taylor123"
    }
  ```
  **Response**
  
  `Success 200`
  ```json
    {
      "fisrtName": "Taylor",
      "lastName": "Smith",
      "email": "taylor.smith@example.com",
      "birthDate": "1995-09-12",
      "password": "taylor123"
    }
  ```

### Delete User
  Delete a user by email.
  
  **Request**
  * HTTP Method: **DELETE**
  * Endpoint: `/users/deleteAccount`
  * Request Body:
  ```json
    {
      "email": "taylor.smith@example.com"
    }
  ```
  **Response**
  
  `Success 200`
  ```json
    "User deleted successfully."
  ```

## GENRE

### Get a genre
Get genre by ID

**Request**
* HTTP Method: **GET**
* Endpoint: `/genres`
* Request Body:
```json
  {
      "id": 10
  }
```
**Response**

`Success 200`
```json
  "Family"
```

### Create genre
Create genre in the collection

**Request**
* HTTP Method: **POST**
* Endpoint: `/genres/register`
* Request Body:
```json
  {
      "id": 0,
      "name": "Cult"
  }
```
**Response**

`Success 200`
```json
  "Genre created successfully"
```
## MEDIA AND WATCHED LIST 

### Search media
Get genre by name search

**Request**
* HTTP Method: **GET**
* Endpoint: `/media?entry=iCarly`

  
**Response**

`Success 200`
```json
  "Genre created successfully"
```

### Add media to watched list
Add movie or tv show in watched list

**Request**
* HTTP Method: **POST**
* Endpoint: `/media/user/add`
* Request Body:
```json
 {
      "email": "taylor.smith@example.com",
      "media_id": 268278
  }
```
**Response**

`Success 200`
```json
  {
    "id": 268278,
    "year": "2007",
    "title": "iCarly",
    "isAdult": false,
    "genres": [],
    "endYear": "2012"
  }
```

### Get a watched list
Get a user watched list by email

**Request**
* HTTP Method: **GET**
* Endpoint: `/media/user`
* Request Body:
```json
 {
      "email": "taylor.smith@example.com"
  }
```
**Response**

`Success 200`

`List of movies or TV Shows in Watched list`

### Remove item from watched list
Remove a media from watched list by user email and media id

**Request**
* HTTP Method: **DELETE**
* Endpoint: `/media/user/remove`
* Request Body:
```json
 {
    "email": "taylor.smith@example.com",
    "media_id": 268278
}
```
**Response**

`Success 200`

```
"Media removed from the watched list successfuly."
```
