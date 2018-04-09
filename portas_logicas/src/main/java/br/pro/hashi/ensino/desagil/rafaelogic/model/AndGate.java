package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate{
	private NandGate nand = new NandGate();
	private NandGate nand_2 = new NandGate();
	
	public AndGate() {
		super(2,"And");
		nand.connect(0, nand_2);
		nand.connect(1, nand_2);
	}
	
	public void connect(int pinIndex, Emitter emitter){		
		nand_2.connect(pinIndex, emitter);
	}
	
	public boolean read() {
		return nand.read();
	}
}