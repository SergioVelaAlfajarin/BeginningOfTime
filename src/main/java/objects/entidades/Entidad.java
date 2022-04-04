package objects.entidades;


public abstract class Entidad {
	private final String nombre;
	private int actualHP;
	private int maxHP;
	private final int claseEntidad;
	private int ad, ap, ar, mr, agl, lvl;
	private boolean estado;
	private boolean bloqueo;

	protected Entidad(String nombre, int claseEntidad) {
		this.nombre = nombre;
		this.claseEntidad = claseEntidad;
		this.estado = true;
		this.bloqueo = false;

		setStats();
	}

	public String getNombre() {
		return nombre;
	}

	public int getClaseEntidad() {
		return claseEntidad;
	}

	public int getActualHP() {
		return actualHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getAd() {
		return ad;
	}

	public int getAp() {
		return ap;
	}

	public int getAr() {
		return ar;
	}

	public int getMr() {
		return mr;
	}

	public int getAgl() {
		return agl;
	}

	public int getLvl() {
		return lvl;
	}

	public boolean isEstado() {
		return estado;
	}

	public boolean isBloqueo() {
		return bloqueo;
	}

	private void setStats() {
		// TODO metodo set Estadisticas y clase con las estadisticas
		maxHP = 100;
		actualHP = maxHP;
		ad = 3;
		ap = 3;
		ar = 3;
		mr = 3;
		agl = 3;
		lvl = 3;
	}

	public boolean recibirDmg(int dmg) {
		actualHP -= dmg;
		if (actualHP <= 0) {
			estado = false;
			bloqueo = false;
			actualHP = 0;
		}
		return estado;
	}
}
