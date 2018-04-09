package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;


// A classe JPanel representa um painel da interface gráfica,
// onde você pode adicionar componentes como menus e botões.
// Esta em particular representa o subpainel de uma calculadora.
// A interface ActionListener é explicada melhor mais abaixo.
public class LogicView extends JPanel implements ActionListener {

	// Não é necessário entender esta linha, mas se você estiver curioso
	// pode ler http://blog.caelum.com.br/entendendo-o-serialversionuid/.
	private static final long serialVersionUID = 1L;


	private Gate gate;

	// A classe JTextField representa um componente usado para digitação de texto.
	// https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
	private	JCheckBox entrada1;
	private	JCheckBox entrada2;
	private JCheckBox saida;


	public LogicView(Gate gate) {
		this.gate = gate;
		
		JLabel entrada = new JLabel("Entrada: ");
		JLabel saidaL = new JLabel("Sa�da: ");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(entrada);

		if(gate.getSize()!=1){
			entrada1 = new JCheckBox();
			entrada2 = new JCheckBox();
			saida = new JCheckBox();	
			add(entrada2);
			entrada2.addActionListener(this);
		}
		
		else {
			entrada1 = new JCheckBox();
			saida = new JCheckBox();
		}

		add(entrada1);
		add(saidaL);
		add(saida);

		entrada1.addActionListener(this);

		saida.setEnabled(false);

		update();
	}


	// Método que lê o peso e o raio dos primeiros campos,
	// calcula a densidade e a escreve no terceiro campo.
	private void update() {
		Source entr1 = new Source();

		if(gate.getSize()!=1){
			Source entr2 = new Source();
			if(entrada2.isSelected()){
				entr2.turn(true);		
			}
			
			else if(entrada2.isSelected()==false){
				entr2.turn(false);
			}
			
			gate.connect(1, entr2);

		}
		
		
		if(entrada1.isSelected()){
			entr1.turn(true);
		}
		
		else if(entrada1.isSelected()==false){
			entr1.turn(false);
		}
		
		gate.connect(0, entr1);
		
		if(gate.read()==true) {
			saida.setSelected(true);
		}

		else if(gate.read()==false){
			saida.setSelected(false);
		}
	}


	// Método exigido pela interface ActionListener, que representa
	// a reação a uma digitação do usuário nos dois primeiros campos.
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
}
