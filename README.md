# Agrix Fase C 

Continuação das fases A e B do Projeto Agrix, que consiste numa API de controle de fazendas e plantações, utilizando Spring Boot, Spring Boot Web, Spring Boot Starter Actuator, Spring Data JPA e Spring Security e MySQL Connector-J.

<br>

## Premissa do projeto

Chegamos à Fase C do projeto Agrix! Agora que temos um projeto funcional, precisamos começar a nos preocupar com aspectos de segurança.

<br>

## Habilidades desenvolvidas

- Aplicar o conhecimento sobre Spring Security para adicionar autenticação ao projeto.
- Garantir que diferentes rotas atendam a regras específicas de autorização.

<br> 

## Instalação

1. Clone o repositório

- Use o comando: `git clone git@github.com:yurioneix/agrix-fase-c.git`
- Entre na pasta do repositório que você acabou de clonar:
    - `cd agrix-fase-c`

2. Instale as dependências

- `mvn install -DskipTests`

3. Suba os containers

- `docker-compose up -d`

<br>

## Endpoints

- <strong> POST `/farms` </strong>

<details>
  <summary>Cadastra uma nova fazenda</summary>

  - Exemplo de requisição:
    ```json
      {
        "name": "Fazendinha",
        "size": 5
      }
    ```

  - Exemplo de resposta com `status 200`:

    ```json
      {
        "id": 1,
        "name": "Fazendinha",
        "size": 5
      }
    ```
</details>

<br>

- <strong> GET `/farms` </strong>

<details>
  <summary>Retorna todas as fazendas cadastradas</summary>

  - Exemplo de resposta com `status 200`:

    ```json
      [
        {
          "id": 1,
          "name": "Fazendinha",
          "size": 5.0
        },
        {
          "id": 2,
          "name": "Fazenda do Júlio",
          "size": 2.5
        }
      ]
    ```

  - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.
  - Caso a pessoa autenticada não possua role de `USER`, `MANAGER` ou `ADMIN`, retorna status 403

</details>

<br>

- <strong> GET `/farms/{id}` </strong>

<details>
  <summary>Retorna uma fazenda pelo seu id</summary>

  - Exemplo de resposta com `status 200` para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

    ```json
      {
        "id": 3,
        "name": "My Cabbages!",
        "size": 3.49
      }
    ```
</details>

<br>

- <strong> POST `/farms/{farmId}/crops`</strong>

<details>
  <summary>Associa uma plantação à uma fazenda, através do id da fazenda</summary>

  - Exemplo de requisição na rota `/farms/1/crops` com `status 201` (supondo que exista uma fazenda com `id = 1`):

    ```json
      {
        "name": "Couve-flor",
        "plantedArea": 5.43
      }
    ```

  - Exemplo de resposta:

    ```json
      {
        "id": 1,
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "farmId": 1
      }
    ```

  - Caso não exista uma fazenda com o `id` passado, a rota deve retornar o `status HTTP 404` com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

</details>

<br>

- <strong> POST `/farms/{farmId}/crops` </strong>

<details>
  <summary> Associa uma plantação à uma fazenda, através do id da fazenda </summary>

  - Ajusta a rota da fase A para receber 2 novos campos com datas.

  - Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

    ```json
      {
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "plantedDate": "2022-12-05",
        "harvestDate": "2023-06-08"
      }
    ```

  - Exemplo de resposta com `status 201`:
  
    ```json
      {
        "id": 1,
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "plantedDate": "2022-12-05",
        "harvestDate": "2023-06-08",
        "farmId": 1
      }
    ```
</details>

<br>

- <strong> GET `/farms/{farmId}/crops` </strong>

<details>
  <summary>Retorna uma plantação pelo id da fazenda em que ela está associada</summary>

  Exemplo de resposta, com `status 200`, para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

  ```json
    [
      {
        "id": 1,
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "plantedDate": "2022-12-05",
        "harvestDate": "2023-06-08",
        "farmId": 1
      },
      {
        "id": 2,
        "name": "Alface",
        "plantedArea": 21.3,
        "plantedDate": "2022-02-15",
        "harvestDate": "2023-02-20",
        "farmId": 1
      }
    ]
  ```
</details>

<br>

- <strong> GET `/crops` </strong>

<details>
  <summary>Retorna todas as plantações cadastradas</summary>

  - Exemplo de resposta da requisição:
    ```json
        [
          {
            "id": 1,
            "name": "Couve-flor",
            "plantedArea": 5.43,
            "plantedDate": "2022-02-15",
            "harvestDate": "2023-02-20",
            "farmId": 1
          },
          {
            "id": 2,
            "name": "Alface",
            "plantedArea": 21.3,
            "plantedDate": "2022-02-15",
            "harvestDate": "2023-02-20",
            "farmId": 1
          },
          {
            "id": 3,
            "name": "Tomate",
            "plantedArea": 1.9,
            "plantedDate": "2023-05-22",
            "harvestDate": "2024-01-10",
            "farmId": 2
          }
        ]
    ```

  - Caso a pessoa autenticada não possua a role `MANAGER` ou `ADMIN`, retorna status 403. 

</details>

<br>

