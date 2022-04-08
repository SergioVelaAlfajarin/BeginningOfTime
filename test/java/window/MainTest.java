package window;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static window.Main.buildInfoString;

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
}