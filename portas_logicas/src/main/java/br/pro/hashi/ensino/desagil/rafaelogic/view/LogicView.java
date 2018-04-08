package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;

public class LogicView extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private Gate gate;
	
	private	JCheckBox in1;
	private	JCheckBox in2;
	private JCheckBox out;
	
	public LogicView(Gate gate) {
		this.gate = gate;
	
		in1 = new JCheckBox();
		in2 = new JCheckBox();
		out = new JCheckBox();
		
		JLabel inlabel = new JLabel("Entrada");
		JLabel outlabel = new JLabel("Saída");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(inlabel);
		add(in1);
		add(in2);
		add(outlabel);
		add(out);
				
		in1.addActionListener(this);
		in2.addActionListener(this);
		
		out.setEnabled(false);
		
		update();
	}
	
	private void update() {
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}

}
	


