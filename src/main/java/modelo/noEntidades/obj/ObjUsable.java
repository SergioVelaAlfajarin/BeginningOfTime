package modelo.noEntidades.obj;

import exception.JuegoException;

public final class ObjUsable extends Objeto {
	private final String ID;

	private String nombre;
	private String descripcion;
	private String efecto;

	public ObjUsable(String ID) {
		if (ID == null) {
			throw new JuegoException("el id es nulo");
		}
		this.ID = ID;
		initObjUsable();
	}

	private void initObjUsable() {
		// asignar las cosas como en Ataque
	}

	public String getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEfecto() {
		return efecto;
	}

}
