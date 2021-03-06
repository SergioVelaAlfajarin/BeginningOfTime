package sva.tbot.gestores;

import sva.tbot.exception.JuegoException;
import sva.tbot.modelo.entidades.ene.Enemigo;
import sva.tbot.modelo.entidades.per.Personaje;

import java.util.ArrayList;
import java.util.Arrays;

public class ListasEntidad<T> {
	private static ListasEntidad<Enemigo> enemigoSingleton;
	private static ListasEntidad<Personaje> personajeSingleton;

	public static ListasEntidad<Enemigo> enemigoList(){
		if(enemigoSingleton ==null){
			enemigoSingleton = new ListasEntidad<>();
		}
		return enemigoSingleton;
	}

	public static ListasEntidad<Personaje> personajeList(){
		if(personajeSingleton == null){
			personajeSingleton = new ListasEntidad<>();
		}
		return personajeSingleton;
	}

	//-----------------------------------

	private final ArrayList<T> lista = new ArrayList<>();

	@SafeVarargs
	public final void initLista(T... list){
		lista.clear();
		lista.addAll(Arrays.asList(list));
	}

	@SafeVarargs
	public final void addLista(T... list){
		lista.addAll(Arrays.asList(list));
	}

	public T get(int pos) throws JuegoException {
		if(pos >= lista.size() || pos < 0){
			throw new JuegoException("La posicion no es valida.");
		}
		return lista.get(pos);
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}

	public int getSize(){
		return lista.size();
	}

	public boolean isAnyVivo(){
		for(T element: lista){
			return true; //TODO
		}
		return false;
	}

	public ArrayList<T> lista() {
		return lista;
	}
}
