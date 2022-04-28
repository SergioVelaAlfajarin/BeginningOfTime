package window;

import exception.*;
import gestores.*;
import modelo.entidades.*;
import modelo.noEntidades.*;

import java.util.ArrayList;
import java.util.Scanner;

public final class Principal {
	//Mover main aqui, haciendo los metodos testeables
	private static final int MAX_COMBATES = 10;
	private static int contadorPartidas = 0;

	public static void main(String[] args) {
		try{
			boolean repetir;
			do {
				contadorPartidas++;
				introduceOpcionInicioJuego();
				//rellenarArrays();
				//muestraHistoria();
				//empiezaJuego();
				repetir = /*juegoCompletado();*/ true;
				pause();
			} while (repetir);
		}catch(JuegoException e){
			printLn(e.getMessage());
		}
	}

	private static void introduceOpcionInicioJuego() throws JuegoException {
		boolean hayError = false;
		String error = "";
		do{
			clear();
			doublePrintLn(Menu.MENU_PRINCIPAL);

			if(hayError)
				doublePrintLn(error);

			int numero = pideNumero(Menu.PIDE_ACCION);

			if(numero <= 0 || numero >= 6){
				error = Menu.ERROR_NUMERO_NO_VALIDO;
				hayError = true;
			}else{
				realizaAccionMenuPrincipal(numero);
				hayError = false;
				error = "";
			}
		}while(hayError);
	}

	private static void realizaAccionMenuPrincipal(int num) throws JuegoException{
		clear();
		if (num == 0) {
			resetJuego();
		} else if (num == 2) {
			doublePrintLn(Menu.MENU_EXTRAS);
			pause();
		} else if (num == 3) {
			doublePrintLn(Menu.MENU_CHANGELOG);
			pause();
		} else if (num == 4) {
			doublePrintLn(Menu.MENU_CAMBIAR_IDIOMA);
			pause();
		}
		clear();
	}

	/**
	 * Metodo resetJuego
	 * <hr/>
	 * <p>
	 *     Reinicia los arrays del juego y resetea el contador de partidas.
	 * </p>
	 */
	private static void resetJuego() {
		GestorEntidad.resetArrays();
		contadorPartidas = 0;
	}

	//TODO usar clase menu para los textos (metodo temporal)
	static String buildInfoString(
			String name, int dmg, boolean isCritical, boolean isBloqueo, boolean isVivo
	) {

		StringBuilder sb = new StringBuilder();
		if (isBloqueo)
			sb.append("El enemigo estaba bloqueando.\n");

		if (isCritical)
			sb.append("Ataque critico! ");

		sb.append(String.format("Has conseguido hacer %d pts de daño.\n",dmg));

		if (!isVivo)
			sb.append(String.format("%s ha muerto.\n",name));

		return sb.toString();
	}

	static boolean accionBloquear(Personaje p) throws JuegoException{
		if (p.isBloqueo()) {
			throw new JuegoException(Menu.ERROR_BLOQUEO_YA_ACTIVO);
		}
		doublePrintLn(Menu.MSG_BLOQUEO); //mover esto a ayuda o dejar permanente???
		boolean confirmacion = pideConfirmacion();
		if (confirmacion) {
			p.activarBloqueo();
			doublePrintLn(String.format(Menu.MSG_CONFIRMACION_BLOQUEO,p.getNombre()));
		}
		return !confirmacion;
	}

	// Utilidades --------------

	private static Enemigo pideEnemigo() {
		boolean repetir;
		Enemigo e;
		do {
			repetir = false;
			int op = pideNumero(/*1, GestorEntidad.getSizeEnemigo(), */Menu.PIDE_ENEMIGO);
			e = GestorEntidad.getEnemigoPorID(op - 1);
			if (e == null || !e.isEstado()) {
				printLn(Menu.MSG_ENEMIGO_NO_DISPONIBLE);
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


	private static int pideNumero(String cadena) throws JuegoException{
		try {
			print("\n" + cadena);
			return Integer.parseInt(pideCadena());
		} catch (Exception e) {
			throw new JuegoException(Menu.ERROR_NUMERO_NO_VALIDO);
		}
	}

	private static boolean pideConfirmacion() {
		print("\n" + Menu.PIDE_CONFIRMACION);
		String text = pideCadena();
		return text.equalsIgnoreCase("1")       ||
				text.equalsIgnoreCase("si")     ||
				text.equalsIgnoreCase("yes")    ||
				text.equalsIgnoreCase("y")      ||
				text.equalsIgnoreCase("s");
	}

	private static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				new ProcessBuilder().command("clear");
		} catch (Exception ignored) {
		}
	}

	private static void pause() {
		print("\n" + Menu.MSG_PAUSA);
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
