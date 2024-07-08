
# GismeteoApi Integration Microservice

## Описание
Микросервис для интеграции с [Gismeteo API](https://www.gismeteo.ru/api/). Этот сервис позволяет получать данные о погоде через REST API.

## Используемые технологии
- Spring Boot 3.3.1
- Spring Data JPA
- Lombok
- Liquibase
- OpenAPI with Swagger-UI
- MapStruct
- PostgreSQL

## Использование
В `application.yaml` установите личный токен для подключения к Gismeteo API. (Токен нельзя передавать третьим лицам). Запросить токен можно в поддержке [здесь](https://www.gismeteo.ru/b2b/).

```yaml
gismeteo:
  token: your_personal_token_here
```


## Документация API
После запуска приложения, документация API будет доступна по следующему адресу:
```
http://localhost:8080/swagger-ui/index.html
```

