package sva.tbot.modelo.entidades;

public enum TiposClase {
	ASESINO     (100,4,1,1,1,2),
	TANQUE      (100,1,3,2,2,1),
	CABALLERO   (120,2,1,2,1,1),
	MAGO        (100,1,3,1,2,2),
	MARGINADO   (100,2,1,2,1,1),

	LOBO        (100,4,1,1,1,2),
	OSO         (100,1,3,2,2,1),
	SERPIENTE   (120,2,1,2,1,1),
	DRAGON      (100,1,3,1,2,2);

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
