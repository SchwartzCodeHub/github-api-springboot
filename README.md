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

- Uses Spring Boot with RestTemplate to call GitHub’s public API.

- No authentication required for basic requests, but GitHub rate limits unauthenticated calls.

- A local Caffeine cache is used to reduce calls to the GitHub API and avoid rate limits.

- Generic exception handling via controller advice prevents leaking internal errors to the user.
