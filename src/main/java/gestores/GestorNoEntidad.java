package gestores;

import modelo.noEntidades.Ataque;

import java.util.ArrayList;

public abstract class GestorNoEntidad {

	private static final ArrayList<Ataque> listaAtaques = new ArrayList<>();

	public static void addAtaque(Ataque ataque) {
		listaAtaques.add(ataque);
	}

	public static Ataque getAtaquePorID(String id) {
		for (Ataque a : listaAtaques) {
			if (a.getID().equalsIgnoreCase(id)) {
				return a;
			}
		}
		return null;
	}

	public static boolean isArrayAtaquesVacio() {
		return listaAtaques.isEmpty();
	}

	public static String getInfoAtaques() {
		StringBuilder sb = new StringBuilder();
		for (Ataque a : listaAtaques) {
			sb.append(a).append("\n");
		}
		return sb.toString();
	}
}
