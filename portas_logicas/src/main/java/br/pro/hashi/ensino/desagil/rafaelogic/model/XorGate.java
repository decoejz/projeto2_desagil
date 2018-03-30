package br.pro.hashi.ensino.desagil.rafaelogic.model; 

public class XorGate extends Gate{
	private NandGate nand0;
	private NandGate nand1;
	private NandGate nand2;
	private NandGate nand3;
	
	public XorGate() {
		nand0 = new NandGate();
		nand1 = new NandGate();
		nand2 = new NandGate();
		nand3 = new NandGate();
		
		nand1.connect(1, nand0);
		nand2.connect(1, nand0);
		nand3.connect(0, nand1);
		nand3.connect(1, nand2);
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		nand0.connect(pinIndex, emitter);
		
		if (pinIndex == 0){
			nand1.connect(0, emitter);
		}
		
		else if (pinIndex==1){
			nand2.connect(0, emitter);
		}
		else{	
		}
	}

	@Override
	public boolean read() {
		return (nand3.read());
	}
}