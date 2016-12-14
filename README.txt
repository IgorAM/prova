

Assumi que o sistema deveria ter a flexibilidade de que cada plano FaleMais possa ter uma taxa adicional própria, portanto
na tabela Plan coloquei a coluna addition_tax.

1) Downlaod Maven 3.3.9
https://maven.apache.org/download.cgi

2) Download JDK 8u111
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

3) Necessário ter instalado o MYSQL Client.

4) Acesssar o arquivo application.properties e alterar as propriedades jdbc.username e jdbc.password, utilizando os dados do seu banco localhost(MYSQL)
caminho do arquivo: falemais/src/main/resources/application.properties

5) Executar o script localizado no caminho abaixo em seu banco localhost mysql:
falemais/src/main/resources/META-INF/sql/create_schema.sql


6) Para subir o Tomcat, executar o comando:
mvn clean install tomcat7:run

7) O projeto poderá ser acessado no endereço:
http://localhost:8080/falemais

