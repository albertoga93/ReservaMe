# 📦 ReservaMe

**ReservaMe** es una aplicación web desarrollada con **Java y Spring Boot** para la gestión de productos y reservas en distintos tipos de negocios.

El objetivo del proyecto es proporcionar una solución flexible que permita a los negocios gestionar su catálogo de productos y controlar las reservas realizadas por sus empleados.

> 🚧 **Proyecto actualmente en desarrollo** 

---

## 🎯 Objetivo

ReservaMe está diseñada para adaptarse a diferentes tipos de negocios que necesiten gestionar productos mediante un sistema de reservas.

La aplicación permite separar las responsabilidades entre los distintos usuarios del negocio mediante un sistema de roles:

* **Dueño** → gestión y administración del negocio.
* **Empleado** → gestión de reservas de productos.

Las reservas son realizadas exclusivamente por los empleados del negocio.

---

## ✨ Funcionalidades

### 📦 Gestión de productos

* Creación de productos.
* Gestión de variantes de productos.
* Gestión de categorías.
* Control de stock.
* Activación y desactivación de productos y variantes.
* Generación automática de SKU para las variantes.

### 📅 Gestión de reservas

* Creación de reservas por parte de los empleados.
* Gestión de productos y cantidades reservadas.
* Control del estado de las reservas.

### 👥 Usuarios y roles

La aplicación contempla dos roles principales:

| Rol             | Responsabilidades                    |
| --------------- | ------------------------------------ |
| 👑 **Dueño**    | Gestión y administración del negocio |
| 👤 **Empleado** | Gestión de reservas                  |

---

## 🏗️ Arquitectura

El proyecto está siendo desarrollado siguiendo una arquitectura basada en capas, separando las diferentes responsabilidades de la aplicación.

```text
Controller
     ↓
  Service
     ↓
 Repository
     ↓
   JPA
     ↓
PostgreSQL
```

El proyecto también utiliza **DTOs** para separar los datos recibidos y enviados por la API de las entidades utilizadas para la persistencia.

---

## 🛠️ Tecnologías

* ☕ **Java**
* 🌱 **Spring Boot**
* 🗄️ **JPA**
* 🐘 **PostgreSQL**
* 📦 **Maven**
* 🔀 **Git / GitHub**

---

## 🚧 Estado del proyecto

El proyecto se encuentra actualmente **en desarrollo**.

### Actualmente implementado

* [x] Definición de los casos de uso
* [x] Modelado de las principales entidades
* [x] DTOs
* [ ] Repositorios
* [ ] Servicios
* [ ] Controladores / endpoints
* [ ] Validaciones
* [ ] Gestión de errores
* [ ] Sistema de autenticación y autorización
* [ ] Tests
* [ ] Documentación de la API

---

## 🗺️ Próximos pasos

1. Implementar los DTOs.
2. Implementar los repositorios.
3. Implementar la lógica de negocio.
4. Implementar los endpoints.
5. Añadir las validaciones.
6. Implementar autenticación y autorización.
7. Añadir manejo de errores.
8. Crear tests.
9. Documentar la API.
10. Mejorar y ampliar las funcionalidades de gestión de reservas.

---

## 📚 Sobre el proyecto

**ReservaMe** es un proyecto desarrollado para poner en práctica y profundizar en el desarrollo de aplicaciones backend utilizando el ecosistema **Java + Spring Boot**.

Durante el desarrollo se trabajan conceptos como:

* Diseño de APIs REST.
* Arquitectura por capas.
* Programación orientada a objetos.
* Persistencia de datos con JPA.
* Modelado de relaciones entre entidades.
* DTOs.
* Validación de datos.
* Gestión de excepciones.
* Autenticación y autorización.
* Gestión de bases de datos relacionales.
* Control de versiones con Git.

---

## 👨‍💻 Autor

**Alberto García Carrasco**

Proyecto desarrollado como parte del proceso de aprendizaje y desarrollo profesional en **software backend con Java y Spring Boot**.
