# Sistema de Gestión y Análisis de Incidentes

Aplicación backend inspirada en plataformas de observabilidad y monitoreo como Dynatrace. El proyecto expone una API REST para gestionar incidentes, registrar cambios de estado y ejecutar análisis automáticos que permiten clasificar incidentes como nuevos o recurrentes.

## Tecnologías

* Java 21
* Spring Boot 3.5.x
* Spring Web
* Spring Data JPA
* MySQL
* MapStruct
* Lombok
* Spring Validation
* Spring Boot Actuator
* OpenAPI / Swagger

## Funcionalidades

### Gestión de Incidentes

* Creación de incidentes.
* Consulta de incidentes por ID.
* Listado completo de incidentes.
* Cambio de estado de incidentes.
* Historial de cambios de estado.

### Motor de Análisis

* Análisis automático al crear un incidente.
* Detección de incidentes nuevos.
* Detección de incidentes recurrentes.
* Persistencia de resultados de análisis.
* Consulta de análisis por incidente.

### Contexto de Negocio

* Gestión de aplicaciones.
* Gestión de servicios de negocio.
* Asociación de incidentes a servicios específicos.

## Modelo de Dominio

```text
Application
    ↓
BusinessService
    ↓
Incident
    ↓
Analysis

Incident
    ↓
IncidentHistory
```

## Resultados de Análisis

Actualmente el motor de análisis soporta:

* `NEW_INCIDENT`
* `RECURRING_INCIDENT`

Versiones futuras incluirán:

* `FALSE_POSITIVE`
* Puntaje de confianza (Confidence Score)
* Análisis asistido por IA
* Integración con Dynatrace
* Integración con Jira

## Estructura Principal

* `application`: gestión de aplicaciones.
* `businessservice`: servicios de negocio.
* `incident`: gestión de incidentes e historial.
* `analysis`: motor de análisis de incidentes.
* `shared`: constantes, excepciones y utilidades compartidas.

## Requisitos

* Java 21
* Maven 3.9+
* MySQL 8+

## Configuración Local

Crear la base de datos:

```sql
CREATE DATABASE incident_manager;
```

Configurar las credenciales en:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/incident_manager
spring.datasource.username=root
spring.datasource.password=
```

## Ejecución

Con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

O utilizando Maven instalado:

```bash
mvn spring-boot:run
```

La API quedará disponible en:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Endpoints Principales

### Incidentes

Crear incidente:

```http
POST /api/v1/incidents
```

Listar incidentes:

```http
GET /api/v1/incidents
```

Obtener incidente por ID:

```http
GET /api/v1/incidents/{id}
```

Cambiar estado:

```http
PUT /api/v1/incidents/change-status/{id}
```

Obtener historial:

```http
GET /api/v1/incidents/{id}/history
```

Obtener análisis:

```http
GET /api/v1/incidents/{id}/analysis
```

## Ejemplo de Respuesta de Análisis

```json
{
  "analysisResult": "RECURRING_INCIDENT",
  "reason": "Found recurring incidents in the last 30 days.",
  "createdAt": "2026-06-21T16:18:14"
}
```

## Reglas de Análisis

### Incidente Recurrente

Un incidente se considera recurrente cuando la cantidad de incidentes asociados al mismo servicio de negocio durante la ventana de análisis supera el umbral configurado.

Configuración actual:

* Ventana de análisis: 30 días.
* Umbral de recurrencia: 3 incidentes.

## Roadmap

* [x] Gestión de incidentes.
* [x] Historial de incidentes.
* [x] Cambio de estado.
* [x] Motor de análisis automático.
* [x] Detección de incidentes nuevos.
* [x] Detección de incidentes recurrentes.
* [ ] Detección de falsos positivos.
* [ ] Confidence Score.
* [ ] Integración con IA.
* [ ] Integración con Dynatrace.
* [ ] Integración con Jira.

## Notas

* Cada incidente genera automáticamente un análisis al momento de su creación.
* Los resultados de análisis se almacenan para permitir auditoría y trazabilidad.
* La arquitectura está organizada por dominios de negocio para facilitar el mantenimiento y escalabilidad del proyecto.
* El proyecto busca simular escenarios reales de observabilidad y gestión de incidentes utilizados en entornos corporativos.
