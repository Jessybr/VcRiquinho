package vcRiquinho;

public class RendaFixa extends ProdutosInvestimento {
	private int carenciaDias;

    public RendaFixa(String nome, String descricao, double rendimentoMensal, int carenciaDias) {
        super(nome, descricao, rendimentoMensal);
        this.carenciaDias = carenciaDias;
    }

    public int getCarenciaDias() {
        return carenciaDias;
    }

    @Override
    public double calcularRendimento(double saldoInvestido, int dias) {
        if (dias < carenciaDias) {
            System.out.println("Investimento " + getNome() + " ignorado (carência de " + carenciaDias + " dias)");
            return 0;
        }

        double meses = dias / 30.0;
        return saldoInvestido * (1 + (getRendimentoMensal() / 100) * meses) - saldoInvestido;
    }
}
