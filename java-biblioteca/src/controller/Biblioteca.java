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
 * Classe com os métodos principais para o programa
 *
 * @author Bruna Oriani
 * @since 18/02/2021
 */
public class Biblioteca {

	// Vetores de armazenamento
	Livro livros[];
	Usuario usuarios[];
	Locacao locacoes[];

	// variaveis auxiliar para não permitis que o tamanho dos vetores seja inferior
	// o u igual a 0
	private int quantidadeLivros = 0;
	private int quantidadeUsuarios = 0;
	private int quantidadeLocacoes = 0;

	// método construtor da classe
	public Biblioteca() {
		processar();
	}

	// Método principal do programa
	public void processar() {

		// Capturando do usuário a quantidade máxima que será inserido de livros,
		// usuários e locações

		do {
			quantidadeLivros = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade máxima de livros."));

		} while (quantidadeLivros <= 0);

		do {
			quantidadeUsuarios = Integer
					.parseInt(JOptionPane.showInputDialog("Informe a quantidade máxima de usuários."));

		} while (quantidadeUsuarios <= 0);

		do {
			quantidadeLocacoes = Integer
					.parseInt(JOptionPane.showInputDialog("Informe quantos livros serão alugados."));

		} while (quantidadeLocacoes <= 0);

		// Definindo o numeros máximo de livros, usuarios e locações
		livros = new Livro[quantidadeLivros];
		usuarios = new Usuario[quantidadeUsuarios];
		locacoes = new Locacao[quantidadeLocacoes];

		// Processamento de looping do programa
		while (true) {
			escolhaUsuario();
		}
	}

	// Método para capturar do usuário a opção
	public void escolhaUsuario() {
		String menu = "";
		menu = "Informe a opção desejada:\n\n";
		menu += "OPÇÃO 1: Cadastrar livro\n";
		menu += "OPÇÃO 2: Cadastrar usuário\n";
		menu += "OPÇÃO 3: Efetuar locação de livros\n";
		menu += "OPÇÃO 4: Efetuar devolução de livros\n";
		menu += "OPÇÃO 5: Listar livros cadastrados\n";
		menu += "OPÇÃO 6: Listar usuários cadastrados\n";
		menu += "OPÇÃO 7: Pesquisar livros por editora\n";
		menu += "OPÇÃO 8: Pesquisar livros por autor\n";
		menu += "OPÇÃO 9: Pesquisar locações por data\n";
		menu += "OPÇÃO 10: Sair do sistema";

		int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
		escolhaProcessamento(escolha);
	}

