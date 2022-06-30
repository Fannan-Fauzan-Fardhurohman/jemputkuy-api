# jemputkuy-api

# API Specs

## Base url

```
https://jemputkuy-api.herokuapp.com
```

## Table endpoints
### User Customer

| Name              | Endpoint                     | Method   | JWT token | Body and response                             |
|-------------------|------------------------------|----------|-----------|-----------------------------------------------|
| Register customer | `/api/v1/user/customer/register` | `POST`   | no        |           |
| Login customer    | `/api/v1/user/customer/login`    | `POST`   | no        |              |
| Get customer info | `/api/v1/user/customer/customer`          | `GET`    | yes       |  |

### User Driver

| Name            | Endpoint                   | Method   | JWT token | Body and response                         |
|-----------------|----------------------------|----------|-----------|-------------------------------------------|
| Register driver | `api/user/driver/register` | `POST`   | no        |         |
| Login driver    | `api/user/driver/login`    | `POST`   | no        |            |
| Get driver info | `api/user/driver`          | `GET`    | yes       |  |

---

---
## Table Role
| Name Role             | Keterangan                     |
|-------------------|------------------------------|
| 1 | Customer |
| 2 | Driver |

---

## User Customer Examples
### User Customer - Register

```
POST /api/v1/user/customer/register
```

Body

```json
{
    "username":"4",
    "password":"1234",
    "email":"fauzan@gmail.com",
    "address":"jl raya tagok",
    "phone_number":"08999222",
    "first_name": "fannan",
    "last_name" : "fauzan"
}
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": true
}
```

### User Customer - Login

```
POST /api/user/customer/login
```

Body

```json
{
    "username" : "4",
    "password" : "1234"
}
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzZGM3MmRmYi05OTU1LTQ0NDMtYTEyYi1hMGMyYWIxMmZiZmMiLCJhdXRoIjpbIjQiXSwiZXhwIjoxNjU2Njk3NDk2fQ.nA....
    }
}
```

### User Customer - Get customer info

```
GET /api/user/customer
HEADER Authorization eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzZGM3MmRmYi05OTU1LTQ0NDMtYTEyYi1hMGMyYWIxMmZiZmMiLCJhdXRoIjpbIjQiXSwiZXhwIjoxNjU2Njk3NDk2fQ.nA....
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": {
        "id": "3dc72dfb-9955-4443-a12b-a0c2ab12fbfc",
        "username": "4",
        "password": "null",
        "email": "fauzan@gmail.com",
        "address": "jl raya tagok",
        "first_name": "fannan",
        "last_name": "fauzan",
        "phone_number": "08999222",
        "role": 1
    }
}
```

---

## User Driver Examples
### User Driver - Register
### User Customer - Register

```
POST /api/v1/user/driver/register
```

Body

```json
{
    "username":"4",
    "password":"1234",
    "email":"fauzan@gmail.com",
    "address":"jl raya tagok",
    "phone_number":"08999222",
    "first_name": "fannan",
    "last_name" : "fauzan"
}
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": true
}
```

### User Driver - Login

```
POST /api/user/driver/login
```

Body

```json
{
    "username" : "4",
    "password" : "1234"
}
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzZGM3MmRmYi05OTU1LTQ0NDMtYTEyYi1hMGMyYWIxMmZiZmMiLCJhdXRoIjpbIjQiXSwiZXhwIjoxNjU2Njk3NDk2fQ.nA....
    }
}
```

### User Driver - Get Driver info

```
GET /api/user/driver
HEADER Authorization eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzZGM3MmRmYi05OTU1LTQ0NDMtYTEyYi1hMGMyYWIxMmZiZmMiLCJhdXRoIjpbIjQiXSwiZXhwIjoxNjU2Njk3NDk2fQ.nA....
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": {
        "id": "3dc72dfb-9955-4443-a12b-a0c2ab12fbfc",
        "username": "4",
        "password": "null",
        "email": "fauzan@gmail.com",
        "address": "jl raya tagok",
        "first_name": "fannan",
        "last_name": "fauzan",
        "phone_number": "08999222",
        "role": 2
    }
}
```
