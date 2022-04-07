package window;

import exception.JuegoException;
import gestores.GestorEntidad;
import gestores.GestorNoEntidad;
import modelo.entidades.Enemigo;
import modelo.entidades.Personaje;
import modelo.noEntidades.Ataque;

import java.util.Scanner;

public class Main {
	private static final int MAX_COMBATES = 8;
	private static int contadorPartidas = 0;

	/**
	 * Metodo main
	 * <hr/>
	 * Pedira la opcion principal a elegir. Si esta es 1, el juego comenzara.
	 * Si no, el juego terminara.
	 * <br/>
	 * <p>
	 * Una vez el juego empieza, Rellenara los arrays, si estan vacios,
	 * mostrara la historia, y luego ejecutara los combates del juego.
	 * Cuando este termine, se ejecutara el metodo juegoCompletado, que pedira de nuevo una opcion.
	 *  <ul>
	 *      <li> Si la opcion es 0, se empezara una nueva partida.</li>
	 *      <li>
	 *          Si la opcion es 1, se repetira la parte de los combates
	 *          con los mismos personajes y enemigos mas poderosos.
	 *      </li>
	 *      <li>Si la opcion es 5, se saldra del juego.</li>
	 *  </ul>
	 *  Cada vuelta, tendra un contador que identificara el numero de partida actual.
	 * </p>
	 */
	public static void main(String[] args) {
		int opcionMenuInicio = menuInicial();
		if (opcionMenuInicio == 1) {
			try {
				do {
					contadorPartidas++;
					rellenarArrays();
					muestraHistoria();
					empiezaJuego();
					opcionMenuInicio = juegoCompletado();
				} while (opcionMenuInicio != 5);
			} catch (JuegoException e) {
				doublePrintLn(e.getMessage());
			}
		}
	}

	/**
	 * Metodo menuInicial
	 * <hr/>
	 * <p>
	 * Limpia la pantalla, y muestra el menu principal del juego. <br/>
	 * Pedira un numero, dentro del rango indicado(1-5).<br/>
	 * Por ultimo, realizara la accion correspondiente al numero.<br/>
	 * El bucle continuara hasta que 1(Empezar juego) o 5(salir juego) sea introducido.<br/>
	 * </p>
	 *
	 * @return Numero 1 o 5, empezar juego o salir juego.
	 */
	private static int menuInicial() {
		int numero;
		clear();
		do {
			doublePrintLn(Menu.menuPrincipal() + "\n");
			numero = pideNumero(1, 5, Menu.pideAccion());
			realizaAccionMenuPrincipal(numero);
		} while (numero != 1 && numero != 5);
		return numero;
	}

	/**
	 * Metodo realizaAccionMenuPrincipal
	 * <hr/>
	 * Evalua la accion elegida en el menu principal.
	 * <ul>
	 *     <li>0: Reset Juego. Solo elegible desde el menu principal del final del juego.</li>
	 *     <li>1: Empezar Juego. Este metodo no realiza ninguna accion si 1 es elegido.</li>
	 *     <li>2: Menu creditos. Mostrara el menu de las personas relacionadas con el juego.</li>
	 *     <li>3: Menu changelog. Mostrara los cambios respecto a la version anterior.</li>
	 *     <li>4: Menu cambiar idioma. Permitira cambiar el idioma a uno de los disponibles. </li>
	 *     <li>5: Salir juego. No realizara ninguna accion si 5 es elegido. </li>
	 * </ul>
	 *
	 * @param num Accion elegida por el usuario en el menu principal.
	 */
	private static void realizaAccionMenuPrincipal(int num) {
		clear();
		if (num == 0) {
			resetJuego();
		} else if (num == 2) {
			doublePrintLn(Menu.menuCreditos());
			pause();
		} else if (num == 3) {
			doublePrintLn(Menu.menuChangeLog());
			pause();
		} else if (num == 4) {
			doublePrintLn(Menu.changeLangMenu());
			pause();
		}
		clear();
	}

