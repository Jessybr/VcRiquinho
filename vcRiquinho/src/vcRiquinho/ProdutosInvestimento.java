package vcRiquinho;

public abstract class ProdutosInvestimento {
    private String nome;
    private String descricao;
    private double rendimentoMensal;

    public ProdutosInvestimento(String nome, String descricao, double rendimentoMensal) {
        this.nome = nome;
        this.descricao = descricao;
        this.rendimentoMensal = rendimentoMensal;
    }

    public String getNome() { 
    	return nome; 
    }
    
    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getRendimentoMensal() { 
    	return rendimentoMensal; 
    }

    public abstract double calcularRendimento(double saldoInvestido, int dias);
}
