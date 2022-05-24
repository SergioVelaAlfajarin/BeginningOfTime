package sva.tbot.modelo.items;

public final class Item implements Activo {
	private final String ID;
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