	/**
	 * Metodo rellenarArrays
	 * <hr/>
	 * <p>
	 * Rellenara los arrays, si estos no estan vacios.
	 * Se hace la comprobacion para ahorrar recursos al empezar Nueva Partida +.
	 * </p>
	 * <p>
	 * Si la lista de personajes no esta vacia, significa que no es una partida nueva.
	 * Por lo tanto, Aumentaran las estadisticas de todos los personajes y enemigos.
	 * </p>
	 */
	private static void rellenarArrays() {
		if (GestorEntidad.isListaPersonajesVacio()) {
			for (int i = 0; i < Personaje.MAX_PERSONAJES; i++) {
				buildPersonaje(i);
				clear();
			}
		} else {
			GestorEntidad.aumentaEstadisticasAdicionales(contadorPartidas);
		}

		if (GestorEntidad.isListaEnemigosVacio()) {
			GestorEntidad.addEnemigo(new Enemigo(0));
			GestorEntidad.addEnemigo(new Enemigo(1));
		}

		if (GestorNoEntidad.isArrayAtaquesVacio()) {
			GestorNoEntidad.addAtaque(new Ataque("af_ad_1"));
			GestorNoEntidad.addAtaque(new Ataque("gg_ad_2"));
			GestorNoEntidad.addAtaque(new Ataque("bf_ad_3"));
			GestorNoEntidad.addAtaque(new Ataque("as_ap_1"));
			GestorNoEntidad.addAtaque(new Ataque("gc_ap_2"));
			GestorNoEntidad.addAtaque(new Ataque("tm_ap_2"));
		}
	}

	/**
	 * Metodo buildPersonaje
	 * <hr/>
	 * <p>
	 * Mostrara un mensaje de bienvenida, o de confirmacion, dependiendo de si ya se ha creado
	 * un personaje.
	 * </p>
	 * <p>
	 * Imprimira el menu y luego pedira un numero del 0 al 5.
	 * El 0 es la ayuda, y lo demas las clases correspondientes.
	 * </p>
	 * <p>
	 * Si se introduce un 0 imprimira la ayuda de las clases.
	 * Si no, pedira el nombre, comprobara EasterEggs, y creara un personaje.
	 * </p>
	 *
	 * @param i Numero del personaje actual en creacion.
	 */
	private static void buildPersonaje(int i) {
		boolean repetir;
		int claseElegida;
		String nombreElegido = "";
		do {
			muestraMsgPersonajeCreado(i);
			doublePrintLn(Menu.menuClases() + "\n");

			claseElegida = pideNumero(0, 5, Menu.pideClases());

			repetir = compruebaClaseElegida(claseElegida);
			if (!repetir) {
				nombreElegido = pideNombre();
			}
		} while (repetir);
		checkEasterEggs(nombreElegido);
		GestorEntidad.addPersonaje(new Personaje(nombreElegido, claseElegida));
	}

	/**
	 * Metodo compruebaClaseElegida
	 * <hr/>
	 * <p>
	 * Comprobara si la clase elegida es 0. Si lo es, imprimira la ayuda.
	 * </p>
	 *
	 * @param claseElegida numero introducido por el usuario al escoger la clase.
	 * @return true, si es 0, o false si no lo es.
	 */
	private static boolean compruebaClaseElegida(int claseElegida) {
		if (claseElegida == 0) {
			clear();
			doublePrintLn(Menu.muestraAyudaClases() + "\n");
			pause();
			clear();
			return true;
		}
		return false;
	}

	/**
	 * Metodo pideNombre
	 * <hr/>
	 * <p>
	 * Pide el nombre del personaje al usuario.
	 * Comprueba si el nombre introducido es mayor de 2 caracteres, y si no, lo volvera a pedir.
	 * </p>
	 *
	 * @return nombre valido para la clase introducido por el usuario.
	 */
	private static String pideNombre() {
		boolean repetir;
		String nombre;
		do {
			repetir = false;
			print(Menu.pideNombre());
			nombre = pideCadena();
			if (nombre.length() <= 2) {
				printLn(Menu.errorNombreNoValido());
				repetir = true;
			}
		} while (repetir);
		return nombre;
	}

	/**
	 * Metodo muestraMsgPersonajeCreado
	 * <hr/>
	 * <p>
	 * Comprueba si el array de personajes esta vacio. Si lo esta, imprime un mensaje de bienvenida.
	 * </p>
	 * <p>
	 * Si no, mostrara confirmacion de la creacion del personaje anterior.
	 * </p>
	 *
	 * @param i Numero del personaje en creacion actual. Se mostrara la informacion del anterior.
	 */
	private static void muestraMsgPersonajeCreado(int i) {
		if (GestorEntidad.isListaPersonajesVacio()) {
			printLn(Menu.msgCreacionClase());
		} else {
			int ultPersonajeCreado = i - 1;
			Personaje p = GestorEntidad.getPersonajePorID(ultPersonajeCreado);
			if (p != null) {
				printLn(Menu.msgPersonajeCreado().formatted(
						p.getNombre(), i, Personaje.MAX_PERSONAJES
				));
			}
		}
	}

