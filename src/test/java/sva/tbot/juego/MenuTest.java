package sva.tbot.juego;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
	@Test
	void testEncargadoDeComprobarQueTodosLosMenusDevuelvenLoQueDeberian(){
		assertEquals(Menu.menuPrincipal(0),
				Menu.LOGO_DEV + Menu.SALTO_LINEA +
				"1.- Nueva Partida" + Menu.SALTO_LINEA +
				"2.- Extras" + Menu.SALTO_LINEA +
				"3.- Historial de cambios" + Menu.SALTO_LINEA +
				"4.- Cambiar Idioma (Change Language)" + Menu.SALTO_LINEA +
				"5.- Salir del juego" + Menu.SALTO_LINEA);

		assertEquals(Menu.menuPrincipal(2),
				Menu.LOGO_DEV + Menu.SALTO_LINEA +
						"0.- Empezar de nuevo" + Menu.SALTO_LINEA +
						"1.- Nueva Partida++" + Menu.SALTO_LINEA +
						"2.- Extras" + Menu.SALTO_LINEA +
						"3.- Historial de cambios" + Menu.SALTO_LINEA +
						"4.- Cambiar Idioma (Change Language)" + Menu.SALTO_LINEA +
						"5.- Salir del juego" + Menu.SALTO_LINEA);

		assertEquals(Menu.menuPrincipal(10),
				Menu.LOGO_DEV + Menu.SALTO_LINEA +
						"0.- Empezar de nuevo" + Menu.SALTO_LINEA +
						"1.- Nueva Partida+10" + Menu.SALTO_LINEA +
						"2.- Extras" + Menu.SALTO_LINEA +
						"3.- Historial de cambios" + Menu.SALTO_LINEA +
						"4.- Cambiar Idioma (Change Language)" + Menu.SALTO_LINEA +
						"5.- Salir del juego" + Menu.SALTO_LINEA);

		assertEquals(Menu.menuChangeLog(),"""
				========================= ITEMS UPDATE 0.5 (ACTUAL) =========================
				+ Nombre cambiado a The Beginning of Time.
				+ Añadida historia.
				+ Añadida posibilidad de que los enemigos suelten objetos, equipamiento y experiencia despues de cada combate.
				+ Revisadas todas las opciones y mejorada la seleccion de las mismas.
				+ Ahora cada personaje tiene inventario propio, con posibilidad de usar objetos y equiparse armadura.
				+ Ahora para subir de nivel sera necesario alcanzar cierto nivel de XP.
				+ Añadido nuevos menus, para incrementar la accesibilidad.
				+ Estadisticas rediseñadas por completo, con mas niveles.
				+ Rama de habilidades de apoyo sustituidas por objetos.
				+ Ahora los enemigos se dividen en 4 tipos.
				+ Posibilidad de empezar Nueva Partida +, con tus personajes de la anterior partida y enemigos mas complicados.
				+ Juego publicado en GitHub!

				========================= CONTENT UPDATE 0.6 (PROXIMAMENTE) =================
				+ Traduccion a otros idiomas.
				+ Mas enemigos, personajes, objetos y equipamiento.
				""");

		assertEquals(Menu.menuExtras(),"""
				Idea original: Shiku.
				Programador: Shiku.
				Diseños conceptuales: Josert
				Testers: Dakos, Josert.
				Traductor al ingles: Dakos.
				Historia basada en el origen de tiid.
				""");

		assertEquals(Menu.menuClases(),
				"0.- " + "Ayuda e informacion sobre las clases." + Menu.SALTO_LINEA +
				"1.- " + String.format("%-12s","Tanque") + "(HP: 100, AD: 1, AP: 1, AR: 2, MR: 2, AGL: 0)" + Menu.SALTO_LINEA +
				"2.- " + String.format("%-12s","Asesino") + "(HP: 90,  AD: 2, AP: 1, AR: 1, MR: 0, AGL: 3)" + Menu.SALTO_LINEA +
				"3.- " + String.format("%-12s","Caballero") + "(HP: 90,  AD: 2, AP: 0, AR: 2, MR: 2, AGL: 1)" + Menu.SALTO_LINEA +
				"4.- " + String.format("%-12s","Mago") + "(HP: 90,  AD: 1, AP: 3, AR: 0, MR: 0, AGL: 2)" + Menu.SALTO_LINEA +
				"5.- " + String.format("%-12s","Marginado") + "(HP: 90,  AD: 2, AP: 0, AR: 1, MR: 2, AGL: 2)" + Menu.SALTO_LINEA);

		assertEquals(Menu.menuCambiaIdioma(), """
				Que idioma quieres elegir?
				1.- ESPAÑOL
				2.- ENGLISH
				""");

		assertEquals(Menu.menuAcciones(),"""
				1.- Fisico
				2.- Magico
				3.- Varios
				4.- Bloquear
				5.- Inventario
				""");

		assertEquals(Menu.menuAccionesFisico(),
				"1.- " + String.format("%-12s", "Ataque rapido") + "(Min: 2 AD)" + Menu.SALTO_LINEA +
				"2.- " + String.format("%-12s","Gran Golpe") + "(Min: 10 AD)" + Menu.SALTO_LINEA +
				"3.- " + String.format("%-12s","Barrido")   + "(Min: 6 AD)" + Menu.SALTO_LINEA +
				"4.- Volver" + Menu.SALTO_LINEA);

		assertEquals(Menu.menuAccionesMagico(),
				"1.- " + String.format("%-12s", "AquaSplash") + "(Min: 2 AD)" + Menu.SALTO_LINEA +
						"2.- " + String.format("%-12s","Combustion") + "(Min: 10 AD)" + Menu.SALTO_LINEA +
						"3.- " + String.format("%-12s","Tornado")   + "(Min: 6 AD)" + Menu.SALTO_LINEA +
						"4.- Volver" + Menu.SALTO_LINEA);

		assertEquals(Menu.menuAccionesOtros(),"""
				1.- Estadisticas de Personajes y Enemigos
				2.- Informacion del Juego
				3.- Informacion de los ataques
				4.- Salir del juego
				5.- Volver
				""");

		assertEquals(Menu.muestraAyudaClases(),"""
				============ AYUDA SOBRE LAS CLASES ============

				1.- Tanque: Clase especializada en la resistencia y el aguante. Mucha vida y armadura.
				Sera capaz de aguantar mas golpes que ninguna clase, pero sera poco agil.
				Nivel 20: (HP: 320, AD: 3, AP: 6, AR: 13, MR: 13, AGL: 4)

				2.- Asesino: Clase especializada en el daño Fisico. Mucho daño fisico y agilidad.
				Tendra mas posibilidad de esquivar los ataques, pero a cambio tendra muy poca armadura.
				Nivel 20: (HP: 200,  AD: 18, AP: 4, AR: 5, MR: 5, AGL: 18)

				3.- Caballero: Clase especializada en la estabilidad. Tendra un poco de todo.
				Esta clase hara un daño normal, y recibira daño normal.
				Nivel 20: (HP: 250,  AD: 12, AP: 7, AR: 8, MR: 8, AGL: 10)

				4.- Mago: Clase especializada en el daño Magico. Mucho daño magico.
				Clase para jugadores veteranos. Sera capaz de realizar mucho daño, pero tendra muy poca resistencia.
				Nivel 20: (HP: 160,  AD: 5, AP: 20, AR: 6, MR: 10, AGL: 12)

				5.- Marginado: Clase para jugadores curtidos. No tiene ninguna ventaja. Le costara mas subir de nivel,
				y sus estadisticas no aumentaran tanto como las otras clases. Un verdadero reto.
				Nivel 20: (HP: 180,  AD: 8, AP: 8, AR: 5, MR: 6, AGL: 6)
				""");

		assertEquals(Menu.muestraAyudaJuego(),"Aqui ira la ayuda del juego.");

		assertEquals(Menu.muestraHistoriaJuego(),"Aqui ira la historia del juego.");

		assertEquals(Menu.pideAccion(),"Que quieres hacer?");

		assertEquals(Menu.pideNombre(),"Como quieres llamar al personaje?");

		assertEquals(Menu.msgBienvenida(), "Bienvenido a The Beginning of Time!");

		assertEquals(Menu.msgTurno(),"===== Turno de %s =====");

		assertEquals(Menu.msgEmpiezaJuego(),"========= Comienza el juego =========");

		assertEquals(Menu.msgPersonajeCreado(),"%s ha sido creado. (%02d/%02d)");

		assertEquals(Menu.msgSubidaNivel(),"%s ha subido al nivel %d!");

		assertEquals(Menu.msgSalirJuego(),"Hasta la proxima!");

		assertEquals(Menu.pideClases(),"Que clase quieres elegir?");

		assertEquals(Menu.pideConfirmacion(),"Estas Seguro? (1:si/2:no)");

		assertEquals(Menu.msgPausa(),Menu.SALTO_LINEA + "Pulsa enter para continuar....");

		assertEquals(Menu.msgEnemigoDerrotado(),"Has derrotado a todos los Enemigos!");

		assertEquals(Menu.msgJuegoPerdido(),"Todos los personajes han muerto. Has perdido.");

		assertEquals(Menu.pideEnemigo(),"Que enemigo quieres elegir?");

		assertEquals(Menu.enemigoElegidoNoDisponible(), "El enemigo que has elegido esta muerto.");

		assertEquals(Menu.msgBloqueo(),"Bloquear pasara el turno y reducira el daño del siguiente ataque recibido.");

		assertEquals(Menu.msgConfirmaBloqueo(),"Quieres bloquear?");

		assertEquals(Menu.msgPosVacia(),"Vacio");

		assertEquals(Menu.msgCreacionClase(),"Elige una clase para tu Personaje:");

		assertEquals(Menu.msgEnemigoAtaqueEsquivado(),"El enemigo ha esquivado el ataque!");

		assertEquals(Menu.msgPersonajeAtaqueEsquivado(),"Has esquivado el ataque.");

		assertEquals(Menu.confimaBloqueo(),"Ahora %s esta bloqueando.");

		assertEquals(Menu.errorEnemigoNoEncontrado(),"El Enemigo especificado no ha sido encontrado.");

		assertEquals(Menu.errorPersonajeNoEncontrado(),"El Personaje especificado no ha sido encontrado.");

		assertEquals(Menu.errorNumeroNoValido(),"Introduce un numero valido.");

		assertEquals(Menu.errorAtaqueNoDisponible(),"El ataque seleccionado no esta disponible.");

		assertEquals(Menu.errorInesperado(),"El juego ha encontrado un error y debe cerrarse.");

		assertEquals(Menu.errorBloqueoYaActivo(),"Bloquear ya estaba activo.");

		assertEquals(Menu.errorNombreNoValido(),"Introduce un nombre valido.");

		assertEquals(Menu.msgDespedida(),"Gracias por jugar!");
	}
}