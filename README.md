# DESAFIO TÉCNICO
## _TARGET API, by Douglas Eleutério_


O desafio é construir uma API com tema para atender um Grande E-commerce Brasileiro.


## Tech

Tecnologias utilizadas no projeto:

- [Java 8]
- [Spring Boot 2.1.8]
- [Spring Data]
- [Spring Security]
- [MySQL]
- [Token JWT]
- [Flyway]
- [Maven]
- [Junit]

## Installation

Necessita ter instalado JDK 8 ou Superior
Maven 3.x.x ou superior
MySQL 8.x.x

Para executar o projeto, além da importação para sua IDE, pode executar via linha de comando.
Para executar via linha de comando, após realizar download do projeto e acessar a pasta raiz, execute o seguinte comando em seu terminal, considerando que o Maven esteja instalado.

```sh
mvn spring-boot:run
```
Atenção ao banco de dados, ele está configurado para conectar na porta 3306 com as credenciais:
- user: root
- password: root

As configurações podem ser alteradas no arquivo application.properties localizado na pasta 'src/main/resources'

A estrutura do banco de dados é criada automaticamente.

No arquivo 'V1__gerar_e_inserir.sql' estão os scripts de criação e inserção de alguns dados para facilitar o processo de testes/simulação.

Após a execução o sistema estará pronto para receber requisições do tipo REST na porta 8080.

## Autenticação

Para a criação de um usuário, enviar uma requisição do tipo POST para
http://localhost:8080/api/auth/signup contendo 'corpo' do tipo JSON obedecendo o layout

``` json
{
    "username": "douglasE",
    "email": "douglas-nesgocios@hotmail.com",
    "password": "123456",
    "role":[
        "ROLE_ADMIN"
    ]
}
```

Após registro do usuário, necessitamos fazer login para recebermos nossas credenciais.
Para obter o token de acesso, enviar uma requisição do tipo POST para http://localhost:8080/api/auth/signin contendo 'corpo' do tipo JSON obedecendo o layout:

```json
{
    "username": "douglasE",
    "password": "123456"
}
```

Deverá receber o seguinte objeto JSON no corpo da resposta
```json
{
    "id": 1,
    "username": "douglasE",
    "email": "douglas-nesgocios@hotmail.com",
    "roles": [
        "ROLE_USER"
    ],
    "tokenType": "Bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb3VnbGFzRSIsImlhdCI6MTYzMDA5MzUyMywiZXhwIjoxNjMwMDk0NDIzfQ.wz88rUYEoLXI9YoPhHp4mO9UyGUZaHYx0cWJtRWDWVing5ps-oqK4DJvivRLo-oFS5l2XICYEkCjINWEYwcvYg"
}
```
Seu token de acesso será o hash retornado como value da variável 'accesstoken', como no exemplo:

```text
 "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb3VnbGFzRSIsImlhdCI6MTYzMDA5MzUyMywiZXhwIjoxNjMwMDk0NDIzfQ.wz88rUYEoLXI9YoPhHp4mO9UyGUZaHYx0cWJtRWDWVing5ps-oqK4DJvivRLo-oFS5l2XICYEkCjINWEYwcvYg"
 ```

Com token em mãos, estará habilitado para enviar requisições durante um período de 15 minutos, posteriormente, terá que obter uma nova chave.

## Produtos

Para enviar um produto para registro, deverá utilizar o método POST no link abaixo, inserir seu token do tipo Bearer nas configurações e inserir o objeto JSON no 'corpo' da requisição obedecendo o layout:

http://localhost:8080/api/products

 ```json
{
  "name":"Bone New Era",
  "price": 150.00,
  "categories":[
    {"id": 1}
  ]
}
```

Será retornado uma resposta com Status Code 200 e um objeto no corpo da resposta similar ao abaixo:
```json
{
  "message": "Product saved: ",
  "date": "2021-09-07T12:14:56.349+0000",
  "obj": {
    "id": 6,
    "name": "Bone New Era",
    "price": 150.0
  }
}
```

O Sistema está pronto para Armazenar, Recuperar por Id, Recuperar todos, Recuperar por Id da categoria.
Exemplo de recuperação de todos os produtos:

http://localhost:8080/api/products/

