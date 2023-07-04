<p align="center">
  <img src="https://github.com/ShowTracker/show-tracker-api-poo/assets/86852231/55ced9aa-c025-458f-abdb-4c0e9a91e8e6" width="100px">
</p>

# Show Tracker API

The **Show Tracker API** is developed to allow you to save and manage your list of watched movies and TV shows. This application was created as part of the Object-Oriented Programming and Database I courses in the Digital Systems and Media program at UFC. The API is built using `Java`, `Spring Boot`, and `SQLite` as the database.

With the Show Tracker API, you can easily add, view, and delete movies and TV shows from your watchlist. The API provides comprehensive CRUD (Create, Read, Update, Delete) functionalities in a clear and practical manner.

## Getting Started
Since the application is not hosted on a server, you need to install and run it locally by following these steps.

### Clone the repository locally
```
git clone https://github.com/ShowTracker/show-tracker-api-poo.git
```

### Open and run the project in an IDE
You can use IDEs such as Eclipse, NetBeans, or IntelliJ to run the Java application.

### The initial API URL is set to port 8088:
```
http://localhost:8088
```
If this port is already in use on your machine, you can modify it in the `application.properties` file.

## Endpoints

### USERS

#### Create User
Create a new user in the collection.

**Request**
- HTTP Method: **POST**
- Endpoint: `/users/register`
- Request Body:
```json
{
  "firstName": "Taylor",
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
  "firstName": "Taylor",
  "lastName": "Smith",
  "email": "taylor.smith@example.com",
  "birthDate": "1995/09/12",
  "password": "taylor123"
}
```

#### Get User
Get a user by email.

**Request**
- HTTP Method: **GET**
- Endpoint: `/users`
- Request Body:
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
  "firstName": "Taylor",
  "lastName": "Smith",
  "email": "taylor.smith@example.com",
  "birthDate": "1995-09-12",
  "password": "taylor123"
}
```

#### Delete User
Delete a user by email.

**Request**
- HTTP Method: **DELETE**
- Endpoint: `/users/deleteAccount`
- Request Body:
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

### GENRE

#### Get a Genre
Get a genre by ID.

**Request**
- HTTP Method: **GET**
- Endpoint: `/genres`
- Request Body:
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

#### Create Genre
Create a genre in the collection.

**Request**
- HTTP Method: **POST**
- Endpoint: `/genres/register`
- Request Body:
```json
{
  "id": 0,
  "name": "Cult"
}
```
**Response**

`Success 200`
```json
"Genre created successfully."
```

### MEDIA AND WATCHED LIST

#### Search Media
Get a media by name search.

**Request**
- HTTP Method: **GET**
-

 Endpoint: `/media?entry=iCarly`
  
**Response**

`Success 200`
```json
"Genre created successfully."
```

#### Add Media to Watched List
Add a movie or TV show to the watched list.

**Request**
- HTTP Method: **POST**
- Endpoint: `/media/user/add`
- Request Body:
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

#### Get Watched List
Get a user's watched list by email.

**Request**
- HTTP Method: **GET**
- Endpoint: `/media/user`
- Request Body:
```json
{
  "email": "taylor.smith@example.com"
}
```
**Response**

`Success 200`

`List of movies or TV shows in the watched list`

#### Remove Item from Watched List
Remove a media from the watched list by user email and media ID.

**Request**
- HTTP Method: **DELETE**
- Endpoint: `/media/user/remove`
- Request Body:
```json
{
  "email": "taylor.smith@example.com",
  "media_id": 268278
}
```
**Response**

`Success 200`
```
"Media removed from the watched list successfully."
```
