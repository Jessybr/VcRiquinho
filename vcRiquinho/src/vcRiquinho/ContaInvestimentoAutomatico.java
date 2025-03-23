package vcRiquinho;

import java.util.ArrayList;
import java.util.List;

public class ContaInvestimentoAutomatico extends Conta {
	private List<ProdutosInvestimento> produtos = new ArrayList<ProdutosInvestimento>();
	private Double taxaTipoCliente;

	public ContaInvestimentoAutomatico(Cliente cliente, Double saldo) {
		super(cliente, saldo);
	}
	
	public Double getTaxaTipoCliente() {
		return taxaTipoCliente;
	}

	public void setTaxaTipoCliente(Double taxaTipoCliente) {
		this.taxaTipoCliente = taxaTipoCliente;
	}
	
	
	public void adicionarProduto(ProdutosInvestimento produto) {
		produtos.add(produto);
	}
	
	@Override
	public Double calcularRendimento(Integer dias) {
	    if (produtos.isEmpty()) {
	    	System.out.println("É necessário associar produtos de investimentos para calcular o rendimento!");
	    	return 0.0; 
	    }

	    double rendimentoTotal = 0;
	    double saldoPorProduto = getSaldo() / produtos.size();

	    for (ProdutosInvestimento produto : produtos) {
	        double rendimentoProduto = produto.calcularRendimento(saldoPorProduto, dias);

	        if (produto instanceof RendaFixa) {
	            RendaFixa fixa = (RendaFixa) produto;
	            if (dias < fixa.getCarenciaDias()) {
	                System.out.println("Produto " + produto.getNome() + " ignorado (carência de " + fixa.getCarenciaDias()+ " dias)");
	                continue;
	            }
	        }

	        rendimentoTotal += rendimentoProduto;
	    }
	    
	    definirTaxaTipoCliente();
	    double taxaCobrada = rendimentoTotal * taxaTipoCliente;

	    return rendimentoTotal - taxaCobrada;
	}
	
	private void definirTaxaTipoCliente() {

	    if (getCliente() instanceof PessoaFisica) {
	        this.taxaTipoCliente = 0.001; // 0,1%
	    } else 
	    	if (getCliente() instanceof PessoaJuridica) {
		        this.taxaTipoCliente = 0.0015; // 0,15%
		    } else {
		        throw new IllegalArgumentException("Tipo de cliente desconhecido");
		    }
	}

}
