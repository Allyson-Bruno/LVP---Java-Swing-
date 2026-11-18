package lpv.cadastrocliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;

public class ClienteIg extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	//Para a tabela.
	private JTable table;
	private DefaultTableModel modeloTabela;
	
	private JPanel panelBotoes;
	private JButton btnCliente;
	private JButton btnVisualizar;
	private JButton btnExcluir;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemMenuNovoCliente;
	private JMenuItem itemMenuSair;
	private JMenu menuExibir;
	private JCheckBoxMenuItem checkItemBarraStatus;
	private JLabel lblBarraStatus;
	private JMenu menuFonte;
	private JRadioButtonMenuItem radioItemPequeno;
	private JRadioButtonMenuItem radioItemGrande;
	private JRadioButtonMenuItem radioItemMedio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteIg frame = new ClienteIg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteIg() {

		//Configurações básicas da janela principal.
		setResizable(false);
		setSize(765, 565);
		setLocationRelativeTo(null);

		setTitle("Cadastro de clientes");
		
		//Pergunta ao usuário se deseja que o programa se encerre.
		//Porém, para isso a janela deve estar com DO_NOTHING_ON_CLOSE.
		//Caso contrário, irá soltar a pergunta, mas o evento de fechar irá ocorrer de qualquer maneira.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(
						ClienteIg.this, 
						"Deseje fechar o programa?", 
						getTitle(), 
						JOptionPane.YES_OPTION);
				
				if (resposta == JOptionPane.YES_OPTION) {
					ClienteIg.this.dispose();
				}
			}
		});
		
		//Área do Menu.
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);

		//Novo Cliente: deverá abrir o formulário de cadastro de clientes. (v)
		//Em suma o actionListener do botão "Novo Cliente" será o desse.
		itemMenuNovoCliente = new JMenuItem("Novo cliente");
		
		//Atalho adicionado.
		itemMenuNovoCliente.setMnemonic(KeyEvent.VK_N);
		
		menuArquivo.add(itemMenuNovoCliente);
		
		itemMenuSair = new JMenuItem("Sair");
		
		//Segundo atalho.
		itemMenuSair.setMnemonic(KeyEvent.VK_S);
		menuArquivo.add(itemMenuSair);
		
		menuExibir = new JMenu("Exibir");
		menuBar.add(menuExibir);
		
		//O segundo parâmetro se refere ao status do item da checkbox.
		//Dado que: A opção deverá iniciar marcada.
		checkItemBarraStatus = new JCheckBoxMenuItem("Exibir barra de status", true);
		menuExibir.add(checkItemBarraStatus);
		
		menuFonte = new JMenu("Fonte");
		menuBar.add(menuFonte);
		
		radioItemPequeno = new JRadioButtonMenuItem("Pequeno");
		menuFonte.add(radioItemPequeno);
		
		//Definindo como opção padrão.
		radioItemMedio = new JRadioButtonMenuItem("Medio", true);
		menuFonte.add(radioItemMedio);
		
		radioItemGrande = new JRadioButtonMenuItem("Grande");
		menuFonte.add(radioItemGrande);

		//Ações dos botões que alteram a fonte.
		radioItemPequeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setFont(new Font("Segoe UI", Font.BOLD, 12));
			}
		});
		
		//Alteração se da sob o tamanho da fonte na tabela dos clientes.
				
		radioItemMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setFont(new Font("Segoe UI", Font.BOLD, 16));
			}
		});
		
		
		radioItemGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setFont(new Font("Segoe UI", Font.BOLD, 20));
			}
		});
		
		//Importante adicionar eles no grupo para o funcionamento.
		ButtonGroup buttonGroup = new ButtonGroup();

		buttonGroup.add(radioItemPequeno);
		buttonGroup.add(radioItemMedio);
		buttonGroup.add(radioItemGrande);

		//Ação dos itens do Menu.
		itemMenuNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FormularioCadastroDialog formCadastroCliente = 
						new FormularioCadastroDialog(ClienteIg.this);
				
				formCadastroCliente.setVisible(true);
			}
		});
		
		//Sair: deverá encerrar a aplicação.
		itemMenuSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		/*
		 * Comportamento esperado:
		 * 
		 * Quando a opção estiver marcada, a barra de status deverá ser exibida.
		 * Quando a opção estiver desmarcada, a barra de status deverá ser ocultada.

		 * Por fim:
		 * A mensagem deverá ser atualizada conforme as operações pelo usuário.
		 * 
		 */
		
		checkItemBarraStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!checkItemBarraStatus.isSelected()) {
					lblBarraStatus.setVisible(false);
				} else {
					lblBarraStatus.setVisible(true);
				}
				
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/*
		 * O JScrollPane oferece uma área de visualização que pode apresentar barras de rolagem quando necessário.
		 * Ele é especialmente útil para JTable, JTextArea. 
		 * E outros componentes que podem ultrapassar o espaço disponível.
		 * 
		 */

		//Responsável por gerenciar os dados.
		modeloTabela = new DefaultTableModel(new Object[][] {}, 
				new String[] {"Nome", "E-mail", "Telefone", "CPF", "Data nascimento"}
		
		) {
			//Impede a edição de céculas de forma direta.
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		//Apresenta os dados visualmente.
		table = new JTable(modeloTabela);
		
		//Usuário pode selecionar uma linha por vez.
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Já aclopa a tabela.
		JScrollPane scrollPane = new JScrollPane(table);
		
		//O JPopuMenu deve ser associado a tabela.
		//Se não existe tabela não tem popu, ou deveria aparecer mesmo sem?
		JPopupMenu jPopupVisualizarCliente = new JPopupMenu();
		
		//O item de visualizar deve abrir a JDialog com as informações do cliente.
		JMenuItem visualizarClienteItem = new JMenuItem("Visualizar");
		
		//Deverá solicitar a confirmação do usuário para a remoção do cliente.
		JMenuItem excluirClienteItem = new JMenuItem("Excluir");
		
		//Adicionando os itens ao JPopupMenu.
		jPopupVisualizarCliente.add(visualizarClienteItem);
		jPopupVisualizarCliente.add(excluirClienteItem);

		//Ações dos itens. Popup
		visualizarClienteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = table.getSelectedRow();
				
				if (!temLinhaSelecionada(linha)) {
					JOptionPane.showMessageDialog(
							ClienteIg.this, 
							"Selecione uma linha", 
							getTitle(), 
							JOptionPane.INFORMATION_MESSAGE);
					
					//Se não encerrar o método abaixo tenta encontar o cliente com -1.
					return;
				}
			
				//Sendo válido converte a linha e exibe os dados.
				int linhaModelo = table.convertRowIndexToModel(linha);
				
				String nome = (String) modeloTabela.getValueAt(linhaModelo, 0);
				String email = (String) modeloTabela.getValueAt(linhaModelo, 1);
				String telefone = (String) modeloTabela.getValueAt(linhaModelo, 2);
				String cpf = (String) modeloTabela.getValueAt(linhaModelo, 3);
				String dataNascimento = (String) modeloTabela.getValueAt(linhaModelo, 4);
				
				FormularioCadastroDialog formVisualizarCliente = 
						new FormularioCadastroDialog(ClienteIg.this);
			
				formVisualizarCliente.preencherFormulario(nome, email, telefone, cpf, dataNascimento);
				
				formVisualizarCliente.configurarVisualizacao(ClienteIg.this);
				
				atualizaStatus("Cliente selecionado: " + nome);
				
				formVisualizarCliente.setVisible(true);
			}
		});
		
		//Ação do botão de excluir do Popup.
		excluirClienteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = table.getSelectedRow();
				
				if (!temLinhaSelecionada(linhaSelecionada)) {
					
					JOptionPane.showMessageDialog(
							ClienteIg.this, 
							"Selecione pelo menos uma linha.", 
							getTitle(), 
							JOptionPane.INFORMATION_MESSAGE);
					
					return;
				}
				
				//Seguindo a lógica já aplicada: pergunta se deseja excluir.
				int resposta = JOptionPane.showConfirmDialog(
						ClienteIg.this, 
						"Deseja excluir o cliente selecionado?", 
						getTitle(), 
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				//Sendo sim, converte a linha model e realiza a exclusão.
				if (resposta == JOptionPane.YES_OPTION) {
					
					int linhaModelo = table.convertRowIndexToModel(linhaSelecionada);
					modeloTabela.removeRow(linhaModelo);

					JOptionPane.showMessageDialog(
							ClienteIg.this, 
							"Cliente excluído com sucesso", 
							getTitle(), 
							JOptionPane.INFORMATION_MESSAGE);
					
					atualizaStatus("Cliente excluído com sucesso");
				}
				
			}
		});

		//Atenção ao método especifíco.
		table.setComponentPopupMenu(jPopupVisualizarCliente);
		
		//O painel de antes erá só um intermediário.
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panelBotoes = new JPanel();
		contentPane.add(panelBotoes, BorderLayout.SOUTH);
		
		lblBarraStatus = new JLabel("Pronto");
		panelBotoes.add(lblBarraStatus);
		
		//Área dos botões.
		
		btnCliente = new JButton("Novo cliente");
		
		btnCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panelBotoes.add(btnCliente);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panelBotoes.add(btnVisualizar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panelBotoes.add(btnExcluir);

		//Ações dos botões.
		
		//Ação de cadastro.
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				//Se for usado em mais lugares:
				//Considerar criar como variável de classe.
				FormularioCadastroDialog formCadastroCliente = 
						new FormularioCadastroDialog(ClienteIg.this);
				
				//Sendo criada de forma independente abre a possibilidade para ser alterada.
				//Tanto na sua própria classe, como aqui;
				formCadastroCliente.setVisible(true);
			}
		
		});
		
		/*
		 * Ideia pensada é a seguinte:
		 * 
		 * Como a visualização usa o mesmo molde do FormularioCadastroDialog.
		 * Basta eu chamar ela, só que recebendo e apresentando dados da coluna selecionada.
		 * Criar um método que faz o setText para cada coluna.
		 * 
		 */
		
		//Ação de visualizar.
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linha = table.getSelectedRow();
				
				//Se nenhuma linha estiver selecionada o getSelectedRow retorna -1.
				if (!temLinhaSelecionada(linha)) {
					JOptionPane.showMessageDialog(
							ClienteIg.this, 
							"Selecione um cliente para visualizar", getTitle(), 
							JOptionPane.INFORMATION_MESSAGE);
					
					//Encerra o método.
					return;
				}
				
				//Sendo uma linha válida:
				int linhaModelo = table.convertRowIndexToModel(linha);
				
				//Pega os dados.
				//Pega o valor na linha e sua coluna respectiva. Indexado a partir de 0.
				String nome = modeloTabela.getValueAt(linhaModelo, 0).toString();
				String email = modeloTabela.getValueAt(linhaModelo, 1).toString();
				String telefone = modeloTabela.getValueAt(linhaModelo, 2).toString();
				String cpf = modeloTabela.getValueAt(linhaModelo, 3).toString();
				String dataNascimento = modeloTabela.getValueAt(linhaModelo, 4).toString();

				//Uma instância de formulário com o método preencherCampos irá receber os valores.
				//Setar eles e apresentar, desabilitando e removendo a visibilidade dos botões.
				
				FormularioCadastroDialog visualizarCliente = 
						new FormularioCadastroDialog(ClienteIg.this);
				
				visualizarCliente.preencherFormulario(nome, email, telefone, cpf, dataNascimento);
				
				visualizarCliente.configurarVisualizacao(ClienteIg.this);
				
				visualizarCliente.setVisible(true);
				
				atualizaStatus("Cliente selecionado: " + nome);
				
			}
		});
		
		//Ação de excluir.
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linha = table.getSelectedRow();
				
				if(!temLinhaSelecionada(linha)) {
					
					JOptionPane.showMessageDialog(ClienteIg.this, 
						"Selecione um cliente para excluir", 
						getTitle(), JOptionPane.INFORMATION_MESSAGE);
					
					return;
				} 
				
				//Sendo válido, pergunta e se for sim, realiza a exclusão.
				int resposta = JOptionPane.showConfirmDialog(
						ClienteIg.this,
						"Deseja realmente excluir o cliente selecionado?", 
						"Exclusão", 
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				if (resposta == JOptionPane.YES_OPTION) {

					int linhaModelo = table.convertRowIndexToModel(linha);
					modeloTabela.removeRow(linhaModelo);
						
					JOptionPane.showMessageDialog(
							ClienteIg.this,
							"Cliente excluído com sucesso!",
							"Exclusão",
							JOptionPane.INFORMATION_MESSAGE);
					
					atualizaStatus("Cliente excluído com sucesso");
					
					return;
				}
				
			}
		});
		
		//Aqui para garantir que table esteja instanciada.
		//Além do true no botão médio, realiza o click para deixar a fonte padrão.
		radioItemMedio.doClick();
		
	}//Fim do construtor da janela principal.
	
	/**
	 * 
	 * Recebe o conteúdo das áreas de texto do formulário.
	 * E os adiciona no modelo da tabela.
	 * 
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param cpf
	 * @param dataNascimento
	 */
	public void recebeDadosForm(String nome, String email, 
			String telefone, String cpf, String dataNascimento) {
		
		Object[] novaLinha = {nome, email, telefone, cpf, dataNascimento};
		
		modeloTabela.addRow(novaLinha);
	}
	
	/**
	 * Válida a linha selecionada pelo usuário.
	 * Sendo igual a -1. Nenhuma linha foi selecionada retorna falso.
	 * Caso contrário, retorna verdadeiro.
	 * 
	 * @param linha
	 */
	
	private boolean temLinhaSelecionada(int linha) {
		
		if (linha == -1) {
			return false;
		} 
		
		return true;
	}
	
	/**
	 * Método responsável por verificar e atualizar o status da aplicação.
	 * 
	 * @param status
	 */
	
	public void atualizaStatus(String status) {
		lblBarraStatus.setText(status);
	}

}//class CadastroClienteIg.
