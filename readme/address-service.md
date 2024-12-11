
# Address Service
* Address Service is a microservice that provides address management functionality.
* The service is built using Spring Boot and MongoDB.
* The service provides CRUD operations for addresses.
* The service is secured using JWT authentication.
* Each address is associated with an object ID.

---

## Table Of Contents
- [Address Service](#address-service)
  - [Table Of Contents](#table-of-contents)
  - [Features](#features)
  - [Usage](#usage)
    - [Endpoints](#endpoints)
    - [Address Requests](#address-requests)
    - [Address Responses](#address-responses)
  - [Author](#author)

---

## Features

* Create a new address
* Retrieve all addresses
* Retrieve an address by ID
* Update an existing address
* Delete an address by ID
* Retrieve all addresses by Object ID

--- 

## Usage

### Endpoints

* http://localhost:8090/address/**

| Method  | Endpoint with Param/Path Variable            | Description                                    | Request Body (Linked to Section)     | Response Body (Linked to Section)                   |
|---------|----------------------------------------------|------------------------------------------------|--------------------------------------|-----------------------------------------------------|
| POST    | `/create-address`                            | Create a new address                          | [AddressRequest](#address-request)   | [CreateAddressResponse](#create-address-response) |
| GET     | `/get-addresses`                             | Retrieve all addresses                        | None                                 | [AddressesResponse](#addresses-response)           |
| GET     | `/get-address`                               | Retrieve an address by ID                     | None (uses `addressId` query param) | [AddressResponse](#address-response)               |
| PUT     | `/update-address`                            | Update an existing address                    | [AddressRequest](#address-request)   | [UpdateAddressResponse](#update-address-response) |
| DELETE  | `/delete-address`                            | Delete an address by ID                       | None (uses `addressId` query param) | [DeleteAddressResponse](#delete-address-response) |
| GET     | `/get-addresses-by-object-id`                | Retrieve all addresses by Object ID           | None (uses `objectId` query param)  | [AddressesResponse](#addresses-response)           |

---

### Address Requests

#### Address Request

```json
{
    "country" : "TR",
    "city" : "54",
    "street" : "Muhsin yaz. oglu",
    "postalCode" : "54500",
    "additionalInfo" : "work addresss",
    "addressOfId" : "1"
}
```

### Address Responses

#### Create Address Response

* The address created successfully.

```json
{
  "message": "Address created successfully",
  "httpStatusCode": 200,
  "response": {
    "id": "675a1bf2b750cd245c2a58cd",
    "country": "TR",
    "city": "54",
    "street": "Muhsin yaz. oglu",
    "postalCode": "54500",
    "additionalInfo": "work addresss",
    "addressOfId": "1"
  }
}
```

#### Addresses Response

* The addresses retrieved successfully.

```json
{
  "message": "Addresses retrieved successfully",
  "httpStatusCode": 200,
  "response": [
    {
      "id": "675a108e61f39a1bbfaf884f",
      "country": "TR",
      "city": "54",
      "street": "Muhsin yaz. oglu",
      "postalCode": "54500",
      "additionalInfo": "work addresss",
      "addressOfId": "1"
    },
    {
      "id": "675a1bf2b750cd245c2a58cd",
      "country": "TR",
      "city": "54",
      "street": "Muhsin yaz. oglu",
      "postalCode": "54500",
      "additionalInfo": "work addresss",
      "addressOfId": "1"
    }
  ]
}
```

#### Address Response

* The address retrieved successfully.

```json
{
  "message": "Address retrieved successfully",
  "httpStatusCode": 200,
  "response": {
    "id": "675a108e61f39a1bbfaf884f",
    "country": "TR",
    "city": "54",
    "street": "Muhsin yaz. oglu",
    "postalCode": "54500",
    "additionalInfo": "work addresss",
    "addressOfId": "1"
  }
}
```

* No address found with the given ID.

```json
{
  "message": "Address not found",
  "httpStatusCode": 404,
  "response": {
    "id": "675a100361f39a1bbfaf884e"
  }
}
```

#### Update Address Response

* The address updated successfully.

```json
{
  "message": "Address updated successfully",
  "httpStatusCode": 200,
  "response": {
    "id": "675a108e61f39a1bbfaf884f",
    "country": "TR",
    "city": "54",
    "street": "Muhsin yaz. oglu",
    "postalCode": "54500",
    "additionalInfo": "work addresss",
    "addressOfId": "1"
  }
}
```

* No address found with the given ID.

```json
{
  "message": "Address not found",
  "httpStatusCode": 404,
  "response": {
    "id": "675a100361f39a1bbfaf884e"
  }
}
```

#### Delete Address Response

* The address deleted successfully.

```json
{
  "message": "Address deleted successfully",
  "httpStatusCode": 200,
  "response": null
}
```


* No address found with the given ID.

```json
{
  "message": "Address not found",
  "httpStatusCode": 404,
  "response": null
}
```




---

## Author

👤 **Ismail Kattan**

- Github: [IsmailKattan](https://github.com/IsmailKattan)
- LinkedIn: [ismail kattan](https://www.linkedin.com/in/ismail-kattan-538b87219/)
- Email: [ismail kattan](mailto:ismailkattan.contact@gmail.com)
- Phone: [+905314326118](tel:+905070457070)

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
