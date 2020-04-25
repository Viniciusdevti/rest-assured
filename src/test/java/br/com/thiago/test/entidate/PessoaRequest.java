package br.com.thiago.test.entidate;

public class PessoaRequest {

	private String nome;
	private String job;
	
	public PessoaRequest(String nome, String job) {
		super();
		this.nome = nome;
		this.job = job;
	}
	public PessoaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	
}