	/**
	 * wip
	 *
	 * @param nombre
	 */
	private static void checkEasterEggs(String nombre) {
		// pinga, jose, david, shiku, wakala, uwu ->(owo,nwn), peko,
		// misteroliva, rickroll, a, fnaf -> david bisbal(Ataque fisico-> ataque
		// giratorio)
	}

	/**
	 * Metodo empiezaJuego
	 * <hr/>
	 * <p>
	 * aqui ocurrira todo el juego. Es un bucle for que se repetira tantas
	 * veces como encuentros haya en el juego.
	 * En ciertas vueltas, comprobando el valor de i, la historia ira avanzando.
	 * </p>
	 * <p>
	 * En cada vuelta imprimira que se acercan nuevos enemigos.
	 * Y entrara en un bucle while, que se repetira mientras que ambos lados esten vivos.
	 * Terminara cuando:
	 *     <ul>
	 *         <li> Ambos personajes mueran: se lanzara una excepcion y el juego terminara.</li>
	 *         <li> Ambos enemigos mueran: el combate terminara e iran a la siguiente vuelta del bucle for.</li>
	 *     </ul>
	 * </p>
	 *
	 * @throws JuegoException Error si ambos personajes mueren, terminando el juego.
	 */
	private static void empiezaJuego() throws JuegoException {
		clear();
		for (int i = 1; i <= MAX_COMBATES; i++) {
			boolean repetirCombate;
			if (i == 4) {
				clear();
				doublePrintLn("aquí pasara algo relacionado con la historia \n");
				pause();
				clear();
			}
			doublePrintLn(Menu.msgEnemigosSeAcercan());
			do {
				repetirCombate = false;
				boolean isAnyPersonajeVivo = GestorEntidad.isAnyPersonajeVivo();
				boolean isAnyEnemigoVivo = GestorEntidad.isAnyEnemigoVivo();

				if (isAnyEnemigoVivo && isAnyPersonajeVivo) {
					clear();
					ejecutaTurnos();
					repetirCombate = true;
				} else if (isAnyPersonajeVivo) {
					doublePrintLn(Menu.msgEnemigoDerrotado());
				} else if (isAnyEnemigoVivo) {
					throw new JuegoException(Menu.msgJuegoPerdido());
				}
			} while (repetirCombate);
		}
	}

	//TODO planificar turnos

	private static void ejecutaTurnos() {
		int arrPersonajeSize = GestorEntidad.getSizePersonaje();
		int arrEnemigoSize = GestorEntidad.getSizeEnemigo();

		for (int i = 0; i < arrPersonajeSize; i++) {
			Personaje p = GestorEntidad.getPersonajePorID(i);
			if (p != null && p.isEstado()) {
				clear();
				accionesTurno(p);
				pause();
			}
		}

		/*
		 * for (int i = 0; i < arrEnemigoSize; i++) { // todo turno enemigo }
		 */
	}

	private static void accionesTurno(Personaje p) {
		boolean repetirTurno;
		do {
			doublePrintLn(String.format(Menu.msgTurno(),p.getNombre()));
			doublePrintLn(Menu.menuAcciones() + "\n");
			int accion = pideNumero(1, 5, Menu.pideAccion());
			try {
				repetirTurno = ejecutaAcciones(accion, p);
			} catch (JuegoException e) {
				doublePrintLn(e.getMessage());
				pause();
				repetirTurno = true;
			}
			if (repetirTurno) {
				clear();
			}
		} while (repetirTurno);
	}

	private static boolean ejecutaAcciones(int accion, Personaje p) {
		return switch (accion) {
			case 1 -> accionFisica(p); // Accion fisica
			case 2 -> accionMagica(p); // Accion magica
			case 3 -> accionVarios(); //varios
			case 4 -> accionBloquear(p); // bloquear
			default -> accionInventario(p); // inventario
		};
	}

	private static boolean accionFisica(Personaje p) {
		doublePrintLn(Menu.menuAccionesFisico() + "\n");
		int accionF = pideNumero(1, 4, Menu.pideAccion());
		if (accionF == 4) {
			return true;
		}
		eligeAtaqueFisico(accionF, p.getAd(), p.getAgl());
		return false;
	}

