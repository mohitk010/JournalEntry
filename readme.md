# Journal Entry App

A Spring Boot REST API for writing and managing personal journal entries. Each user keeps their own private journal, secured with role-based access, and gets a weekly email digest of their entries every Sunday. The app also includes weather lookup with Redis caching.

## Tech Stack

- **Java 17**, **Spring Boot**
- **MongoDB Atlas** for persistence (Spring Data MongoDB + MongoTemplate)
- **Spring Security** with BCrypt password hashing and role-based access
- **Redis** for caching external API responses
- **Gmail SMTP** (Spring Mail) for email delivery
- **Spring Scheduling** for automated weekly jobs
- **Weatherstack API** for weather data
- **Maven** build

## Features

### Users and journal entries
The app has two MongoDB collections: `users` and `journal_entries`. Each user document holds a `journalEntries` list that references only the entries that user created, so users never see each other's entries.

### Atomic operations with transactions
Operations that touch both collections, such as deleting an entry, run inside a MongoDB transaction (`MongoTransactionManager` + `@Transactional`). The entry is removed from the user's list and from the database together, and if either step fails, both are rolled back.

### Security
- Passwords are hashed with `BCryptPasswordEncoder` before being stored.
- Users are assigned roles: `USER` or `ADMIN`.
- Regular users can only access their own entries; admins can view all entries.

### Weather integration with Redis caching
Users can look up the current weather for any city through the Weatherstack API. Responses are cached in Redis with a 15-minute expiry, so repeated requests for the same city are served from the cache instead of calling the external API each time.

### Custom queries with MongoTemplate
Beyond standard repository methods, the app uses `MongoTemplate` with `Query` and `Criteria` to fetch users matching specific conditions.

### Weekly email digest
An email service sends mail through Gmail SMTP. A scheduled job runs every Sunday, gathers each user's entries from the past week, and emails them a combined summary.

## Getting Started

### Prerequisites
- Java 17+
- A MongoDB Atlas cluster (transactions require a replica set, which Atlas provides)
- A running Redis instance
- A Weatherstack API key
- A Gmail account with an [App Password](https://support.google.com/accounts/answer/185833) for SMTP

### Configuration
`application.properties` is not committed because it contains credentials. Create it at `src/main/resources/application.properties`:

```properties
# MongoDB
spring.data.mongodb.uri=<your-mongodb-atlas-uri>
spring.data.mongodb.database=<your-database-name>

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

# Weather API
weather.api.key=<your-weatherstack-api-key>

# Email (Gmail SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<your-gmail-address>
spring.mail.password=<your-gmail-app-password>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Run

```bash
# macOS / Linux
./mvnw spring-boot:run

# Windows
.\mvnw spring-boot:run
```

The app starts on `http://localhost:8080`.

## Project Structure

```
src/main/java/com/edigest/journalApp
├── config/        # Security, Redis, and transaction configuration
├── controller/    # REST endpoints
├── entity/        # User and JournalEntry documents
├── repository/    # Spring Data repositories and MongoTemplate queries
├── Scheduler/     # Weekly email digest job
└── servies/       # Business logic, weather, email, and caching services
```