- <strong> GET `/crops/{id}` </strong>

<details>
  <summary>Retorna uma plantação pelo seu id</summary>

  - Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

  ```json
    {
      "id": 3,
      "name": "Tomate",
      "plantedArea": 1.9,
      "plantedDate": "2023-05-22",
      "harvestDate": "2024-01-10",
      "farmId": 2
    }
  ```
</details>

<br>

- <strong> GET `/crops/search` </strong>

<details>
  <summary>Busca uma plantação pela data de colheita</summary>

  - Deve receber dois parâmetros por query string para busca:
    - `start`: data de início
    - `end`: data de fim
  - Deve retornar uma lista com as plantações nas quais o campo `harvestDate` esteja entre as data de início e de fim.

  - Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

    ```json
      [
        {
          "id": 1,
          "name": "Couve-flor",
          "plantedArea": 5.43,
          "plantedDate": "2022-02-15",
          "harvestDate": "2023-02-20",
          "farmId": 1
        },
        {
          "id": 3,
          "name": "Tomate",
          "plantedArea": 1.9,
          "plantedDate": "2023-05-22",
          "harvestDate": "2024-01-10",
          "farmId": 2
        }
      ]
    ```
</details>

<br>

- <strong> POST `/fertilizers` </strong>

<details>
  <summary> Cria um fertilizante </summary>

  - Exemplo de requisição:

    ```json
      {
        "name": "Compostagem",
        "brand": "Feita em casa",
        "composition": "Restos de alimentos"
      }
    ```

  - Exemplo de resposta:
  
    ```json
    {
      "id": 1,
      "name": "Compostagem",
      "brand": "Feita em casa",
      "composition": "Restos de alimentos"
    }
    ```
</details>

<br>

- <strong> GET `/fertilizers` </strong>

<details>
  <summary>Lista todos os fertilizantes cadastrados</summary>

  - Exemplo de resposta:
    ```json
      [
        {
          "id": 1,
          "name": "Compostagem",
          "brand": "Feita em casa",
          "composition": "Restos de alimentos"
        },
        {
          "id": 2,
          "name": "Húmus",
          "brand": "Feito pelas minhocas",
          "composition": "Muitos nutrientes"
        },
        {
          "id": 3,
          "name": "Adubo",
          "brand": "Feito pelas vaquinhas",
          "composition": "Esterco"
        }
      ]
    ```

  - Caso a pessoa autenticada não possua role `ADMIN`, retorna status 403.
</details>

<br> 

- <strong> GET `/fertilizers/{id}` </strong>

<details>
  <summary>Retorna uma fertilizante pelo seu id</summary>

  - Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

    ```json
      {
        "id": 3,
        "name": "Adubo",
        "brand": "Feito pelas vaquinhas",
        "composition": "Esterco"
      }
    ```

</details> 

<br>

- <strong> POST `/crops/{cropId}/fertilizers/{fertilizerId}` </strong>

<details>
  <summary>Associa uma plantação com um fertilizante</summary>

  - Caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.
  - Caso não exista um fertilizante com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Fertilizante não encontrado!` no corpo da resposta.

  - Exemplo de resposta para a rota `/crops/1/fertilizers/2` (supondo que exista uma plantação com `id = 1` e um fertilizante com `id = 2`):

```text
Fertilizante e plantação associados com sucesso!
```
</details>

<br>

- <strong> POST `/persons` </strong>

<details>
  <summary>Cadastra novas pessoas no banco</summary>

  - Exemplo de requisição na rota POST `/persons`:

    ```json
      {
        "username": "zerocool",
        "password": "senhasecreta",
        "role": "ADMIN"
      }
    ```

  - Exemplo de resposta:
  
    ```json
      {
        "id": 1,
        "username": "zerocool",
        "role": "ADMIN"
      }
    ```

</details>

<br>

- <strong> POST `auth/login` </strong>

<details>
  <summary>Login de usuário cadastrado</summary>
  
  - Deve receber o `username` e `password` no corpo da requisição
  
  - Deve validar os dados passados utilizando as pessoas que foram criadas pela rota `/persons`
    
  - Caso os dados estejam incorretos, deve retornar status 403
    
  - Caso os dados estejam corretos, deve retornar um campo `token` contendo um JWT gerado
    
  - Exemplo de requisição na rota POST `/auth/login` (suppondo que os dados estejam corretos):
    
    ```json
      {
        "username": "zerocool",
        "password": "senhasecreta"
      }
    ```
    
  - Exemplo de resposta:
    ```json
      {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZ3JpeCIsInN1YiI6Im1ycm9ib3QiLCJleHAiOjE2ODk5ODY2NTN9.lyha4rMcMhFd_ij-farGCXuJy-1Tun1IpJd5Ot6z_5w"
      }
    ```
</details>

<br> 

## Pastas/arquivos desenvolvidos por mim
```bash
  src/main/java/com.betrybe.agrix.controller
  src/main/java/com.betrybe.agrix.model
  src/main/java/com.betrybe.agrix.security
  src/main/java/com.betrybe.agrix.service
  src/main/java/com.betrybe.agrix.util
  docker-compose.yaml
  Dockerfile
```
