package sva.tbot.juego;

import sva.tbot.exception.JuegoException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

	/*
	RUTAS:
		IDE: src/main/resources/*file*.txt
		BAT: *file*.txt
		JAR: resources/*file*.txt
	 */

public final class Menu {
	public static Menu singleton = null;
	public static final String SALTO_LINEA = "\n";
	public static final String CSV_SEPARATOR = ";";
	public static final String CSV_RPATH = "a.csv";
	public static final String LOGO_DEV = """
				         /\\
				      _-/- \\
				     - /   -\\-_
				     -/__    \\_-
				     /     _--\\
				     ----------

				     SHI-GAMES
				    2021 - SPAIN
				""";

	public static Menu getMenu(){
		if(singleton == null){
			singleton = new Menu();
		}
		return singleton;
	}

	private int columnaIdioma;
	private final ArrayList<String> TEXTOS_FILE;
	private final ArrayList<String> LISTA_IDIOMAS;

	public Menu() throws JuegoException{
		TEXTOS_FILE = new ArrayList<>();
		LISTA_IDIOMAS = new ArrayList<>();
		rellenaArchivoIdiomas();
		rellenaListaIdiomas();
		columnaIdioma = 1; //por defecto sera español
	}

	// MENUS ----------------------------------------------

	public String menuPrincipal(int vecesJugado) {
		StringBuilder sb = new StringBuilder(LOGO_DEV + SALTO_LINEA+ SALTO_LINEA);
		if(vecesJugado >= 1){
			sb.append("0.- ").append(buscaTexto("option0")).append(SALTO_LINEA);
			sb.append("1.- ").append(buscaTexto("option1"));
			if (vecesJugado >= 5) {
				sb.append("+").append(vecesJugado);
			} else {
				sb.append("+".repeat(vecesJugado));
			}
			sb.append(SALTO_LINEA);
		}else{
			sb.append("1.- ").append(buscaTexto("option1")).append(SALTO_LINEA);
		}
		sb.append("2.- ").append(buscaTexto("option2")).append(SALTO_LINEA);
		sb.append("3.- ").append(buscaTexto("option3")).append(SALTO_LINEA);
		sb.append("4.- ").append(buscaTexto("option4")).append(SALTO_LINEA);
		sb.append("5.- ").append(buscaTexto("option15")).append(SALTO_LINEA);
		return sb.toString();
	}

