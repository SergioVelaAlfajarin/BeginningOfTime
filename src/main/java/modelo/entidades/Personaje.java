package modelo.entidades;

import gestores.GestorEntidad;
import modelo.noEntidades.obj.ObjEquipable;
import modelo.noEntidades.obj.ObjUsable;
import window.Menu;

public final class Personaje extends Entidad {
	public static final int MAX_PERSONAJES = 3;
	public static final int MAX_SIZE_INVENTARIO = 8;
	public static final String NOMBRE_PERSONAJE_PRINCIPAL = "Karim";

	private final int ID;
	private final ObjUsable[] inv = new ObjUsable[MAX_SIZE_INVENTARIO];
	private int xpRequerida, xpActual;

	private ObjEquipable cabeza;
	private ObjEquipable pechera;
	private ObjEquipable pantalones;
	private ObjEquipable guantes;
	private ObjEquipable botas;
	private ObjEquipable colgante;

	public Personaje(String nombre, int clase) {
		super(nombre, clase);
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
