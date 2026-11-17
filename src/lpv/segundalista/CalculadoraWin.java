package lpv.segundalista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CalculadoraWin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	//Preciso de uma área comum a todos os textos.
	private JTextField textFieldResultado;
	
	private final JButton btnInverteSinal = new JButton("±");
	//Total acumlado durante as operações.
	private double total;
	
	//Operação que deve ser executada.
	private String operadorPendente = null;
	
	//Indica se o próximo digito inicia um novo número.
	private boolean iniciarNovoNumero = true;

	private JLabel lblCalculo;
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getOperadorPendente() {
		return operadorPendente;
	}

	public void setOperadorPendente(String operadorPendente) {
		this.operadorPendente = operadorPendente;
	}

	public boolean isIniciarNovoNumero() {
		return iniciarNovoNumero;
	}

	public void setIniciarNovoNumero(boolean iniciarNovoNumero) {
		this.iniciarNovoNumero = iniciarNovoNumero;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraWin frame = new CalculadoraWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 *Construtor da interface gráfica. 
	 * 
	 */
	public CalculadoraWin() {
		setResizable(false);
		
		setTitle("Calculadora");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Alteração do tamanho da janela.
		setBounds(100, 100, 291, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCalculo = new JPanel();
		contentPane.add(panelCalculo, BorderLayout.NORTH);
		panelCalculo.setLayout(new BorderLayout(0, 0));
		
		lblCalculo = new JLabel(" ");
		lblCalculo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblCalculo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		panelCalculo.add(lblCalculo, BorderLayout.NORTH);
		
		JPanel panel_botoes = new JPanel();
		contentPane.add(panel_botoes, BorderLayout.CENTER);
		panel_botoes.setLayout(new GridLayout(6, 4, 8, 8));
		
		JButton btnPocentagem = new JButton("%");
		panel_botoes.add(btnPocentagem);
		
		JButton btnNewButton_2 = new JButton("CE");
		panel_botoes.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("C");
		panel_botoes.add(btnNewButton_3);
		
		JButton btnBackSpace = new JButton("⌫");
		
		panel_botoes.add(btnBackSpace);
		
		JButton btnNewButton_5 = new JButton("1/x");
		panel_botoes.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("x²");
		panel_botoes.add(btnNewButton_6);
		
		JButton btnRaizQuadrada = new JButton("√x");
		panel_botoes.add(btnRaizQuadrada);
		
		JButton btnDivisao = new JButton("÷");
		panel_botoes.add(btnDivisao);
		
		JButton btnSete = new JButton("7");
		panel_botoes.add(btnSete);
		
		JButton btnOito = new JButton("8");
		panel_botoes.add(btnOito);
		
		JButton btnNove = new JButton("9");
		panel_botoes.add(btnNove);
		
		JButton btnMultiplicacao = new JButton("x");
		panel_botoes.add(btnMultiplicacao);
		
		JButton btnQuatro = new JButton("4");
		panel_botoes.add(btnQuatro);
		
		JButton btnCinco = new JButton("5");
		panel_botoes.add(btnCinco);
		
		JButton btnSeis = new JButton("6");
		panel_botoes.add(btnSeis);
		
		JButton btnSubtracao = new JButton("- ");
		panel_botoes.add(btnSubtracao);
		
		JButton btnUm = new JButton("1");
		
		panel_botoes.add(btnUm);
		
		JButton btnDois = new JButton("2");
		panel_botoes.add(btnDois);
		
		JButton btnTres = new JButton("3");
		panel_botoes.add(btnTres);
		
		JButton btnSoma = new JButton("+");
		
		panel_botoes.add(btnSoma);
		panel_botoes.add(btnInverteSinal);
		
		JButton btnZero = new JButton("0");
		panel_botoes.add(btnZero);
		
		JButton btnVirgula = new JButton(",");
		panel_botoes.add(btnVirgula);
		
		JButton btnIgual = new JButton("=");
		panel_botoes.add(btnIgual);
		
		JPanel panel_resultado = new JPanel();
		panel_resultado.setLayout(new BorderLayout(0, 0));
		panelCalculo.add(panel_resultado, BorderLayout.CENTER);
		
		//Impede que o usuário digite algo fora as opções disponíveis dos botões.
		textFieldResultado = new JTextField();
		textFieldResultado.setEditable(false);
		
		//Controla a altura do visor.
		textFieldResultado.setPreferredSize(new Dimension(0, 65));
		
		//Alinha o texto a direita.
		textFieldResultado.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Destaca os números.
		textFieldResultado.setFont(new Font("Segoe UI", Font.BOLD, 28));
		
		//Valor padrão inicializado da calculadora.
		textFieldResultado.setText("0");
		
		/*
		 * Pensamentos:
		 *
		 * Deve ser adicionado algum espaço após o apertar de teclas referente a determinadas operações.
		 * O número 0 aparece inicialmente, mas é substituido por qualquer outro número.
		 * 
		 * Importante entender:
		 * 
		 * O resultado da conta aparece em negrito sob destaque.
		 * No fundo que será no painel norte, será onde o cálculo será montado.
		 * 
		 * Sobre o funcionamento da cálculadora:
		 * 
		 * Digitado um número ele sempre vai a direita do atual. 
		 * Com exceção do número 0, que se estiver sozinho é substituido por outro número.
		 * 
		 * E para os outros, digitando um número ele vai para a direita.
		 * 
		 * Se for digitado um simbolo das operações básicas, o número em questão.
		 * 
		 * Vai para a área do cálculo, junto do símbolo de operação aguardando outro número.
		 * 
		 * O resultado desse cálculo fica na área de resultado, repetindo o processo acima.
		 * 
		 * O problema está em após ser digitado um número e ele ser levado para a área de cálculo.
		 * 
		 * Se eu digito outro número e aperto mais a soma já é contabilizada.
		 * 
		 * A lógica do total também aparece, que terá que ser uma variável de instância.
		 * 
		 * Cada total referente a um objeto calculadora.
		 * 
		 */
		
		panel_resultado.add(textFieldResultado, BorderLayout.CENTER);
		
		//Área de clique dos botões.

		//Botão 0.
		btnZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("0");
			}
		});
		
		//Botão 1.
		btnUm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("1");
			}
		});
		
		//Botão 2.
		btnDois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("2");
			}
		});
		
		//Botão 3.
		btnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("3");
			}
		});
		
		//Botão 4.
		btnQuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("4");
			}
		});
		
		//Botão 5.
		btnCinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("5");
			}
		});

		//Botão 6.
		btnSeis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("6");
			}
		});
		
		//Botão 7.
		btnSete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("7");
			}
		});
		
		//Botão 8.
		btnOito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("8");
			}
		});
		
		//Botão 9.
		btnNove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitarNumero("9");
			}
		});
		
		/*
		 * Soma e sua lógica:
		 * 
		 * 1. O usúario digita 12.
		 * 2. Ao pressionar +, o programa armazena 12 em total e registra o operador "+".
		 * 3. O usuário digita 7, substituindo o valor exibido no visor, mas mantendo total = 12.
		 * 4. Ao pressionar =, ou novamente o operador de +, soma com total.
		 * 
		 */
		
		
		//Botão soma.
		//Apertou qualquer operação espaço a esquerda, operação e espaço a direita.
		btnSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaOperacao("+");
			}
		});
		
		//Botão subtração.
		btnSubtracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaOperacao("-");
			}
		});
		
		//Botão multiplicação.
		btnMultiplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaOperacao("x");
			}
		});
		
		//Botão divisão.
		btnDivisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaOperacao("÷");
			}
		});
		
		
		//Botão de igualdade.
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (operadorPendente != null && !iniciarNovoNumero) {
					
					double primeiroNumero = total;
					
					String operador = operadorPendente;
					
					String segundoNumero = textFieldResultado.getText();
					
					//Executa a operação pendente.
					if (!executarOPeracaoPendente()) {
						return;
					}
					
					//Apresenta a expressão realizada.
					lblCalculo.setText(String.format("%s %s %s", 
							primeiroNumero, operador, segundoNumero));
					
					//Finaliza a operação.
					operadorPendente = null;
					
					//Prepara uma nova entrada.
					iniciarNovoNumero = true;
				}
					
			}
		});
		
		/*
		 * Funcionamento:
		 * Apaga um digito mais a esquerda.
		 * Será utilizado o método subtring.
		 * Como sabe o índice da palavra fica mais fácil de remover.
		 */
		
		btnBackSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Deve ser apagado o número mais a direita. (último digitado)
				String textoNumero = textFieldResultado.getText();
				
				//Não sendo maior que o número 1, retorna para 0 que é o número padrão.
				if (textoNumero.length() > 1) {
					//O primeiro parâmetro é a onde começa a extração texto.
					textFieldResultado.setText(textoNumero.substring(0, textoNumero.length() - 1));
				} else {
					textFieldResultado.setText("0");
				}
				
			}
		});
		
	}//Fim do construtor.

	private void digitarNumero(String numero) {
		
		String atual = textFieldResultado.getText();
		
		//Sendo um novo número ou igual a 0 ele então é setado.
		if(iniciarNovoNumero || atual.equals("0")) {
			textFieldResultado.setText(numero);
		
		} else {
			
			//Caso contrário é adicionado a direita.
			//Para acressentar usando textField. Se usa:
			//Seta o texto concatenado com o número a direita.
			textFieldResultado.setText(textFieldResultado.getText() + numero);
		}
		
		//Informa que já foi iniciado a digitação do número atual.
		iniciarNovoNumero = false;
		
	}
	
	private boolean executarOPeracaoPendente() {
		
		//Não há uma operação pronta para executar.
		if(operadorPendente ==  null || iniciarNovoNumero) {
			return true;
		}
		
		//Recupera o segundo número.
		double segundoNumero = Double.valueOf(textFieldResultado.getText());
		
		total += segundoNumero;
		
		//Executa a operação correspondente.
		switch (operadorPendente) {
			
			case "+":
				total += segundoNumero;
			break;
			
			case "-":
				total -= segundoNumero;
			break;

			//Se atente para não cair em confusão.
			case "x":
				total *= segundoNumero;
			break;
			
			case "÷":
				
				//A calculadora do windows mostra na área do resultado:
				//"Não é possível dividir por zero.
				//Enquanto na área do cálculo mostra. Ex: 25 ÷ 
				if (segundoNumero == 0) {
					
					
					iniciarNovoNumero = true;
					
					
					textFieldResultado.setFont(new Font("Segoe UI", Font.BOLD, 14));

					//Impedir que seja possível apagar esse texto.
					textFieldResultado.setText("Não é possível dividir por 0");
					lblCalculo.setText(String.format("%s %s", total, operadorPendente));
					
					//Reseta tudo após mostrar.
					total = 0;
					operadorPendente = null;

					return false;
				} 
				
				total /= segundoNumero;		
			
			break;

		}
		
		//Apresenta o resultado da operação.
		textFieldResultado.setText(String.valueOf(total));
		textFieldResultado.setFont(new Font("Segoe UI", Font.BOLD, 28));
		
		return true;
		
	}
	
	private void selecionaOperacao(String operador) {
		
		if (operadorPendente == null) {
			
			//Primeira operação.
			total = Double.valueOf(textFieldResultado.getText());
			
		} else {
			
			//Executa a operação anterior se houver um segundo número digitado.
			//Apenas se executarOperacaoPendente retornar falso, ou seja:
			//Não houver operação para se executar.
			if (!executarOPeracaoPendente()) {
				return;
			}
		}
		
		//Armazena o novo operador.
		operadorPendente = operador;
		
		//Atualiza a expressão.
		lblCalculo.setText(total + " " + operador);
		
		//Prepara a digitação do próximo número,
		iniciarNovoNumero = true;
		
	}
	
	
}//class CalculadoraWin.

