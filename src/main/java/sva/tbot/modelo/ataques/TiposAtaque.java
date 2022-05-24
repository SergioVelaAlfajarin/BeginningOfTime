package sva.tbot.modelo.ataques;

public enum TiposAtaque {
	ATAQUE_RAPIDO       ("af_ad_1",30, 2 , 'd', 4.5),
	GRAN_GOLPE          ("gg_ad_2",60, 10, 'd', 4.0),
	BARRIDO_FISICO      ("bf_ad_3",42, 6 , 'd', 3.5),
	AQUASPLASH          ("as_ap_1",20, 2 , 'p', 5.5),
	GRAN_COMBUSTION     ("gc_ap_2",25, 10, 'p', 6.0),
	TORNADO_MAGICO      ("tm_ap_3",20, 6 , 'p', 4.8);

	public final String ID;
	public final Integer dmgAtaque;
	public final Integer minRequerido;
	public final Character stat;
	public final Double escalado;

	TiposAtaque(String ID, Integer dmgAtaque, Integer minRequerido, Character stat, Double escalado) {
		this.ID = ID;
		this.dmgAtaque = dmgAtaque;
		this.minRequerido = minRequerido;
		this.stat = stat;
		this.escalado = escalado;
	}
}
