package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class NotGate extends Gate{
	private NandGate nand = new NandGate();
	
	public void NotGate(NandGate nand) {
		this.nand = nand;
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		NotGate.this.nand.connect(0, emitter);
		NotGate.this.nand.connect(1, emitter);
	}

	@Override
	public boolean read() {
		return this.nand.read();
	}
}
