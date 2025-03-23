package vcRiquinho;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removerCliente(int index) {
        if (index >= 0 && index < clientes.size()) {
            clientes.remove(index);
        } else {
            System.out.println("Índice inválido!");
        }
    }

    public Cliente buscarCliente(String emailCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(emailCliente)) {
                return cliente;
            }
        }
        return null;
    }
}
