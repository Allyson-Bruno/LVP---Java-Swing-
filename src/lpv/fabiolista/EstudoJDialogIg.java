package lpv.fabiolista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EstudoJDialogIg extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstudoJDialogIg frame = new EstudoJDialogIg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Construtor da janela principal.
	 */
	public EstudoJDialogIg() {
		
		setTitle("Janela principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//Área dos Menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuArquivo = new JMenu("Aquivo");
		menuBar.add(menuArquivo);
		
		JMenuItem menuNovoItem = new JMenuItem("Novo");
		
		menuArquivo.add(menuNovoItem);
		
		//Adiciona uma linha para demonstrar a separação dos itens.
		//Repare que ela deve ficar entre os itens.
		menuArquivo.addSeparator();
		
		JMenuItem MenuSairItem = new JMenuItem("Sair");
		menuArquivo.add(MenuSairItem);
		
		JMenu menuControle = new JMenu("Componentes");
		menuBar.add(menuControle);
		
		JMenuItem itemComponenteControle = new JMenuItem("Janela de componentes");
		
		itemComponenteControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			
			}
		});
		
		menuControle.add(itemComponenteControle);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButton = new JPanel();
		contentPane.add(panelButton, BorderLayout.SOUTH);
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		
		progressBar.setIndeterminate(false);
		
		progressBar.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		progressBar.setForeground(new Color(0, 250, 154));
		progressBar.setStringPainted(true);
		progressBar.setValue(50);
		panelButton.add(progressBar);
		
		JButton btnJanela = new JButton("Abrir Janela");
		
		panelButton.add(btnJanela);
	
		//Parte 3: Componentes de controle.
		//Repare nos argumentos passados: 
		//Apresenta valores entre 0 e 100 e inicia com o valor 50.
		
		JSlider slider = new JSlider(0, 100, 50);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(slider.getValue());
			}
		});
		
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setForeground(Color.BLACK);
		contentPane.add(slider, BorderLayout.NORTH);

		//JSpinner.
		//Todos os componentes de controle tem um grande uso via interface gráfica.
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(5, 0, 100, 1));

		//Assim como o JSlider, o JSpinner utiliza eventos de alteração.
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int numeroAtual =  (int) spinner.getValue();
				if (numeroAtual == 10) {
					System.out.println("Você chegou no número dez");
				}
			}
		});
		
		
		//Bloquando o campo de texto.
		JSpinner.DefaultEditor editor = (DefaultEditor) spinner.getEditor();
		editor.getTextField().setEditable(false);
		
		panelButton.add(spinner);
		
		/**
		 * Clicado neste botão será criado um JDialog.
		 * Sendo um JDialog simples, não é necessário criar uma classe separada.
		 * Porém havera um exemplo abaixo.
		 * 
		 * Repare que uma vez que as coisas contidas na janela aumentam.
		 * Torna-se um problema na manuntenção da janela.
		 */
		
		btnJanela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Sendo true, ou seja, modal o usuário NÃO pode interagir com a janela principal.
				JDialog jDialog = new JDialog(EstudoJDialogIg.this, "Janela modal", true);
				
				//jDialog.setTitle("Janela de diálogo");
				jDialog.setSize(381, 180);
				jDialog.setLocationRelativeTo(EstudoJDialogIg.this); 
				
				JLabel jLabelApresentacaoJanela = new JLabel();
				
				jLabelApresentacaoJanela.setText("Esta é uma janela de diálogo");
				jLabelApresentacaoJanela.setHorizontalAlignment(SwingConstants.CENTER);
				
				jDialog.getContentPane().add(jLabelApresentacaoJanela);
				
				
				/*
				 * Nesse caso de criar uma maneira de sair personalizada.
				 * É necessário colocar o DO NOTHING ON CLOSE. 
				 * Para travar o usuário a essa única saída. 
				 */
				
				jDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				
				jDialog.addWindowListener(new WindowAdapter() {
				
					@Override
					public void windowClosing(WindowEvent e) {

						//Aqui não é necessário o this
						int resposta = JOptionPane.showConfirmDialog(jDialog, "Deseja sair?", 
								"confirme", JOptionPane.YES_OPTION);
						
						if (resposta == JOptionPane.YES_OPTION) {
							
							jDialog.dispose();
						}
					}
				});
				
				//Por padrão vem o HIDE_ON_CLOSE.
				
				jDialog.setVisible(true);
			}
		});
		
		menuNovoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(
						EstudoJDialogIg.this, 
						"Deseja criar um novo arquivo?", 
						getTitle(),
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE);
			}
		});
		
		

		//Sendo passado null centraliza na tela.
		setLocationRelativeTo(null);
		
		
	}//Fim do construtor da janela.
	
	

}//class CadastroClienteIg
