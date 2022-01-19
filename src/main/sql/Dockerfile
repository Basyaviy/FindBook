FROM mysql:8.0
COPY ./02-book.sql /docker-entrypoint-initdb.d
ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_USER=buser
ENV MYSQL_PASSWORD=buser
ENV MYSQL_DATABASE=my_schema

