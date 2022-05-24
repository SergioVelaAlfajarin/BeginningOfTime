package sva.tbot.modelo.ataques;

public enum TiposAtaque {
	ATAQUE_RAPIDO("af_ad_1"),
	GRAN_GOLPE("gg_ad_2"),
	BARRIDO_FISICO("bf_ad_3"),
	AQUASPLASH("as_ap_1"),
	GRAN_COMBUSTION("gc_ap_2"),
	TORNADO_MAGICO("tm_ap_3");

	public final String id;

	TiposAtaque(String id) {
		this.id = id;
	}
}
