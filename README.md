# Sistema de Gestión y Análisis de Incidentes

Aplicación backend inspirada en plataformas de observabilidad y monitoreo como Dynatrace. El proyecto expone una API REST para gestionar incidentes, registrar cambios de estado y ejecutar análisis automáticos que permiten clasificar incidentes según su impacto y comportamiento histórico.

El objetivo del proyecto es simular escenarios reales de observabilidad corporativa, incorporando conceptos como severidad, análisis automático, recurrencia de incidentes y futuras integraciones con herramientas de monitoreo e ITSM.

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
* Asociación de incidentes a servicios de negocio.

### Motor de Severidad

* Cálculo automático de severidad al crear un incidente.
* Clasificación automática en:

    * `LOW`
    * `MEDIUM`
    * `HIGH`
    * `CRITICAL`
* Evaluación basada en métricas operacionales:

    * Tasa de error (`errorRate`)
    * Cantidad de solicitudes (`requestCount`)
    * Tiempo promedio de respuesta (`avgResponseTime`)
    * Usuarios impactados (`impactedUsers`)
* Sistema de puntuación (Severity Score) para determinar la severidad final.

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

## Modelo de Incidente

Cada incidente almacena información relevante para el análisis automático:

* Título
* Descripción
* Servicio de negocio asociado
* Severidad calculada automáticamente
* Estado
* Tasa de error (`errorRate`)
* Cantidad de solicitudes (`requestCount`)
* Tiempo promedio de respuesta (`avgResponseTime`)
* Usuarios impactados (`impactedUsers`)
* Fecha de creación
* Fecha de actualización

## Motor de Severidad

La severidad de un incidente es calculada automáticamente por el sistema a partir de las métricas registradas.

### Métricas Analizadas

* Error Rate
* Request Count
* Average Response Time
* Impacted Users

### Flujo de Cálculo

```text
Incident
    ↓
SeverityScoreCalculator
    ↓
Score
    ↓
SeverityCalculator
    ↓
Severity
```

### Severidades Soportadas

* LOW
* MEDIUM
* HIGH
* CRITICAL

### Severity Score

Cada métrica aporta una cantidad de puntos según los umbrales configurados.

El puntaje total determina la severidad final del incidente.

## Resultados de Análisis

Actualmente el motor de análisis soporta:

* `NEW_INCIDENT`
* `RECURRING_INCIDENT`
* `HIGH_IMPACT_INCIDENT`
* `FALSE_POSITIVE`

## Reglas de Análisis

### Incidente Recurrente

Un incidente se considera recurrente cuando la cantidad de incidentes asociados al mismo servicio de negocio durante la ventana de análisis supera el umbral configurado.

Configuración actual:

* Ventana de análisis: 30 días.
* Umbral de recurrencia: 3 incidentes.

### Incidente de Alto Impacto

Un incidente se considera de alto impacto cuando su severidad es:

* HIGH
* CRITICAL

### Falso Positivo

Un incidente se considera potencialmente falso positivo cuando cumple las siguientes condiciones:

* Severity = LOW
* Impacted Users < 5

### Incidente Nuevo

Un incidente se considera nuevo cuando no cumple ninguna de las reglas anteriores y no supera el umbral de recurrencia definido para el servicio de negocio asociado.

## Estructura Principal

* `application`: gestión de aplicaciones.
* `businessservice`: servicios de negocio.
* `incident`: gestión de incidentes e historial.
* `analysis`: motor de análisis y severidad.
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

## Ejemplo de Incidente

```json
{
  "title": "Database Connection Timeout",
  "description": "The payment service is experiencing database connection timeouts.",
  "businessServiceId": 1,
  "errorRate": 8,
  "requestCount": 75,
  "avgResponseTime": 2200,
  "impactedUsers": 600
}
```

## Ejemplo de Respuesta de Análisis

```json
{
  "analysisResult": "RECURRING_INCIDENT",
  "reason": "Found recurring incidents in the last 30 days.",
  "createdAt": "2026-06-21T16:18:14"
}
```

## Roadmap

### Completado

* [x] Gestión de aplicaciones.
* [x] Gestión de servicios de negocio.
* [x] Gestión de incidentes.
* [x] Historial de incidentes.
* [x] Cambio de estado.
* [x] Motor de severidad.
* [x] Severity Score.
* [x] Clasificación automática de severidad.
* [x] Motor de análisis automático.
* [x] Detección de incidentes nuevos.
* [x] Detección de incidentes recurrentes.
* [x] Detección de incidentes de alto impacto.
* [x] Detección de falsos positivos.

### Próximamente


* [ ] Confidence Score.
* [ ] Pesos dinámicos para métricas.
* [ ] Análisis asistido por IA.
* [ ] Integración con Dynatrace.
* [ ] Integración con Jira.
* [ ] Dashboard de observabilidad.
* [ ] Métricas históricas por servicio.
* [ ] Reportes de incidentes.

## Notas

* Cada incidente genera automáticamente un análisis al momento de su creación.
* La severidad es calculada automáticamente por el sistema y no es enviada por el cliente.
* Los resultados de análisis se almacenan para permitir auditoría y trazabilidad.
* La arquitectura está organizada por dominios de negocio para facilitar el mantenimiento y la escalabilidad.
* El proyecto busca simular escenarios reales de observabilidad, monitoreo y gestión de incidentes utilizados en entornos corporativos.
* El motor de severidad fue diseñado para evolucionar hacia modelos de análisis más avanzados basados en IA y Confidence Score.

