package window;

import gestores.GestorEntidad;

public abstract class Menu {

	// MENUS ----------------------------------------------

	public static String menuInicialSecundario(int vecesJugado) {
		StringBuilder temp = new StringBuilder(Menu.muestraMarca() + "\n");

		temp.append("0.- Empezar de nuevo");
		temp.append("\n");
		temp.append("1.- Nueva Partida");
		if (vecesJugado >= 5)
			temp.append("+").append(vecesJugado);
		else
			temp.append("+".repeat(Math.max(1, vecesJugado)));
		temp.append("\n");
		temp.append("""
				2.- Extras
				3.- ChangeLog
				4.- Cambiar Idioma (Change Language)
				5.- Salir del juego""");

		return temp.toString();
	}

	public static String menuPrincipal() {
		return Menu.muestraMarca() + """

				1.- Nueva Partida
				2.- Extras
				3.- ChangeLog
				4.- Cambiar Idioma (Change Language)
				5.- Salir del juego""";
	}

	public static String menuChangeLog() {
		return """

				========================= ITEMS UPDATE 0.5 (ACTUAL) =========================
				+ Nombre cambiado a The Beginning of Time.
				+ Añadida historia.
				+ Añadida posibilidad de que los enemigos suelten objetos o equipamiento después de cada combate.
				+ Revisadas todas las opciones y mejorada la selección de las mismas.
				+ Ahora cada personaje tiene inventario propio, con posibilidad de usar objetos y equiparse armadura.
				+ Ahora para subir de nivel será necesario alcanzar cierto nivel de XP.
				+ Añadido nuevos menus, para incrementar la accesibilidad.
				+ Estadísticas rediseñadas por completo, con más niveles.
				+ Rama de habilidades de apoyo sustituidas por objetos.
				+ Ahora los enemigos se dividen en 4 tipos.
				+ Posibilidad de empezar Nueva Partida +, con tus personajes de la anterior partida y enemigos mas complicados.
				+ Juego publicado en GitHub!

				========================= CONTENT UPDATE 0.6 (PRÓXIMAMENTE) =================
				 + Traducción a otros idiomas.
				 + Mas enemigos, personajes, objetos y equipamiento.""";
	}

	public static String menuCreditos() {
		return """

				Idea original: Shiku.
				Programador: Shiku.
				Diseños conceptuales: Josert
				Testers: Dakos, Josert.""";
	}

	public static String menuClases() {
		return """
				0.- Ayuda e información sobre las clases.
				1.- Tanque        (HP: 100, AD: 1, AP: 1, AR: 2, MR: 2, AGL: 0)
				2.- Asesino       (HP: 90,  AD: 2, AP: 1, AR: 1, MR: 0, AGL: 3)
				3.- Caballero     (HP: 90,  AD: 2, AP: 0, AR: 2, MR: 2, AGL: 1)
				4.- Mago          (HP: 90,  AD: 1, AP: 3, AR: 0, MR: 0, AGL: 2)
				5.- Marginado     (HP: 90,  AD: 2, AP: 0, AR: 1, MR: 2, AGL: 2)""";
	}

	public static String changeLangMenu() {
		return "\nEsta opción esta temporalmente deshabilitada.";
	}

	public static String menuAcciones() {
		return """
				1.- Físico
				2.- Mágico
				3.- Inventario
				4.- Bloquear
				5.- Varios""";
	}

	public static String menuAccionesFisico() {
		return """
				1.- Ataque rápido   (Mínimo: 2 AD)
				2.- Gran Golpe      (Mínimo: 9 AD)
				3.- Barrido         (Mínimo: 5 AD)
				4.- Volver""";
	}

	public static String menuAccionesMagico() {
		return """
				1.- AquaSplash		(Mínimo: 2 AP)
				2.- Combustion		(Mínimo: 9 AP)
				3.- Tornado			(Mínimo: 5 AP)
				4.- Volver""";
	}

	public static String menuAccionesOtros() {
		return """
				1.- Estadísticas de Personajes y Enemigos
				2.- Información del Juego
				3.- Información de los ataques
				4.- Limpiar pantalla
				5.- Salir del juego
				6.- volver""";
	}

	// MUESTRA COSAS -----------------------------------

	public static String muestraMarca() {
		return """
				         /\\
				      _-/- \\
				     - /   -\\-_
				     -/__    \\_-
				     /     _--\\
				     ----------

				     SHI-GAMES
				    2021 - SPAIN
				""";
	}

