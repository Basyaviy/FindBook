//Ошибки
После изменения записи кнопкой Update, затираются остальные поля записи

//Что может

Веб сервис поиска книги в библиотеке. Книги библиотеки хранятся в формате fb2 в открытом виде или в zip архивах. 

- По нажатию на кнопку refillDB происходит два крупных события:
    - Обход всех файлов библиотеки, и распарсивание fb2 заголовка. Из заголовочной информации получаем такие поля как : Имя Фамилия автора, Название книги и тд
    - Запись полученных данных о книге в Базу Данных
- Поле поиска настроено на поиск по Имени, Фамилии автора и Названии Книги
- По кнопке Update можно изменить записи о книге в Базе Данных. По кнопке refillDB восстанавливается исходное состояние
- По кнопке Download книгу можно скачать

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

1. Добавить переменную окружения
    
    ```xml
    JAVA_TOOL_OPTIONS = -Dfile.encoding=UTF8
    ```
    
2. Собрать проект
    
    ```xml
    mvn package
    ```
    
3. Расположить на сервере приложений. Для запуска использовался Tomcat 9
    
    Если запуск производится не через IDE, необходимо добавить в web.xml строки:
    
    ```xml
     
    	<filter>  
    	    <filter-name>encodingFilter</filter-name>  
    	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
    	    <init-param>  
    	       <param-name>encoding</param-name>  
    	       <param-value>UTF-8</param-value>  
    	    </init-param>  
    	    <init-param>  
    	       <param-name>forceEncoding</param-name>  
    	       <param-value>true</param-value>  
    	    </init-param>  
    	</filter>  
    	<filter-mapping>  
    	    <filter-name>encodingFilter</filter-name>  
    	    <url-pattern>/*</url-pattern>  
    	</filter-mapping>
    ```
    
4. Стартовать Сервер приложений и пройти по строке localhost:8080/FindBook
5. Для Первичного нaполнения базы нажать кнопку reFillDB

---

//Используемые технологии

- Maven
- MySQL
- Hibernate
- Spring MVC
