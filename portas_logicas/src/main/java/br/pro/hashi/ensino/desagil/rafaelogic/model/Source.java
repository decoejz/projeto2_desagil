package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class Source implements Emitter {
    private boolean on;

    //Construtor, define o on inicialmente como false.
    public Source() {
        on = false;
    }
    
    //Transforma o on para o que for desejado (true ou false).
    public void turn(boolean on) {
        this.on = on;
    }

    //Lê o valor do on, seja ele true ou false
    @Override
    public boolean read() {
        return on;
    }
}