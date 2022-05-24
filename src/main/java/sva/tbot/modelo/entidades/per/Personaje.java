package sva.tbot.modelo.entidades.per;

import sva.tbot.gestores.GestorEntidad;
import sva.tbot.modelo.entidades.Entidad;
import sva.tbot.modelo.equipamiento.Equipo;
import sva.tbot.modelo.items.Item;
import sva.tbot.juego.Menu;

public final class Personaje extends Entidad {
	public static final int MAX_PERSONAJES = 3;
	public static final int MAX_SIZE_INVENTARIO = 8;
	public static final String NOMBRE_PERSONAJE_PRINCIPAL = "Karim";

	private final int ID;
	private final Item[] inv = new Item[MAX_SIZE_INVENTARIO];
	private int xpRequerida, xpActual;

	//TODO revisar y comprobar si testeable

	private Equipo cabeza;
	private Equipo pechera;
	private Equipo pantalones;
	private Equipo guantes;
	private Equipo botas;
	private Equipo colgante;

	public Personaje(String nombre, int clase) {
		super(nombre, null);
		ID = GestorEntidad.getSizePersonaje();
		initPersonaje();
	}

	private void initPersonaje() {
		xpRequerida = 100;
		xpActual = 0;
	}

	public int getID() {
		return ID;
	}

	public String getInv() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != null) {
				sb.append(i + 1).append(" - ").append(inv[i].getNombre()).append("\n");
			} else {
				sb.append(i + 1).append(" - ").append(Menu.msgPosicionInventarioVacia()).append("\n");
			}
		}

		return sb.toString();
	}
}
