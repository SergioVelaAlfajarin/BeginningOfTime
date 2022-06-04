package sva.tbot.juego;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sva.tbot.gestores.ListasEntidad;
import sva.tbot.modelo.entidades.TiposClase;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrincipalTest {
	@BeforeEach
	void setUp() {
		var e = new Enemigo[]{new Enemigo(TiposClase.LOBO, 2), new Enemigo(TiposClase.LOBO, 2)};
		ListasEntidad.enemigoList().initLista(e);
		var p = new Personaje[]{new Personaje("e",2), new Personaje("o",3)};
		ListasEntidad.personajeList().initLista(p);
	}

	@Test
	void compruebaMensajeCreacionPersonaje(){

		ArrayList<Personaje> p = new ArrayList<>();

		assertEquals(
				Menu.getMenu().msgPideClasePrincipal() + Menu.SALTO_LINEA + Menu.SALTO_LINEA,
				Principal.muestraMsgPersonajeCreado(p)
		);

		p.add(new Personaje("jose", 1));

		assertEquals(
				String.format("jose ha sido creado. (01/03)%n%s%n%n", Menu.getMenu().msgCreacionClase()),
				Principal.muestraMsgPersonajeCreado(p)
			);

		p.add(new Personaje("juan", 1));

		assertEquals(
				String.format("juan ha sido creado. (02/03)%n%s%n%n", Menu.getMenu().msgCreacionClase()),
				Principal.muestraMsgPersonajeCreado(p)
		);

		p.add(new Personaje("lol", 1));

		assertEquals(
				String.format("lol ha sido creado. (03/03)%n%s%n%n", Menu.getMenu().msgCreacionClase()),
				Principal.muestraMsgPersonajeCreado(p)
		);
	}

	@AfterEach
	void tearDown() {
		ListasEntidad.enemigoList().lista().clear();
		ListasEntidad.personajeList().lista().clear();
	}
}