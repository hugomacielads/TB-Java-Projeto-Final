## Babybylon Bank
### Babybylon Bank é um banco inovador com a missão de ensinar as pessoas a utilizarem serviços bancários para promover sua segurança financeira e aumentar o entendimento das relações mercantis e da educação financeira. Nosso objetivo é capacitar nossos clientes com o conhecimento necessário para tomar decisões financeiras informadas e eficazes.

Termos mercado financeiro:
Spread Bancário baseado em clientes de longo prazo, que crescem sua carteira conosco.

## Descrição do Projeto
Este projeto implementa um sistema bancário simples utilizando Java e o framework Spring Boot. O sistema segue o modelo MVC (Model-View-Controller) e permite interação via API RESTful. O sistema é capaz de gerenciar a criação de contas correntes e de pagamento, além de realizar transações bancárias.

## Funcionalidades

### Produtos e Categorias de Clientes:
O sistema categoriza os clientes com base em sua renda mensal, oferecendo produtos específicos para cada categoria:

- Cheque Especial: Limite igual a 10% da renda do cliente
- Cartão de Crédito: Limite correspondente a 60% da renda do cliente
- Financiamento: Parcelas não podem ultrapassar 30% da renda do cliente.
- Investimentos: CDB.

### Categorias
- Bronze: Renda inferior a 2000 - Apenas conta pagamento
- Prata: Renda inferior a 3000 - Conta pagamento com cheque especial e cartão de crédito
- Ouro: Renda inferior a 5000 - Financiamentos
- Platinum: Renda superior a 5000 - Cartão de crédito sem limite e Investimentos.

## Requisitos Funcionais
1. Cadastro e atualização de clientes.
2. Os clientes se cadastram no sistema fornecendo informações como nome, CPF, email, endereço e renda salarial.
3. Após o cadastro do cliente, uma conta bancária é aberta em seu nome.
4. A categoria do cliente é definida e atualizada automaticamente com base na sua renda.
5. A conta do cliente deve receber sua renda mensalmente.
6. Realização de transações financeiras (crédito e débito).
7. Acesso ao saldo, extrato de conta pagamento e extrato de cartão de crédito.

## Endpoints da API

### Clientes
- POST /clientes: Cadastra um novo cliente.
- PUT /clientes/{id}: Atualiza os dados de um cliente existente.
- GET /clientes: Lista todos os clientes.
- GET /clientes/{id}: Obtém os dados de um cliente específico.

### Transações
- POST /api/clientes/{id}/transacoes: Realiza uma transação (crédito ou débito) na conta do cliente.
- GET /api/clientes/{id}/transacoes: Lista todas as transações da conta do cliente.

### Transações Cartão de Crédito
- POST /api/clientes/{id}/transacoesCredito: Realiza uma transação do cartão de crédito. Débito na Fatura.
- GET /clientes/{id}/transacoesCredito: Lista todas as transações da conta de crédito do cliente.

## Estrutura do Projeto
A estrutura do projeto segue a arquitetura MVC, integração com banco de dados PostgreSQL.

## Dependências
As principais dependências do projeto incluem:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database
- Spring Boot Starter Test
