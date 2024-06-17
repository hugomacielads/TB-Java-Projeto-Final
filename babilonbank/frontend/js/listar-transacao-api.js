async function listarTransacao() {
    try {

        const id = document.getElementById('id').value;

        // Fazer a requisição GET para a API
        const response = await fetch(`http://localhost:8080/clientes/${id}/transacoes`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(transacao) // AQUI É O ERRO
            });
        
        // Verificar se a requisição foi bem-sucedida
        if (!response.ok) {
            throw new Error('Erro ao carregar os dados');
        }
        
        // Obter os dados da resposta
        const transacao = await response.json();
        
        // Selecionar o elemento tbody onde os dados serão inseridos
        const conteudoTabelaTransacoes = document.getElementById('conteudoTabelaTransacoes');
        
        // Limpar qualquer conteúdo existente no tbody
        conteudoTabelaTransacoes.innerHTML = '';
        
        // Iterar sobre os dados recebidos e criar as linhas da tabela
        transacoes.forEach(transacao => {
            const tr = document.createElement('tr');
            
            tr.innerHTML = `
                <td>${transacao.valor}</td>
                <td>${transacao.tipo}</td>
            `;
            
            conteudoTabelaTransacoes.appendChild(tr);
        });
    } catch (error) {
        console.error(error);''
        alert('Erro ao carregar os dados');
    }
}