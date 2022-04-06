package modelo.noEntidades.obj;

import exception.JuegoException;

public final class ObjEquipable extends Objeto {
	private final String ID;
	private String tipo;
	private String parteCuerpo;
	private String efecto;
	private String pasiva;

	public ObjEquipable(String ID) {
		if (ID == null) {
			throw new JuegoException("ObjEquipable ID is null");
		}
		this.ID = ID;
		initialize();
	}

	private void initialize() {

	}

	public String getID() {
		return ID;
	}

	public String getTipo() {
		return tipo;
	}

	public String getParteCuerpo() {
		return parteCuerpo;
	}

	public String getEfecto() {
		return efecto;
	}

	public String getPasiva() {
		return pasiva;
	}
}
