package vcRiquinho;

public class PessoaJuridica extends Cliente {
	private String cpnj;

	public PessoaJuridica(String nome, String email, String cpnj) {
		super(nome, email);
		this.cpnj = cpnj;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}
	
}
