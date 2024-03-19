# README - Executando a Aplicação Backend Spring Boot

Este README fornecerá instruções sobre como executar a aplicação backend Spring Boot e interagir com ela.

## Pré-requisitos
Certifique-se de ter os seguintes requisitos instalados em sua máquina:

* Java Development Kit (JDK) - Versão 17 ou superior

* Gradle - Sistema de automação de compilação e construção

## Configuração do Ambiente
Clonar o Repositório: Clone o repositório do projeto em sua máquina local.

## Executando a Aplicação
Após configurar o ambiente, você pode executar a aplicação backend Spring Boot com os seguintes passos:

* Compilar o Projeto: Abra um terminal na raiz do projeto e execute o seguinte comando para compilar o projeto usando o Gradle:
gradle build

* Executar a Aplicação: Após a compilação bem-sucedida, execute a aplicação com a sua IDE de preferência.

* Isso iniciará a aplicação Spring Boot e estará disponível em http://localhost:8080 (ou outro URL, dependendo das configurações).

## Interagindo com a Aplicação

Agora que a aplicação está em execução, você pode interagir com ela usando ferramentas como Postman, cURL ou qualquer cliente HTTP de sua preferência. Consulte a documentação da API ou do projeto para obter informações detalhadas sobre os endpoints disponíveis e como usá-los.

A Api também disponibiliza interação via Swagger no link: http://localhost:8080/swagger-ui/index.html

Você também pode interagir rodando o frontend na sua máquina local, seguindo os passos para a execução dele contidas no pacote "frontend"

## Exemplo de Requisição
Aqui está um exemplo de como fazer uma solicitação para o endpoint split-order usando o JSON fornecido:
```
curl -X POST http://localhost:8080/api/v1/order/split-order \
     -H "Content-Type: application/json" \
     -d '{
          "participants": [
            {
              "name": "Juliano",
              "items": [
                {
                  "name": "Hamburguer",
                  "value": 40.0
                },
                {
                  "name": "Sobremesa",
                  "value": 2.0
                }
              ]
            },
            {
              "name": "Amigo",
              "items": [
                {
                  "name": "Sanduíche",
                  "value": 8.0
                }
              ]
            }
          ],
          "increases":[
            {
              "name": "frete",
              "value": 8.0,
              "isPercentage": false
            }
          ],
          "discounts":[
            {
              "name": "cupomDesconto",
              "value": 20,
              "isPercentage": false
            }
          ]
        }'
```
## Conclusão

Agora você está pronto para executar e interagir com a aplicação backend Spring Boot. Se precisar de mais informações ou ajuda, consulte a documentação do Spring Boot ou entre em contato com o desenvolvedor. Divirta-se! 🚀
