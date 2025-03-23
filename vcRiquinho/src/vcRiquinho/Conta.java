package vcRiquinho;

import java.util.Random;

public  abstract class Conta {
	private Cliente cliente;
	private Integer numeroConta;
	private Double saldo;
	private Double rendimento;

	public Conta(Cliente cliente, Double saldo) {
		super();
		this.cliente = cliente;
		this.numeroConta = geraNumeroConta();
		this.saldo = saldo;
	}

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public abstract Double calcularRendimento(Integer periodo);
	
	public void depositar(Double valor) {
		this.saldo += valor;
	}
	
	public void sacar(Double valor) {
		this.saldo -= valor;
	}
	
	private Integer geraNumeroConta() {
		Random gerador = new Random();
		return gerador.nextInt(2000);
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Número: ").append(numeroConta).append("\n");
	    sb.append("Saldo: ");
	    sb.append(saldo != null ? saldo : "0").append("\n");
	    sb.append("Rendimentos: ");
	    sb.append(rendimento != null ? rendimento : "0").append("\n");
	    
	    
	    return sb.toString();
	}
}
