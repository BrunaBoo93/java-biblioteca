package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import model.Autor;
import model.Data;
import model.Editora;
import model.Livro;
import model.Locacao;
import model.Usuario;

/**
 * Classe com os m�todos principais para o programa
 *
 * @author Bruna Oriani
 * @since 18/02/2021
 */
public class Biblioteca {

	// Vetores de armazenamento
	Livro livros[];
	Usuario usuarios[];
	Locacao locacoes[];

	// variaveis auxiliar para n�o permitis que o tamanho dos vetores seja inferior
	// o u igual a 0
	private int quantidadeLivros = 0;
	private int quantidadeUsuarios = 0;
	private int quantidadeLocacoes = 0;

	// m�todo construtor da classe
	public Biblioteca() {
		processar();
	}

	// M�todo principal do programa
	public void processar() {

		// Capturando do usu�rio a quantidade m�xima que ser� inserido de livros,
		// usu�rios e loca��es

		do {
			quantidadeLivros = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade m�xima de livros."));

		} while (quantidadeLivros <= 0);

		do {
			quantidadeUsuarios = Integer
					.parseInt(JOptionPane.showInputDialog("Informe a quantidade m�xima de usu�rios."));

		} while (quantidadeUsuarios <= 0);

		do {
			quantidadeLocacoes = Integer
					.parseInt(JOptionPane.showInputDialog("Informe quantos livros ser�o alugados."));

		} while (quantidadeLocacoes <= 0);

		// Definindo o numeros m�ximo de livros, usuarios e loca��es
		livros = new Livro[quantidadeLivros];
		usuarios = new Usuario[quantidadeUsuarios];
		locacoes = new Locacao[quantidadeLocacoes];

		// Processamento de looping do programa
		while (true) {
			escolhaUsuario();
		}
	}

	// M�todo para capturar do usu�rio a op��o
	public void escolhaUsuario() {
		String menu = "";
		menu = "Informe a op��o desejada:\n\n";
		menu += "OP��O 1: Cadastrar livro\n";
		menu += "OP��O 2: Cadastrar usu�rio\n";
		menu += "OP��O 3: Efetuar loca��o de livros\n";
		menu += "OP��O 4: Efetuar devolu��o de livros\n";
		menu += "OP��O 5: Listar livros cadastrados\n";
		menu += "OP��O 6: Listar usu�rios cadastrados\n";
		menu += "OP��O 7: Pesquisar livros por editora\n";
		menu += "OP��O 8: Pesquisar livros por autor\n";
		menu += "OP��O 9: Pesquisar loca��es por data\n";
		menu += "OP��O 10: Sair do sistema";

		int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
		escolhaProcessamento(escolha);
	}

