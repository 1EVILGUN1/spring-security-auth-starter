Username / Password Authentication

Starter поддерживает аутентификацию по username / password через JSON-endpoint.
Реализация основана на стандартном DaoAuthenticationProvider Spring Security.

```yaml
security:
  auth:
    username-password:
      enabled: true
      login-path: /login
```
### Параметры конфигурации

| Поле        | Обязательное | Описание                                                 |
|-------------|--------------|----------------------------------------------------------|
| enabled     | yes          | Включает username/password аутентификацию(default false) |
| login-path      | no           | HTTP endpoint для логина(default /login)                 |

### Endpoint
#### POST {login-path}

Request body (JSON)
```json
{
  "username": "user",
  "password": "password"
}
```

### Поведение
- Endpoint доступен без аутентификации
- Используется stateless-режим
- CSRF отключён
- Формы (formLogin) не используются
- Логин работает только через JSON

### Что делает starter
- Регистрирует JsonUsernamePasswordAuthenticationFilter
- Делегирует аутентификацию в AuthenticationManager
- Использует DaoAuthenticationProvider
- Заполняет SecurityContext при успехе

### Что starter НЕ делает
- ❌ не хранит пользователей
- ❌ не реализует UserDetailsService
- ❌ не проверяет пароль самостоятельно
- ❌ не создаёт JWT
- ❌ не управляет сессией

[На главную](../../../../README.md)