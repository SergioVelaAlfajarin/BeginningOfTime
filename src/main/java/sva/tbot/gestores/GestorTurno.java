package sva.tbot.gestores;

import sva.tbot.modelo.entidades.Entidad;

public class GestorTurno <T> {
	private T emisor;

	private Byte accionElegida;


	public void turno(Entidad... receptores){
		for(Entidad e: receptores){
			System.out.println(e);
		}
	}
}
