package model;

/**
 *
 * @author Bruna Oriani
 * @since 18/02/2021
 */
public class Autor {
	
	//método construtor da classe
	public Autor() {
		
	}
	
	//declarando atributos
	private int codigo;
	private String nome;
	private String nacionalidade;

	// Métodos para acessar os atributos 
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	
}
