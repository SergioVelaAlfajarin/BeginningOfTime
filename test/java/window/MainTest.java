package window;

import exception.JuegoException;
import modelo.entidades.Personaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static window.Principal.buildInfoString;
import static window.Principal.accionBloquear;

class MainTest {
	@Test
	void compruebaSiLaCadenaConstruidaEsCorrecta() {
		assertEquals(
				"El enemigo estaba bloqueando.\nAtaque critico! Has conseguido hacer 100 pts de daño.\nEnemigo1 ha muerto.\n",
				buildInfoString("Enemigo1", 100, true, true, false)
		);
		assertEquals(
				"Ataque critico! Has conseguido hacer 100 pts de daño.\nEnemigo1 ha muerto.\n",
				buildInfoString("Enemigo1", 100, true, false, false)
		);
		assertEquals(
				"El enemigo estaba bloqueando.\nHas conseguido hacer 100 pts de daño.\nEnemigo1 ha muerto.\n",
				buildInfoString("Enemigo1", 100, false, true, false)
		);
		assertEquals(
				"Has conseguido hacer 100 pts de daño.\nEnemigo1 ha muerto.\n",
				buildInfoString("Enemigo1", 100, false, false, false)
		);
		assertEquals(
				"Ataque critico! Has conseguido hacer 100 pts de daño.\n",
				buildInfoString("Enemigo1", 100, true, false, true)
		);
		assertEquals(
				"Has conseguido hacer 100 pts de daño.\n",
				buildInfoString("Enemigo1", 100, false, false, true)
		);
	}

	//TODO comprobar todo el codigo para que sea capaz de recibir test (organizar metodos)

	@Test
	void compruebaAccionBloquear() {

		var per = new Personaje("prueba",1);
		per.activarBloqueo();
		assertThrows(
				JuegoException.class,
				() -> accionBloquear(per)
		);
		per.desactivaBloqueo();

		/*private static boolean accionBloquear(Personaje p) throws JuegoException{
			if (p.isBloqueo()) {
				throw new JuegoException(Menu.errorBloqueoYaActivo());
			}
			doublePrintLn(Menu.msgBloqueo()); //mover esto a ayuda o dejar permanente???
			boolean confirmacion = pideConfirmacion();
			if (confirmacion) {
				p.activarBloqueo();
				doublePrintLn(String.format(Menu.confimaBloqueo(),p.getNombre()));
			}
			return !confirmacion;
		}*/
	}
}