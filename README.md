Criação de api bancaria de abertura de conta (Gerenciamento de Clientes) baseado na historia de usuario abaixo

[Story Abertura de Conta]


Enquanto cliente, gostaria de abrir minha conta PF ou PJ e que ao final do processo já existisse um crédito pré aprovado.


[Critérios de Aceite:]

*Pessoa física - O cliente pode abrir conta Corrente, Poupança ou Poupança e Corrente.
*Pessoa jurídica - O cliente pode abrir somente conta corrente.
*Nome completo [acima de 10 caracteres se PF] / Razão Social
*Data de nascimento para PF ou data de abertura da empresa para PJ no formato PT-BR [DD/MM/YYYY]
No caso de PF, menores de idade não podem ter conta!
*CPF / CNPJ
*RG (Se PF)
*Pontuação da Serasa (Se PF)
*Nome da Mãe (se PF)
*Nome do pai (se PF)
*Senha de acesso:
A senha deve conter no mínimo 6 caracteres, uma letra maiúscula e um caractere especial.



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