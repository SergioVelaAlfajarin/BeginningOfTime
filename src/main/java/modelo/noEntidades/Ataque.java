package modelo.noEntidades;

import exception.JuegoException;
import window.Main;

import java.util.Objects;

public final class Ataque {
	// 1:dmg, 2:minRequired, 3:stat, 4:scaling
	private static final Object[] af_ad_1_info = {30, 2, "ad", 4.5};
	private static final Object[] gg_ad_2_info = {60, 10, "ad", 4.0};
	private static final Object[] bf_ad_3_info = {42, 6, "ad", 3.5};
	private static final Object[] as_ap_1_info = {20, 2, "ap", 5.5};
	private static final Object[] gc_ap_2_info = {25, 10, "ap", 6.0};
	private static final Object[] tm_ap_2_info = {20, 6, "ap", 4.8};

	private String ID;
	private Integer dmgAtaque;
	private Integer minRequerido;
	private String stat;
	private Double escalado;

	public Ataque(String ID) {
		setID(ID);
		initAtaque();
	}

	public static boolean isCritico(int posibilidad) {
		int probabilidad = (int) (posibilidad * Math.PI);
		return (Main.generarRandomNum(150)) < probabilidad;
	}

	private void initAtaque() {
		switch (ID.toLowerCase()) {
			case "af_ad_1" -> rellenaVariables(af_ad_1_info);
			case "gg_ad_2" -> rellenaVariables(gg_ad_2_info);
			case "bf_ad_3" -> rellenaVariables(bf_ad_3_info);
			case "as_ap_1" -> rellenaVariables(as_ap_1_info);
			case "gc_ap_2" -> rellenaVariables(gc_ap_2_info);
			case "tm_ap_2" -> rellenaVariables(tm_ap_2_info);
			default -> throw new JuegoException("Ataque ID no valido");
		}
	}

	public static int calcularReduccionDmgArmadura(int ar) {
		return (int) (ar * Math.PI);
	}

	public int getDmgAtaque() {
		return dmgAtaque;
	}

	public int getMinRequerido() {
		return minRequerido;
	}

	public String getStat() {
		return stat;
	}

	public String getID() {
		return ID;
	}

	public double getEscalado() {
		return escalado;
	}

	public boolean isAttackUsable(int stat) {
		return stat >= minRequerido;
	}

	public int calculateDamage(int stat, boolean isCritico, boolean isBloqueo) {
		double dmgAdicional = stat * escalado;
		int dmgAleatorio = Main.generarRandomNum(((dmgAdicional * 18) / 100));
		double dmgTotal = (dmgAtaque + dmgAleatorio + dmgAdicional);
		if(isCritico){
			dmgTotal *= 1.4;
		}
		if(isBloqueo){
			dmgTotal /= 2;
		}
		return (int) dmgTotal;
	}

	public static int calcularReduccionDmgMagico(int mr) {
		return (int) (mr * (Math.PI * 1.5));
	}

	private void setID(String ID) {
		if (ID == null || ID.length() == 0) {
			throw new JuegoException("Ataque ID no puede ser nulo");
		}
		String regExId = "[a-z][a-z]_[a-z][a-z]_[0-9]";
		if (!(ID.toLowerCase().matches(regExId))) {
			throw new JuegoException("Ataque ID no es valido");
		}
		this.ID = ID.toLowerCase();
	}

	private void rellenaVariables(Object[] arr) {
		dmgAtaque = (Integer) arr[0];
		minRequerido = (Integer) arr[1];
		stat = (String) arr[2];
		escalado = (Double) arr[3];
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
