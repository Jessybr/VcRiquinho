package vcRiquinho;

public class ContaCdi extends Conta {
	private final Double taxaServico = 0.07;
	private final Double cdi = 0.00138;
	
	public ContaCdi(Cliente cliente, Double saldo) {
		super(cliente, saldo);
	}
	
	public Double getTaxaServico() {
		return taxaServico;
	}

	public Double getCdi() {
		return cdi;
	}
	
	public Double calcularRendimento(Integer periodo) {
		Double rendimentoBruto = periodo * cdi * getSaldo();
		Double rendimentoLiquido = rendimentoBruto - (rendimentoBruto * taxaServico);
		setRendimento(rendimentoLiquido);
		return rendimentoLiquido;
		
	}
}
