package sva.tbot.modelo.entidades.ene;

import sva.tbot.exception.JuegoException;
import sva.tbot.modelo.entidades.Entidad;
import sva.tbot.modelo.entidades.TiposClase;
import sva.tbot.modelo.entidades.per.Personaje;

public final class Enemigo extends Entidad{
	/*
	 * AD: 2,4,6 TANQ: 8,10,12 AP: 14,16,18 BOSS: 20 TODO nombrar enemigos String[]
	 * tiposEnemigos = {"tipo1","tipo2","tipo3","tipo4/boss"};
	 */
	public static final int MAX_ENEMIGOS = 4;
	public static final int MIN_ENEMIGOS = 2;

	public Enemigo(int nEne, TiposClase tc) throws JuegoException {
		super(tc.toString() + " " + nEne, tc);
	}

}
