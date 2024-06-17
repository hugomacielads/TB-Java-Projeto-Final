async function cadastrarTransacao() {
    // Pego os dados vindos do formulário
    // const id = document.getElementById('id').value;
    const id = document.getElementById('id').value;
    const valor = document.getElementById('valor').value;
    const tipo = document.getElementById('tipo').value;

    // aqui, eu crio um objeto JSON com os dados capturados do formulário
    const transacao = {
        // id: id,
        valor: valor,
        tipo: tipo,
    };

    // Faço a request para a API enviando os dados do formulário
    try {
        const response = await fetch(`http://localhost:8080/clientes/${id}/transacoes`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(transacao)
        });

        // Validação para saber a resposta da API
        if (response.ok) {
            // Caso a resposta seja OK
            alert(`Transação Cliente ${id} realizada com sucesso!`);
        } else {
            // Erro
            const errorData = await response.json();
            alert('Erro ao Realizar Transação: ' + errorData.message);
        }
    } catch (error) {
        alert('Erro na requisição: ' + error.message);
    }
}