	// M�todo para sele��o das op��es do programa
	public void escolhaProcessamento(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarLivro();
			break;

		case 2:
			cadastrarUsuario();
			break;

		case 3:
			efetuarLocacao();
			break;

		case 4:
			efetuarDevolucao();
			break;

		case 5:
			listarLivrosCadastrados();
			break;

		case 6:
			listarUsuariosCadastrados();
			break;

		case 7:
			pesquisarPorEditora();
			break;

		case 8:
			pesquisarPorAutor();
			break;

		case 9:
			pesquisarPorData();
			break;

		case 10:
			sairDoSistema();
			break;

		default:
			JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Biblioteca", 0); // erro
			break;
		}
	}

	public void cadastrarLivro() {

		// Vari�vel auxiliar para ver se o usu�rio foi cadastrado
		boolean cadastrado = false;

		// La�o para verificar espa�o em estoque
		for (int i = 0; i < livros.length; i++) {// in�cio do for
			if (livros[i] == null) {
				livros[i] = criarLivro();
				cadastrado = true;
				break;
			}

		}
		// Verificando se a biblioteca n�o est� cheia
		if (cadastrado) {
			JOptionPane.showMessageDialog(null, "Livro gravado com sucesso!", "Biblioteca", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o � poss�vel cadastrar mais livros!", "Biblioteca", 2);
		}
	}

	// M�todo para criar um livro
	public Livro criarLivro() {
		// Criando as vari�veis de um livro
		Livro livro = new Livro();
		Editora editora = new Editora();
		Autor autor = new Autor();

		// Pedindo ao usu�rio as informa��es do livro
		livro.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do livro.")));
		livro.setTitulo(JOptionPane.showInputDialog("Digite o t�tulo do livro."));
		livro.setAnoPublicacao(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de publica��o.")));

		// Pedindo informa��es da editora
		editora.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo da editora.")));
		editora.setNome(JOptionPane.showInputDialog("Digite o nome da editora."));
		editora.setPais(JOptionPane.showInputDialog("Digite o pa�s da editora."));

		// Solicitando as informa��es do autor
		autor.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do autor.")));
		autor.setNome(JOptionPane.showInputDialog("Digite o nome do autor."));
		autor.setNacionalidade(JOptionPane.showInputDialog("Digite a nacionalidade do autor."));

		// Colocando as informa��es solicitadas no livro
		livro.setEditora(editora);
		livro.setAutor(autor);
		livro.setDisponivel(true);

		// Retornando o livro obtido
		return livro;
	}

	public void cadastrarUsuario() {

		// Vari�vel auxiliar para ver se o usu�rio foi cadastrado
		boolean cadastrado = false;

		// La�o para verificar espa�o em estoque
		for (int i = 0; i < usuarios.length; i++) {// in�cio do for
			if (usuarios[i] == null) {
				usuarios[i] = criarUsuario();
				cadastrado = true;
				break;
			}

		} // fim do for

		if (cadastrado) {
			JOptionPane.showMessageDialog(null, "Usu�rio registrado com sucesso!", "Biblioteca", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o � poss�vel cadastrar mais usu�rios!", "Biblioteca", 2);
		}

	}

	public Usuario criarUsuario() {

		// Criando as vari�veis de um usu�rio
		Usuario usuario = new Usuario();
		Data dataNascimento = new Data();

		// Solicitando as informa��es para o usu�rio
		usuario.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do usu�rio")));
		usuario.setNome(JOptionPane.showInputDialog("Digite o nome do usu�rio"));
		usuario.setTelefone(JOptionPane.showInputDialog("Digite o telefone do usu�rio"));
		usuario.setEmail(JOptionPane.showInputDialog("Digite o email do usu�rio"));

		// Solicitando data de nascimento
		String dataquebrada[] = JOptionPane
				.showInputDialog("Digite a data de nascimento - dd/MM/AAAA - usando '/' como separador.").split("/");
		dataNascimento.setDia(Integer.parseInt(dataquebrada[0]));
		dataNascimento.setMes(Integer.parseInt(dataquebrada[1]));
		dataNascimento.setAno(Integer.parseInt(dataquebrada[2]));

		// Adicionando a data para o objeto usuario
		usuario.setDataNascimento(dataNascimento);

		// Retornando o usu�rio
		return usuario;
	}

	public void efetuarLocacao() {

		// Vari�vel auxiliar para verificar se foi efetuada a loca��o
		boolean locado = false;

		// Variavel auxiliar para verificar se existem livros disponiveis para loca��o
		boolean semLivrosDisponiveis = true;

		// Variavel para verificar se existem usu�rios cadastrados
		boolean usuariosCadastrados = true;

		// variavel para verificar as loca��es
		boolean cheio = true;

		// La�o para verificar espa�o no vetor
		for (int i = 0; i < locacoes.length; i++) { // inicio for principal
			if (locacoes[i] == null) { // inicio if
				cheio = false;
				// Verifica se existem usuarios cadastrados
				for (int k = 0; k < usuarios.length; k++) {
					if (usuarios[k] != null) {
						// Indica que possui usu�rios cadastrados no sistema
						usuariosCadastrados = false;
						break;
					}
				}
				if (usuariosCadastrados) {
					break;
				}
				// Indica se tem livros dispon�veis para loca��o
				for (int j = 0; j < livros.length; j++) {
					if ((livros[j] != null) && (livros[j].isDisponivel())) {
						// indica que tem livros disponiveis
						semLivrosDisponiveis = false;
						// chama m�todo para criar uma loca��o
						locacoes[i] = locarLivros();
						// atualiza como locado
						locado = true;
						break;
					}
				}
				break;
			} // fim if
		} // fim for principal

		// La�o para gerenciar os erros
		if (cheio) {
			JOptionPane.showMessageDialog(null, "O n�mero m�ximo de loca��es j� foram realizadas!", "Biblioteca", 2);
		} else if (locado) {
			JOptionPane.showMessageDialog(null, "Loca��o feita com sucesso!", "Loca��o de livros", 1);
		} else if (usuariosCadastrados) {
			JOptionPane.showMessageDialog(null,
					"Imposs�vel realizar esta loca��o - Nenhum usu�rio cadastrado no sistema!", "Loca��o de Livros", 2);
		} else if (semLivrosDisponiveis) {
			JOptionPane.showMessageDialog(null, "Imposs�vel realizar esta loca��o - Nenhum livro dispon�vel!",
					"Loca��o de Livros", 2);
		} else {
			JOptionPane.showMessageDialog(null, "Imposs�vel realizar esta loca��o - Cadastro cheio!",
					"Loca��o de Livros", 2);
		}
	}

	// M�todo para alugar os livros
	public Locacao locarLivros() {

		Data dataDevolucao = new Data();
		Locacao locacao = new Locacao();
		Data data = new Data();

		// M�todos para receber a data e alterar seu formato
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		LocalDateTime now = LocalDateTime.now();

		// Separando a data em 3
		String dataQuebrada[] = (dtf.format(now).split("/"));

		// Colocando as datas correspondentes no objeto data
		data.setDia(Integer.parseInt(dataQuebrada[0]));
		data.setMes(Integer.parseInt(dataQuebrada[1]));
		data.setAno(Integer.parseInt(dataQuebrada[2]));
		locacao.setDataLocacao(data);

		// Recebendo do usu�rio o c�digo da loca��o
		locacao.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Entre com o c�digo da loca��o")));

		// Criando um objeto usu�rio e instanciando-o
		Usuario usuario = new Usuario();
		int codigoUsuario = -1;

		// Criando vari�vel auxiliar para montar uma mensagem
		String mensagem = "";

		// Recebendo a lista de usu�rios
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				mensagem += "C�digo " + (i + 1) + " - " + usuarios[i].getNome() + "\n";
			}
		}

		// Recebendo o c�digo do usu�rio, com erro caso n�o esteja cadastrado
		do {
			codigoUsuario = Integer.parseInt(JOptionPane.showInputDialog("Informe o c�digo do usu�rio:\n" + mensagem))
					- 1;
			if ((codigoUsuario < 0) || (codigoUsuario >= usuarios.length) || (usuarios[codigoUsuario] == null)) {
				JOptionPane.showMessageDialog(null, "C�digo inv�lido!\nInforme o c�digo correto", "C�digo inv�lido", 2);
			}
		} while ((codigoUsuario < 0) || (codigoUsuario >= usuarios.length) || (usuarios[codigoUsuario] == null));

		// Recebendo o usu�rio informado, caso ele esteja cadastrado
		usuario = usuarios[codigoUsuario];

		// Vari�vel auxiliar para contar os livros dispon�veis
		int livrosDisponiveis = 0;

		// Contando livros disponiveis para locar
		for (int j = 0; j < livros.length; j++) {
			if ((livros[j] != null) && (livros[j].isDisponivel())) {
				livrosDisponiveis++;
			}
		}

		// Vari�vel auxiliar para controlar quantos livros ser�o emprestados
		int livrosAlugadosUsuario = 0;
		do {
			livrosAlugadosUsuario = Integer.parseInt(JOptionPane
					.showInputDialog("Quantos livros voc� deseja locar? (M�ximo: " + livrosDisponiveis + ")"));
			if (livrosAlugadosUsuario > livrosDisponiveis || livrosAlugadosUsuario <= 0) {
				JOptionPane.showMessageDialog(null, "N�mero inv�lido de loca��es!", "Biblioteca", 2);
				livrosAlugadosUsuario = 0;
			}
		} while (livrosAlugadosUsuario <= 0);

		// Criando lista dos livros alugados pelo usu�rio
		Livro livrosLocados[] = new Livro[livrosAlugadosUsuario];

		// La�o que controla as loca��es
		for (int i = 0; i < livrosLocados.length; i++) {

			// Atualizando a mensagem
			mensagem = "";

			// Vari�veis auxiliares para mostrar os c�digos dispon�veis para loca��o
			int cont = 0;
			int aux[];
			aux = new int[livrosDisponiveis];

			// Mostrando o livro ao usu�rio caso esteja dispon�vel
			for (int j = 0; j < livros.length; j++) {

				if ((livros[j] != null) && (livros[j].isDisponivel())) {
					aux[cont] = j;
					mensagem += "C�digo " + (cont + 1) + " - " + livros[aux[cont]].getTitulo() + "\n";
					cont++;
				}
			}

			// Solicita o c�digo do livro que ser� alugado ao usu�rio.
			int codigoLivro = -1;
			do {
				codigoLivro = Integer
						.parseInt(JOptionPane.showInputDialog("Informe o c�digo do livro desejado:\n" + mensagem)) - 1;
				if ((codigoLivro < 0) || (codigoLivro >= livrosDisponiveis) || (livros[codigoLivro] == null)) {
					JOptionPane.showMessageDialog(null, "C�digo inv�lido!\nInforme o c�digo correto", "C�digo invalido",
							2);
				}
			} while ((codigoLivro < 0) || (codigoLivro >= livrosDisponiveis) || (livros[codigoLivro] == null));

			livrosLocados[i] = livros[(aux[codigoLivro])];

			// Muda a disponibilidade do livro
			livros[aux[codigoLivro]].setDisponivel(false);

			if (i < livrosAlugadosUsuario - 1) {
				int querContinuar = JOptionPane.showConfirmDialog(null, "Deseja alugar mais algum livro?");
				if (querContinuar == JOptionPane.NO_OPTION) {
					i = livrosLocados.length;
				}
			}

		}

		// recebendo e quebrando a data da devolu��o
		do {
			dataQuebrada = JOptionPane.showInputDialog("Informe a data da devolu��o").split("/");
			if (((Integer.parseInt(dataQuebrada[2]) == data.getAno())
					&& (Integer.parseInt(dataQuebrada[1]) == data.getMes())
					&& (Integer.parseInt(dataQuebrada[0]) > data.getDia()))
					|| ((Integer.parseInt(dataQuebrada[2]) > data.getAno())
							|| (Integer.parseInt(dataQuebrada[2]) == data.getAno()
									&& (Integer.parseInt(dataQuebrada[1]) > data.getMes())))) {
				dataDevolucao.setDia(Integer.parseInt(dataQuebrada[0]));
				dataDevolucao.setMes(Integer.parseInt(dataQuebrada[1]));
				dataDevolucao.setAno(Integer.parseInt(dataQuebrada[2]));
			} else {
				JOptionPane.showMessageDialog(null, "Data inv�lida!\nInforme uma data posterior a data atual",
						"Data inv�lida", 2);
			}

		} while (!(((Integer.parseInt(dataQuebrada[2]) == data.getAno())
				&& (Integer.parseInt(dataQuebrada[1]) == data.getMes())
				&& (Integer.parseInt(dataQuebrada[0]) > data.getDia()))
				|| ((Integer.parseInt(dataQuebrada[2]) > data.getAno())
						|| (Integer.parseInt(dataQuebrada[2]) == data.getAno()
								&& (Integer.parseInt(dataQuebrada[1]) > data.getMes())))));

		// Atribuindo os valores ao objeto locacao
		locacao.setLivros(livrosLocados);
		locacao.setUsuario(usuario);
		locacao.setAtivo(true);

		// Retorna a loca��o que foi criada
		return locacao;
	}

	// M�todo para efetuar uma devolu��o do livro
	public void efetuarDevolucao() {

		// Vari�vel auxiliar para receber o c�digo
		int codigoLocacao;

		// Variavel auxiliar que exibe as loca��es ativas para o usuario
		String mensagem = "";
		// Variaveis auxiliares para adicionar o c�digo real do livro
		int cont = 0;
		int locacoesAtivas = 0;
		int aux[];

		// Verificando quantas loca��es est�o ativas
		for (int i = 0; i < locacoes.length; i++) { // inicio do for
			if ((locacoes[i] != null) && locacoes[i].isAtivo()) {
				locacoesAtivas++;
			}
		}
		aux = new int[locacoesAtivas];

		for (int i = 0; i < locacoes.length; i++) { // inicio do for
			if ((locacoes[i] != null) && locacoes[i].isAtivo()) {
				aux[cont] = i;
				mensagem += "C�digo " + (cont + 1) + " - " + locacoes[i].getUsuario().getNome() + " - "
						+ locacoes[i].getDataLocacao() + "\n";
				cont++;
			}
		}

		if (mensagem != "") {
			// Recebendo o c�digo de qual loca��o ir� fazer a devolu��o
			do {
				codigoLocacao = (Integer
						.parseInt(JOptionPane.showInputDialog("Informe o c�digo da loca��o desejada:\n" + mensagem)))
						- 1;
			} while ((codigoLocacao < 0) || (codigoLocacao >= locacoes.length) || (locacoes[codigoLocacao] == null));

			Livro auxLivros[] = new Livro[locacoes[aux[codigoLocacao]].getLivros().length];

			auxLivros = locacoes[aux[codigoLocacao]].getLivros();

			for (int i = 0; i < auxLivros.length; i++) {
				for (int j = 0; j < livros.length; j++) {
					if (auxLivros[i] == livros[j]) {
						livros[j].setDisponivel(true);
					}
				}
			}

			locacoes[aux[codigoLocacao]].setAtivo(false);

			JOptionPane.showMessageDialog(null, "Devolu��o realizada com sucesso!", "Biblioteca", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Impossivel fazer devolu��o - Nenhuma loca��o ativa!",
					"Loca��o de Livros", 2);
		}
	}

	public void listarLivrosCadastrados() {

		// Vari�vel auxiliar para exibir os livros
		String mensagem = "";

		// Vari�vel auxiliar para verificar se existe livro cadastrado
		boolean existe = false;

		// Varrendo o estoque para verificar se existem livros cadastrados
		for (int i = 0; i < livros.length; i++) {
			if (livros[i] != null) {
				mensagem += (i + 1) + " - " + livros[i].getTitulo() + "\n";
				existe = true;
			}
		}

		// Exibindo resultado da pesquisa se existe o livro
		if (existe) {
			// Exibindo os usu�rios
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de livros", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o existe nenhum livro cadastrado!", "Cadastro de livros", 2);
		}
	}

	public void listarUsuariosCadastrados() {
		// Vari�vel auxiliar para exibir os usu�rios
		String mensagem = "";

		// Vari�vel auxiliar para verificar se existe livro cadastrado
		boolean existe = false;

		// Varrendo o estoque para verificar se existe usu�rios cadastrados
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				mensagem += (i + 1) + " - " + usuarios[i].getNome() + "\n";
				existe = true;
			}
		}

		// Exibindo resultado da pesquisa se existe o livro
		if (existe) {
			// Exibindo os usu�rios
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de usu�rios", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o existe nenhum usu�rio cadastrado!", "Cadastro de usu�rios", 2);
		}

	}

	// M�todo para retornar se existe algum livro cadastrado
	public boolean existemLivros() {
		// Vari�vel auxiliar para verificar se existe algum livro cadastrado
		boolean existeLivro = false;

		// Varrendo o estoque para verificar se existe usu�rios cadastrados
		for (int i = 0; i < livros.length; i++) {
			if (livros[i] != null) {
				existeLivro = true;
			}
		}
		return existeLivro;
	}

	public void pesquisarPorEditora() {

		// Vari�vel auxiliar para verificar se existem livros cadastrados
		boolean existeLivro = existemLivros();
		if (existeLivro) {
			// Recebendo do usuario qual editora a ser pesquisada
			String editora = JOptionPane.showInputDialog("Informe a editora para pesquisa");

			// Vari�vel auxiliar para exibir a mensagem do resultado pesquisado
			String mensagem = "Pesquisa por editora:\n\n";

			// Vari�vel auxiliar para verificar se existe o modelo cadastrado
			boolean existe = false;

			// Varrendo o vetor e verificando a editora cadastrado
			for (int i = 0; i < livros.length; i++) {
				if (livros[i] != null && livros[i].getEditora().getNome().equals(editora)) {
					mensagem += mensagem(livros[i]);
					existe = true;
				}
			}

			// Exibindo o resultado da pesquisa para o usuario
			if (existe) {
				JOptionPane.showMessageDialog(null, mensagem, "Biblioteca", 1);
			} else {
				JOptionPane.showMessageDialog(null, "Essa editora n�o est� cadastrada", "Biblioteca", 2);
			}

		} else {
			// Mensagem de erro caso nenhum livro esteja cadastrado
			JOptionPane.showMessageDialog(null, "Nenhum livro cadastrado!", "Biblioteca", 2);
		}
	}

	public void pesquisarPorAutor() {

		// Vari�vel auxiliar para verificar se existem livros cadastrados
		boolean existeLivro = existemLivros();
		if (existeLivro) {
			// Recebendo do usuario qual autor a ser pesquisado
			String autor = JOptionPane.showInputDialog("Informe o autor para pesquisa");

			// Variavel auxiliar para exibir a mensagem do resultado buscado
			String mensagem = "Pesquisa por autor:\n\n";

			// Variavel auxiliar para verificar se existe o modelo cadastrado
			boolean existe = false;

			// Varrendo o vetor e verificando o autor cadastrado
			for (int i = 0; i < livros.length; i++) {
				if (livros[i] != null && livros[i].getAutor().getNome().equals(autor)) {
					mensagem += mensagem(livros[i]);
					existe = true;
				}
			}

			// Exibindo o resultado da pesquisa para o usuario
			if (existe) {
				JOptionPane.showMessageDialog(null, mensagem, "Biblioteca", 1);
			} else {
				JOptionPane.showMessageDialog(null, "Esse autor n�o est� cadastrado", "Biblioteca", 2);
			}
		} else {
			// Erro caso nenhum livro esteja cadastrado
			JOptionPane.showMessageDialog(null, "Nenhum livro cadastrado!", "Biblioteca", 2);
		}
	}

	public boolean existeLocacao() {
		// Vari�vel auxiliar para verificar se existe algum livro cadastrado
		boolean existeLocacao = false;

		// Varrendo o estoque para verificar se existe usu�rios cadastrados
		for (int i = 0; i < locacoes.length; i++) {
			if (locacoes[i] != null) {
				existeLocacao = true;
			}
		}
		return existeLocacao;
	}

	public void pesquisarPorData() {

		// Vari�vel auxiliar para verificar se existem loca��es
		boolean existeLocacao = existeLocacao();

		if (existeLocacao) {

			// Recebendo e quebrando a data da loca��o
			String dataQuebrada[] = JOptionPane.showInputDialog("Informe a data da loca��o").split("/");

			// Variavel auxiliar para verificar se existem loca��es na data buscada
			boolean existe = false;

			// Variavel para exibir a mensagem do resultado pesquisado
			String mensagem = "Pesquisa loca��o por data: " + dataQuebrada[0] + "/" + dataQuebrada[1] + "/"
					+ dataQuebrada[2] + "\n\n";

			// Varrendo o vetor e verificando as editoras
			for (int i = 0; i < locacoes.length; i++) { // inicio do for
				if ((locacoes[i] != null)
						&& (locacoes[i].getDataLocacao().getDia() == Integer.parseInt(dataQuebrada[0]))) {
					mensagem += "Loca��o: " + locacoes[i].getCodigo() + " Data: "
							+ locacoes[i].getDataLocacao().toString() + " Usu�rio: "
							+ locacoes[i].getUsuario().getNome() + "\n";
					existe = true;
				}
			} // fim do for

			// Exibindo o resultado da consulta
			if (existe) {
				JOptionPane.showMessageDialog(null, mensagem, "Pesquisa por Loca��o", 1);
			} else {
				JOptionPane.showMessageDialog(null, "N�o possui nenhuma loca��o na data buscada",
						"Pesquisa por Loca��o", 2);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhuma loca��o foi feita!", "Biblioteca", 2);
		}
	}

	// M�todo para constru��o de mensagem para o usu�rio
	public String mensagem(Livro livro) {
		// Vari�vel auxiliar que ir� retornar a mensagem formatada
		String msg = "\n";

		// Construindo a mensagem que ir� aparecer para o usu�rio
		msg += "C�digo: " + livro.getCodigo() + "\n";
		msg += "T�tulo: " + livro.getTitulo() + "\n";
		msg += "Editora: " + livro.getEditora().getNome() + "\n";
		msg += "Autor: " + livro.getAutor().getNome() + "\n";
		msg += "Ano de publica��o: " + livro.getAnoPublicacao() + "\n";

		// Retornando a vari�vel formatada
		return msg;
	}

	public void sairDoSistema() {
		System.exit(0);
	}

}
