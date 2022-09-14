## Процедура запуска автотестов
***
#### Необходимое ПО:
1. PuTTY
2. IntelliJ IDEA

#### Шаги запуска:
1. В PuTTY ввести адрес IP – 185.119.57.64, логин/пароль - student/HTIPDN
2. Перейти в папку diplom:
   cd diplom
3. Запустить контейнеры:
   docker-compose up -d --force-recreate
4. Запустить jar-файл:
   java -Dspring.datasource.url=jdbc:mysql://185.119.57.64:3306/app -jar artifacts/aqa-shop.jar
5. В терминале IntelliJ IDEA запустить тестирование с помощью фреймворка Allure:
   ./gradlew allureServe
   ./gradlew clean test allureReport
