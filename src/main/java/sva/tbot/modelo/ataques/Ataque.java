package sva.tbot.modelo.ataques;

import sva.tbot.juego.Principal;

import java.util.Objects;

public final class Ataque implements Atacable {
	private final String nombre;
	private final Integer dmgAtaque, minRequerido;
	private final Character stat;
	private final Double escalado;

	public Ataque(TiposAtaque ta) {
		this.nombre = ta.ID;
		this.dmgAtaque = ta.dmgAtaque;
		this.minRequerido = ta.minRequerido;
		this.stat = ta.stat;
		this.escalado = ta.escalado;
	}

	public boolean isCritico(int posibilidad) {
		int probabilidad = (int) (posibilidad * Math.PI);
		return (Principal.generarRandomNum(150)) < probabilidad;
	}

	public boolean calculaEsquive(int agl) {
		//TODO hacer esquivar ataque donde=?
		return false;
	}

	@Override
	public boolean isUsable(int stat) {
		return stat >= minRequerido;
	}

	@Override
	public int calculaDmg(int statEmisor, int statReceptor, boolean isCritico, boolean isBloqueo) {
		double dmgAdicional = statEmisor * escalado;
		double dmgReduccion = stat == 'd' ? statReceptor * Math.PI : statReceptor * (Math.PI * 1.2);
		int dmgAleatorio = Principal.generarRandomNum(((dmgAdicional * 18) / 100));
		double dmgTotal = (dmgAtaque + dmgAleatorio + dmgAdicional);
		if(isCritico){
			dmgTotal *= 1.4;
		}
		if(isBloqueo){
			dmgTotal /= 2;
		}
		return (int) (dmgTotal - dmgReduccion);
	}

	@Override
	public String toString() {
		return String.format("%s: (DMG: %d, MIN:%d, %s", nombre, dmgAtaque, minRequerido, stat == 'd' ? "ad" : "ap");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ataque ataque)) return false;
		return nombre.equals(ataque.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
}
