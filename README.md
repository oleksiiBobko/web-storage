album
=====

Spring MVC + Security + web project which aimed to store photos in DB.

Generic dao + Hibernate + c3p0

Mock testing not implemented yet.

http://tomcat-bobkofiles.rhcloud.com


do not forget to add jdbc.properties file into WEB-INF directory

example:

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
