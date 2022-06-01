package sva.tbot.modelo.ataques;

public interface Atacable {
	int calculaDmg(int statEmisor, int statReceptor, boolean isCritico, boolean isBloqueo);
	boolean isUsable(int stat);
}
