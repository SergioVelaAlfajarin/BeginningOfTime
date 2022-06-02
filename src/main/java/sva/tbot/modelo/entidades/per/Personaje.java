package sva.tbot.modelo.entidades.per;

import sva.tbot.exception.JuegoException;
import sva.tbot.modelo.entidades.Entidad;
import sva.tbot.modelo.entidades.TiposClase;
import sva.tbot.modelo.equipamiento.Equipo;
import sva.tbot.modelo.items.Item;

public final class Personaje extends Entidad {
	public static final int MAX_PERSONAJES = 3;
	public static final int MAX_SIZE_INVENTARIO = 8;
	public static final String NOMBRE_PERSONAJE_PRINCIPAL = "Tiid";

	private final Item[] inventario = new Item[MAX_SIZE_INVENTARIO];
	private Equipo cabeza, pechera, pantalones, guantes, botas;
	private int xpRequerida, xpActual;

	public Personaje(String nombre, int clase) throws JuegoException{
		super(nombre, getTipoClase(clase));
		initPersonaje();
	}

	private static TiposClase getTipoClase(int clase) throws JuegoException{
		return switch(clase){
			case 1 -> TiposClase.ASESINO;
			case 2 -> TiposClase.TANQUE;
			case 3 -> TiposClase.CABALLERO;
			case 4 -> TiposClase.MAGO;
			case 5 -> TiposClase.MARGINADO;
			default -> throw new JuegoException("Clase no soportada.");
		};
	}

	private void initPersonaje() {
		xpRequerida = 100;
		xpActual = 0;
	}


}
