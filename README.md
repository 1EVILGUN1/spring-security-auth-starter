# spring-security-auth-starter

Spring Boot starter для унифицированной аутентификации:

- JWT
- Username / Password
- OAuth2 Resource Server

Starter предназначен для проектов, которые используют **одинаковый security-шаблон** и хотят избежать копипаста.

---

## Возможности

- JWT-аутентификация (stateless)
- Единый `SecurityFilterChain`
- Унифицированные ответы:
    - 401 Unauthorized
    - 403 Forbidden
- Настройка через `application.yml`
- Все компоненты переопределяемы
- Без магии и скрытого поведения

---

## Установка

### Maven

```xml

<dependency>
    <groupId>com.castom.security</groupId>
    <artifactId>spring-security-auth-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

- [JWT](src/main/java/jwt/README.md)
- [Username / Password](src/main/java/usernamepassword/README.md)
- [OAuth2 Resource Server](src/main/java/oauth2/README.md)