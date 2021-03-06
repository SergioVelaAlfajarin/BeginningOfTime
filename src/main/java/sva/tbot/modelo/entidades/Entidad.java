package sva.tbot.modelo.entidades;

public abstract class Entidad {
	private static int multiplicadorEstadisticas = 0;

	private String nombre, claseEntidad;
	private Integer actualHP, maxHP, ad, ap, ar, mr, agl, lvl;
	private Integer adAdicional, apAdicional, arAdicional, mrAdicional, aglAdicional, maxHPAdicional;
	private Boolean estado, bloqueo;

	//INIT ----------------------------------------------------------------------------------------

	protected Entidad(String nombre, TiposClase classEntity) {
		this.nombre = nombre;
		claseEntidad = classEntity.toString();
		initEntidad(classEntity);
	}

	private void initEntidad(TiposClase classEntity) {
		//set base stats
		this.maxHP = classEntity.hp;
		this.actualHP = maxHP;
		this.ad = classEntity.ad;
		this.ap = classEntity.ap;
		this.ar = classEntity.ar;
		this.mr = classEntity.mr;
		this.agl = classEntity.agl;

		//set additional stats
		addEstadisticasAdicionales();

		//set normal variables
		this.estado = true;
		this.bloqueo = false;
		this.lvl = 3;
	}

	//SETTERS ------------------------------------------------------------------------------------

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setClaseEntidad(String claseEntidad) {
		this.claseEntidad = claseEntidad;
	}

	public void setActualHP(Integer actualHP) {
		this.actualHP = actualHP;
	}

	public void setMaxHP(Integer maxHP) {
		this.maxHP = maxHP;
	}

	public void setAd(Integer ad) {
		this.ad = ad;
	}

	public void setAp(Integer ap) {
		this.ap = ap;
	}

	public void setAr(Integer ar) {
		this.ar = ar;
	}

	public void setMr(Integer mr) {
		this.mr = mr;
	}

	public void setAgl(Integer agl) {
		this.agl = agl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public void setAdAdicional(Integer adAdicional) {
		this.adAdicional = adAdicional;
	}

	public void setApAdicional(Integer apAdicional) {
		this.apAdicional = apAdicional;
	}

	public void setArAdicional(Integer arAdicional) {
		this.arAdicional = arAdicional;
	}

	public void setMrAdicional(Integer mrAdicional) {
		this.mrAdicional = mrAdicional;
	}

	public void setAglAdicional(Integer aglAdicional) {
		this.aglAdicional = aglAdicional;
	}

	public void setMaxHPAdicional(Integer maxHPAdicional) {
		this.maxHPAdicional = maxHPAdicional;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public void setBloqueo(Boolean bloqueo) {
		this.bloqueo = bloqueo;
	}

	//GETTERS --------------------------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public String getClaseEntidad() {
		return claseEntidad;
	}

	public Integer getActualHP() {
		return actualHP;
	}

	public Integer getMaxHP() {
		return maxHP;
	}

	public Integer getAd() {
		return ad;
	}

	public Integer getAp() {
		return ap;
	}

	public Integer getAr() {
		return ar;
	}

	public Integer getMr() {
		return mr;
	}

	public Integer getAgl() {
		return agl;
	}

	public Integer getLvl() {
		return lvl;
	}

	public Integer getAdAdicional() {
		return adAdicional;
	}

	public Integer getApAdicional() {
		return apAdicional;
	}

	public Integer getArAdicional() {
		return arAdicional;
	}

	public Integer getMrAdicional() {
		return mrAdicional;
	}

	public Integer getAglAdicional() {
		return aglAdicional;
	}

	public Integer getMaxHPAdicional() {
		return maxHPAdicional;
	}

	public Boolean isEstado() {
		return estado;
	}

	public Boolean isBloqueo() {
		return bloqueo;
	}

	// SETTER AVANZADOS -----------------------------------------------------------------------

	public void addEstadisticasAdicionales(){
		this.adAdicional    += multiplicadorEstadisticas;
		this.apAdicional    += multiplicadorEstadisticas;
		this.arAdicional    += multiplicadorEstadisticas;
		this.mrAdicional    += multiplicadorEstadisticas;
		this.aglAdicional   += multiplicadorEstadisticas;
		this.maxHPAdicional += multiplicadorEstadisticas;
	}

	public static void aumentarMultiplicadorEstadisticas(int contadorPartidas){
		multiplicadorEstadisticas = contadorPartidas * 10;
	}



	//METODOS ---------------------------------------------------------------------------------

	public boolean recibirDmg(int dmg) {
		actualHP -= dmg;
		if (actualHP <= 0) {
			estado = false;
			bloqueo = false;
			actualHP = 0;
		}
		return estado;
	}

	//OVERRIDE --------------------------------------------------------------------------------

	@Override
	public String toString() {
		return "Entidad{" +
				"nombre='" + nombre + '\'' +
				", claseEntidad=" + claseEntidad +
				'}';
	}
}
