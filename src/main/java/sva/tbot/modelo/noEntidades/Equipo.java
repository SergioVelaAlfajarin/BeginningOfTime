package sva.tbot.modelo.noEntidades;

import sva.tbot.exception.JuegoException;
import sva.tbot.modelo.interfaces.Equipable;

public final class Equipo implements Equipable {
	private final String ID;
	private String tipo;            //tpo_
	private String parteCuerpo;     //pte_ca - pte_pe - pte_pa - _gu - _bo - _co
	private String efecto;
	private String pasiva;



	public Equipo(String ID) {
		if (ID == null) {
			throw new JuegoException("Equipo ID is null");
		}
		this.ID = ID;
		initialize();
	}

	private void initialize() {

	}


	/*
    armadura --------------------------------------------

     CONJUNTO 1 (Tier3)
    casco: Casco de cuero reforzado
    guantes: Guantes de cuero reforzado
    grebas: Grebas de cuero reforzado
    pechera: pechera de cuero reforzado
    botas: botas de cuero reforzado

     CONJUNTO 2 (tier2)
    casco: Casco de laton
    guantes: Guantes de laton
    grebas: Grebas de laton
    pechera: Pechera de laton
    botas: Botas de laton

     CONJUNTO 3(tier1)
    casco: casco de acero de damasco
    guantes: guantes de acero de damasco
    grebas: grebas de acero de damasco
    pechera: pechera de acero de damasco
    botas: botas de acero de damasco

    conjunto4? armadura de hierro que iria perdiendo su efecto cada turno.
    al final , se romperia.
     */
}

