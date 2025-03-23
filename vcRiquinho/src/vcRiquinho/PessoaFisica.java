package vcRiquinho;

public class PessoaFisica extends Cliente{
	private Long cpf;

	public PessoaFisica(String nome, String email, Long cpf) {
		super(nome, email);
		this.cpf = cpf;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
}
