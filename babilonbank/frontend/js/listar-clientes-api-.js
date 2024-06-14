async function listarClientes() {
    try {
        // Fazer a requisição GET para a API
        const response = await fetch('http://localhost:8080/clientes');
        
        // Verificar se a requisição foi bem-sucedida
        if (!response.ok) {
            throw new Error('Erro ao carregar os dados');
        }
        
        // Obter os dados da resposta
        const clientes = await response.json();
        
        // Selecionar o elemento tbody onde os dados serão inseridos
        const conteudoTabela = document.getElementById('conteudoTabela');
        
        // Limpar qualquer conteúdo existente no tbody
        conteudoTabela.innerHTML = '';
        
        // Iterar sobre os dados recebidos e criar as linhas da tabela
        clientes.forEach(cliente => {
            const tr = document.createElement('tr');
            
            tr.innerHTML = `
                <td>${cliente.nome}</td>
                <td>${cliente.cpf}</td>
                <td>${cliente.email}</td>
                <td>${cliente.rendaMensal}</td>
                <td>${cliente.categoria}</td>
                <td><button onclick="removerCliente(${cliente.id})" class="btn btn-danger"><i class="bi bi-trash"></i></button></td>
                <td><button onclick="editarCliente(${cliente.id})" class="btn btn-warning"><i class="bi bi-pencil"></i></button></td>
            `;
            
            conteudoTabela.appendChild(tr);
        });
    } catch (error) {
        console.error(error);
        alert('Erro ao carregar os dados');
    }
}