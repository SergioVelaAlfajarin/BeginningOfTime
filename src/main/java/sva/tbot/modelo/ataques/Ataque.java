package sva.tbot.modelo.ataques;

import sva.tbot.juego.Principal;

import java.util.Objects;

public final class Ataque implements Atacable {
	private String ID;
	private Integer dmgAtaque, minRequerido;
	private Character stat;
	private Double escalado;

	public Ataque(TiposAtaque ta) {
		this.ID = ta.ID;
		this.dmgAtaque = ta.dmgAtaque;
		this.minRequerido = ta.minRequerido;
		this.stat = ta.stat;
		this.escalado = ta.escalado;
	}

	public boolean calculaCritico(int posibilidad) {
		int probabilidad = (int) (posibilidad * Math.PI);
		return (Principal.generarRandomNum(150)) < probabilidad;
	}

	public boolean calculaEsquive(int agl) {
		//TODO hacer esquivar ataque
		return false;
	}

	@Override
	public boolean isAtaqueUsable(int stat) {
		return stat >= minRequerido;
	}

	@Override
	public int calculaDmgAtaque(int statEmisor, int statReceptor, boolean isCritico, boolean isBloqueo) {
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
		return "Ataque{wip}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Ataque attack))
			return false;
		return Objects.equals(ID, attack.ID);
	}

	@Override
	public int hashCode() {
		return ID != null ? ID.hashCode() : 0;
	}
}
