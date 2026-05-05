# HR Management System

Proyecto backend desarrollado con Spring Boot para la gestión de recursos humanos dentro de una empresa.

Incluye funcionalidades como gestión de empleados, departamentos, nóminas, solicitudes de vacaciones y control de acceso por roles.

---

## Funcionalidades principales

### Gestión de empleados
- Crear, actualizar y eliminar empleados
- Asignar departamento
- Cambiar estado (ACTIVE / SUSPENDED)
- Consultar saldo de vacaciones disponibles

### Gestión de departamentos
- CRUD de departamentos
- Asignación de jefe de departamento (manager)
- Listado de empleados por departamento

### Solicitudes de vacaciones (Leave Requests)
- Crear solicitudes de vacaciones
- Aprobar o rechazar solicitudes
- Control de estado (PENDING, APPROVED, REJECTED)
- Cálculo y descuento automático de días disponibles

### Nóminas (Payroll)
- Generación de nóminas por empleado
- Cálculo de salario neto (salario base + bonus - deducciones - impuestos)
- Gestión por ciclos de pago (WEEKLY, BIWEEKLY, MONTHLY)
- Consulta de nóminas por empleado y por ciclo
- Endpoint de coste total de personal

### Proyectos de departamento
- Definición de proyectos asociados a departamentos
- Control de estado (PLANNED, IN_PROGRESS, COMPLETED, CANCELLED)
- Consulta de proyectos por departamento

### Seguridad
- Autenticación mediante Spring Security (Basic Auth)
- Autorización basada en roles (ADMIN, HR, EMPLOYEE)
- Control de acceso a endpoints según rol

### Vistas con Thymeleaf
- Visualización básica de empleados y departamentos desde el navegador
- Navegación simple entre páginas

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Spring Security
- Thymeleaf

---

## Arquitectura

El proyecto sigue una arquitectura por capas:

- **Controller** → exposición de endpoints REST
- **Service** → lógica de negocio
- **Repository** → acceso a base de datos
- **DTOs** → separación entre modelo interno y API

Se han aplicado buenas prácticas como:
- uso de DTOs para evitar exponer entidades
- validación de datos
- manejo de errores global
- separación de responsabilidades

---

##  Endpoints principales

### Employees
- `GET /employees`
- `POST /employees`
- `PUT /employees/{id}`
- `PUT /employees/{id}/status`
- `GET /employees/{id}/vacation-balance`

### Departments
- `GET /departments`
- `POST /departments`
- `PUT /departments/{id}/manager`
- `GET /departments/{id}/employees`

### Leave Requests
- `POST /leave-requests`
- `PUT /leave-requests/{id}/approve`
- `PUT /leave-requests/{id}/reject`

### Payroll
- `POST /payrolls`
- `GET /payrolls`
- `GET /payrolls/cycle/{cycle}`
- `GET /payrolls/total-cost`

### Department Projects
- `POST /department-projects`
- `GET /department-projects`
- `GET /department-projects/department/{departmentId}`

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/tu-usuario/hr-management-system.git
