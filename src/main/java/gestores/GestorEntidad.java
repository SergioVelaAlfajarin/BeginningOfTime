package gestores;

import java.util.ArrayList;

import exception.JuegoException;
import modelo.entidades.Enemigo;
import modelo.entidades.Personaje;
import window.Menu;

public abstract class GestorEntidad {

	private static final ArrayList<Personaje> listaPersonajes = new ArrayList<>();
	private static final ArrayList<Enemigo> listaEnemigos = new ArrayList<>();

	public static boolean isListaPersonajesVacio() {
		return listaPersonajes.isEmpty();
	}

	public static boolean isListaEnemigosVacio() {
		return listaEnemigos.isEmpty();
	}

	public static int getSizeEnemigo() {
		return listaEnemigos.size();
	}

	public static int getSizePersonaje() {
		return listaPersonajes.size();
	}

	public static Personaje[] getArrayPersonajes() {
		Personaje[] perArray = new Personaje[listaPersonajes.size()];
		int contadorPosicion = 0;
		for (Personaje c : listaPersonajes) {
			perArray[contadorPosicion] = c;
			contadorPosicion++;
		}
		return perArray;
	}

	public static Enemigo[] getArrayEnemigos() {
		Enemigo[] eneArray = new Enemigo[listaEnemigos.size()];
		int contadorPosicion = 0;
		for (Enemigo c : listaEnemigos) {
			eneArray[contadorPosicion] = c;
			contadorPosicion++;
		}
		return eneArray;
	}

	public static void addPersonaje(Personaje p) {
		listaPersonajes.add(p);
	}

	public static void addEnemigo(Enemigo e) {
		listaEnemigos.add(e);
	}

	public static Personaje getPersonajePorID(int ID) {
		for (Personaje p : listaPersonajes) {
			if (p.getID() == ID) {
				return p;
			}
		}
		return null;
	}

	public static Enemigo getEnemigoPorID(int ID) {
		for (Enemigo e : listaEnemigos) {
			if (e.getID() == ID) {
				return e;
			}
		}
		return null;
	}

	public static void resetArrays() {
		listaPersonajes.clear();
		listaEnemigos.clear();
	}

	public static int[] getSizeYNivelEnemigos() {
		int cantidad = listaEnemigos.size();
		Enemigo e = listaEnemigos.get(0);
		if (e != null) {
			int nivel = e.getLvl();
			return new int[] { cantidad, nivel };
		}
		throw new JuegoException(Menu.errorEnemigoNoEncontrado());
	}

	public static boolean isAnyPersonajeVivo() {
		for (Personaje p : listaPersonajes) {
			if (p.isEstado()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAnyEnemigoVivo() {
		for (Enemigo e : listaEnemigos) {
			if (e.isEstado()) {
				return true;
			}
		}
		return false;
	}
}
