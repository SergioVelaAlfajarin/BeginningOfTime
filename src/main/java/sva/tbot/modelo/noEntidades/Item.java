package sva.tbot.modelo.noEntidades;

public final class Item implements Usable {
	private String ID;
	private String nombre;
	private String descripcion;
	private String efecto;

	public Item(String ID) {
		this.ID = ID;
		initialize();
	}

	private void initialize() {
		//TODO init
	}

	public String getNombre() {
		return null;
	}
}
