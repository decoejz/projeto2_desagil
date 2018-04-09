package br.pro.hashi.ensino.desagil.rafaelogic.model;

public abstract class Gate implements Emitter, Receiver {
	private int size;
	private String name;
	
	protected Gate(int size, String name) {
		this.size=size;
		this.name=name;
	}
	
	public int getSize(){
		return size;
	}
	
	public String toString() {
		return name;
	}
}
