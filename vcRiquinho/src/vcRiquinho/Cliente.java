package vcRiquinho;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	private String nome;
	private String email;
	private List<Conta> contas;
	
	public Cliente(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
		this.contas = new ArrayList<Conta>();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	public Conta localizarConta(int numeroConta) {
		for (Conta conta : contas) {
			if(numeroConta == conta.getNumeroConta()) {
				return conta;
			}
		}
		return null;
	}
	
	
	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}
	
	public Double calcularRendimentos(int dias) {
		Double rendimentoTotal = 0.0;
		for (Conta conta : contas) {
			rendimentoTotal += conta.getRendimento();
		}
		return rendimentoTotal;
	}

	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Cliente:\n");
	    sb.append("Nome: ").append(nome).append("\n");
	    sb.append("Email: ").append(email).append("\n");
	    sb.append("Contas:\n");
	    
	    for (Conta conta : contas) {
	        sb.append(conta.toString());
	    }
	    
	    return sb.toString();
	}

	
}