	// Método para seleção das opções do programa
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
			JOptionPane.showMessageDialog(null, "Opção inválida!", "Biblioteca", 0); // erro
			break;
		}
	}

	public void cadastrarLivro() {

		// Variável auxiliar para ver se o usuário foi cadastrado
		boolean cadastrado = false;

		// Laço para verificar espaço em estoque
		for (int i = 0; i < livros.length; i++) {// início do for
			if (livros[i] == null) {
				livros[i] = criarLivro();
				cadastrado = true;
				break;
			}

		}
		// Verificando se a biblioteca não está cheia
		if (cadastrado) {
			JOptionPane.showMessageDialog(null, "Livro gravado com sucesso!", "Biblioteca", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não é possível cadastrar mais livros!", "Biblioteca", 2);
		}
	}

	// Método para criar um livro
	public Livro criarLivro() {
		// Criando as variáveis de um livro
		Livro livro = new Livro();
		Editora editora = new Editora();
		Autor autor = new Autor();

		// Pedindo ao usuário as informações do livro
		livro.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro.")));
		livro.setTitulo(JOptionPane.showInputDialog("Digite o título do livro."));
		livro.setAnoPublicacao(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de publicação.")));

		// Pedindo informações da editora
		editora.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o código da editora.")));
		editora.setNome(JOptionPane.showInputDialog("Digite o nome da editora."));
		editora.setPais(JOptionPane.showInputDialog("Digite o país da editora."));

		// Solicitando as informações do autor
		autor.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o código do autor.")));
		autor.setNome(JOptionPane.showInputDialog("Digite o nome do autor."));
		autor.setNacionalidade(JOptionPane.showInputDialog("Digite a nacionalidade do autor."));

		// Colocando as informações solicitadas no livro
		livro.setEditora(editora);
		livro.setAutor(autor);
		livro.setDisponivel(true);

		// Retornando o livro obtido
		return livro;
	}

	public void cadastrarUsuario() {

		// Variável auxiliar para ver se o usuário foi cadastrado
		boolean cadastrado = false;

		// Laço para verificar espaço em estoque
		for (int i = 0; i < usuarios.length; i++) {// início do for
			if (usuarios[i] == null) {
				usuarios[i] = criarUsuario();
				cadastrado = true;
				break;
			}

		} // fim do for

		if (cadastrado) {
			JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!", "Biblioteca", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não é possível cadastrar mais usuários!", "Biblioteca", 2);
		}

	}

	public Usuario criarUsuario() {

		// Criando as variáveis de um usuário
		Usuario usuario = new Usuario();
		Data dataNascimento = new Data();

		// Solicitando as informações para o usuário
		usuario.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o código do usuário")));
		usuario.setNome(JOptionPane.showInputDialog("Digite o nome do usuário"));
		usuario.setTelefone(JOptionPane.showInputDialog("Digite o telefone do usuário"));
		usuario.setEmail(JOptionPane.showInputDialog("Digite o email do usuário"));

		// Solicitando data de nascimento
		String dataquebrada[] = JOptionPane
				.showInputDialog("Digite a data de nascimento - dd/MM/AAAA - usando '/' como separador.").split("/");
		dataNascimento.setDia(Integer.parseInt(dataquebrada[0]));
		dataNascimento.setMes(Integer.parseInt(dataquebrada[1]));
		dataNascimento.setAno(Integer.parseInt(dataquebrada[2]));

		// Adicionando a data para o objeto usuario
		usuario.setDataNascimento(dataNascimento);

		// Retornando o usuário
		return usuario;
	}

	public void efetuarLocacao() {

		// Variável auxiliar para verificar se foi efetuada a locação
		boolean locado = false;

		// Variavel auxiliar para verificar se existem livros disponiveis para locação
		boolean semLivrosDisponiveis = true;

		// Variavel para verificar se existem usuários cadastrados
		boolean usuariosCadastrados = true;

		// variavel para verificar as locações
		boolean cheio = true;

		// Laço para verificar espaço no vetor
		for (int i = 0; i < locacoes.length; i++) { // inicio for principal
			if (locacoes[i] == null) { // inicio if
				cheio = false;
				// Verifica se existem usuarios cadastrados
				for (int k = 0; k < usuarios.length; k++) {
					if (usuarios[k] != null) {
						// Indica que possui usuários cadastrados no sistema
						usuariosCadastrados = false;
						break;
					}
				}
				if (usuariosCadastrados) {
					break;
				}
				// Indica se tem livros disponíveis para locação
				for (int j = 0; j < livros.length; j++) {
					if ((livros[j] != null) && (livros[j].isDisponivel())) {
						// indica que tem livros disponiveis
						semLivrosDisponiveis = false;
						// chama método para criar uma locação
						locacoes[i] = locarLivros();
						// atualiza como locado
						locado = true;
						break;
					}
				}
				break;
			} // fim if
		} // fim for principal

		// Laço para gerenciar os erros
		if (cheio) {
			JOptionPane.showMessageDialog(null, "O número máximo de locações já foram realizadas!", "Biblioteca", 2);
		} else if (locado) {
			JOptionPane.showMessageDialog(null, "Locação feita com sucesso!", "Locação de livros", 1);
		} else if (usuariosCadastrados) {
			JOptionPane.showMessageDialog(null,
					"Impossível realizar esta locação - Nenhum usuário cadastrado no sistema!", "Locação de Livros", 2);
		} else if (semLivrosDisponiveis) {
			JOptionPane.showMessageDialog(null, "Impossível realizar esta locação - Nenhum livro disponível!",
					"Locação de Livros", 2);
		} else {
			JOptionPane.showMessageDialog(null, "Impossível realizar esta locação - Cadastro cheio!",
					"Locação de Livros", 2);
		}
	}

	// Método para alugar os livros
	public Locacao locarLivros() {

		Data dataDevolucao = new Data();
		Locacao locacao = new Locacao();
		Data data = new Data();

		// Métodos para receber a data e alterar seu formato
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		LocalDateTime now = LocalDateTime.now();

		// Separando a data em 3
		String dataQuebrada[] = (dtf.format(now).split("/"));

		// Colocando as datas correspondentes no objeto data
		data.setDia(Integer.parseInt(dataQuebrada[0]));
		data.setMes(Integer.parseInt(dataQuebrada[1]));
		data.setAno(Integer.parseInt(dataQuebrada[2]));
		locacao.setDataLocacao(data);

		// Recebendo do usuário o código da locação
		locacao.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Entre com o código da locação")));

		// Criando um objeto usuário e instanciando-o
		Usuario usuario = new Usuario();
		int codigoUsuario = -1;

		// Criando variável auxiliar para montar uma mensagem
		String mensagem = "";

		// Recebendo a lista de usuários
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				mensagem += "Código " + (i + 1) + " - " + usuarios[i].getNome() + "\n";
			}
		}

		// Recebendo o código do usuário, com erro caso não esteja cadastrado
		do {
			codigoUsuario = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do usuário:\n" + mensagem))
					- 1;
			if ((codigoUsuario < 0) || (codigoUsuario >= usuarios.length) || (usuarios[codigoUsuario] == null)) {
				JOptionPane.showMessageDialog(null, "Código inválido!\nInforme o código correto", "Código inválido", 2);
			}
		} while ((codigoUsuario < 0) || (codigoUsuario >= usuarios.length) || (usuarios[codigoUsuario] == null));

		// Recebendo o usuário informado, caso ele esteja cadastrado
		usuario = usuarios[codigoUsuario];

		// Variável auxiliar para contar os livros disponíveis
		int livrosDisponiveis = 0;

		// Contando livros disponiveis para locar
		for (int j = 0; j < livros.length; j++) {
			if ((livros[j] != null) && (livros[j].isDisponivel())) {
				livrosDisponiveis++;
			}
		}

		// Variável auxiliar para controlar quantos livros serão emprestados
		int livrosAlugadosUsuario = 0;
		do {
			livrosAlugadosUsuario = Integer.parseInt(JOptionPane
					.showInputDialog("Quantos livros você deseja locar? (Máximo: " + livrosDisponiveis + ")"));
			if (livrosAlugadosUsuario > livrosDisponiveis || livrosAlugadosUsuario <= 0) {
				JOptionPane.showMessageDialog(null, "Número inválido de locações!", "Biblioteca", 2);
				livrosAlugadosUsuario = 0;
			}
		} while (livrosAlugadosUsuario <= 0);

		// Criando lista dos livros alugados pelo usuário
		Livro livrosLocados[] = new Livro[livrosAlugadosUsuario];

		// Laço que controla as locações
		for (int i = 0; i < livrosLocados.length; i++) {

			// Atualizando a mensagem
			mensagem = "";

			// Variáveis auxiliares para mostrar os códigos disponíveis para locação
			int cont = 0;
			int aux[];
			aux = new int[livrosDisponiveis];

			// Mostrando o livro ao usuário caso esteja disponível
			for (int j = 0; j < livros.length; j++) {

				if ((livros[j] != null) && (livros[j].isDisponivel())) {
					aux[cont] = j;
					mensagem += "Código " + (cont + 1) + " - " + livros[aux[cont]].getTitulo() + "\n";
					cont++;
				}
			}

			// Solicita o código do livro que será alugado ao usuário.
			int codigoLivro = -1;
			do {
				codigoLivro = Integer
						.parseInt(JOptionPane.showInputDialog("Informe o código do livro desejado:\n" + mensagem)) - 1;
				if ((codigoLivro < 0) || (codigoLivro >= livrosDisponiveis) || (livros[codigoLivro] == null)) {
					JOptionPane.showMessageDialog(null, "Código inválido!\nInforme o código correto", "Código invalido",
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

		// recebendo e quebrando a data da devolução
		do {
			dataQuebrada = JOptionPane.showInputDialog("Informe a data da devolução").split("/");
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
				JOptionPane.showMessageDialog(null, "Data inválida!\nInforme uma data posterior a data atual",
						"Data inválida", 2);
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

		// Retorna a locação que foi criada
		return locacao;
	}

	// Método para efetuar uma devolução do livro
	public void efetuarDevolucao() {

		// Variável auxiliar para receber o código
		int codigoLocacao;

		// Variavel auxiliar que exibe as locações ativas para o usuario
		String mensagem = "";
		// Variaveis auxiliares para adicionar o código real do livro
		int cont = 0;
		int locacoesAtivas = 0;
		int aux[];

		// Verificando quantas locações estão ativas
		for (int i = 0; i < locacoes.length; i++) { // inicio do for
			if ((locacoes[i] != null) && locacoes[i].isAtivo()) {
				locacoesAtivas++;
			}
		}
		aux = new int[locacoesAtivas];

		for (int i = 0; i < locacoes.length; i++) { // inicio do for
			if ((locacoes[i] != null) && locacoes[i].isAtivo()) {
				aux[cont] = i;
				mensagem += "Código " + (cont + 1) + " - " + locacoes[i].getUsuario().getNome() + " - "
						+ locacoes[i].getDataLocacao() + "\n";
				cont++;
			}
		}

		if (mensagem != "") {
			// Recebendo o código de qual locação irá fazer a devolução
			do {
				codigoLocacao = (Integer
						.parseInt(JOptionPane.showInputDialog("Informe o código da locação desejada:\n" + mensagem)))
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

			JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso!", "Biblioteca", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Impossivel fazer devolução - Nenhuma locação ativa!",
					"Locação de Livros", 2);
		}
	}

	public void listarLivrosCadastrados() {

		// Variável auxiliar para exibir os livros
		String mensagem = "";

		// Variável auxiliar para verificar se existe livro cadastrado
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
			// Exibindo os usuários
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de livros", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe nenhum livro cadastrado!", "Cadastro de livros", 2);
		}
	}

	public void listarUsuariosCadastrados() {
		// Variável auxiliar para exibir os usuários
		String mensagem = "";

		// Variável auxiliar para verificar se existe livro cadastrado
		boolean existe = false;

		// Varrendo o estoque para verificar se existe usuários cadastrados
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				mensagem += (i + 1) + " - " + usuarios[i].getNome() + "\n";
				existe = true;
			}
		}

		// Exibindo resultado da pesquisa se existe o livro
		if (existe) {
			// Exibindo os usuários
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de usuários", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe nenhum usuário cadastrado!", "Cadastro de usuários", 2);
		}

	}

	// Método para retornar se existe algum livro cadastrado
	public boolean existemLivros() {
		// Variável auxiliar para verificar se existe algum livro cadastrado
		boolean existeLivro = false;

		// Varrendo o estoque para verificar se existe usuários cadastrados
		for (int i = 0; i < livros.length; i++) {
			if (livros[i] != null) {
				existeLivro = true;
			}
		}
		return existeLivro;
	}

	public void pesquisarPorEditora() {

		// Variável auxiliar para verificar se existem livros cadastrados
		boolean existeLivro = existemLivros();
		if (existeLivro) {
			// Recebendo do usuario qual editora a ser pesquisada
			String editora = JOptionPane.showInputDialog("Informe a editora para pesquisa");

			// Variável auxiliar para exibir a mensagem do resultado pesquisado
			String mensagem = "Pesquisa por editora:\n\n";

			// Variável auxiliar para verificar se existe o modelo cadastrado
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
				JOptionPane.showMessageDialog(null, "Essa editora não está cadastrada", "Biblioteca", 2);
			}

		} else {
			// Mensagem de erro caso nenhum livro esteja cadastrado
			JOptionPane.showMessageDialog(null, "Nenhum livro cadastrado!", "Biblioteca", 2);
		}
	}

	public void pesquisarPorAutor() {

		// Variável auxiliar para verificar se existem livros cadastrados
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
				JOptionPane.showMessageDialog(null, "Esse autor não está cadastrado", "Biblioteca", 2);
			}
		} else {
			// Erro caso nenhum livro esteja cadastrado
			JOptionPane.showMessageDialog(null, "Nenhum livro cadastrado!", "Biblioteca", 2);
		}
	}

	public boolean existeLocacao() {
		// Variável auxiliar para verificar se existe algum livro cadastrado
		boolean existeLocacao = false;

		// Varrendo o estoque para verificar se existe usuários cadastrados
		for (int i = 0; i < locacoes.length; i++) {
			if (locacoes[i] != null) {
				existeLocacao = true;
			}
		}
		return existeLocacao;
	}

	public void pesquisarPorData() {

		// Variável auxiliar para verificar se existem locações
		boolean existeLocacao = existeLocacao();

		if (existeLocacao) {

			// Recebendo e quebrando a data da locação
			String dataQuebrada[] = JOptionPane.showInputDialog("Informe a data da locação").split("/");

			// Variavel auxiliar para verificar se existem locações na data buscada
			boolean existe = false;

			// Variavel para exibir a mensagem do resultado pesquisado
			String mensagem = "Pesquisa locação por data: " + dataQuebrada[0] + "/" + dataQuebrada[1] + "/"
					+ dataQuebrada[2] + "\n\n";

			// Varrendo o vetor e verificando as editoras
			for (int i = 0; i < locacoes.length; i++) { // inicio do for
				if ((locacoes[i] != null)
						&& (locacoes[i].getDataLocacao().getDia() == Integer.parseInt(dataQuebrada[0]))) {
					mensagem += "Locação: " + locacoes[i].getCodigo() + " Data: "
							+ locacoes[i].getDataLocacao().toString() + " Usuário: "
							+ locacoes[i].getUsuario().getNome() + "\n";
					existe = true;
				}
			} // fim do for

			// Exibindo o resultado da consulta
			if (existe) {
				JOptionPane.showMessageDialog(null, mensagem, "Pesquisa por Locação", 1);
			} else {
				JOptionPane.showMessageDialog(null, "Não possui nenhuma locação na data buscada",
						"Pesquisa por Locação", 2);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhuma locação foi feita!", "Biblioteca", 2);
		}
	}

	// Método para construção de mensagem para o usuário
	public String mensagem(Livro livro) {
		// Variável auxiliar que irá retornar a mensagem formatada
		String msg = "\n";

		// Construindo a mensagem que irá aparecer para o usuário
		msg += "Código: " + livro.getCodigo() + "\n";
		msg += "Título: " + livro.getTitulo() + "\n";
		msg += "Editora: " + livro.getEditora().getNome() + "\n";
		msg += "Autor: " + livro.getAutor().getNome() + "\n";
		msg += "Ano de publicação: " + livro.getAnoPublicacao() + "\n";

		// Retornando a variável formatada
		return msg;
	}

	public void sairDoSistema() {
		System.exit(0);
	}

}
