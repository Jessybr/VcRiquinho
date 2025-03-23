package vcRiquinho;

public class ContaCorrente extends Conta {
	public ContaCorrente(Cliente cliente, Double saldo) {
		super(cliente, saldo);
	}

	public Double calcularRendimento(Integer periodo) {
		return 0.0;
	}
}
