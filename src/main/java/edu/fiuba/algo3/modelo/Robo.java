package edu.fiuba.algo3.modelo;
import java.util.List;

public class Robo {
    List<Pais> via;
    Artefacto artefacto;
    Ladron ladron;
    IGeneradorDeRobo generador = new GeneradorDeRobo();

    public Robo(Dificultad dificultad) throws Exception{
        via = generador.viaDePaises(dificultad);
        ladron = generador.generarLadron();
        artefacto = generador.artefacto(dificultad);
    } 

    public Robo(Dificultad dificultad, IGeneradorDeRobo generador) throws Exception {
        via = generador.viaDePaises(dificultad);
        ladron = generador.generarLadron();
        artefacto = generador.artefacto(dificultad);
    } 

    //TODO: implementar rango
    public String reportarRobo(Object rango) {
        return "Hola, ".concat(rango.toString()).concat(". Hubo un robo de ").concat(artefacto.toString());
    }

    public Pais lugarDeRobo() {
        return via.get(0);
    }

    public String nombreDeArtefacto() {
        return artefacto.nombre;
    }

    public Ladron obtenerLadron() {
        return ladron;
    }

    //TODO: generar distintos tipos de pistas
    public Pista pistaParaPais(Pais pais) {
        return new Pista(this.paisDespuesDe(pais).toString());
    }    
    
    //TODO: generar distintos tipos de pistas
    public Pista pistaParaLadron(){
        return new Pista("El genero era ".concat(this.ladron.genero().toString()));
    }

    public Pais paisDespuesDe(Pais pais) {
        return via.get(via.indexOf(pais) + 1);
    }
}