Retorno da chamada:
```json
{
    "message": "List of Products: ",
    "date": "2021-09-07T12:18:34.040+0000",
    "obj": [
        {
            "id": 1,
            "name": "Camisa Tommy Masculina G",
            "price": 159.99
        },
        {
            "id": 2,
            "name": "Carteira Tommy Masculina ",
            "price": 119.99
        },
        {
            "id": 3,
            "name": "Bolsa Gucci preta",
            "price": 1959.99
        },
        {
            "id": 4,
            "name": "Bone New Era",
            "price": 150.0
        },
        {
            "id": 5,
            "name": "Bone New Era",
            "price": 150.0
        },
        {
            "id": 6,
            "name": "Bone New Era",
            "price": 150.0
        }
    ]
}
```

## Cupom

Para enviar um produto para registro, deverá utilizar o método POST no link abaixo, inserir seu token do tipo Bearer nas configurações e inserir o objeto JSON no 'corpo' da requisição obedecendo o layout:

http://localhost:8080/api/coupons/

```json
{
    "name":"Liquida de Verão 2",
    "discount": 5.99,
    "partner_id": "1"
}
```
Será retornado uma resposta com Status Code 200 e um objeto no corpo da resposta similar ao abaixo:
```json
{
  "message": "Coupon saved: ",
  "date": "2021-09-07T12:21:43.238+0000",
  "obj": {
    "id": 5,
    "name": "Liquida de Verão 2",
    "discount": 5.99
  }
}
```

O Sistema está pronto para Armazenar e Recuperar todos.
Exemplo de recuperação de todos os cupons:

http://localhost:8080/api/coupons

Retorno da chamada:
```json
{
  "message": "List of coupons",
  "date": "2021-09-07T12:22:36.651+0000",
  "obj": [
    {
      "id": 1,
      "name": "LIQUIDA TUDO",
      "discount": 7.5
    },
    {
      "id": 2,
      "name": "LOUCURA TOTAL",
      "discount": 10.0
    },
    {
      "id": 3,
      "name": "Liquida de Verão",
      "discount": 55.99
    }
  ]
}
```

## Carrinho de Compras

Para enviar um checkout, deverá utilizar o método POST no link abaixo, inserir seu token do tipo Bearer nas configurações e inserir o objeto JSON no 'corpo' da requisição obedecendo o layout:

http://localhost:8080/api/shopping/ 

```json
{
  "coupons": [
    {"id":3},
    {"id":1},
    {"id":2}
  ],
  "itens":[
    {"product":{"id":1}, "quantity": 2},
    {"product":{"id":2}, "quantity": 2},
    {"product":{"id":3}, "quantity": 2}
  ]
}
```
Será retornado uma resposta com Status Code 200 e um objeto no corpo da resposta similar ao abaixo:
```json
{
  "message": "Total for Payment: R$ 1873.04, Value without discount R$ 4479.94 ",
  "date": "2021-09-07T12:26:41.125+0000",
  "obj": {
    "date": "2021-09-07T12:26:40.964+0000",
    "couponId": 3,
    "discount": 2606.89,
    "total": 4479.94,
    "totalPayment": 1873.04
  }
}
```

Sobre os descontos, surgiu dúvida quando ao limite de acumulos de descontos, que pode ser por cupom(único), acima de 10 Itens e desconto progressivo.
Questionei a recrutadora sobre os descontos, segue resposta por parte dela.

```text
Douglas, 

Segue o retorno em relação a sua dúvida: 

Os descontos são cumulativos. Apenas os cupons é que não são. Então, fique atento a essas duas premissas bases. 


cordialmente,

Suelen Oliveira
Recursos Humanos
Facilit Tecnologia

```

O Envio dos Cupons são opcionais, podendo ser enviado mais de um. Caso envie mais de um cupom, será levado em consideração o cupom com maior valor.

No cenário da execução para teste, o cupom com maior valor é o de Id número 3.

O Sistema está armazenando as 'compras', simulando uma operação real de um e-commerce.

A compra está sendo persistido na tabela 'purchase_order'

```sql
    CREATE TABLE IF NOT EXISTS purchase_order (
        id            int primary key auto_increment,
        date          timestamp,
        coupon_id     int,
        discount      float,
        total         float,
        total_payment float
    );
```

Além da tabela citada acima, existe a tabela de 'Partner' para gravar informaçõs dos parceiros que receberão os cupons para divulgação, além de outras tabelas.

Coloco-me a disposição para eventuais esclarecimentos ou dúvidas.
