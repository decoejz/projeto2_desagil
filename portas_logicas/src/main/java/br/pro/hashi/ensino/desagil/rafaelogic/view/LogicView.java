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
public class LogicView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Gate gate; //Criação das portas lógicas.

	// A classe JCheckBox é utilizada para a criação de botões clicáveis
	//como caixas de checagem.
	private	JCheckBox entrada1CheckBox; //Criação da primeira caixa.
	private	JCheckBox entrada2CheckBox; //Criação da segunda caixa.
	private JCheckBox saidaCheckBox; //Criação da caixa de saída.


	public LogicView(Gate gate) {
		this.gate = gate; //Diz qual será o gate utilizado
		
		//Escreve um texto desejado para aparecer na interface.
		JLabel entradaLabel = new JLabel("Entrada:");
		JLabel saidaLabel = new JLabel("Saida:");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Os passos add() servem para adicionar os componentes, já criados, na interface.
		add(entradaLabel);

		//Essa condição if diz quantos checkbox terão na interface.
		//Caso o número de entradas seja diferente de 1, ele cria duas checkboxes de entrada.
		//Caso sejá apenas 1, ele cria apenas uma checkbox de entrada.
		if(gate.getSize()!=1){
			entrada1CheckBox = new JCheckBox(); //Criação da primeira checkbox de entrada.
			entrada2CheckBox = new JCheckBox(); //Criação da segiunda checkbox de entrada.
			saidaCheckBox = new JCheckBox(); //Criação da checkbox de saida.
			add(entrada2CheckBox);
			entrada2CheckBox.addActionListener(this);
		}
		
		else {
			entrada1CheckBox = new JCheckBox(); //Criação da checkbox de entrada.
			saidaCheckBox = new JCheckBox(); //Criação da checkbox de saida.
		}

		add(entrada1CheckBox);
		add(saidaLabel);
		add(saidaCheckBox);

		entrada1CheckBox.addActionListener(this);

		saidaCheckBox.setEnabled(false);
		
		//Atualiza os valores das caixas.
		update();
	}


	//Método que verifica a lógica e indica qual deverá ser a saída.
	private void update() {
		Source entrada1Source = new Source(); //Criação do primeiro emissor lógico.
		
		//Caso sejam duas entradas
		if(gate.getSize()!=1){
			Source entrada2Source = new Source(); //Criação do segundo emissor lógico.
			
			//Se estiver selecionado
			if(entrada2CheckBox.isSelected()){
				//Emissor é igual a true.
				entrada2Source.turn(true);		
			}
			
			//Se não estiver selecionado
			else if(entrada2CheckBox.isSelected()==false){
				//Emissor é igual a false
				entrada2Source.turn(false);
			}
			
			//Mandando a entrada 2 para a posição 1 de onde a lógica será feita.
			gate.connect(1, entrada2Source);

		}
		
		//Se estiver selecionado
		if(entrada1CheckBox.isSelected()){
			//Emissor é igual a true.
			entrada1Source.turn(true);
		}
		
		//Se não estiver selecionado
		else if(entrada1CheckBox.isSelected()==false){
			//Emissor é igual a false
			entrada1Source.turn(false);
		}
		
		//Mandando a entrada 1 para a posição 0 de onde a lógica será feita.
		gate.connect(0, entrada1Source);
		
		//Se a leitura da lógica for verdadeira.
		if(gate.read()==true) {
			//O resultado deverá ser verdadeiro (CheckBox marcado)
			saidaCheckBox.setSelected(true);
		}

		//Se a leitura da lógica for falsa
		else if(gate.read()==false){
			//O resultado deverá ser falso (CheckBox desmarcado)
			saidaCheckBox.setSelected(false);
		}
	}


	// Método exigido pela interface ActionListener, que representa
	// a reação a uma digitação do usuário nos dois primeiros campos.
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
}
