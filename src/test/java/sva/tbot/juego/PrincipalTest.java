package sva.tbot.juego;

import org.junit.jupiter.api.Test;
import sva.tbot.modelo.entidades.per.Personaje;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrincipalTest {
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
}