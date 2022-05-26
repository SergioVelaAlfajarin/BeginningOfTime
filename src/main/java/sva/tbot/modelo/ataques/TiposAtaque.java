package sva.tbot.modelo.ataques;

import sva.tbot.juego.Menu;

public enum TiposAtaque {
	AT_RA_1 (Menu.getMenu().buscaTexto("attack0"),30, 2 , 'd', 4.5),
	GR_GO_2 (Menu.getMenu().buscaTexto("attack1"),60, 10, 'd', 4.0),
	BA_FI_3 (Menu.getMenu().buscaTexto("attack2"),42, 6 , 'd', 3.5),
	AQ_PL_4 (Menu.getMenu().buscaTexto("attack3"),20, 2 , 'p', 5.5),
	GR_CO_5 (Menu.getMenu().buscaTexto("attack4"),25, 10, 'p', 6.0),
	TO_MA_6 (Menu.getMenu().buscaTexto("attack5"),20, 6 , 'p', 4.8);

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
