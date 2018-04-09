package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;


public class View extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//As duas componentes do painel principal:
	//o menu e o subpainel da interface lógica.
	private JComboBox<Gate> menu;
	private LogicView logicview;
	
	public View(LinkedList<Gate> model) {
		//A classe JComboBox representa um componente que pode ser usado como menu.
		menu = new JComboBox<>();
		
		//Os itens do menu são instâncias de Gate. O texto exibido para cada
		//item é a String devolvida pelo método toString.
		for(Gate gate: model) {
			menu.addItem(gate);
		}
		
		//Todo painel precisa de um layout, que estabelece como os componentes
		//são organizados dentro dele. O layout escolhido na linha abaixo é o
		//mais simples possível: simplesmente enfileira todos eles na vertical.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Adiciona o menu a esse painel.
		add(menu);
		
		//Adiciona o subpainel da primeira porta lógica a este painel.
		addlogicview(0);
		
		//Estabelece que este painel reage a mudanças no menu.
		//Isso só pode ser feito se este painel for do tipo
		//ActionListener, por isso ele implementa essa interface.
		menu.addActionListener(this);
	}
	
	//Adiciona o subpainel de um Gate a este painel.
	private void addlogicview(int index) {
		Gate gate = menu.getItemAt(index);
		logicview = new LogicView(gate);
		add(logicview);
	}
	
	//Método exigido pela interface ActionListener, que representa a reação a uma
	//mudança no menu: remover o subpainel de gate que está atualmente neste
	//painel e adicionar o subpainel correspondente ao gate selecionado no menu.
	@Override
	public void actionPerformed(ActionEvent event) {
		remove(logicview);
		int index = menu.getSelectedIndex();
		addlogicview(index);
		
		// Linha necessária para evitar um bug gráfico.
		((JFrame) SwingUtilities.getRoot(this)).pack();
	}
}