# springbootvue

## Descrição do projeto 

<p align="justify">
  Projeto CRUD para inclusão, listagem, alteração e deleção de documento (entidade fictícia).
</p>

## Status

<p align="justify">
  Em desenvolvimento
</p>

## Pré-requisitos

- ``Java 11``
- ``Maven``
- ``[Node](https://nodejs.org/en/download/)``
- ``Vue CLI ( Standard Tooling for Vue.js Development - npm install -g @vue/cli )``
- ``Eclipse IDE for Java Developers [IDE for backend with Spring Tools 4 ( aka Spring Tool Suite 4 )]``
- ``Visual Studio Code (IDE for frontend)``
- ``PostgreSQL 14``

## Tecnologias utilizadas

- ``Springboot 2.6.6 [Spring Data JPA, Validation, JDBC PostgreSQL Driver]``
- ``VUE 2.6.14 [bootstrap, vue-router, axios]``

## Como rodar a aplicação

- ``1. Configure o banco de dados em \backend\src\main\resources\application.properties``

<Pendente de conclusão>

## EndPoints [DocumentoController](https://github.com/GuidoFPC/springbootvue/blob/main/backend/src/main/java/br/leg/camara/helloword/controllers/DocumentoController.java)

- ``GET: http://localhost:8180/documentos?page=0&size=5&sort=nome,ASC``
- ``GET: http://localhost:8180/documentos/{id}``
- ``POST: http://localhost:8180/documentos``
- ``PUT: http://localhost:8180/documentos/{id}``
- ``DELETE: http://localhost:8180/documentos/{id}``

## Tarefas em aberto

:memo: 1. Componente de mensagem ( exibir msgs do sistema )

:memo: 2. Validação de formulário

:memo: 3. Paginação

:memo: 4. Ordenação da tabela

:memo: 5. Filtros da tabela