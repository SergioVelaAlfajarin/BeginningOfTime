package gestores;

import sva.tbot.modelo.noEntidades.Ataque;
import sva.tbot.modelo.noEntidades.Equipo;
import sva.tbot.modelo.noEntidades.Item;

import java.util.ArrayList;

public abstract class GestorNoEntidad {

	private static final ArrayList<Ataque> listaAtaques = new ArrayList<>();
	private static final ArrayList<Item> listaItems = new ArrayList<>();
	private static final ArrayList<Equipo> listaEquipamientos = new ArrayList<>();

	//Testeable
	public static void addAtaque(Ataque ataque) {
		listaAtaques.add(ataque);
	}

	//Testeable
	public static void addItem(Item item){
		listaItems.add(item);
	}

	//Testeable
	public static void addEquipo(Equipo equipo){
		listaEquipamientos.add(equipo);
	}

	//Testeable
	public static Ataque getAtaquePorID(String id) {
		for (Ataque a : listaAtaques) {
			if (a.getID().equalsIgnoreCase(id)) {
				return a;
			}
		}
		return null;
	}

	//Testeable
	public static boolean isArrayAtaqueVacio() {
		return listaAtaques.isEmpty();
	}
	//Testeable
	public static boolean isArrayItemVacio() {
		return listaItems.isEmpty();
	}
	//Testeable
	public static boolean isArrayEquipoVacio(){
		return listaEquipamientos.isEmpty();
	}


	//Testeable?
	public static String getInfoAtaques() {
		StringBuilder sb = new StringBuilder();
		for (Ataque a : listaAtaques) {
			sb.append(a).append("\n");
		}
		return sb.toString();
	}
}
