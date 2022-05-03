package window;

import exception.*;
import gestores.*;
import modelo.entidades.*;
import modelo.noEntidades.*;

import java.util.Scanner;

public final class Principal {
	//Mover main aqui, haciendo los metodos testeables
	private static final int MAX_COMBATES = 10;
	private static int contadorPartidas = 0;

	public static void main(String[] args) {
		temp2();
	}

	private static void temp2(){
		try{
			boolean repetir;
			do {
				contadorPartidas++;
				introduceOpcionInicioJuego();
				rellenarArrays();
				//muestraHistoria();
				//empiezaJuego();
				repetir = /*juegoCompletado();*/ true;
				System.out.println("aqui ira el juego");
				pause();
			} while (repetir);
		}catch(JuegoException e){
			clear();
			doublePrintLn(e.getMessage());
		}
	}

	/**
	 * Pide opcion y actua en consecuencia. lanza una excepcion si la opcion elegida es salir del juego.
	 * @throws JuegoException
	 */
	private static void introduceOpcionInicioJuego() throws JuegoException {
		int opcion = muestraOpcionesYPideNumero(1, 5, Menu.MENU_PRINCIPAL);
		clear();
		switch(opcion){
			case 2 -> doublePrintLn(Menu.MENU_EXTRAS);
			case 3 -> doublePrintLn(Menu.MENU_CHANGELOG);
			case 4 -> doublePrintLn(Menu.MENU_CAMBIAR_IDIOMA);
			case 5 -> throw new JuegoException("Gracias por jugar!");
		}
		pause();
		clear();
	}

	private static void rellenarArrays() {
		rellenaArrayEnemigo();
		if(GestorEntidad.isArrayPersonajeVacio())
			rellenaArrayPersonaje();
		if(GestorNoEntidad.isArrayEquipoVacio())
			rellenaArrayEquipo();
		if(GestorNoEntidad.isArrayItemVacio())
			rellenarArrayItem();
		if(GestorNoEntidad.isArrayAtaqueVacio())
			rellenarArrayAtaque();
	}

	private static void rellenarArrayAtaque() {
		GestorNoEntidad.addAtaque(new Ataque("af_ad_1"));
		GestorNoEntidad.addAtaque(new Ataque("gg_ad_2"));
		GestorNoEntidad.addAtaque(new Ataque("bf_ad_3"));
		GestorNoEntidad.addAtaque(new Ataque("as_ap_1"));
		GestorNoEntidad.addAtaque(new Ataque("gc_ap_2"));
		GestorNoEntidad.addAtaque(new Ataque("tm_ap_2"));
	}

	private static void rellenarArrayItem() {

	}

	private static void rellenaArrayEquipo() {

	}

	private static void rellenaArrayEnemigo() {

	}
			//TODO rellenar arrays
	private static void rellenaArrayPersonaje() {

	}

	private static int muestraOpcionesYPideNumero(int min, int max, String menu){
		String error = "";
		int numero;

		while(true){
			clear();
			doublePrintLn(menu);

			if(!error.isEmpty())
				doublePrintLn(error);

			try{
				numero = pideNumero(Menu.PIDE_ACCION);

				if(numero < min || numero > max)
					error = Menu.ERROR_NUMERO_NO_VALIDO;
				else
					return numero;
			}catch (JuegoException e){
				error = e.getMessage();
			}
		}
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


	private static void salirJuego() throws JuegoException {
		if(pideConfirmacion()){
			throw new JuegoException("Gracias por jugar!");
		}
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


/*	private static Enemigo pideEnemigo() {
		boolean repetir;
		Enemigo e;
		do {
			repetir = false;
			int op = pideNumero(Menu.PIDE_ENEMIGO);
			e = GestorEntidad.getEnemigoPorID(op - 1);
			if (e == null || !e.isEstado()) {
				printLn(Menu.MSG_ENEMIGO_NO_DISPONIBLE);
				repetir = true;
			}
		} while (repetir);
		return e;
	}*/

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
