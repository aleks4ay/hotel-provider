# hotel-room_db
Part of Hotel microservices

## Liquibase migrations
- Переливка осуществляется при запуске приложения
- Перелитые данные повторно не заливаются

## Testes

- Для тестирования был добавлен профиль `@ActiveProfiles("test")` и добавлен файл с настройками `application-test.yml`.
  Настройки будут браться из основного файла `application.yml`, потом перезатираться взятыми из `application-test.yml`.
- В конце еще настройки подключения к БД будут перезатерты данными, полученными от контейнера
    - "spring.datasource.url", sqlServerContainer::getJdbcUrl
    - "spring.datasource.username", sqlServerContainer::getUsername
    - "spring.datasource.password", sqlServerContainer::getPassword
- Для конкретного примера можно было профиль и `application-test.yml` не добавлять, так как ни одна из переменных не отличается от тех, что в `application.yml`