package vcRiquinho;

public class RendaVariavel extends ProdutosInvestimento {

	public RendaVariavel(String nome, String descricao, double rendimentoMensal) {
        super(nome, descricao, rendimentoMensal);
    }

    @Override
    public double calcularRendimento(double saldoInvestido, int dias) {
        double meses = dias / 30.0;
        double montanteFinal = saldoInvestido * Math.pow(1 + (getRendimentoMensal() / 100), meses);
        return montanteFinal - saldoInvestido;
    }
    
    public double getRendimentoEsperado(int dias) {
        double meses = dias / 30.0;
        double investimentoSimulado = 1000; 
        double montanteFinal = investimentoSimulado * Math.pow(1 + (getRendimentoMensal() / 100), meses);
        
        return montanteFinal - investimentoSimulado; 
    }
	
}
