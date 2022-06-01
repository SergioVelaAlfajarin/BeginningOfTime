package sva.tbot.gestores;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import static org.junit.jupiter.api.Assertions.*;

class HistorialTurnosTest {
	@BeforeEach
	void setUp() {
		var e = new Enemigo[]{new Enemigo(2), new Enemigo(2)};
		ListasEntidad.enemigoList().initLista(e);
		var p = new Personaje[]{new Personaje("e",2), new Personaje("o",3)};
		ListasEntidad.personajeList().initLista(p);
	}

	@Test
	void checkNextID() {
		assertEquals(1,HistorialTurnos.getNextID());
		HistorialTurnos.addTurno();
		assertEquals(1, HistorialTurnos.getUltimoID());
		assertEquals(1,HistorialTurnos.getPrimerID());

		assertEquals(2,HistorialTurnos.getNextID());
		HistorialTurnos.addTurno();
		assertEquals(2, HistorialTurnos.getUltimoID());
		assertEquals(1,HistorialTurnos.getPrimerID());

		assertEquals(3,HistorialTurnos.getNextID());
		HistorialTurnos.addTurno();
		assertEquals(3, HistorialTurnos.getUltimoID());
		assertEquals(1,HistorialTurnos.getPrimerID());

		assertEquals(4,HistorialTurnos.getNextID());
		HistorialTurnos.addTurno();
		assertEquals(4, HistorialTurnos.getUltimoID());
		assertEquals(2,HistorialTurnos.getPrimerID());

		assertEquals(5,HistorialTurnos.getNextID());
		HistorialTurnos.addTurno(); //deberia tener id 5
		assertEquals(5, HistorialTurnos.getUltimoID()); //el ulitmo deberia ser 5
		assertEquals(3, HistorialTurnos.getPrimerID()); //el primero deberia ser 3

		assertEquals(6, HistorialTurnos.getNextID()); //deberia ser 6
		assertNotNull(HistorialTurnos.getPrimerTurnoDisponible());
	}

	@AfterEach
	void tearDown() {
		ListasEntidad.enemigoList().lista().clear();
		ListasEntidad.personajeList().lista().clear();
	}
}