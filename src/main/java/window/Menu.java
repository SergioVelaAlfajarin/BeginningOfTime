package window;

import sva.tbot.gestores.GestorEntidad;
import sva.tbot.gestores.GestorNoEntidad;

public abstract class Menu {

	// MENUS ----------------------------------------------

	public static String MENU_PRINCIPAL_2 = Menu.MUESTRA_MARCA + """

				0.- Empezar de nuevo
				1.- Nueva Partida %s
				2.- Extras
				3.- ChangeLog
				4.- Cambiar Idioma (Change Language)
				5.- Salir del juego""";

	public static final String MENU_PRINCIPAL = Menu.MUESTRA_MARCA + """

				1.- Nueva Partida
				2.- Extras
				3.- ChangeLog
				4.- Cambiar Idioma (Change Language)
				5.- Salir del juego""";

	public static final String MENU_CHANGELOG = """
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
				 + Mas enemigos, personajes, objetos y equipamiento.""";

	public static final String MENU_EXTRAS = """
				Idea original: Shiku.
				Programador: Shiku.
				Diseños conceptuales: Josert
				Testers: Dakos, Josert.""";

	public static String MENU_CLASES = """
				0.- Ayuda e informacion sobre las clases.
				1.- Tanque        (HP: 100, AD: 1, AP: 1, AR: 2, MR: 2, AGL: 0)
				2.- Asesino       (HP: 90,  AD: 2, AP: 1, AR: 1, MR: 0, AGL: 3)
				3.- Caballero     (HP: 90,  AD: 2, AP: 0, AR: 2, MR: 2, AGL: 1)
				4.- Mago          (HP: 90,  AD: 1, AP: 3, AR: 0, MR: 0, AGL: 2)
				5.- Marginado     (HP: 90,  AD: 2, AP: 0, AR: 1, MR: 2, AGL: 2)""";

	public static final String MENU_CAMBIAR_IDIOMA =
			"Esta opcion esta temporalmente deshabilitada.";

	public static final String MENU_ACCIONES = """
				1.- Fisico
				2.- Magico
				3.- Varios
				4.- Bloquear
				5.- Inventario""";

	public static final String MENU_ACCIONES_FISICO = """
				1.- Ataque rapido   (Minimo: 2 AD)
				2.- Gran Golpe      (Minimo: 9 AD)
				3.- Barrido         (Minimo: 5 AD)
				4.- Volver""";
	

	public static final String MENU_ACCIONES_MAGICO = """
				1.- AquaSplash      (Minimo: 2 AP)
				2.- Combustion      (Minimo: 9 AP)
				3.- Tornado         (Minimo: 5 AP)
				4.- Volver""";
	

	public static final String MENU_ACCIONES_VARIOS = """
				1.- Estadisticas de Personajes y Enemigos
				2.- Informacion del Juego
				3.- Informacion de los ataques
				4.- Salir del juego
				5.- volver""";
	

	// MUESTRA COSAS -----------------------------------

	public static final String MUESTRA_MARCA = """
				         /\\
				      _-/- \\
				     - /   -\\-_
				     -/__    \\_-
				     /     _--\\
				     ----------

				     SHI-GAMES
				    2021 - SPAIN
				""";
	

	public static final String MUESTRA_AYUDA_CLASES = """
				============ AYUDA SOBRE LAS CLASES ================

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
				Nivel 20: (HP: 180,  AD: 8, AP: 8, AR: 5, MR: 6, AGL: 6)""";
	

	public static final String MUESTRA_AYUDA_ATAQUES = GestorNoEntidad.getInfoAtaques();

	public static final String MUESTRA_AYUDA_JUEGO = "Aqui ira la ayuda del juego.";

	public static final String MUESTRA_HISTORIA = "Aqui ira la historia del juego.";

	public static final String MUESTRA_STATS = GestorEntidad.getStatsPersonajes() + "\n" + GestorEntidad.getStatsEnemigos();

	// MENSAJES --------------------------------------------

	public static final String PIDE_ACCION = "Que quieres hacer?";

	public static final String PIDE_NOMBRE = "Como quieres llamar al personaje?";

	public static final String MSG_BIENVENIDA = "Bienvenido a The Beginning of Time!";

	public static final String MSG_TURNO = "===== Turno de %s =====";

	public static final String msgEnemigosSeAcercan = GestorEntidad.getSizeYNivelEnemigos();

	public static final String MSG_EMPIEZA_JUEGO = "========= Comienza el juego =========";

	public static final String MSG_PERSONAJE_CREADO = "%s ha sido creado. (%02d/%02d)";

	public static final String MSG_SUBIDA_NIVEL = "%s ha subido a nivel %d!";

	public static final String MSG_SALIR_JUEGO = "Hasta la proxima";

	public static final String PIDE_CLASE = "Que clase quieres elegir?";

	public static final String PIDE_CONFIRMACION = "Estas Seguro? (1:si/2:no)";

	public static final String MSG_PAUSA = "\nIntroduce enter para continuar....";

	public static final String MSG_ENEMIGO_DERROTADOS = "Has derrotado a todos los Enemigos!!";

	public static final String MSG_JUEGO_PERDIDO = "Todos los personajes han muerto. Has perdido.";

	public static final String PIDE_ENEMIGO = "Que enemigo quieres elegir? ";

	public static final String MSG_ENEMIGO_NO_DISPONIBLE = "El enemigo que has elegido esta muerto.";

	public static final String MSG_BLOQUEO = "Bloquear pasara el turno y reducira el daño del siguiente ataque recibido.";

	public static final String MSG_POSICION_INVENTARIO_VACIA = "Vacio";

	public static final String MSG_CREACION_CLASE = "Elige una clase para tu Personaje: ";

	public static final String MSG_ENEMIGO_ATAQUE_ESQUIVADO = "El enemigo ha esquivado el ataque!";

	public static final String MSG_CONFIRMACION_BLOQUEO = "Ahora %s esta bloqueando.";

	// ERRORES -----------------------------------------------------------

	public static final String ERROR_ENEMIGO_NO_ENCONTRADO = "El Enemigo especificado no ha sido encontrado.";

	public static final String ERROR_PERSONAJE_NO_ENCONTRADO = "El enemigoPersonaje especificado no ha sido encontrado";

	public static final String ERROR_NUMERO_NO_VALIDO = "Introduce un numero valido.";

	public static final String ERROR_ATAQUE_NO_DISPONIBLE = "El ataque seleccionado no esta disponible";

	public static final String ERROR_INESPERADO = "El juego ha encontrado un error y debe cerrarse.";

	public static final String ERROR_BLOQUEO_YA_ACTIVO = "Bloquear ya estaba activo.";

	public static final String ERROR_NOMBRE_NO_VALIDO = "Introduce un nombre valido.";
}
