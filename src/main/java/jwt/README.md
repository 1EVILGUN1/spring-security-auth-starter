JWT Configuration

Для включения JWT-аутентификации добавьте настройки в application.yml:

```yaml
security:
  auth:
    jwt:
      enabled: true
      issuer: my-issuer
      secret: super-secret-key-should-be-long
      roles-claim: roles
      role-prefix: ROLE_
```

### Описание параметров

| Поле        | Обязательное | Описание                                                                                       |
|-------------|--------------|------------------------------------------------------------------------------------------------|
| enabled     | yes          | Включает JWT-аутентификацию. Если false, JWT-фильтр и связанные бины не регистрируются.        |
| issuer      | yes          | Значение iss (issuer), которое должно присутствовать в JWT. Используется для валидации токена. |
| secret      | yes          | Секретный ключ для проверки подписи JWT (HMAC). Минимум 256 бит для HS256.                     |
| roles-claim | no           | Имя claim в JWT, содержащего список ролей. По умолчанию: roles.                                |
| role-prefix | no           | Префикс для ролей при создании GrantedAuthority. По умолчанию: ROLE_.                          |

### Пример JWT payload

```json
{
  "sub": "user-123",
  "iss": "my-issuer",
  "roles": [
    "ADMIN",
    "USER"
  ]
}
```

В SecurityContext будут созданы authority:

```nginx
ROLE_ADMIN
ROLE_USER
```

### Поведение по умолчанию

- Все запросы требуют аутентификации
- Stateless (SessionCreationPolicy.STATELESS)
- CSRF отключён
- JWT-фильтр добавляется перед UsernamePasswordAuthenticationFilter

### Обработка ошибок

### 401 Unauthorized

Возвращается, если пользователь не аутентифицирован.

```json
{
  "error": "unauthorized",
  "message": "Authentication is required"
}
```

### 403 Forbidden

Возвращается, если у пользователя нет прав доступа.

```json
{
  "error": "forbidden",
  "message": "Access is denied"
}
```

### Переопределение поведения

Любой бин можно переопределить в приложении:

- JwtTokenService
- AuthenticationEntryPoint
- AccessDeniedHandler
- SecurityFilterChain

### Требования

- Java 17+
- Spring Boot 3.x
- Servlet-based приложение

[На главную](../../../../README.md)