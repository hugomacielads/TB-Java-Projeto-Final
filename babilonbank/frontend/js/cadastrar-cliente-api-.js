async function cadastrarCliente() {
    // Pego os dados vindos do formulário
    const id = document.getElementById('id').value;
    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const email = document.getElementById('email').value;
    const rendaMensal = document.getElementById('rendaMensal').value;

    // aqui, eu crio um objeto JSON com os dados capturados do formulário
    const cliente = {
        id: id,
        nome: nome,
        cpf: cpf,
        email: email,
        rendaMensal: rendaMensal
    };

    // Faço a request para a API enviando os dados do formulário
    try {
        const response = await fetch('http://localhost:8080/clientes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        });

        // Validação para saber a resposta da API
        if (response.ok) {
            // Caso a resposta seja OK
            alert('Cliente cadastrado com sucesso!');
        } else {
            // Erro
            const errorData = await response.json();
            alert('Erro ao cadastrar cliente: ' + errorData.message);
        }
    } catch (error) {
        alert('Erro na requisição: ' + error.message);
    }
}