	public String menuChangeLog() {
		StringBuilder sb = new StringBuilder();
		sb.append("========================= ").append(buscaTexto("changelogmenu0")).append(" =========================");
		sb.append(SALTO_LINEA);
		for(int i=1; i<= 12; i++){
			sb.append("+ ").append(buscaTexto("changelogmenu" + i)).append(SALTO_LINEA);
		}
		sb.append(SALTO_LINEA);
		sb.append("========================= ").append(buscaTexto("changelogmenu13")).append(" =================");
		sb.append(SALTO_LINEA);
		for(int i=14; i<= 15; i++){
			sb.append("+ ").append(buscaTexto("changelogmenu" + i)).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public String menuExtras() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= 5; i++){
			sb.append(buscaTexto("extras" + i)).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public String menuClases() {
		return  "0.- " + buscaTexto("option11") + SALTO_LINEA +
				"1.- " + String.format("%-12s", buscaTexto("class0")) + "(HP: 100, AD: 1, AP: 1, AR: 2, MR: 2, AGL: 0)"
				+ SALTO_LINEA +
				"2.- " + String.format("%-12s", buscaTexto("class1")) + "(HP: 90,  AD: 2, AP: 1, AR: 1, MR: 0, AGL: 3)"
				+ SALTO_LINEA +
				"3.- " + String.format("%-12s", buscaTexto("class2")) + "(HP: 90,  AD: 2, AP: 0, AR: 2, MR: 2, AGL: 1)"
				+ SALTO_LINEA +
				"4.- " + String.format("%-12s", buscaTexto("class3")) + "(HP: 90,  AD: 1, AP: 3, AR: 0, MR: 0, AGL: 2)"
				+ SALTO_LINEA +
				"5.- " + String.format("%-12s", buscaTexto("class4")) + "(HP: 90,  AD: 2, AP: 0, AR: 1, MR: 2, AGL: 2)"
				+ SALTO_LINEA;
	}

	public String menuCambiaIdioma() {
		StringBuilder sb = new StringBuilder(SALTO_LINEA);
		sb.append(buscaTexto("langmenu0")).append(SALTO_LINEA);
		sb.append(SALTO_LINEA);
		sb.append("0.- ").append(buscaTexto("option10")).append(SALTO_LINEA);
		for (int i = 0; i < LISTA_IDIOMAS.size(); i++) {
			sb.append(i+1).append(".- ").append(LISTA_IDIOMAS.get(i)).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public String menuAcciones() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(i+1).append(".- ").append(buscaTexto("option" + (i +5))).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public String menuAccionesFisico() {
		return  "1.- " + String.format("%-12s", buscaTexto("attack0")) + "(Min: 2 AD)" + SALTO_LINEA +
				"2.- " + String.format("%-12s", buscaTexto("attack1")) + "(Min: 10 AD)" + SALTO_LINEA +
				"3.- " + String.format("%-12s", buscaTexto("attack2")) + "(Min: 6 AD)" + SALTO_LINEA +
				"4.- " + buscaTexto("option10") + SALTO_LINEA;
	}

	public String menuAccionesMagico() {
		return  "1.- " + String.format("%-12s", buscaTexto("attack3")) + "(Min: 2 AD)" + SALTO_LINEA +
				"2.- " + String.format("%-12s", buscaTexto("attack4")) + "(Min: 10 AD)" + SALTO_LINEA +
				"3.- " + String.format("%-12s", buscaTexto("attack5")) + "(Min: 6 AD)" + SALTO_LINEA +
				"4.- " + buscaTexto("option10") + SALTO_LINEA;
	}

	public String menuAccionesOtros() {
		return "1.- "  + buscaTexto("option12") + SALTO_LINEA +
				"2.- " + buscaTexto("option13") + SALTO_LINEA +
				"3.- " + buscaTexto("option14") + SALTO_LINEA +
				"4.- " + buscaTexto("option15") + SALTO_LINEA +
				"5.- " + buscaTexto("option10") + SALTO_LINEA;
	}

	// MUESTRA COSAS -----------------------------------

	public String muestraAyudaClases() {
		return "============ " + buscaTexto("classInfo0") + " ============" +
				SALTO_LINEA + SALTO_LINEA +
				"1.- " + buscaTexto("classInfo1") + SALTO_LINEA +
				buscaTexto("classInfo2") + SALTO_LINEA +
				buscaTexto("classInfo3") + SALTO_LINEA +
				SALTO_LINEA +
				"2.- " + buscaTexto("classInfo4") + SALTO_LINEA +
				buscaTexto("classInfo5") + SALTO_LINEA +
				buscaTexto("classInfo6") + SALTO_LINEA +
				SALTO_LINEA +
				"3.- " + buscaTexto("classInfo7") + SALTO_LINEA +
				buscaTexto("classInfo8") + SALTO_LINEA +
				buscaTexto("classInfo9") + SALTO_LINEA +
				SALTO_LINEA +
				"4.- " + buscaTexto("classInfo10") + SALTO_LINEA +
				buscaTexto("classInfo11") + SALTO_LINEA +
				buscaTexto("classInfo12") + SALTO_LINEA +
				SALTO_LINEA +
				"5.- " + buscaTexto("classInfo13") + SALTO_LINEA +
				buscaTexto("classInfo14") + SALTO_LINEA +
				buscaTexto("classInfo15") + SALTO_LINEA;
	}

	public String muestraAyudaJuego() {
		return buscaTexto("gameinfo0");
	}

	public String muestraHistoriaJuego() {
		return buscaTexto("gameStory0");
	}

	// MENSAJES --------------------------------------------

	public String confirmaCambioIdioma() {
		return buscaTexto("langmenu1");
	}

	public String pideAccion() {
		return buscaTexto("message1");
	}

	public String pideNombre() {
		return buscaTexto("message2");
	}

	public String msgBienvenida() {
		return buscaTexto("message3");
	}

	public String msgTurno() {
		return "===== " + buscaTexto("message4") + " %s =====";
	}

	public String msgEmpiezaJuego() {
		return "========= " + buscaTexto("message5") +" =========";
	}

	public String msgPersonajeCreado() {
		return "%s " + buscaTexto("message6") + " (%02d/%02d)";
	}

	public String msgSubidaNivel() {
		return "%s " + buscaTexto("message7") + " %d!";
	}

	public String msgSalirJuego() {
		return buscaTexto("message8");
	}

	public String pideClases() {
		return buscaTexto("message9");
	}

	public String pideConfirmacion() {
		return buscaTexto("message10");
	}

	public String msgPausa() {
		return SALTO_LINEA + buscaTexto("message11");
	}

	public String msgEnemigoDerrotado() {
		return buscaTexto("message12");
	}

	public String msgJuegoPerdido() {
		return buscaTexto("message13");
	}

	public String pideEnemigo() {
		return buscaTexto("message14");
	}

	public String enemigoElegidoNoDisponible() {
		return buscaTexto("message15");
	}

	public String msgBloqueo() {
		return buscaTexto("message16");
	}

	public String msgConfirmaBloqueo() {
		return buscaTexto("message17");
	}

	public String msgPosVacia(){
		return buscaTexto("message18");
	}

	public String msgCreacionClase() {
		return buscaTexto("message19");
	}

	public String msgEnemigoAtaqueEsquivado() {
		return buscaTexto("message23");
	}

	public String msgPersonajeAtaqueEsquivado() {
		return buscaTexto("message29");
	}

	public String msgDespedida(){
		return buscaTexto("message22");
	}

	public String confimaBloqueo() {
		return buscaTexto("message20") + " %s " + buscaTexto("message21");
	}

	// ERRORES -----------------------------------------------------------

	public String errorEnemigoNoEncontrado() {
		return buscaTexto("errormessage0");
	}

	public String errorPersonajeNoEncontrado() {
		return buscaTexto("errormessage1");
	}

	public String errorNumeroNoValido() {
		return buscaTexto("errormessage2");
	}

	public String errorAtaqueNoDisponible() {
		return buscaTexto("errormessage3");
	}

	public String errorInesperado() {
		return buscaTexto("errormessage4");
	}

	public String errorBloqueoYaActivo() {
		return buscaTexto("errormessage5");
	}

	public String errorNombreNoValido() {
		return buscaTexto("errormessage6");
	}

	// metodos configuradores del lector. -----------------------------------------------

	/**
	 * leera el archivo externo y lo guardara en el arraylist.
	 * @throws JuegoException si el archivo no existe.
	 */
	private void rellenaArchivoIdiomas() throws JuegoException{
		try (BufferedReader reader = new BufferedReader(new FileReader(CSV_RPATH))){
			String linea;
			while((linea = reader.readLine()) != null) {
				TEXTOS_FILE.add(linea);
			}
		}catch(IOException e){
			throw new JuegoException(e.getMessage());
		}
	}

	/**
	 * Rellena el archivo de idiomas en base al arraylist con el archivo leido.
	 */
	private void rellenaListaIdiomas() {
		System.out.println("imprimiendo TEXTOS file");
		System.out.println(TEXTOS_FILE);
		String[] listaIdiomas = TEXTOS_FILE.get(0).split(CSV_SEPARATOR);
		LISTA_IDIOMAS.addAll(Arrays.asList(listaIdiomas).subList(1, listaIdiomas.length));
	}

	/**
	 * Cambia la columna de la que recoger el texto.
	 * @param numNuevoIdioma numero correspondiente a 1 - num max de idiomas en el archivo.
	 */
	public void cambiarIdioma(int numNuevoIdioma){
		columnaIdioma = numNuevoIdioma;
	}

	/**
	 * recorre el archivo con los textos del juego.
	 * comprueba la columna id con el parametro para saber que dialogo escoger.
	 * @param id id para buscar en el archivo.
	 * @return devolvera el dialogo correspondiente a la fila con el mismo id,
	 *         y la columna dependiendo del idioma elegido.
	 */
	public String buscaTexto(String id){
		for(String i: TEXTOS_FILE){
			String[] splitted = i.split(CSV_SEPARATOR);
			if(splitted[0].equalsIgnoreCase(id)){
				String text = splitted[columnaIdioma].trim();
				if(text.isEmpty()){
					throw new JuegoException("No se ha encontrado el texto requerido.");
				}
				return text;
			}
		}
		throw new JuegoException("Dialogo inexiste en archivo.");
	}

	/**
	 * Devuelve la cantidad de idiomas.
	 * @return longitud del array de idiomas.
	 */
	public int getCantidadIdiomas(){
		return LISTA_IDIOMAS.size();
	}
}