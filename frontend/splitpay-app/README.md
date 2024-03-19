# splitpay-app

## README - Executando a Aplicação Frontend
```
Este README fornecerá instruções sobre como executar a aplicação frontend Vue.js e interagir com ela.
```

## Pré-requisitos
```
Certifique-se de ter os seguintes requisitos instalados em sua máquina:

Node.js e npm (geralmente instalados juntos)
```

### Configuração do Ambiente
```
Instalação de Dependências: Abra um terminal na raiz do projeto e execute o seguinte comando para instalar as dependências do projeto: npm install
```

Variáveis de Ambiente: Crie um arquivo .env na raiz do projeto e defina a variável de ambiente VUE_APP_API_URL com o URL da API. Por exemplo: VUE_APP_API_URL=http://localhost:8080

```

### Executando a Aplicação
Após concluir a configuração do ambiente, você pode executar a aplicação frontend com o seguinte comando: npm run serve

Isso iniciará um servidor de desenvolvimento e a aplicação estará disponível em http://localhost:3000 (ou outro URL, dependendo das configurações).

Abra seu navegador da web e acesse o URL fornecido para visualizar e interagir com a aplicação.

### Interagindo com a Aplicação

A aplicação permite que você informe pessoas, itens comprados, gastos adicionais e descontos adicionais. Após preencher os dados, você pode clicar no botão "Dividir os gastos entre os participantes" para enviar os dados para a API e obter os links de pagamento e o total a ser pago por cada participante.

Siga as instruções na interface da aplicação para interagir com ela de acordo com suas necessidades.
