package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

//TODO: artefactos, etc dependen de la dificultad
public class EntregaTest {
    @Test
    public void robaronTesoroNacionalEnMontreal() throws Exception { 
        List<PaisSinPistas> paises = new ArrayList<PaisSinPistas>();
        //TODO: capitales aun no implementadas. digamos que montreal es un pais
        paises.add(new PaisSinPistas("Montreal"));
        //TODO: lanzar error si se inicializa robo con <2 paises
        paises.add(new PaisSinPistas("Zimbabwe"));
        Ladron carmen = new Ladron("Carmen Sandiego", "F", "Moto", "Oscuro", "Bien bonita");
        Artefacto arte = new Artefacto("La pantera rosa");
        Robo elRobo = new Robo(paises, carmen, arte);

        Pais montreal = new Pais(elRobo.lugarDeRobo().nombre, elRobo);
        Edificio edificio = new Edificio("El banco", montreal, elRobo, new EdificioEconomico());
        montreal.agregarEdificio(edificio);
        Policia roberta = new Policia(montreal);
        roberta.entrarA(edificio);
        assertEquals(roberta.cuestionarTestigo(), "Zimbabwe");
    }

    @Test
    public void PoliciaVisitaBancoLuegoBiblioteca() throws Exception { 
        List<PaisSinPistas> paises = new ArrayList<PaisSinPistas>();
        paises.add(new PaisSinPistas("Montreal"));
        paises.add(new PaisSinPistas("Lima"));

        //TODO: usar robo mock en esta seccion
        Ladron carmen = new Ladron("Carmen Sandiego", "F", "Moto", "Oscuro", "Bien bonita");
        Artefacto arte = new Artefacto("La pantera rosa");
        Robo elRobo = new Robo(paises, carmen, arte);

        Pais montreal = new Pais(elRobo.lugarDeRobo().nombre, elRobo);

        Edificio banco = new Edificio("El banco", montreal, elRobo, new EdificioEconomico());
        Edificio biblio = new Edificio("La biblioteca", montreal, elRobo, new EdificioEconomico());
        montreal.agregarEdificio(banco);
        montreal.agregarEdificio(biblio);


        Policia roberta = new Policia(montreal);

        roberta.entrarA(banco);

        assertEquals(roberta.cuestionarTestigo(), "Lima");
        roberta.salirDe(banco);
        roberta.entrarA(biblio);
        assertEquals(roberta.cuestionarTestigo(), "Lima");
    }
    
    //Nota: test redundante en PoliciaTest. Puesto aqui por conveniencia
    @Test
    public void viajaDeMontrealAMéxico() throws Exception{
        IPais montreal = new PaisMock("Montreal");
        IPais mexico = new PaisMock("México");
        montreal.conectarA(mexico);
        Policia paco = new Policia(montreal);

        assertEquals(montreal.nombre(), paco.paisActual().toString());
        paco.viajarA(mexico);
        assertEquals(mexico.nombre(), paco.paisActual().toString());
    }
    
    //Nota: test redundante en PoliciaTest. Puesto aqui por conveniencia
    @Test
    public void Entrar3VecesAlAeropuertoY55VecesAlPuerto() throws Exception{
        List<PaisSinPistas> paises = new ArrayList<PaisSinPistas>();
        paises.add(new PaisSinPistas("Montreal"));
        paises.add(new PaisSinPistas("Lima"));

        //TODO: usar robo mock en esta seccion
        Ladron carmen = new Ladron("Carmen Sandiego", "F", "Moto", "Oscuro", "Bien bonita");
        Artefacto arte = new Artefacto("La pantera rosa");
        Robo elRobo = new Robo(paises, carmen, arte);

        Pais montreal = new Pais(elRobo.lugarDeRobo().nombre, elRobo);

        Edificio banco = new Edificio("El banco", montreal, elRobo, new EdificioEconomico());
        Edificio biblio = new Edificio("La biblioteca", montreal, elRobo ,new EdificioEconomico());
        montreal.agregarEdificio(banco);
        montreal.agregarEdificio(biblio);


        Policia roberta = new Policia(montreal);
        for(int i = 0; i < 3; i++){
            roberta.entrarA(banco);
            assertEquals(roberta.cuestionarTestigo(), "Lima");
            roberta.salirDe(banco);
        }

        for(int i = 0; i < 55; i++){
            roberta.entrarA(biblio);
            assertEquals(roberta.cuestionarTestigo(), "Lima"); 
            roberta.salirDe(biblio); 
        }
    }
    
    //Nota: test redundante en PoliciaTest. Puesto aqui por conveniencia
    @Test
    public void PoliciaEsHeridoConCuchilloYDuerme() throws Exception{
        IPais montreal = new PaisMock("Montreal");
        Temporizador t = new Temporizador(9, 20, 36);
        Policia undyne = new Policia(montreal, t);
        assertEquals(t.horasTranscurridas(), 0);
        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 3);

        //hacer que pasen 12 horas para que duerma el policia
        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 6);
        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 9);
        undyne.recibirHeridaConCuchillo();
        assertEquals(t.horasTranscurridas(), 12 + 8);
    }
}