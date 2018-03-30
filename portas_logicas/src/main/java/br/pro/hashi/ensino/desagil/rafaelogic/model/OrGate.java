package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class OrGate extends Gate{
	private NandGate nand =  new NandGate(); // primeiro
	private NandGate nand_2 =  new NandGate(); // e segundo nand
	private NandGate nand_o = new NandGate(); //nand de saída
	
	public OrGate() {
		nand_o.connect(0, nand);
		nand_o.connect(1, nand_2);
	}
	
	public void connect(int pinIndex, Emitter emitter) {
		if(pinIndex==0) {
			nand.connect(0, emitter);
			nand.connect(1, emitter);
		}
		
		else if(pinIndex==1) {
			nand_2.connect(0, emitter);
			nand_2.connect(1, emitter);
		}
		
		else {
			
		}
	}
	
	@Override
    public boolean read() {
		return nand_o.read();
	}
}