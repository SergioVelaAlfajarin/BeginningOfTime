package sva.tbot.juego;

import sva.tbot.exception.*;
import sva.tbot.gestores.*;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import java.util.Scanner;

public final class Principal {
	//Mover main aqui, haciendo los metodos testeables
	private static final int MAX_COMBATES = 10;
	private static int contadorPartidas = 0;

	public static void main(String[] args) {
		//extracted();
		System.out.println(Menu.menuCambiaIdioma());
	}

	private static void extracted() {
		try{
			boolean repetir;
			do {
				contadorPartidas++;
				introduceOpcionInicioJuego();
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

	private static void introduceOpcionInicioJuego() throws JuegoException {
		int opcion = muestraOpcionesYPideNumero(1, 5, Menu.menuPrincipal(0));
		clear();
		switch(opcion){
			case 2 -> menuExtras();
			case 3 -> menuChangelog();
			case 4 -> menuCambiaIdioma();
			case 5 -> throw new JuegoException("Gracias por jugar!");
		}
	}

	private static void menuExtras() {
		doublePrintLn(Menu.menuExtras());
		pause();
		clear();
	}

	private static void menuChangelog(){
		doublePrintLn(Menu.menuChangeLog());
		pause();
		clear();
	}

	private static void menuCambiaIdioma(){
		doublePrintLn(Menu.menuCambiaIdioma());
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
				numero = pideNumero(Menu.pideAccion());

				if(numero < min || numero > max)
					error = Menu.errorNumeroNoValido();
				else
					return numero;
			}catch (JuegoException e){
				error = e.getMessage();
			}
		}
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
		/*if (p.isBloqueo()) {
			throw new JuegoException(Menu.ERROR_BLOQUEO_YA_ACTIVO);
		}
		doublePrintLn(Menu.MSG_BLOQUEO); //mover esto a ayuda o dejar permanente???
		boolean confirmacion = pideConfirmacion();
		if (confirmacion) {
			p.activarBloqueo();
			doublePrintLn(String.format(Menu.MSG_CONFIRMACION_BLOQUEO,p.getNombre()));
		}
		return !confirmacion;*/
		return false;
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
			throw new JuegoException(Menu.errorNumeroNoValido());
		}
	}

	private static boolean pideConfirmacion() {
		print("\n" + Menu.pideConfirmacion());
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
		print("\n" + Menu.msgPausa());
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
