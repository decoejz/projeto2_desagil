package br.pro.hashi.ensino.desagil.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

// A classe JPanel representa um painel da interface gráfica,
// onde você pode adicionar componentes como menus e botões.
// Esta em particular representa o subpainel de uma calculadora.
// A interface ActionListener é explicada melhor mais abaixo.
public class PortasView extends JPanel implements ActionListener {

	// Não é necessário entender esta linha, mas se você estiver curioso
	// pode ler http://blog.caelum.com.br/entendendo-o-serialversionuid/.
	private static final long serialVersionUID = 1L;


	private Gate gate;

	// A classe JTextField representa um componente usado para digitação de texto.
	// https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
	private	JCheckBox entrada1;
	private	JCheckBox entrada2;
	private JCheckBox saida;


	public PortasView(Gate gate) {
		this.gate = gate;

		entrada1 = new JCheckBox();
		entrada2 = new JCheckBox();
		saida = new JCheckBox();

		// A classe JLabel representa um componente que é simplesmente texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		JLabel entrada = new JLabel("Entrada: ");
		JLabel saidaL = new JLabel("Sa�da: ");

		// Todo painel precisa de um layout, que estabelece como os componentes
		// são organizados dentro dele. O layout escolhido na linha abaixo é o
		// mais simples possível: simplesmente enfileira todos eles na vertical.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Adiciona todas as componentes a este subpainel.
		add(entrada);
		add(entrada1);
		add(entrada2);
		add(saidaL);
		add(saida);

		// Estabelece que este subpainel reage ao usuário digitar nos dois
		// primeiros campos. Isso só pode ser feito se este subpainel for
		// do tipo ActionListener, por isso ele implementa essa interface.
		entrada1.addItemListener(this);
		entrada2.addItemListener(this);

		// Estabelece que o terceiro campo está desativado, não é digitável.
		saida.setEnabled(false);

		// Não podemos esquecer de chamar update na inicialização,
		// caso contrário a interface só ficará consistente depois
		// da primeira interação do usuário com os campos de texto.
		// A definição exata do método update é dada logo abaixo.
		update();
	}


	// Método que lê o peso e o raio dos primeiros campos,
	// calcula a densidade e a escreve no terceiro campo.
	private void update() {
		boolean entrada1;
		boolean entrada2;

		try {
			weight = Double.parseDouble(weightField.getText());
			radius = Double.parseDouble(radiusField.getText());
		}
		catch(NumberFormatException exception) {
			resultField.setText("?");
			return;
		}

		boolean saida = gate.calculate(weight, radius);
		resultField.setText(Double.toString(result));
	}


	// Método exigido pela interface ActionListener, que representa
	// a reação a uma digitação do usuário nos dois primeiros campos.
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
}