	private static boolean accionMagica(Personaje p) {
		doublePrintLn(Menu.menuAccionesMagico() + "\n");
		int accionM = pideNumero(1, 4, Menu.pideAccion());
		if (accionM == 4) {
			return true;
		}
		eligeAtaqueMagico(accionM, p.getAp(), p.getAgl());
		return false;
	}

	private static void eligeAtaqueFisico(int accionF, int ad, int agl) throws JuegoException {
		switch (accionF) {
			case 1 -> ejecutaAtaque(GestorNoEntidad.getAtaquePorID("af_ad_1"), ad, agl);
			case 2 -> ejecutaAtaque(GestorNoEntidad.getAtaquePorID("gg_ad_2"), ad, agl);
			case 3 -> ejecutaAtaque(GestorNoEntidad.getAtaquePorID("bf_ad_3"), ad, agl);
		}
	}

	private static void eligeAtaqueMagico(int opcion, int ap, int agl) throws JuegoException {
		switch (opcion) {
			case 1 -> ejecutaAtaque(GestorNoEntidad.getAtaquePorID("as_ap_1"), ap, agl);
			case 2 -> ejecutaAtaque(GestorNoEntidad.getAtaquePorID("gc_ap_2"), ap, agl);
			case 3 -> ejecutaAtaque(GestorNoEntidad.getAtaquePorID("tm_ap_2"), ap, agl);
		}
	}

	private static void ejecutaAtaque(Ataque at, int stat, int agl) throws JuegoException {
		if (at == null) {
			throw new JuegoException(Menu.errorInesperado());
		}
		if (!at.isAttackUsable(stat)) {
			throw new JuegoException(Menu.errorAtaqueNoDisponible());
		}

		int dmg = at.calculateDamage(stat);
		boolean isCritico = Ataque.isCritico(agl);
		if (isCritico) {
			dmg *= 1.4;
		}

		Enemigo e = pideEnemigo();
		dmg -= at.getStat().equalsIgnoreCase("ad") ? Ataque.calcularReduccionDmgArmadura(e.getAr())
				: Ataque.calcularReduccionDmgMagico(e.getMr());

		boolean isBloqueo = e.isBloqueo();

		if (isBloqueo) {
			dmg /= 2;
		}

		boolean isVivo = e.recibirDmg(dmg);

		String strInfo = buildInfoString(isCritico, isVivo, isBloqueo, dmg, e.getNombre());
		doublePrintLn(strInfo);
	}

	private static String buildInfoString(boolean isCritical, boolean isAlive,
	                                      boolean isBloqueo, int dmg, String name) {
		StringBuilder sb = new StringBuilder();
		if (isCritical)
			sb.append("Ataque critico! ");
		if (isBloqueo) {
			sb.append("El enemigo estaba bloqueando y ha recibido %d de daño.".formatted(dmg));
		} else {
			sb.append("Has conseguido hacer %d pts de daño. ".formatted(dmg));
		}
		if (!isAlive)
			sb.append("%s ha muerto".formatted(name));
		sb.append("\n");
		return sb.toString();
	}

	private static boolean accionVarios() {
		doublePrintLn(Menu.menuAccionesOtros() + "\n");
		int accionV = pideNumero(1, 5, Menu.pideAccion());
		switch (accionV) {
			case 1 -> accionVariosEstadisticas(); // stats personajes y enemigos
			case 2 -> accionVariosAyudaJuego(); // info game
			case 3 -> accionVariosAyudaAtaques(); // info attacks
			case 4 -> salirJuego(); // exitgame
			// volver: no hay opcion porque volvera automaticamente.
		}
		return true;
	}

	private static void accionVariosEstadisticas() {
		clear();
		doublePrintLn(Menu.muestraStats());
		pause();
	}

	private static void accionVariosAyudaJuego() {
		clear();
		doublePrintLn(Menu.muestraAyudaJuego());
		pause();
	}

	private static void accionVariosAyudaAtaques() {
		clear();
		doublePrintLn(Menu.muestraAyudaAtaques());
		pause();
	}

