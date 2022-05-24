package sva.tbot.modelo.ataques;

public interface Atacable {
	int calculaDmgAtaque(int statEmisor, int statReceptor, boolean isCritico, boolean isBloqueo);
	boolean isAtaqueUsable(int stat);
}
