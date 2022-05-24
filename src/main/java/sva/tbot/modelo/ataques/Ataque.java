package sva.tbot.modelo.ataques;

import sva.tbot.exception.JuegoException;
import sva.tbot.juego.Principal;

import java.util.Objects;

public final class Ataque implements Atacable {
	private String ID;
	private Integer dmgAtaque;
	private Integer minRequerido;
	private String stat;
	private Double escalado;

	public Ataque(TiposAtaque ta) {
		setID(ta.id);
		initDatosAtaque();
	}

	private void initDatosAtaque() {
		switch (ID) { // 1:dmg, 2:minRequired, 3:stat, 4:scaling
			case "af_ad_1" -> rellenaVariables(new Object[]{30, 2 , "ad", 4.5});
			case "gg_ad_2" -> rellenaVariables(new Object[]{60, 10, "ad", 4.0});
			case "bf_ad_3" -> rellenaVariables(new Object[]{42, 6 , "ad", 3.5});
			case "as_ap_1" -> rellenaVariables(new Object[]{20, 2 , "ap", 5.5});
			case "gc_ap_2" -> rellenaVariables(new Object[]{25, 10, "ap", 6.0});
			case "tm_ap_3" -> rellenaVariables(new Object[]{20, 6 , "ap", 4.8});
			default -> throw new JuegoException("Ataque ID no valido");
		}
	}

	public boolean isAttackUsable(int stat) {
		return stat >= minRequerido;
	}

	public int calculateDamage(int statEmisor, int statReceptor, boolean isCritico, boolean isBloqueo) {
		double dmgAdicional = statEmisor * escalado;
		int dmgAleatorio = Principal.generarRandomNum(((dmgAdicional * 18) / 100));
		double dmgTotal = (dmgAtaque + dmgAleatorio + dmgAdicional);
		if(isCritico){
			dmgTotal *= 1.4;
		}
		if(isBloqueo){
			dmgTotal /= 2;
		}
		double dmgReduccion = stat.equals("ad") ?
				(statReceptor * Math.PI) :
				(statReceptor * (Math.PI * 1.2));
		return (int) (dmgTotal - dmgReduccion);
	}

	public static int calcularReduccionDmgMagico(int mr) {
		return (int) (mr * (Math.PI * 1.5));
	}

	private void setID(String ID) {
		if (ID == null || ID.length() == 0) {
			throw new JuegoException("Ataque ID no puede ser nulo");
		}
		ID = ID.toLowerCase();
		String regExId = "[a-z][a-z]_[a-z][a-z]_[0-9]";
		if (!(ID.matches(regExId))) {
			throw new JuegoException("Ataque ID no es valido");
		}
		this.ID = ID;
	}

	private void rellenaVariables(Object[] arr) {
		dmgAtaque = (Integer) arr[0];
		minRequerido = (Integer) arr[1];
		stat = (String) arr[2];
		escalado = (Double) arr[3];
	}


	public static boolean isCritico(int posibilidad) {
		int probabilidad = (int) (posibilidad * Math.PI);
		return (Principal.generarRandomNum(150)) < probabilidad;
	}

	public static boolean calcularAtaqueEsquivado(int agl) {
		//TODO hacer esquivar ataque
		return false;
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