	private static boolean accionBloquear(Personaje p) {
		if (p.isBloqueo()) {
			throw new JuegoException(Menu.errorBloqueoYaActivo());
		}
		doublePrintLn(Menu.msgBloqueo()); //mover esto a ayuda o dejar permanente???
		boolean confirmacion = pideConfirmacion();
		if (confirmacion) {
			p.activarBloqueo();
			doublePrintLn(Menu.confimaBloqueo().formatted(p.getNombre()));
		}
		return !confirmacion;
	}

	private static boolean accionInventario(Personaje p) {
		doublePrintLn("INV wip");
		return false;
	}

	//TODO planificar turnos

	/**
	 * Metodo salirJuego
	 * <hr/>
	 * Pide confirmacion y sale del programa.
	 */
	private static void salirJuego() {
		if (pideConfirmacion()) {
			doublePrintLn(Menu.msgSalirJuego());
			System.exit(0);
		}
	}

	/**
	 * Metodo muestraHistoria
	 * <hr/>
	 * imprime la historia del juego y pausa la ejecucion.
	 */
	private static void muestraHistoria() {
		doublePrintLn(Menu.muestraHistoriaJuego());
		pause();
	}

	/**
	 * Metodo juegoCompletado
	 * <hr/>
	 * <p>
	 *      Mostrara el nuevo menu Principal, al terminar el juego, con la opcion
	 *      extra de poder empezar nueva partida+.
	 * </p>
	 * <p>
	 *     Si 0 (empezar de nuevo el juego) es introducido, pedira confirmacion. y si se introduce que no,
	 *     el bucle se repetira sin que ocurra nada.
	 * </p>
	 * @return 0, 1 o 5. 0 significa empezar de nuevo, 1 empezar nueva partida +, y 5 es salir del juego.
	 */
	private static int juegoCompletado() {
		clear();
		int opcion;
		do {
			doublePrintLn(Menu.menuInicialSecundario(contadorPartidas));
			opcion = pideNumero(0, 5, Menu.pideAccion());
			if ((opcion == 0) && (!pideConfirmacion())) {
				opcion = Integer.MIN_VALUE;
			}
			realizaAccionMenuPrincipal(opcion);
		} while (opcion != 0 && opcion != 5 && opcion != 1);
		return opcion;
	}

	private static void resetJuego() {
		GestorEntidad.resetArrays();
		contadorPartidas = 0;
	}

	// Utilidades --------------

	private static Enemigo pideEnemigo() {
		boolean repetir;
		Enemigo e;
		do {
			repetir = false;
			int op = pideNumero(1, GestorEntidad.getSizeEnemigo(), Menu.pideEnemigo());
			e = GestorEntidad.getEnemigoPorID(op - 1);
			if (e == null || !e.isEstado()) {
				printLn(Menu.enemigoElegidoNoDisponible());
				repetir = true;
			}
		} while (repetir);
		return e;
	}

	private static String pideCadena() {
		Scanner sc = new Scanner(System.in);
		print(" -> ");
		return sc.nextLine().trim();
	}

	private static int pideNumero() throws JuegoException {
		try {
			return Integer.parseInt(pideCadena());
		} catch (Exception e) {
			throw new JuegoException(Menu.errorNumeroNoValido());
		}
	}

	private static int pideNumero(int min, int max, String msg) {
		int num = 0;
		boolean repetir;

		do {
			print(msg + String.format(" [%d-%d]", min, max));
			try {
				num = pideNumero();
				repetir = (num > max || num < min);
				if (repetir)
					printLn(Menu.errorNumeroNoValido());
			} catch (JuegoException e) {
				printLn(e.getMessage());
				repetir = true;
			}
		} while (repetir);

		return num;
	}

	private static boolean pideConfirmacion() {
		print(Menu.pideConfirmacion());
		String text = pideCadena();
		return text.equalsIgnoreCase("1") ||
				text.equalsIgnoreCase("si") ||
				text.equalsIgnoreCase("yes") ||
				text.equalsIgnoreCase("y") ||
				text.equalsIgnoreCase("s");
	}

	private static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (Exception ignored) {
		}
	}

	private static void pause() {
		print(Menu.msgPausa());
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}

	public static int generarRandomNum(double n) {
		return (int) (Math.random() * n) + 1;
	}

	// metodos print -----------

	private static void print(String txt) {
		System.out.printf("%s", txt);
	}

	private static void printLn(String txt) {
		System.out.printf("%s%n", txt);
	}

	private static void doublePrintLn(String txt) {
		System.out.printf("%n%s%n", txt);
	}
}
