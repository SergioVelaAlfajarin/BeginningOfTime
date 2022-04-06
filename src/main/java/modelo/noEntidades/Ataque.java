/*
package src.window;

import src.exception.JuegoException;
import src.objects.entity.Enemigo;

public abstract class Ataque {
	private static final int DMG_PHY_1 = 30;
	private static final int REQ_PHY_1 = 2;
	public static boolean isPhyAttack1Usable(int ad)   {
		return ad>=REQ_PHY_1;
	}
	public static int calculateDmgPhyAttack1(int ad){
		return calculateDamage(ad, 4.5)+ DMG_PHY_1;
	}


	private static final int DMG_PHY_2 = 60;
	private static final int REQ_PHY_2 = 10;
	public static boolean isPhyAttack2Usable(int ad)  {
		return ad>=REQ_PHY_2;
	}
	public static int calculateDmgPhyAttack2(int ad){
		return calculateDamage(ad, 4)+ DMG_PHY_2;
	}


	private static final int DMG_PHY_3 = 42;
	private static final int REQ_PHY_3 = 6;
	public static boolean isPhyAttack3Usable(int ad)  {
		return ad>=REQ_PHY_3;
	}
	public static int calculateDmgPhyAttack3(int ad){
		return calculateDamage(ad, 3.5)+ DMG_PHY_3;
	}

	private static final int DMG_MGC_1 = 20;
	private static final int REQ_MGC_1 = 2;
	public static boolean isMgcAttack1Usable(int ap){
		return ap>=REQ_MGC_1;
	}
	public static int calculateDmgMgcAttack1(int ap){
		return calculateDamage(ap, 5.5) + DMG_MGC_1;
	}


	private static final int DMG_MGC_2 = 25;
	private static final int REQ_MGC_2 = 10;
	public static boolean isMgcAttack2Usable(int ap){
		return ap>=REQ_MGC_2;
	}
	public static int calculateDmgMgcAttack2(int ap){
		return calculateDamage(ap, 6) + DMG_MGC_2;
	}


	private static final int DMG_MGC_3 = 20;
	private static final int REQ_MGC_3 = 6;
	public static boolean isMgcAttack3Usable(int ap)  {
		return ap>=REQ_MGC_3;
	}
	public static int calculateDmgMgcAttack3(int ap){
		return calculateDamage(ap, 4.8) + DMG_MGC_3;
	}


	public static boolean isCriticalAttack(int chance){
		int probability = (int) (chance * Math.PI);
		return (Main.genRandomNum(150)) < probability;
	}

	private static int calculateDamage(int stat, double scaling){
		double additionalDamage = stat * scaling;
		int randomDamage = Main.genRandomNum(((additionalDamage*20)/100));
		return (int) (randomDamage + additionalDamage);

	}

	public static int calculatePhyDmgReduction(int ar){
		return (int) (ar * Math.PI);
	}

	public static int calculateMgcDmgReduction(int mr){
		return (int) (mr * (Math.PI*1.5));
	}
}

 */

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

	private final String ID;
	private Integer dmgAtaque;
	private Integer minRequerido;
	private String stat;
	private Double escalado;

	public Ataque(String ID) {
		if (ID == null || ID.length() == 0) {
			throw new JuegoException("Ataque ID no puede ser nulo");
		}
		this.ID = ID;
		initAtaque();
	}

	public static boolean isCritico(int posibilidad) {
		int probabilidad = (int) (posibilidad * Math.PI);
		return (Main.generarRandomNum(150)) < probabilidad;
	}

	public static int calcularReduccionDmgArmadura(int ar) {
		return (int) (ar * Math.PI);
	}

	public static int calcularReduccionDmgMagico(int mr) {
		return (int) (mr * (Math.PI * 1.5));
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

	public int calculateDamage(int stat) {
		double dmgAdicional = stat * escalado;
		int dmgAleatorio = Main.generarRandomNum(((dmgAdicional * 18) / 100));
		return (int) (dmgAtaque + dmgAleatorio + dmgAdicional);
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
