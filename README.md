# GitHub API Spring BSoot

A Spring Boot microservice that integrates with the GitHub API.  
Built with Java 25, and runs on the default Spring Boot port (`8080`).

---

## Prerequisites

- **Java 25**
- **Gradle**

---

## Setup

### Clone the repository:

```bash
git clone https://github.com/SchwartzCodeHub/github-api-springboot.git
cd github-api-springboot
```

### Build the project:

```bash
./gradlew build
```

### Run the service

```bash
./gradlew bootRun
```

---

## Endpoints

**Get GitHub User Stats with Repository**

```http
GET /api/users/{userId}/stats
```

Example

```bash
curl http://localhost:8080/api/users/octocat/stats
```

---

## Design Notes

- Uses Spring Boot with RestTemplate to call GitHub’s public API. This utilizes Spring's provided rest client without needing to pull in additional dependencies, such as Feign Client.

- For this exercise, I decided to avoid authentication to reduce complexity as the APIs are available publicly. This could be expanded to use authentication, reducing issues with the rate limiting.

- A local Caffeine cache is used to reduce calls to the GitHub API and avoid rate limits as well as cache the data. Data is currently cached for 60 minutes to align with the timing for rate limits but this never became a practical concern during development.

- Generic exception handling via controller advice prevents leaking internal errors to the user. Since rate limits can come into play, invalid usernames can be provided, or services can be unreachable, I decided to provide some basic error handling for common response codes while also generically capturing errors and hiding them from the user under generic 500 errors.

- A custom health check point is implemented using the actuator, specifically checking the GitHub status API as this service is fully dependent on the GitHub APIs. If that dependent service is down, this will also report as down to fail fast. The fact that the GitHub API is down is also reported in the health check to reduce troubleshooting efforts.

- Unit tests are provided for all classes to provide protection against regression defects as well as validate the logic of the classes.
