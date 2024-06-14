// Pacientes
let cadastrar = () => {
    // recuperar os dados do usuário e já cria o objeto
    // vamos utilizar programação DOM (document object model)

    let cliente
    let verbo
    if (id) { // vai atualizar
        cliente = {
            //id: document.getElementById("id").value, 
            nome: document.getElementById("nome").value,
            cpf: document.getElementById("cpf").value,
            email: document.getElementById("email").value,
            rendaMensal: Number(document.getElementById("rendaMensal").value),
        }
        verbo = 'PUT'
    }
    else { // vai inserir
        cliente = {
            // id: document.getElementById("id").value, 
            nome: document.getElementById("nome").value,
            cpf: document.getElementById("cpf").value,
            email: document.getElementById("email").value,
            rendaMensal: Number(document.getElementById("rendaMensal").value),
        }
        verbo = 'POST'
    }

    if ((!cliente.nome) || (!cliente.cpf) || (!cliente.email) || (!cliente.rendaMensal)){
        alert(`Campos vazios`)
        return
    }


    // vamos criar a conexão
    let requisicao = new XMLHttpRequest()
    // true indica que a abertura de conexão é assíncrona
    requisicao.open(verbo, 'http://localhost:8080/clientes', true)
    
    let resposta
    requisicao.onload = function () {
        resposta = this.response
    }
    // configura o cabeçalho da requisição
    requisicao.setRequestHeader("Content-Type", "application/json")
    // converte json em string
    requisicao.send(JSON.stringify(cliente))
    alert(resposta)
    preencherTabela()
}

let preencherTabela = () => {

    // criar conexão para chamada de API
    let req = new XMLHttpRequest()
    req.open('GET', 'http://localhost:8080/clientes', true)
    req.onload = function(){
        // recupera os dados da API - converte string em JSON
        let clientes = JSON.parse(this.response)
        console.log(clientes)
        // percorrer os clientes
        let conteudo = ""
        clientes.map(cliente => {
            conteudo = conteudo + `<tr> <td> ${cliente.nome}</td> <td> ${cliente.cpf}</td> <td> ${cliente.email}</td> <td> ${cliente.rendaMensal}</td> <td> ${cliente.categoria} </td> <td> <button onClick="remover(${cliente.id})"> <i class="bi bi-archive-fill"></i> </button> </td> <td> <button onClick="editar(${cliente.nome}, '${cliente.cpf}', '${cliente.email}', ${cliente.rendaMensal})"> <i class="bi bi-pencil-fill"></i> </button> </td> </tr>`
        }) 
        document.getElementById("conteudoTabela").innerHTML = conteudo
    }
    req.send()
    // monta a saída de dados
}

let remover = () => {
    // cria um objeto
    let resp = confirm(`Confirma exclusão do Cliente`)
    if (resp) {
        let req = new XMLHttpRequest()
        req.open('DELETE', `http://localhost:8080/clientes/${id}/delete`, true)
        req.send()
        alert(`Cliente removido com sucesso`)
        preencherTabela()
    }
}

let editar = () => {
    document.getElementById("nome").value = nome
    document.getElementById("cpf").value = cpf
    document.getElementById("email").value = email
    document.getElementById("rendaMensal").value = rendaMensal
}

// Transação
let cadastrarTransacao = () => {
    // recuperar os dados do usuário e já cria o objeto
    // vamos utilizar programação DOM (document object model)

    let transacao
    let verbo

    transacao = {
        // id: document.getElementById("id").value, 
        valor: Number(document.getElementById("rendaMensal").value),
        tipo: document.getElementById("tipo").value,
    }
    verbo = 'POST'


    if ((!transacao.valor) || (!transacao.tipo)){
        alert(`Campos vazios`)
        return
    }


    // vamos criar a conexão
    let requisicao = new XMLHttpRequest()
    // true indica que a abertura de conexão é assíncrona
    requisicao.open(verbo, `http://localhost:8080/clientes/${id}/transacoes`, true)
    
    let resposta
    requisicao.onload = function () {
        resposta = this.response
    }
    // configura o cabeçalho da requisição
    requisicao.setRequestHeader("Content-Type", "application/json")
    // converte json em string
    requisicao.send(JSON.stringify(transacao))
    alert(resposta)
    preencherTabelaTransacao()
}

let preencherTabelaTransacao = () => {
    // criar conexão para chamada de API
    let req = new XMLHttpRequest()
    req.open('GET', `http://localhost:8080/clientes/${id}/transacoes`, true)
    req.onload = function(){
        // recupera os dados da API - converte string em JSON
        let transacoes = JSON.parse(this.response)
        console.log(transacao)
        // percorrer os clientes
        let conteudo = ""
        transacoes.map(transacao => {
            conteudo = conteudo + `<tr> <td> ${transacao.valor}</td> <td> ${transacao.tipo}</td>`
        }) 
        document.getElementById("conteudoTabelaTransacao").innerHTML = conteudo
    }
    req.send()
    // monta a saída de dados
}

// Transação Crédito
// let cadastrarTransacaoCredito = () => {}
//let preencherTabelaTransacaoCredito = () => {}


