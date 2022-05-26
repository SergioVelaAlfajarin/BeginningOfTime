package sva.tbot.juego;

import sva.tbot.exception.*;
import sva.tbot.gestores.*;
import sva.tbot.modelo.entidades.TiposClase;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class Principal {
	/*
	RUTAS:
		IDE: src/main/resources/*file*.txt
		BAT: *file*.txt
		JAR: resources/*file*.txt
	 */
	private static final int MAX_COMBATES = 10;
	private static Menu menus;
	private static int contadorPartidas = 0;

	public static void main(String[] args) throws IOException {
		try{
			menus = Menu.getMenu();

			boolean repetir;
			do {
				if(introduceOpcionInicioJuego()){
					//TODO juego
					repetir = true;
					contadorPartidas++;
				}else{
					System.out.println(menus.msgDespedida());
					repetir = false;
				}
			} while (repetir);
		}catch(JuegoException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// metodos ---------------

	public static boolean introduceOpcionInicioJuego() throws JuegoException {
		int opcion;
		do{
			opcion = muestraOpcionesYPideNumero(1, 5, menus.menuPrincipal(0));
			clear();
			switch(opcion){
				case 2 -> menuExtras();
				case 3 -> menuChangelog();
				case 4 -> menuCambiaIdioma();
			}
		}while(opcion != 1 && opcion != 5);
		return opcion == 1;
	}

	public static void menuExtras() {
		doublePrintLn(menus.menuExtras());
		pause();
		clear();
	}

	public static void menuChangelog(){
		doublePrintLn(menus.menuChangeLog());
		pause();
		clear();
	}

	public static void menuCambiaIdioma(){
		int opcion = muestraOpcionesYPideNumero(1,menus.getCantidadIdiomas(),menus.menuCambiaIdioma());
		menus.cambiarIdioma(opcion);
		doublePrintLn(menus.confirmaCambioIdioma());
		pause();
		clear();
	}

	public static int muestraOpcionesYPideNumero(int min, int max, String menu){
		String error = "";
		int numero;

		while(true){
			clear();
			doublePrintLn(menu);

			if(!error.isEmpty())
				doublePrintLn(error);

			try{
				numero = pideNumero(menus.pideAccion());

				if(numero < min || numero > max)
					error = menus.errorNumeroNoValido();
				else
					return numero;
			}catch (JuegoException e){
				error = e.getMessage();
			}
		}
	}

	public static void salirJuego() throws JuegoException {
		if(pideConfirmacion()){
			throw new JuegoException("Gracias por jugar!");
		}
	}

	public static String buildInfoString(String name, int dmg, boolean isCritical, boolean isBloqueo, boolean isVivo) {
		//TODO usar clase menu para los textos (metodo temporal)
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

	public static boolean accionBloquear(Personaje p) throws JuegoException{
		/*if (p.isBloqueo()) {
			throw new JuegoException(menus.ERROR_BLOQUEO_YA_ACTIVO);
		}
		doublePrintLn(menus.MSG_BLOQUEO); //mover esto a ayuda o dejar permanente???
		boolean confirmacion = pideConfirmacion();
		if (confirmacion) {
			p.activarBloqueo();
			doublePrintLn(String.format(menus.MSG_CONFIRMACION_BLOQUEO,p.getNombre()));
		}
		return !confirmacion;*/
		return false;
	}

	// Utilidades --------------

	public static String pideCadena() {
		Scanner sc = new Scanner(System.in);
		print(" -> ");
		return sc.nextLine().trim();
	}

	public static int pideNumero(String cadena) throws JuegoException{
		try {
			print("\n" + cadena);
			return Integer.parseInt(pideCadena());
		} catch (Exception e) {
			throw new JuegoException(menus.errorNumeroNoValido());
		}
	}

	public static boolean pideConfirmacion() {
		print("\n" + menus.pideConfirmacion());
		String text = pideCadena();
		return text.equalsIgnoreCase("1")       ||
				text.equalsIgnoreCase("si")     ||
				text.equalsIgnoreCase("yes")    ||
				text.equalsIgnoreCase("y")      ||
				text.equalsIgnoreCase("s");
	}

	public static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				new ProcessBuilder().command("clear");
		} catch (Exception ignored) {
		}
	}

	public static void pause() {
		print("\n" + menus.msgPausa());
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}

	public static int generarRandomNum(double n) {
		return (int) (Math.random() * n) + 1;
	}

	// metodos print -----------

	public static void print(String txt) {
		System.out.printf("%s", txt);
	}

	public static void printLn(String txt) {
		System.out.printf("%s%n", txt);
	}

	public static void doublePrintLn(String txt) {
		System.out.printf("%n%s%n", txt);
	}
}
