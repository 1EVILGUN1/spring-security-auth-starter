OAuth2 Resource Server

Starter поддерживает режим OAuth2 Resource Server для валидации JWT,
выданных внешним Identity Provider (Keycloak, Auth0, Okta и т.п.).

### Включение
Через issuer-uri (рекомендуется)
```yaml
security:
  auth:
    oauth2:
      enabled: true
      issuer-uri: https://auth.example.com
```
Через jwk-set-uri
```yaml
security:
  auth:
    oauth2:
      enabled: true
      jwk-set-uri: https://auth.example.com/.well-known/jwks.json
```
| Поле        | Обязательное | Описание                                         |
|-------------|--------------|--------------------------------------------------|
| enabled     | yes          | Включает режим OAuth2 Resource Server |
| issuer-uri      | no           | OIDC issuer. Используется для автоматического получения JWK и валидации iss         |
| jwk-set-uri     | no           | URL JWK Set для проверки подписи JWT         |

###Поведение
- Приложение работает в режиме Resource Server
- Проверяется:
    - подпись JWT
    - срок действия 
    - issuer (iss)
- Аутентификация выполняется без сессий (stateless)
- CSRF отключён
- JWT передаётся через:
```
Authorization: Bearer <token>
```

### Roles / Authorities
Starter извлекает роли из JWT claim:
```json
{
  "roles": ["ADMIN", "USER"]
}
```
И преобразует их в GrantedAuthority:
```
ROLE_ADMIN
ROLE_USER
```
Claim roles и префикс ROLE_ можно вынести в properties при необходимости.

### Что делает starter
- Конфигурирует oauth2ResourceServer()
- Создаёт JwtDecoder
- Подключает JwtAuthenticationConverter
- Преобразует roles → authorities
- Интегрируется в единый SecurityFilterChain

### Что starter НЕ делает
- ❌ не выдаёт токены
- ❌ не реализует Authorization Server
- ❌ не управляет пользователями
- ❌ не хранит client secrets

Он только валидирует входящие JWT.

[На главную](../../../../README.md)