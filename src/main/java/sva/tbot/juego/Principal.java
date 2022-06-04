package sva.tbot.juego;

import sva.tbot.exception.*;
import sva.tbot.gestores.*;
import sva.tbot.modelo.entidades.TiposClase;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


public final class Principal {
	/**
	 * MAX_COMBATES:
	 * Se peleara contra cada enemigo 2 veces por nivel del enemigo.
	 * Esto quiere decir que habiendo 10 niveles enemigos,
	 * habra 20 combates. (menos uno en el boss final)
	 */
	private static final int MAX_COMBATES = 20;
	private static int contadorPartidas = 0;
	private static Menu menus;

	public static void main(String[] args) {
		try{
			menus = Menu.getMenu();
			boolean repetir;
			do {
				if(introduceOpcionInicioJuego()){
					creaPersonajes();
					ejecutaJuego();
					repetir = true;
					contadorPartidas++;
				}else{
					printLn(menus.msgDespedida());
					repetir = false;
				}
			} while (repetir);
		}catch(JuegoException e){
			doublePrintLn(e.getMessage());
			pause();
			System.exit(1);
		}
	}

	// metodos ---------------

	public static boolean introduceOpcionInicioJuego() throws JuegoException {
		int opcion;
		do{
			if(contadorPartidas == 0){
				opcion = muestraOpcionesYPideNumero(1, 5, menus.menuPrincipal(0));
			}else{
				opcion = muestraOpcionesYPideNumero(0, 5, menus.menuPrincipal(contadorPartidas));
			}
			clear();
			switch(opcion){
				case 0 -> System.out.println("restarting match"); //TODO
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
		int opcion = muestraOpcionesYPideNumero(0,menus.getCantidadIdiomas(),menus.menuCambiaIdioma());
		if(opcion != 0){
			menus.cambiarIdioma(opcion);
			doublePrintLn(menus.confirmaCambioIdioma());
			pause();
		}
		clear();
	}

	private static void muestraAyudaClases() {
		clear();
		doublePrintLn(menus.muestraAyudaClases());
		pause();
	}

	public static void creaPersonajes() {
		ArrayList<Personaje> p = new ArrayList<>();
		for(int i = 0; i< Personaje.MAX_PERSONAJES;i++){
			boolean repetir;
			do{
				clear();
				String menu = muestraMsgPersonajeCreado(p) + menus.menuClases();
				int claseElegida = muestraOpcionesYPideNumero(0,5, menu);
				repetir = claseElegida == 0;
				if(!repetir){
					if(i == 0){
						p.add(new Personaje(Personaje.NOMBRE_PERSONAJE_PRINCIPAL, claseElegida));
					}else{
						String nombreElegido = pideNombre();
						p.add(new Personaje(nombreElegido,claseElegida));
					}
				}else{
					muestraAyudaClases();
				}
			}while(repetir);
		}
		Personaje[] arrp = new Personaje[p.size()];
		arrp = p.toArray(arrp);
		ListasEntidad.personajeList().initLista(arrp);
	}

	public static String pideNombre() {
		do {
			print(menus.pideNombre());
			String nombre = pideCadena();
			if (nombre.length() <= 2) {
				printLn(menus.errorNombreNoValido());
			}else{
				return nombre;
			}
		} while (true);
	}

	public static String muestraMsgPersonajeCreado(ArrayList<Personaje> list) {
		ListIterator<Personaje> it = list.listIterator();
		//si el array esta vacio devolvera esto
		if(!it.hasNext()){
			return menus.msgPideClasePrincipal()
					+ Menu.SALTO_LINEA
					+ Menu.SALTO_LINEA;
		}
		//si no lo esta, devolvera informacion sobre el ultimo en el mismo.
		Personaje p = it.next();
		while(it.hasNext()) {
			p = it.next();
		}
		return String.format(
				menus.msgPersonajeCreado() +
				"%n" +
				menus.msgCreacionClase() +
				"%n%n",
				p.getNombre(),
				list.size(),
				Personaje.MAX_PERSONAJES);
	}

	private static void ejecutaJuego() {
		clear();
		doublePrintLn(menus.msgBienvenida());

		for (int i = 2; i <= MAX_COMBATES; i+=2) {
			actualizaListaEnemigos(i);
			for (int j = 0; j < calculaNumeroEncuentros(i); j++) {
				ejecutaCombate();
			}
		}
	}
	
	private static int calculaNumeroEncuentros(int i){
		if(i == 20){
			return 1;
		}
		return 2;
	}

	private static void actualizaListaEnemigos(int i) {
		int cantidad = generarRandomNumRango(Enemigo.MIN_ENEMIGOS, Enemigo.MAX_ENEMIGOS);
		switch (i) {
			case 2 -> generaYAgregaEnemigos(cantidad, TiposClase.LOBO);
			case 8 -> generaYAgregaEnemigos(cantidad, TiposClase.OSO);
			case 14 -> generaYAgregaEnemigos(cantidad, TiposClase.SERPIENTE);
			case 20 -> generaYAgregaEnemigos(1, TiposClase.DRAGON);
			default -> subirNivelEnemigos(i);
		}
		String msg = String.format(menus.msgEnemigosAcercan(),
				ListasEntidad.enemigoList().getSize(),
				ListasEntidad.enemigoList().get(0).getLvl()
		);
		doublePrintLn(msg);
		pause();
		clear();
	}

	private static void generaYAgregaEnemigos(int cantidad, TiposClase tipo){
		Enemigo[] lista = new Enemigo[cantidad];
		for (int i = 0; i < cantidad; i++) {
			lista[i] = new Enemigo(tipo, i + 1);
		}
		ListasEntidad.enemigoList().initLista(lista);
	}

	//TODO estos metodos vvvvvvvvvv

	private static void subirNivelEnemigos(int i) {

	}

	private static void ejecutaCombate() {
		boolean repetirCombate;
		do{
			repetirCombate = false;
			//ejecuta los turnos
			for(Personaje p: ListasEntidad.personajeList().lista()){
				if(p.isEstado()){
					turnoPersonaje(p);
				}
			}
			for(Enemigo e: ListasEntidad.enemigoList().lista()){
				if(e.isEstado()){
					turnoEnemigo(e);
				}
			}
			//comprueba que ha ocurrido despues de ambos turnos
			boolean isAnyPVivo = ListasEntidad.personajeList().isAnyVivo();
			boolean isAnyEVivo = ListasEntidad.enemigoList().isAnyVivo();
			//actua dependiendo de quien haya vivido.
			if(isAnyEVivo && isAnyPVivo){
				repetirCombate = true;
			}else if(isAnyPVivo){
				doublePrintLn(menus.msgEnemigoDerrotado());
				pause();
			}else if(isAnyEVivo){
				throw new JuegoException(menus.msgJuegoPerdido());
			}
		}while(repetirCombate);
	}

	private static void turnoEnemigo(Enemigo e) {

	}

	private static void turnoPersonaje(Personaje p) {

	}

	// Utilidades --------------

	public static int muestraOpcionesYPideNumero(int min, int max, String menu){
		String error = "";
		int numero;

		while(true){
			clear();
			printLn(menu);

			if(!error.isEmpty())
				printLn(error);

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

	public static int generarRandomNumRango(double min, double max){
		return (int) ((Math.random() * (max-min)) + min);
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

	//temp -----------------------------

	/*
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
		if (p.isBloqueo()) {
			throw new JuegoException(menus.ERROR_BLOQUEO_YA_ACTIVO);
		}
		doublePrintLn(menus.MSG_BLOQUEO); //mover esto a ayuda o dejar permanente???
		boolean confirmacion = pideConfirmacion();
		if (confirmacion) {
			p.activarBloqueo();
			doublePrintLn(String.format(menus.MSG_CONFIRMACION_BLOQUEO,p.getNombre()));
		}
		return !confirmacion;
	}
*/
}
