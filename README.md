# GroundedDoc — AI Document Intelligence & RAG Backend

GroundedDoc is a production-ready, Spring Boot backend for multi-format document ingestion, vector embeddings, similarity search with PostgreSQL `pgvector`, and conversational Question & Answering powered by **Google Gemini** and **Spring AI**.

---

## Features

- **Document Ingestion & Processing:**
  - Multi-format parsing (PDF via Apache PDFBox, Word/Office/text via Apache Tika).
  - Configurable token-based chunking with overlap.
  - Document metadata tracking and lifecycle management.
- **Vector Search & Embeddings:**
  - Google Gemini embeddings using `text-embedding-004` (768 dimensions).
  - PostgreSQL `pgvector` store with HNSW index and Cosine distance metric.
- **Conversational RAG Pipeline:**
  - Context retrieval and citation-backed question answering via `gemini-2.5-flash`.
  - Supports both standard JSON responses and reactive streaming responses (`Flux<String>`).
  - Persistent conversation and chat history stored in PostgreSQL via custom `JpaChatMemory`.
- **Security & User Management:**
  - Stateless authentication with JWT (JSON Web Tokens).
  - BCrypt password hashing and role-based access control (`ROLE_USER`, `ROLE_ADMIN`).
- **Interactive API Documentation:**
  - Swagger UI / OpenAPI 3.0 enabled at `/swagger-ui.html`.

---

## Tech Stack

- **Java:** 21
- **Framework:** Spring Boot 3.3.5
- **AI Framework:** Spring AI 1.1.8
- **LLM & Embeddings:** Google Gemini (`gemini-2.5-flash`, `text-embedding-004`)
- **Database:** PostgreSQL 16 with `pgvector` extension
- **ORM / Persistence:** Spring Data JPA / Hibernate
- **Security:** Spring Security + JJWT 0.12.6
- **Documentation:** SpringDoc OpenAPI 2.6.0

---

## Getting Started

### Prerequisites

- Java 21 SDK
- Docker & Docker Compose
- Google Gemini API Key ([Google AI Studio](https://aistudio.google.com/))

### 1. Start the Database

Start PostgreSQL with `pgvector` using Docker Compose:

```bash
docker compose up -d
```

This starts PostgreSQL on port `5434` with the database `docReader` and the `vector` extension pre-installed.

### 2. Configure Environment Variables

Set your Gemini API key and other secrets:

```bash
# Windows PowerShell
$env:GEMINI_API_KEY="your-google-gemini-api-key"
$env:JWT_SECRET="your-256-bit-hex-secret-key"

# Linux / macOS
export GEMINI_API_KEY="your-google-gemini-api-key"
export JWT_SECRET="your-256-bit-hex-secret-key"
```

### 3. Build & Run

Run the application using the Maven wrapper:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

The application starts on port `8081` with the `dev` profile active.

---

## API Documentation

Access the interactive Swagger UI:
- **Swagger UI:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

### Key Endpoints

| Category | Method | Endpoint | Description |
|---|---|---|---|
| **Auth** | `POST` | `/api/v1/auth/register` | Register a new user |
| **Auth** | `POST` | `/api/v1/auth/login` | Authenticate and obtain JWT token |
| **Documents** | `POST` | `/api/v1/documents/upload` | Upload & ingest document into vector store |
| **Documents** | `GET` | `/api/v1/documents` | List uploaded document metadata |
| **Documents** | `DELETE`| `/api/v1/documents/{id}` | Delete document and its embeddings |
| **Chat** | `POST` | `/api/v1/chat` | Send question and receive RAG answer |
| **Chat** | `POST` | `/api/v1/chat/stream` | Stream conversational answer in real-time |
| **Chat** | `POST` | `/api/v1/chat/search` | Direct similarity search over chunks |
| **Chat** | `GET` | `/api/v1/chat/history/{conversationId}` | Fetch conversation history |

---

## License

This project is licensed under the Apache 2.0 License.
