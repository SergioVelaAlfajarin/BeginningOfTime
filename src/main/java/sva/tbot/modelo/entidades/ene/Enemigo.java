package sva.tbot.modelo.entidades.ene;

import sva.tbot.exception.JuegoException;
import sva.tbot.modelo.entidades.Entidad;
import sva.tbot.modelo.entidades.per.Personaje;

public final class Enemigo extends Entidad {
	/*
	 * AD: 2,4,6 TANQ: 8,10,12 AP: 14,16,18 BOSS: 20 TODO nombrar enemigos String[]
	 * tiposEnemigos = {"tipo1","tipo2","tipo3","tipo4/boss"};
	 */

	public static final int MAX_ENEMIGOS = 4;
	// types -1, -2, -3, -4
	private static final String[] tipos = {"Lobo Generico", "Oso Marron", "Serpiente amenazante",
			Personaje.NOMBRE_PERSONAJE_PRINCIPAL};
	//private final int ID;

	//Testeable
	public Enemigo(int n) throws JuegoException {
		super(null, null);
		//super(claseEnemigo(2) + n, -1);
		//ID = GestorEntidad.getSizeEnemigo();
	}

	//Testeable
	private static String claseEnemigo(int i) throws JuegoException {
		if (i <= 6) {
			return tipos[0];
		} else if (i <= 12) {
			return tipos[1];
		} else if (i <= 18) {
			return tipos[2];
		} else if (i <= 20) {
			return tipos[3];
		} else {
			throw new JuegoException("error");
		}
	}
}
