package lpv.fabiolista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ComponenteControleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ComponenteControleDialog dialog = new ComponenteControleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ComponenteControleDialog() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
		
		JSlider slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(25);
		contentPanel.add(slider);
		
		JPanel panelSpiner = new JPanel();
		contentPanel.add(panelSpiner);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(50, 0, 100, 1));
		panelSpiner.add(spinner);
		
		JPanel panelProgressBar = new JPanel();
		contentPanel.add(panelProgressBar);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(50);
		progressBar.setStringPainted(true);
		panelProgressBar.add(progressBar);
		
		//Para garantir a sincronização.
		//Todos os elementos devem trabalhar em cima do mesmo valor.
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				//Deve ser feito para cada um dos listener. Uma vez:
				//Alterado o valor em um, deve refletir em todos os outros.
				int valor = slider.getValue();
				spinner.setValue(valor);
				progressBar.setValue(valor);
			}
		});
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				//O getValue() do spinner retorna Object.
				int valor = (int) spinner.getValue();
				slider.setValue(valor);
				progressBar.setValue(valor);
			}
		});
		
		setLocationRelativeTo(null);
	}

}
