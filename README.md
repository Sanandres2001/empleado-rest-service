# 🌐 Cliente REST - Prueba Técnica

Este proyecto es una aplicación **RESTful** construida con **Spring Boot** que actúa como cliente de un servicio **SOAP**. Se encarga de recibir solicitudes HTTP, procesarlas y consumir el endpoint SOAP expuesto por `empleado-soap-service`.

---

## 🚀 Stack Tecnológico

- Java 17+
- Spring Boot (Web)
- SOAP Client (`@WebServiceTemplate`)
- JAXB
- Maven

---

## 📦 Estructura del Proyecto

```text
src/
├── main/
│   ├── java/
│   │   └── co.parameta.pruebatecnica/
│   │       ├── controller/            # Controlador REST
│   │       ├── client/                # Cliente SOAP
│   │       ├── model/                 # Clases generadas a partir del WSDL
│   │       └── PruebaTecnicaApplication.java  # Clase principal
│   └── resources/
│       └── application.properties     # Configuración del cliente SOAP
└── test/                              # Pruebas unitarias
```
