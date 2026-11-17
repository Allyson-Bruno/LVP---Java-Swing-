package lpv.cadastrocliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormularioCadastroDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JButton salvarButton;
	private JButton cancelarButton;
	
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textTelefone;
	private JTextField textCpf;
	private JTextField textDataNascimento;

	/**
	 * Create the dialog.
	 */
	public FormularioCadastroDialog(ClienteIg proprietario) {
		
		//Chamando o construtor da classe JDialog, da qual a classe herda.
		super(proprietario, "Formulário de cadastro", true);
		
		//Entrou aqui, status atualizado.
		proprietario.atualizaStatus("Cadastrando novo cliente...");
		
		//Fechou a janela, volta para o texto padrão pronto.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				proprietario.atualizaStatus("Pronto");
			}
		});
		
		//Bem mais simples, recebo o propriétário e a partir daqui já é setado as configurações.
		setSize(360, 317);
		
		//Definindo a posição relativa como centralizada em relação a janela principal.
		setLocationRelativeTo(proprietario);

		//Fecha a janela e libera os recursos.
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//Elimina a necessidade de configurar título e modalidade separadamente:
		//setModalityType(DEFAULT_MODALITY_TYPE);
		//setTitle("Formulário de cadastro");
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelForm = new JPanel();
			contentPanel.add(panelForm, BorderLayout.CENTER);
			panelForm.setLayout(new GridLayout(5, 1, 5, 8));
			{
				JPanel panelNome = new JPanel();
				panelForm.add(panelNome);
				panelNome.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNome = new JLabel("Nome:");
					panelNome.add(lblNome, BorderLayout.NORTH);
				}
				{
					textNome = new JTextField();
					textNome.setColumns(10);
					panelNome.add(textNome, BorderLayout.CENTER);
				}
			}
			{
				JPanel panelEmail = new JPanel();
				panelForm.add(panelEmail);
				panelEmail.setLayout(new BorderLayout(8, 0));
				{
					JLabel lblEmail = new JLabel("Email:");
					panelEmail.add(lblEmail, BorderLayout.NORTH);
				}
				{
					textEmail = new JTextField();
					panelEmail.add(textEmail, BorderLayout.CENTER);
					textEmail.setColumns(10);
				}
			}
			{
				JPanel panelTelefone = new JPanel();
				panelForm.add(panelTelefone);
				panelTelefone.setLayout(new BorderLayout(5, 0));
				{
					JLabel lblTelefone = new JLabel("Telefone: ");
					panelTelefone.add(lblTelefone, BorderLayout.NORTH);
				}
				{
					textTelefone = new JTextField();
					panelTelefone.add(textTelefone, BorderLayout.CENTER);
					textTelefone.setColumns(10);
				}
			}
			{
				JPanel panelCpf = new JPanel();
				panelForm.add(panelCpf);
				panelCpf.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblCpf = new JLabel("CPF:");
					panelCpf.add(lblCpf, BorderLayout.NORTH);
				}
				{
					textCpf = new JTextField();
					panelCpf.add(textCpf, BorderLayout.CENTER);
					textCpf.setColumns(10);
				}
			}
			{
				JPanel panelDataNasc = new JPanel();
				panelForm.add(panelDataNasc);
				panelDataNasc.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
					panelDataNasc.add(lblDataNascimento, BorderLayout.NORTH);
				}
				{
					textDataNascimento = new JTextField();
					panelDataNasc.add(textDataNascimento, BorderLayout.CENTER);
					textDataNascimento.setColumns(10);
				}
			}
		}
		{
			JPanel paneBotao = new JPanel();
			paneBotao.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(paneBotao, BorderLayout.SOUTH);
			
			{
				salvarButton = new JButton("Salvar");
				
				//Ação do botão de salvar.
				salvarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						//Verficar se os campos obrigatórios foram preenchidos. (v)
						//Apresentar uma mensagem de erro caso algum campo obrigatório esteja vazio.(v)
						//Is blank já cobre o caso de empty e de múltiplos espaços em branco.
						if (textNome.getText().isBlank() 
							|| textCpf.getText().isBlank()
							|| textTelefone.getText().isBlank() 
							|| textEmail.getText().isBlank() 
							|| textDataNascimento.getText().isBlank()) {
							
							//ERRO_MESSAGE define o ícone vermelho.
							JOptionPane.showMessageDialog(FormularioCadastroDialog.this, 
									"Todos os campos devem ser preenchidos", 
									"Erro de validação", JOptionPane.ERROR_MESSAGE);
							
							//Interrompe a execução, evita colocar else.
							return;
							
						} 
							
						//Captura as informações do formulário.
						//Remove os espaços no início e no final da String.
						String nome = textNome.getText().strip();
						String email = textEmail.getText().strip();
						String telefone = textTelefone.getText().strip();
						String cpf = textCpf.getText().strip();
						String dataNascimento = textCpf.getText().strip();

						//Envia os dados a janela principal.
						proprietario.recebeDadosForm(nome, email, telefone, cpf, dataNascimento);
							
						//Apresentar uma mensagem de confirmação após o cadastro. (v)
						JOptionPane.showMessageDialog(FormularioCadastroDialog.this, 
							"Cliente cadastrado com sucesso", getTitle(), 
							JOptionPane.INFORMATION_MESSAGE);

						proprietario.atualizaStatus("Cliente cadastrado com sucesso");
						
						//Fechar o diálogo(v)
						FormularioCadastroDialog.this.dispose();

					}//Fim da ação do botão de salvar.
				});
				
				salvarButton.setActionCommand("OK");
				paneBotao.add(salvarButton);
				getRootPane().setDefaultButton(salvarButton);
			}
			{
				cancelarButton = new JButton("Cancelar");
				cancelarButton.setActionCommand("Cancel");
				
				//Ação do cancelar.
				cancelarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Fecha a janela e libera os recursos gráficos.
						dispose();
					}
				});
				
				paneBotao.add(cancelarButton);
			}
		}
		
	}//Fim do construtor padrão.
	
	/**
	 * Preenche o formulário com os dados do cliente selecionado.
	 * 
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param cpf
	 * @param dataNascimento
	 */
	
	public void preencherFormulario(String nome, String email, String telefone,  
			String cpf, String dataNascimento) {
		
		textNome.setText(nome);
		textEmail.setText(email);
		textTelefone.setText(telefone);
		textCpf.setText(cpf);
		textDataNascimento.setText(dataNascimento);
		
	}
	
	/**
	 *
	 * Configura o formulário para o modo visualização. 
	 * 
	 */
	
	public void configurarVisualizacao() {
		
		setTitle("Visualizar cliente");
		
		//Deixando invisível já desativa o botão.
		salvarButton.setVisible(false);
	
		cancelarButton.setText("Fechar");
		
		//Desativando os campos para impedir sua edição.
		textNome.setEditable(false);
		textEmail.setEditable(false);
		textTelefone.setEditable(false);
		textCpf.setEditable(false);
		textDataNascimento.setEditable(false);
		
	}
	
	

}//class FormularioCadastroCliente.
