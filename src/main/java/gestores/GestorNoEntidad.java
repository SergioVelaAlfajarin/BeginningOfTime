package gestores;

import java.util.ArrayList;

import objects.noEntidades.Ataque;

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
}
