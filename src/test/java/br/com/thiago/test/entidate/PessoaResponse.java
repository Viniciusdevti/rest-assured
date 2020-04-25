package br.com.thiago.test.entidate;

public class PessoaResponse {

	private String nome;
	private String job;
	private String id;
	private String createdAt;
	
	public PessoaResponse(String nome, String job, String id, String createdAt) {
		super();
		this.nome = nome;
		this.job = job;
		this.id = id;
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public PessoaResponse() {
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
