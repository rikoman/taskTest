# taskTest

## Запуск приложение
1. Склонировать репозиторий на свой компьютер
2. Перейти к корневую папку приложения /taskTest/management-task
3. Прописать в консоли команду docker compose build
4. После успешного билда поднимаем контейнеры командой docker compose up
5. В docker ps будут отображаться 2 контейнера, нам нужно узнать порт на котором запущен management-task-database-1, сделать это можно командой docker inspect management-task-database-1
6. Узнали ipAddress и вставляем его в файле application.yml вместо yourIpAddress
7. Запускаем приложение Spring boot и пользуемся

## Ссылки
1. Swagger UI - http://localhost:8080/swagger-ui/index.html
2. api docs - http://localhost:8080/v3/api-docs
