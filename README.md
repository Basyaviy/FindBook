//Проблемы
Сохраняет в битой кодировке если проект собирать из командной строки. Из IDE всё норм.

# FindBook
//Что может

Веб сервис поиска книги в библиотеке. Книги библиотеки хранятся в формате fb2 в открытом виде или в zip архивах. 

//Процесс установки настройки

1. Сконфигурирвать MySQL базу данных

В spring-mvc-crud-demo-servlet.xml заданы параметры подключения:

```xml
<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/my_schema?useSSL=false&serverTimezone=UTC" />
<property name="user" value="buser" />
<property name="password" value="buser" />
```

Файлы создания базы данных расположен в /sql проекта

- Создание схему и пользователя: 01-create-user.sql
- Создание таблицы “book”: 02-book.sql

В /FindBook/src/main/resources/testLib расположена тестовая библиотека

1. Собрать проект

```xml
mvn package
```

1. Расположить на сервере приложений. Для запуска использовался Tomcat 9
2. Стартовать Сервер приложений и пройти по строке localhost:8080/FindBook
3. Для Первичного нaполнения базы нажать кнопку reFillDB

---

//Используемые технологии

- Maven
- MySQL
- Hibernate
- Spring MVC
