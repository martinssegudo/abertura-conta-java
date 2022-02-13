Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Você pode testar os endpoints e a documentação usando swagger ui
```
http://localhost:8080/swagger-ui.html
```
Para cadastrar novo cliente pela documentação siga as seguintes convenções.

Pessoa:
* 0 - Fisica
* 1 - Jurídica

Conta:
* 0 - Corrente
* 1 - Poupança

São necessários os seguintes pré-requisitos para a execução do projeto:

* Java 8 ou versões superiores.
* Maven
* Intellj IDEA Community Edition ou sua IDE favorita.
* Controle de versão GIT instalado na sua máquina.
* Conta no GitHub para o armazenamento do seu projeto na nuvem.
* H2 para banco de dados.