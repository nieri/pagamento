#Sistema de Pagamento
##Sistema que compreende:

- Realizar transações financeiras
##Tecnologias utilizadas:

- Spring Boot
- Spring-data
- Hibernate
- Jackson 2
- Embedded Tomcat
- Docker

###Para os testes Unitarios
- Junit
- Spring-test
### Banco de dados
- MongoDB

##Serviços
- O serviço está disponível em  http://localhost:8080/

###Passo a passo:

* Criar Docker Network:
```
docker network create springbootmongo

```

* Como rodar o MongoDB no Docker:
```
mkdir -p ~/mongo-data

```

* Agora vamos iniciar o container do mongo:
```
docker run -d --name mongocontainer --network=springbootmongo -v ~/mongo-data:/data/db -d mongo

```

* Compilar o projeto
Entre na pasta ./sistem-contas
Execute:
```
mvn clean install dockerfile:build
```
* Execute o projeto:
```
docker run --network=springbootmongo -p 8080:8080  -t springio/pagamento

```