	public static String muestraAyudaClases() {
		return """
				============ AYUDA SOBRE LAS CLASES ================

				1.- Tanque: Clase especializada en la resistencia y el aguante. Mucha vida y armadura.
				Sera capaz de aguantar mas golpes que ninguna clase, pero sera poco ágil.
				Nivel 20: (HP: 320, AD: 3, AP: 6, AR: 13, MR: 13, AGL: 4)

				2.- Asesino: Clase especializada en el daño Físico. Mucho daño físico y agilidad.
				Tendrá mas posibilidad de esquivar los ataques, pero a cambio tendrá muy poca armadura.
				Nivel 20: (HP: 200,  AD: 18, AP: 4, AR: 5, MR: 5, AGL: 18)

				3.- Caballero: Clase especializada en la estabilidad. Tendrá un poco de todo.
				Esta clase hará un daño normal, y recibirá daño normal.
				Nivel 20: (HP: 250,  AD: 12, AP: 7, AR: 8, MR: 8, AGL: 10)

				4.- Mago: Clase especializada en el daño Mágico. Mucho daño mágico.
				Clase para jugadores veteranos. Sera capaz de realizar mucho daño, pero tendrá muy poca resistencia.
				Nivel 20: (HP: 160,  AD: 5, AP: 20, AR: 6, MR: 10, AGL: 12)

				5.- Marginado: Clase para jugadores curtidos. No tiene ninguna ventaja. Le costara mas subir de nivel,
				y sus estadísticas no aumentaran tanto como las otras clases. Un verdadero reto.
				Nivel 20: (HP: 180,  AD: 8, AP: 8, AR: 5, MR: 6, AGL: 6)""";
	}

	public static String muestraAyudaAtaques() {
		return "Aquí ira la ayuda de los ataques.";
	}

	public static String muestraAyudaJuego() {
		return "Aquí ira la ayuda del juego.";
	}

	public static String muestraHistoriaJuego() {
		return "Aquí ira la historia del juego.";
	}

	public static String muestraStats() {
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	// MENSAJES --------------------------------------------

	public static String pideAccion() {
		return "Que quieres hacer?";
	}

	public static String pideNombre() {
		return "Como quieres llamar al personaje?";
	}

	public static String msgBienvenida() {
		return "Bienvenido a The Beginning of Time!";
	}

	public static String msgTurno() {
		return "===== Turno de %s =====";
	}

	public static String msgEnemigosSeAcercan() {
		int[] cantidadYnivel = GestorEntidad.getSizeYNivelEnemigos();
		return String.format("Se acercan %d Enemigos de nivel %d...", cantidadYnivel[0], cantidadYnivel[1]);
	}

	public static String msgEmpiezaJuego() {
		return "========= Comienza el juego =========";
	}

	public static String msgPersonajeCreado() {
		return "%s ha sido creado. (%02d/%02d)";
	}

	public static String msgSubidaNivel() {
		return "%s ha subido a nivel %d!";
	}

	public static String msgSalirJuego() {
		return "Hasta la próxima";
	}

	public static String pideClases() {
		return "Que clase quieres elegir?";
	}

	public static String pideConfirmacion() {
		return "Estas Seguro? (1:si/2:no)";
	}

	public static String msgPausa() {
		return "\nIntroduce enter para continuar....";
	}

	public static String msgEnemigoDerrotado() {
		return "Has derrotado a todos los Enemigos!!";
	}

	public static String msgJuegoPerdido() {
		return "Todos los personajes han muerto. Has perdido.";
	}

	public static String pideEnemigo() {
		return "Que enemigo quieres elegir? ";
	}

	public static String enemigoElegidoNoDisponible() {
		return "El enemigo que has elegido esta muerto.";
	}

	public static String msgBloqueo() {
		return "Bloquear pasará el turno y reducirá el daño del siguiente ataque recibido.";
	}

	public static String msgPosicionInventarioVacia() {
		return "Vacio";
	}

	// ERRORES -----------------------------------------------------------

	public static String errorEnemigoNoEncontrado() {
		return "El Enemigo especificado no ha sido encontrado.";
	}

	public static String errorPersonajeNoEncontrado() {
		return "El Personaje especificado no ha sido encontrado";
	}

	public static String errorNumeroNoValido() {
		return "Introduce un numero valido.";
	}

	public static String errorAtaqueNoDisponible() {
		return "El ataque seleccionado no esta disponible";
	}

	public static String errorInesperado() {
		return "El juego ha encontrado un error y debe cerrarse.";
	}

}
