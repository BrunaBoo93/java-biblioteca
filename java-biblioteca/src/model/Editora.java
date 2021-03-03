package model;

/**
 *
 * @author Bruna Oriani
 * @since 18/02/2021
 */
public class Editora {
	
	//método construtor da classe
	public Editora() {
		
	}
	
	//declarnado atributos
	private int codigo;
	private String nome;
	private String pais;

	//Método para acessar os atributos
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
	
	
	
}
