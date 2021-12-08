package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Entrega2Test {
    Temporizador t = new Temporizador(16, 20, 36);
    @Test
    public void PoliciaSufreUnaHeridaDeCuchilloYDuerme() throws Exception {
        IPais montreal = new PaisMock("Montreal");
        Policia undyne = new Policia(montreal, t);

        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 2); //Herida con un cuchillo:2 hs la primera vez, 1 hs las próximas veces.

        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 3);

        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 4 + 8);
    }
    @Test
    public void PoliciaConRangoInvestigadorTomaCasoDeUnRoboViajaDeMontrealaMéxico() throws Exception {
        //TODO: Eliminar PaisSinPistas
        List<PaisSinPistas> paises = new ArrayList<PaisSinPistas>();
        paises.add(new PaisSinPistas("Montreal", "Dolar", 0, 0));
        paises.add(new PaisSinPistas("Mexico", "Peso Mexicano", 0, 0));


        Ladron carmen = new Ladron("Carmen Sandiego", "F", "Moto", "Oscuro", "Bien bonita", "tenis");
        Artefacto arte = new Artefacto("La pantera rosa", new Valioso());
        Robo elRobo = new Robo(paises, carmen, arte);

        //TODO: Toma caso de un robo (? , de momento se representa como que el Policia respawnea en el pais en el que ocurre el robo
        IPais paisOrigen = new Pais(elRobo.lugarDeRobo().nombre, new GeneradorMockDeEdificios(), 0,0);
        IPais mexico = new Pais("Mexico", new GeneradorMockDeEdificios(), 0, 0);
        paisOrigen.conectarA(mexico); // TODO: esto deberia hacerse automagicamente cuando se crea la ruta del ladron

        Policia paco = new Policia(paisOrigen, t);

        //Se verifica la promocion de rango
        assertEquals(Novato.class, paco.rango.getClass());
        for(int i = 0; i < 10; i++){ paco.arrestarLadron(); }
        assertEquals(Investigador.class, paco.rango.getClass());

        //Viaja de Montreal a Mexico
        paco.viajarA(mexico);
        assertEquals(mexico.nombre(), paco.paisActual().toString());

    }

    @Test
    public void CargarEnLaComputadoraLosDatosRecopiladosYBuscarSospechosos() throws Exception {

    }

    @Test
    public void IntentasAtraparAlSospechosoSinLaOrdenDeArrestoEmitida() throws Exception {

    }

    @Test
    public void PartidaCompleta() throws Exception {
        //Un Policia hace 6 Arrestos.  ==> RECIBE PROMOCION de Novato a Detective.
        IPais peru = new PaisMock("Peru");
        Temporizador t = new Temporizador(0, 20, 48);
        Policia paco = new Policia(peru, t);

        assertEquals(Novato.class, paco.rango.getClass());
        for(int i = 0; i < 6; i++){ paco.arrestarLadron(); }
        assertEquals(Detective.class, paco.rango.getClass());

        //TODO: Polcia Toma un caso de un sospechoso que robó un Incan Gold Mask

        //RoboMock robo = new RoboMock();

    }


}