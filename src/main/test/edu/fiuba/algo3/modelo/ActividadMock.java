package edu.fiuba.algo3.modelo;

public class ActividadMock implements IActividad {
    int duracion;
    public ActividadMock(int duracion){
        this.duracion = duracion;
    }

    public int duracion() {
        return duracion;
    }

    @Override
    public void reportar(ITemporizador temporizador) {
        temporizador.reportarActividad(this.duracion);
    }
}
