package window;


import exception.JuegoException;
import gestores.GestorEntidad;
import gestores.GestorNoEntidad;
import objects.entidades.Personaje;
import objects.entidades.Enemigo;
import objects.noEntidades.Ataque;

import java.util.Scanner;

public class Main {
	private static final int MAX_COMBATES = 8;
	private static int contadorPartidas = 0;

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
				printLn(e.getMessage());
			}
		}
	}

	private static int menuInicial() {
		int numero;
		clear();
		do {
			doublePrintLn(Menu.menuPrincipal());
			numero = pideNumero(1, 5, Menu.pideAccion());
			realizaAccionMenuPrincipal(numero);
		} while (numero != 1 && numero != 5);
		return numero;
	}

	private static void realizaAccionMenuPrincipal(int num) {
		clear();
		if (num == 0) {
			resetJuego();
		} else if (num == 2) {
			printLn(Menu.menuCreditos());
			pause();
			clear();
		} else if (num == 3) {
			printLn(Menu.menuChangeLog());
			pause();
			clear();
		} else if (num == 4) {
			printLn(Menu.changeLangMenu());
			pause();
			clear();
		} else if (num == 5) {
			doublePrintLn(Menu.msgSalirJuego());
		}
	}

	private static void rellenarArrays() {
		clear();

		if (GestorEntidad.isListaPersonajesVacio()) {
			for (int i = 0; i < Personaje.MAX_PERSONAJES; i++) {
				buildPersonaje(i);
				clear();
			}
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

	private static void buildPersonaje(int i) {
		boolean repetir;
		int claseElegida;
		String nombreElegido = "";
		do {
			muestraMsgPersonajeCreado(i);
			doublePrintLn(Menu.menuClases() + "\n");
			claseElegida = pideNumero(0, 5, Menu.pideClases());
			if (claseElegida == 0) {
				clear();
				printLn(Menu.muestraAyudaClases() + "\n");
				pause();
				clear();
				repetir = true;
			} else {
				print(Menu.pideNombre());
				nombreElegido = pideCadena();
				repetir = false;
			}
		} while (repetir);

		crearPersonaje(claseElegida, nombreElegido);
	}

	private static void muestraMsgPersonajeCreado(int i) {
		if (GestorEntidad.isListaPersonajesVacio()) {
			printLn(Menu.msgBienvenida());
		} else {
			int ultPersonajeCreado = i - 1;
			Personaje p = GestorEntidad.getPersonajePorID(ultPersonajeCreado);
			if (p != null) {
				printLn(Menu.msgPersonajeCreado().formatted(
						i, Personaje.MAX_PERSONAJES, p.getNombre())
				);
			}
		}
	}

	private static void crearPersonaje(int clase, String nombre) {
		checkEasterEggs(nombre);
		GestorEntidad.addPersonaje(new Personaje(nombre, clase));
	}

	private static void checkEasterEggs(String nombre) {
		// pinga, jose, david, shiku, wakala, uwu ->(owo,nwn), peko,
		// misteroliva, rickroll, a, fnaf -> david bisbal(Ataque fisico-> ataque
		// giratorio)
	}

	private static void empiezaJuego() throws JuegoException {
		clear();
		for (int i = 1; i <= MAX_COMBATES; i++) {
			boolean repetirCombate;
			if (i == 5) {
				clear();
				doublePrintLn("aquí pasara algo");
				pause();
				clear();
			}
			doublePrintLn(Menu.msgEnemigosSeAcercan());
			do {
				repetirCombate = false;
				boolean isAnyPersonajeVivo = GestorEntidad.isAnyPersonajeVivo();
				boolean isAnyEnemigoVivo = GestorEntidad.isAnyEnemigoVivo();

				if (isAnyEnemigoVivo && isAnyPersonajeVivo) {
					ejecutaTurnos();
					repetirCombate = true;
				} else if (isAnyPersonajeVivo) {
					doublePrintLn(Menu.msgEnemigoDerrotado());
				} else if (isAnyEnemigoVivo) {
					throw new JuegoException(Menu.msgJuegoPerdido());
				}
			} while (repetirCombate);

			//crear nuevos enemigos
		}
	}

	//TODO planificar turnos

	private static void ejecutaTurnos() {
		int arrPersonajeSize = GestorEntidad.getSizePersonaje();
		int arrEnemigoSize = GestorEntidad.getSizeEnemigo();

		for (int i = 0; i < arrPersonajeSize; i++) {
			Personaje p = GestorEntidad.getPersonajePorID(i);
			if (p != null && p.isEstado()) {
				accionesTurno(p);
			}
		}

		for (int i = 0; i < arrEnemigoSize; i++) {
			//todo turno enemigo
		}

	}

	private static void accionesTurno(Personaje p) {

	}

	private static void accionFisica(int opcion, Personaje p) throws JuegoException {
		switch (opcion) {
			case 1 -> ejecutaAtaque(
					GestorNoEntidad.getAtaquePorID("af_ad_1"),
					p.getAd(),
					p.getAgl()
			);
			case 2 -> ejecutaAtaque(
					GestorNoEntidad.getAtaquePorID("gg_ad_2"),
					p.getAd(),
					p.getAgl()
			);
			case 3 -> ejecutaAtaque(
					GestorNoEntidad.getAtaquePorID("bf_ad_3"),
					p.getAd(),
					p.getAgl()
			);
		}
	}

	private static void acccionMagico(int opcion, Personaje p) throws JuegoException {
		switch (opcion) {
			case 1 -> ejecutaAtaque(
					GestorNoEntidad.getAtaquePorID("as_ap_1"),
					p.getAp(),
					p.getAgl()
			);
			case 2 -> ejecutaAtaque(
					GestorNoEntidad.getAtaquePorID("gc_ap_2"),
					p.getAp(),
					p.getAgl()
			);
			case 3 -> ejecutaAtaque(
					GestorNoEntidad.getAtaquePorID("tm_ap_2"),
					p.getAp(),
					p.getAgl()
			);
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
		dmg -= at.getStat().equalsIgnoreCase("ad") ?
				Ataque.calcularReduccionDmgArmadura(e.getAr()) :
				Ataque.calcularReduccionDmgMagico(e.getMr());

		boolean isVivo = e.recibirDmg(dmg);

		String strInfo = buildInfoString(isCritico, isVivo, dmg, e.getNombre());
		doublePrintLn(strInfo);
	}

	private static String buildInfoString(boolean isCritical, boolean isAlive, int dmg, String name) {
		StringBuilder sb = new StringBuilder();
		if (isCritical)
			sb.append("Ataque critico! ");
		sb.append("Has conseguido hacer %d pts de daño. ".formatted(dmg));
		if (!isAlive)
			sb.append("%s ha muerto".formatted(name));
		sb.append("\n");
		return sb.toString();
	}

	//TODO planificar turnos

	private static void salirJuego() {
		if (pideConfirmacion(Menu.pideConfirmacion())) {
			System.exit(0);
		}
	}

	private static void muestraHistoria() {
		doublePrintLn(Menu.muestraHistoriaJuego());
		pause();
	}

	private static int juegoCompletado() {
		clear();
		int opcion;
		do {
			doublePrintLn(Menu.menuInicialSecundario(contadorPartidas));
			opcion = pideNumero(0, 5, Menu.pideAccion());
			//si 0 es introducido y luego se introduce que no, el bucle se repetira.
			if ((opcion == 0) && (!pideConfirmacion(Menu.pideConfirmacion()))) {
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

	private static boolean pideConfirmacion(String msgConfirmation) {
		print(msgConfirmation);
		String text = pideCadena();
		return text.equalsIgnoreCase("1");
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

	private static void print(String txt) {
		System.out.printf("%s", txt);
	}

	private static void printLn() {
		System.out.printf("%n");
	}

	private static void printLn(String txt) {
		System.out.printf("%s%n", txt);
	}

	private static void doublePrintLn() {
		System.out.printf("%n%n");
	}

	private static void doublePrintLn(String txt) {
		System.out.printf("%n%s%n", txt);
	}
}
