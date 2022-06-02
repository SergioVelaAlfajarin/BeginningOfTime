package sva.tbot.modelo.entidades;

public enum TiposClase {
	ASESINO     (100,4,1,1,1,2),
	TANQUE      (100,1,3,2,2,1),
	CABALLERO   (120,2,1,2,1,1),
	MAGO        (100,1,3,1,2,2),
	MARGINADO   (100,2,1,2,1,1),

	LOBO        (100,1,0,1,1,0), //AD (LVLS: 2,4,6)
	OSO         (150,4,4,3,3,3), //TANK (LVLS: 8,10,12)
	SERPIENTE   (220,4,11,4,4,5), //AP (LVLS: 14,16,18)
	DRAGON      (400,12,14,16,5,10); //BOSS (LVL: 20)

	public final Integer hp, ad, ap, ar, mr, agl;

	TiposClase(int hp, int ad, int ap, int ar, int mr, int agl) {
		this.hp = hp;
		this.ad = ad;
		this.ap = ap;
		this.ar = ar;
		this.mr = mr;
		this.agl = agl;
	}
}
