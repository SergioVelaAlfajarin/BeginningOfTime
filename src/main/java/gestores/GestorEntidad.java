package gestores;

import exception.JuegoException;
import modelo.entidades.Enemigo;
import modelo.entidades.Personaje;
import window.Menu;

import java.util.ArrayList;

public abstract class GestorEntidad {

	private static final ArrayList<Personaje> listaPersonajes = new ArrayList<>();
	private static final ArrayList<Enemigo> listaEnemigos = new ArrayList<>();

	//Testeable
	public static boolean isListaPersonajesVacio() {
		return listaPersonajes.isEmpty();
	}

	//Testeable
	public static boolean isListaEnemigosVacio() {
		return listaEnemigos.isEmpty();
	}

	//Testeable
	public static int getSizeEnemigo() {
		return listaEnemigos.size();
	}

	//Testeable
	public static int getSizePersonaje() {
		return listaPersonajes.size();
	}

	//Testeable
	public static Personaje[] getArrayPersonajes() {
		Personaje[] perArray = new Personaje[listaPersonajes.size()];
		for(int i=0; i<perArray.length; i++){
			perArray[i] = listaPersonajes.get(i);
		}
		return perArray;
	}

	//Testeable
	public static Enemigo[] getArrayEnemigos() {
		Enemigo[] eneArray = new Enemigo[listaEnemigos.size()];
		for(int i=0; i<eneArray.length; i++){
			eneArray[i] = listaEnemigos.get(i);
		}
		return eneArray;
	}

	//Testeable
	public static void addPersonaje(Personaje p) {
		listaPersonajes.add(p);
	}

	//Testeable
	public static void addEnemigo(Enemigo e) {
		listaEnemigos.add(e);
	}

	//Testeable
	public static Personaje getPersonajePorID(int ID) {
		for (Personaje p : listaPersonajes) {
			if (p.getID() == ID) {
				return p;
			}
		}
		return null;
	}

	//Testeable
	public static Enemigo getEnemigoPorID(int ID) {
		for (Enemigo e : listaEnemigos) {
			if (e.getID() == ID) {
				return e;
			}
		}
		return null;
	}

	//Testeable
	public static void resetArrays() {
		listaPersonajes.clear();
		listaEnemigos.clear();
	}

	//Testeable
	public static String getSizeYNivelEnemigos() {
		int cantidad = listaEnemigos.size();
		int contadorNivel = 0;
		for(Enemigo e : listaEnemigos){
			contadorNivel += e.getLvl();
		}
		int mediaNivel = contadorNivel / cantidad;
		//return new int[]{cantidad, mediaNivel};
		return null;//TODO arreglar esto
	}

	//Testeable
	public static boolean isAnyPersonajeVivo() {
		for (Personaje p : listaPersonajes) {
			if (p.isEstado()) {
				return true;
			}
		}
		return false;
	}

	//Testeable
	public static boolean isAnyEnemigoVivo() {
		for (Enemigo e : listaEnemigos) {
			if (e.isEstado()) {
				return true;
			}
		}
		return false;
	}

	//Testeable?
	public static String getStatsPersonajes() {
		StringBuilder sb = new StringBuilder();
		for (Personaje p : listaPersonajes) {
			sb.append(p).append("\n");
		}
		return sb.toString();
	}

	//Testeable?
	public static String getStatsEnemigos() {
		StringBuilder sb = new StringBuilder();
		for (Enemigo e : listaEnemigos) {
			sb.append(e).append("\n");
		}
		return sb.toString();
	}
}
