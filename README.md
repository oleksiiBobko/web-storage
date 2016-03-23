web-storage
=====

Spring MVC + Security + Apache tiles.

Twitter bootstrap + JQuery

Generic dao + Hibernate + c3p0

JUnit Mockito testing.

http://tomcat-bobkofiles.rhcloud.com

do not forget to add jdbc.properties file into classpath

example:
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/album
jdbc.username=root
jdbc.password=root
c3p0.acquireIncrement = 3
c3p0.minPoolSize = 5
c3p0.maxPoolSize = 20
c3p0.maxIdleTime = 3600
hibernate.show_sql = true
hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
hibernate.format_sql = true
hibernate.use_sql_comments = true
hibernate.auto_close_session = true

data.root.path=/home/oleksii/pictures/
pagination.page.size=3
```
email.properties file

```
email.host=smtp.gmail.com
email.port=587
email.username=your_nameo@gmail.com
email.password=secret
```
