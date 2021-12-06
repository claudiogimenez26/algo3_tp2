package edu.fiuba.algo3.modelo;

public class PistaHistorica extends Pista {

    public PistaHistorica(String hechoHistorico) {
        this.contenido = "Me contó algo muy interesante de ese pais. ".concat(hechoHistorico);
    }

    @Override
    public Object dificultad() {
        return new Media(); //TODO pistas de ladron de distinto tipo y dificultad;
    }

}
