### Проект тестов для API

#### Сборка docker контейнера
Перейти в директорию проекта и выполнить команду

`docker build -t apitests .`

#### Запуск тестов
Windows

`docker run -it --rm -v pathTo\build\allure-results:/home/gradle/build/allure-results apitests`

Unix

`docker run -it --rm -v pathT/build/allure-results:/home/gradle/build/allure-results apitests`

#### Генерация отчета
Windows

`allure serve build\allure-results`

Unix

`allure serve build/allure-results`


