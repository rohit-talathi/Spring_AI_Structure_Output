# 🎬 Spring AI Structured Output Demo

This project demonstrates how to use **Spring AI** with different **Output Converters** to transform LLM (Large Language Model) responses into structured Java objects such as:

* Java Bean (POJO)
* List
* Map

---

## 🚀 Features

* Convert AI responses into structured formats
* Use of:

  * `BeanOutputConverter`
  * `ListOutputConverter`
  * `MapOutputConverter`
* REST APIs to fetch movie details in different formats
* Clean and simple Spring Boot implementation

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring AI
* REST API
* Maven

---

## 📁 Project Structure

```
com.springai.strctured.output
│
├── AskService.java        # Business logic for calling AI and converting responses
├── AskController.java     # REST endpoints
├── MovieRecommendation.java  # POJO for structured response (you should create this)
```

---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd <project-folder>
```

### 2. Configure AI Model

Make sure you configure your AI provider (like Ollama / OpenAI) in `application.properties` or `application.yml`.

Example (Ollama):

```properties
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.model=llama3
```

---

### 3. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

---

## 📡 API Endpoints

### 1️⃣ Bean Output Converter

Returns structured response mapped to a Java object.

```
GET /bean-output-converter?movie=Inception
```

✔ Response: `MovieRecommendation` object

---

### 2️⃣ List Output Converter

Returns response as a list of strings.

```
GET /list-output-converter?movie=Inception
```

✔ Response: `List<String>`

---

### 3️⃣ Map Output Converter

Returns response as key-value pairs.

```
GET /map-output-converter?movie=Inception
```

✔ Response: `Map<String, Object>`

---

## 🧠 How It Works

### Step-by-step Flow:

1. User hits REST API with movie name
2. `AskController` receives request
3. Calls `AskService`
4. Prompt is created using `PromptTemplate`
5. AI model generates response
6. Response is converted using respective converter:

   * Bean → `BeanOutputConverter`
   * List → `ListOutputConverter`
   * Map → `MapOutputConverter`
7. Structured response is returned to user

---

## 🧾 Example Prompt Used

```text
Provide movie details for "Inception".
<Expected structured format here>
```

---

## 📌 Important Notes

* Ensure your POJO (`MovieRecommendation`) matches the structure expected by the AI
* Always include `{format}` in prompt to guide AI output
* Debug raw response using:

```java
System.out.println("RAW RESPONSE: " + content);
```

---

