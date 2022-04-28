package modelo.entidades;

public abstract class Entidad {
	private final String nombre;
	private final int claseEntidad;

	private int actualHP, maxHP, ad, ap, ar, mr, agl, lvl;
	private int adAdicional, apAdicional, arAdicional, mrAdicional, aglAdicional, maxHPAdicional;

	private boolean estado, bloqueo;

	protected Entidad(String nombre, int claseEntidad) {
		this.nombre = nombre;
		this.claseEntidad = claseEntidad;
		this.estado = true;
		this.bloqueo = false;
		initEstadisticas();
	}

	public String getNombre() {
		return nombre;
	}

	public int getClaseEntidadInt() {
		return claseEntidad;
	}

	public String getClaseEntidadString() {
		return switch (claseEntidad) {
			case 1 -> "Tanque";
			case 2 -> "Asesino";
			case 3 -> "Caballero";
			case 4 -> "Mago";
			default -> "Marginado";
		};
	}

	public int getActualHP() {
		return actualHP;
	}

	public int getMaxHP() {
		return maxHP + maxHPAdicional;
	}

	public int getAd() {
		return ad + adAdicional;
	}

	public int getAp() {
		return ap + apAdicional;
	}

	public int getAr() {
		return ar + arAdicional;
	}

	public int getMr() {
		return mr + mrAdicional;
	}

	public int getAgl() {
		return agl + aglAdicional;
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

	public void activarBloqueo() {
		bloqueo = true;
	}

	public void desactivaBloqueo() {
		bloqueo = false;
	}

	private void initEstadisticas() {
		//TODO estadisticas
		initEstadisticasAdicionales(0); //innecesario en la primera ejecucion
		/*
		int[] stats = Estadisticas.getStats(3);
		maxHP = stats[0] + maxHPAdicional;
		actualHP = maxHP;
		ad = stats[1];
		ap = stats[2];
		ar = stats[3];
		mr = stats[4];
		agl = stats[5];*/
		lvl = 3;
	}

	//Testeable
	public void initEstadisticasAdicionales(int nPartida) {
		nPartida *= 3;
		maxHPAdicional += nPartida;
		adAdicional += nPartida;
		apAdicional += nPartida;
		arAdicional += nPartida;
		mrAdicional += nPartida;
		aglAdicional += nPartida;
	}

	//Testeable
	public boolean recibirDmg(int dmg) {
		actualHP -= dmg;
		if (actualHP <= 0) {
			estado = false;
			bloqueo = false;
			actualHP = 0;
		}
		if(bloqueo){
			bloqueo = false;
		}
		return estado;
	}

	@Override
	public String toString() {
		return "Entidad{" +
				"nombre='" + nombre + '\'' +
				", claseEntidad=" + claseEntidad +
				'}';
	}

}
