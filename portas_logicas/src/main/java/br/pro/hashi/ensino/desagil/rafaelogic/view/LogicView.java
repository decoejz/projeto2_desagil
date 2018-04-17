package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;


import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;
import br.pro.hashi.ensino.desagil.rafaelogic.view.SimplePanel;



// A classe JPanel representa um painel da interface gráfica,
// onde você pode adicionar componentes como menus e botões.
public class LogicView extends SimplePanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	int size_img = 175;

	private Gate gate; //Criação das portas lógicas.

	// A classe JCheckBox é utilizada para a criação de botões clicáveis
	//como caixas de checagem.
	private	JCheckBox entrada1CheckBox; //Criação da primeira caixa.
	private	JCheckBox entrada2CheckBox; //Criação da segunda caixa.

	private Image image;
	private Color color;
	
	private boolean out;

	public LogicView(Gate gate) {
		
		super(300,300);
		
		this.gate = gate; //Diz qual será o gate utilizado
		
		entrada1CheckBox = new JCheckBox();
		entrada2CheckBox = new JCheckBox();
		
		int pos = size_img/ (this.gate.getSize()+1); //Logica de divisao em relação ao numerod e rows
		int addPos = 275/10; //parte inteira
		
		
		if (this.gate.getSize() >= 2){
			add(entrada1CheckBox, 10, (pos*2)+addPos, 25, 25);
		}
		add(entrada2CheckBox, 10, pos+addPos, 25, 25);
		
		
		
		entrada1CheckBox.addActionListener(this);
		entrada2CheckBox.addActionListener(this);
		
		color = Color.BLACK;
		
		String path = "/" + gate.toString() + ".png";
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();
		
		addMouseListener(this);
		
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
		
		out = gate.read();
		
		if(out == true){
			color = Color.RED;
			repaint();
		}
		
		else{
			color = Color.BLACK;
			repaint();
		}
		
	}


	// Método exigido pela interface ActionListener, que representa
	// a reação a uma digitação do usuário nos dois primeiros campos.
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {

		int x = event.getX();
		int y = event.getY();

		if((Math.pow(x - (240+15), 2) + Math.pow(y - (110+15), 2)) < 225 && out == true){

			color = JColorChooser.showDialog(this, null, color);
			
			repaint();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
	}
	@Override
	public void mouseReleased(MouseEvent event) {
	}
	@Override
	public void mouseEntered(MouseEvent event) {
	}
	@Override
	public void mouseExited(MouseEvent event) {
	}
	
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 50, 40, 175, 175, null);

		g.setColor(color);
		g.drawOval(240, 110, 30, 30);
		g.fillOval(240, 110, 30, 30);

		getToolkit().sync();
    }
}

