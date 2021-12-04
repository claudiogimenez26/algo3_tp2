package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;

public class Pais implements IPais { 
    String nombre;
    List<IPais> adyacentes;
    List<IEdificio> edificios;
    double latitud;
    double longitud;

    public Pais(String nombre, IRobo robo, double latitud, double longitud) throws Exception{
        this.nombre = nombre;
        this.adyacentes = new ArrayList<IPais>();
        this.edificios = new ArrayList<IEdificio>();
        this.latitud = latitud;
        this.longitud = longitud;

        GeneradorDeEdificios g = new GeneradorDeEdificios(robo);
        this.edificios.addAll(g.crearEdificiosPara(this));
    }
    
    public Pais(String nombre, GeneradorDeEdificios g, double latitud, double longitud) throws Exception {
        this.nombre = nombre;
        this.adyacentes = new ArrayList<IPais>();
        this.edificios = new ArrayList<IEdificio>();
        this.edificios.addAll(g.crearEdificiosPara(this));
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public List<IEdificio> edificios(IEdificio edificio) {
        return edificios;
    }

    public void agregarEdificio(IEdificio edificio) {
        edificios.add(edificio);
    }
    
    public Boolean contieneEdificio(IEdificio edificio) {
        return edificios.contains(edificio);
    }

    public void conectarA(IPais otro) {
        adyacentes.add(otro);
    }

    public boolean sePuedeViajarA(IPais pais) {
        return this.adyacentes.contains(pais); // asume que solo hay una instancia de cada pais
    }
    
    public String toString(){
        return this.nombre;
    }

    @Override
    public Object nombre() {
        return nombre;
    }

    @Override
    public double obtenerLatitud() {
        return this.latitud;
    }

    @Override
    public double obtenerLongitud() {
        return this.longitud;
    }

    public double distanciaA(IPais paisDestino) { // luego se puede cambiar por alguna libreria especifica (GeoCalc)
        double radioTerrestre = 6372.7954;
        double dLat = Math.toRadians(paisDestino.obtenerLatitud() - this.latitud);
        double dLon = Math.toRadians(paisDestino.obtenerLongitud() - this.longitud);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLon / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians( this.latitud)) * Math.cos(Math.toRadians(paisDestino.obtenerLatitud()));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = radioTerrestre * c;

        return dist;
    }
}
