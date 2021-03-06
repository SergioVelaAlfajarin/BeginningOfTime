package sva.tbot.gestores;

import sva.tbot.exception.JuegoException;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import java.util.HashMap;

public abstract class HistorialTurnos {
	record Turno(Enemigo[] cpArrayEnemigo, Personaje[] cpArrayPersonaje) {}

	public static final HashMap<Integer, Turno> historial = new HashMap<>();

	public static void addTurno(){
		int id = getNextID();
		Enemigo[] e = getArrayEnemigo();
		Personaje[] p= getArrayPersonaje();
		Turno t = new Turno(e, p);
		eliminaIdAntigua(id);
		historial.put(id, t);
	}

	public static Turno getPrimerTurnoDisponible(){
		return getTurnoPorId(getPrimerID());
	}

	public static Turno getTurnoPorId(int id){
		if(id < getPrimerID() || id > getUltimoID()){
			throw new JuegoException("ID de Turno fuera de rango.");
		}
		return historial.get(id);
	}

	public static Personaje[] getArrayPersonaje() {
		var al = ListasEntidad.personajeList().lista();
		var p = new Personaje[al.size()];
		for (int i = 0; i < p.length; i++) {
			p[i] = al.get(i);
		}
		return p;
	}

	public static Enemigo[] getArrayEnemigo() {
		var al = ListasEntidad.enemigoList().lista();
		var e = new Enemigo[al.size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = al.get(i);
		}
		return e;
	}

	public static void eliminaIdAntigua(int id) {
		int idEliminable = id - 3;
		if(idEliminable >= 1){
			historial.remove(idEliminable);
		}
	}

	public static int getNextID(){
		return getUltimoID() + 1;
	}

	public static int getPrimerID(){
		int min = getNextID(); //asi siempre sera mayor que cualquiera del array actual
		for(int i: historial.keySet()){
			if(i < min){
				min = i;
			}
		}
		return min;
	}

	public static int getUltimoID(){
		int max = 0; //asi siempre sera menor que cualquiera del array actual
		for(int i: historial.keySet()){
			if(i > max){
				max = i;
			}
		}
		return max;
	}
}